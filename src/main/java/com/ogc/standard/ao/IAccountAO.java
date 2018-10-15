package com.ogc.standard.ao;

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

    // 查询各个端人民币账户总余额
    public List<Account> getAccountAmountSumList(String currency, String status,
            String type);
}
