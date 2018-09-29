package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAccountAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public void distributeAccount(String userId) {
        Map<String, ECurrency> currencyMap = ECurrency.getCurrencyMap();

        for (Map.Entry<String, ECurrency> currency : currencyMap.entrySet()) {
            accountBO.distributeAccount(userId, EAccountType.Customer,
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
                User user = userBO.getUserUnCheck(account.getUserId());
                if (null != user) {
                    account.setRealName(user.getRealName());
                    account.setMobile(user.getMobile());
                }

            }
        }

        return page;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountBO.getAccount(accountNumber);
    }

    @Override
    @Transactional
    public List<Account> getAccountByUserId(String userId, String currency) {
        distributeAccount(userId);
        List<Account> accounts = new ArrayList<>();

        Account account = accountBO.getAccountByUser(userId, currency);
        accounts.add(account);

        return accounts;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    @Override
    @Transactional
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        // 检查平台账户是否已存在，如不存在就创建
        checkSystemAccount(fromUserId, fromCurrency);
        checkSystemAccount(toUserId, toCurrency);
        accountBO.transAmount(fromUserId, fromCurrency, toUserId, toCurrency,
            transAmount, fromBizType, toBizType, fromBizNote, toBizNote, refNo);
    }

    private void checkSystemAccount(String userId, String currency) {
        ESysUser sysUser = ESysUser.getDirectionMap().get(userId);
        if (sysUser != null) {
            Account condition = new Account();
            condition.setUserId(userId);
            condition.setCurrency(currency);
            if (accountBO.getTotalCount(condition) <= 0) {

            }
        }
    }

    @Override
    public Account frozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo) {
        Account dbAccount = accountBO.getAccountByUser(userId, currency);
        return accountBO.frozenAmount(dbAccount, freezeAmount, bizType, bizNote,
            refNo);
    }

    @Override
    public Account unfrozenAmount(String userId, String currency,
            BigDecimal unfreezeAmount, String bizType, String bizNote,
            String refNo) {
        Account dbAccount = accountBO.getAccountByUser(userId, currency);
        return accountBO.unfrozenAmount(dbAccount, unfreezeAmount, bizType,
            bizNote, refNo);
    }

    @Override
    public void transAmount(String fromAddress, String toAddress,
            BigDecimal transAmount, String currency) {
        Account fromAccount = getAccount(fromAddress, currency);
        Account toAccount = getAccount(toAddress, currency);
        if (fromAccount == null || toAccount == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "账户未找到");
        }

        if (transAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "转账金额需大于0");
        }

        // 账户可用余额是否充足
        if (fromAccount.getAmount().subtract(fromAccount.getFrozenAmount())
            .compareTo(transAmount) == -1) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "个人账户可用余额不足");
        }
    }

    private Account getAccount(String address, String currency) {
        Account account = null;

        return account;
    }
}
