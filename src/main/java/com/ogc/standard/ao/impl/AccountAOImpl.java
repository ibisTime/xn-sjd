package com.ogc.standard.ao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAccountAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECurrency;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Override
    @Transactional
    public void distributeAccount(String userId) {
        Map<String, ECurrency> currencyMap = ECurrency.getCurrencyMap();

        for (Map.Entry<String, ECurrency> currency : currencyMap.entrySet()) {
            accountBO.distributeAccount(userId, EAccountType.CUSTOMER,
                currency.getKey());
        }
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        Paginable<Account> page = accountBO.getPaginable(start, limit,
            condition);
        if (null != page) {
            List<Account> list = page.getList();
            for (Account account : list) {
                initAccount(account);
            }
        }
        return page;
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = accountBO.getAccount(accountNumber);
        initAccount(account);
        return account;
    }

    @Override
    @Transactional
    public List<Account> getAccountByUserId(String userId, String currency) {
        return accountBO.queryAccountList(userId, currency);
    }

    private void initAccount(Account account) {
        if (EAccountType.OWNER.getCode().equals(account.getType())
                || EAccountType.MAINTAIN.getCode().equals(account.getType())) {
            SYSUser sysUser = sysUserBO.getSYSUser(account.getUserId());
            account.setRealName(sysUser.getRealName());
            account.setMobile(sysUser.getMobile());
        } else if (EAccountType.AGENT.getCode().equals(account.getType())) {
            AgentUser agentUser = agentUserBO.getAgentUser(account.getUserId());
            account.setRealName(agentUser.getRealName());
            account.setMobile(agentUser.getMobile());
        } else if (EAccountType.CUSTOMER.getCode().equals(account.getType())) {
            User user = userBO.getUserUnCheck(account.getUserId());
            if (null != user) {
                account.setRealName(user.getRealName());
                account.setMobile(user.getMobile());
            }
        }
    }

    @Override
    public List<Account> getAccountAmountSumList() {
        return accountBO.queryAccountAmountSumList();
    }
}
