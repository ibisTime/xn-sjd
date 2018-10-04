package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;

public interface IAccountAO {

    String DEFAULT_ORDER_COLUMN = "account_number";

    // 个人创建多账户
    public void distributeAccount(String userId);

    // 分页查询账户
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    // 根据accountNumber查询账户
    public Account getAccount(String accountNumber);

    // 根据用户编号获取账户列表
    public List<Account> getAccountByUserId(String userId, String currency);

    // 不同用户不同币种间资金划转
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo);

    // 冻结金额
    public Account frozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo);

    // 解冻(冻结金额原路返回)
    public Account unfrozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo);

    // 同币种地址内部转账，0手续费不走广播极速到账
    public void transAmount(String fromAddress, String toAddress,
            BigDecimal transAmount, String currency);

}
