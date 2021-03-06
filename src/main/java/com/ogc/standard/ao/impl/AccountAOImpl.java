package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAccountAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourDirection;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EUser;

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

    @Autowired
    private IJourBO jourBO;

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
        List<Account> list = accountBO.queryAccountList(userId, currency);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Account account : list) {
                // 历史总发放/回收额(积分/碳泡泡)
                if (ESystemAccount.SYS_ACOUNT_JF_POOL.getCode()
                    .equals(account.getAccountNumber())
                        || ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode()
                            .equals(account.getAccountNumber())) {

                    BigDecimal historyInAmount = jourBO.getHistoryAmount(
                        account.getAccountNumber(),
                        EJourDirection.IN.getCode());
                    BigDecimal historyOutAmount = jourBO.getHistoryAmount(
                        account.getAccountNumber(),
                        EJourDirection.OUT.getCode());

                    account.setHistoryInAmount(historyInAmount);
                    account.setHistoryOutAmount(historyOutAmount);
                }
            }
        }

        return list;
    }

    private void initAccount(Account account) {

        // 户名
        String realName = null;
        if (EAccountType.CUSTOMER.getCode().equals(account.getType())) {

            // C端用户
            User user = userBO.getUserUnCheck(account.getUserId());
            if (null != user) {
                realName = user.getMobile();
                account.setMobile(realName);
                if (StringUtils.isNotBlank(user.getRealName())) {
                    realName = user.getRealName().concat("-").concat(realName);
                }
            }

        } else if (EAccountType.AGENT.getCode().equals(account.getType())) {

            // 代理用户
            AgentUser agentUser = agentUserBO
                .getAgentUserUnCheck(account.getUserId());
            if (null != agentUser) {
                realName = agentUser.getMobile();
                account.setMobile(realName);
                if (StringUtils.isNotBlank(agentUser.getRealName())) {
                    realName = agentUser.getRealName().concat("-")
                        .concat(realName);
                }
            }

        } else if (EAccountType.PLAT.getCode().equals(account.getType())) {

            // 系统用户
            realName = EUser.ADMIN.getValue();
        } else {

            // 其他用户
            SYSUser sysUser = sysUserBO.getSYSUserUnCheck(account.getUserId());
            if (null != sysUser) {
                realName = sysUser.getMobile();
                account.setMobile(realName);
                if (StringUtils.isNotBlank(sysUser.getRealName())) {
                    realName = sysUser.getRealName().concat("-")
                        .concat(realName);
                }
            }

        }

        account.setRealName(realName);

    }

    @Override
    public List<Account> getAccountAmountSumList(String currency, String status,
            String type) {
        Account condition = new Account();
        condition.setCurrency(currency);
        condition.setStatus(status);
        condition.setType(type);
        return accountBO.queryAccountAmountSumList(condition);
    }
}
