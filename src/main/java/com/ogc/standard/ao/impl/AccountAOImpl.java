package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAccountAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ICoinBO;
import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Coin;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECoinType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IEthXAddressBO ethXAddressBO;

    // @Autowired
    // private ICtqBO ctqBO;

    @Autowired
    private ICoinBO coinBO;

    @Override
    @Transactional
    public void distributeAccount(String userId) {

        // 获取平台开启的币种列表
        List<Coin> coins = coinBO.queryOpenCoinList();

        // 获取用户已经创建的虚拟账户
        List<Account> accounts = accountBO.queryAccountList(userId);

        // 移除已经创建账户的币种
        coins = removeExistAccountCoins(coins, accounts);

        String ethXAddress = null;
        if (CollectionUtils.isNotEmpty(coins)) {
            for (Coin coin : coins) {
                if (ECoinType.ETH.getCode().equals(coin.getType())
                        || ECoinType.ETH_TOKEN.getCode()
                            .equals(coin.getType())) {
                    if (ethXAddress == null) {
                        ethXAddress = ethXAddressBO.generateAddress(userId);
                    }
                    accountBO.distributeAccount(userId, EAccountType.Customer,
                        coin.getSymbol(), ethXAddress);
                }

            }
        }
    }

    private List<Coin> removeExistAccountCoins(List<Coin> coins,
            List<Account> accounts) {
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            boolean isExist = false;
            for (Account account : accounts) {
                if (account.getCurrency().equals(coin.getSymbol())) {
                    isExist = true;
                }
            }
            if (!isExist) {
                results.add(coin);
            }
        }
        return results;
    }

    private String generateAddress(String userId, String accountId, Coin coin) {

        String address = null;

        // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
        // if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
        // address = ethAddressBO.generateAddress(EAddressType.X, userId,
        // accountId);
        // ctqBO.uploadEthAddress(address, EAddressType.X.getCode());
        // } else if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
        // address = btcAddressBO.generateAddress(EAddressType.X, userId,
        // accountId, "system", "注册时自动分配");// 生成比特币地址
        // ctqBO.uploadBtcAddress(address);// 上传比特币地址至橙提取
        // } else if (EOriginialCoin.WAN.getCode().equals(coin.getSymbol())) {
        // address = wanAddressBO.generateAddress(EAddressType.X, userId,
        // accountId);
        // ctqBO.uploadWanAddress(address, EAddressType.X.getCode());
        // } else {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "不支持的币种" + coin.getSymbol());
        // }
        // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
        // address = tokenAddressBO.generateAddress(EAddressType.X, userId,
        // accountId, coin.getSymbol());
        // ctqBO.uploadTokenAddress(address, coin.getSymbol());
        // } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {
        // address = wtokenAddressBO.generateAddress(EAddressType.X, userId,
        // accountId, coin.getSymbol());
        // ctqBO.uploadWanTokenAddress(address, coin.getSymbol());
        // }

        return address;
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
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
        if (StringUtils.isBlank(currency)) {
            List<Coin> coins = coinBO.queryOpenCoinList();
            for (Coin coin : coins) {
                Account account = accountBO.getAccountByUser(userId,
                    coin.getSymbol());
                accounts.add(account);
            }
        } else {
            Account account = accountBO.getAccountByUser(userId, currency);
            accounts.add(account);
        }
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
        Coin coin = coinBO.getCoin(currency);
        if (sysUser != null && coin != null) {
            Account condition = new Account();
            condition.setUserId(userId);
            condition.setCurrency(currency);
            if (accountBO.getTotalCount(condition) <= 0) {
                //
                // if (ESysUser.SYS_USER.getCode().equals(userId)) {
                // accountBO.savePlatAccount("SYS_ACOUNT_" + currency,
                // ESysUser.SYS_USER, "平台" + currency + "盈亏账户", currency);
                // } else if (ESysUser.SYS_USER_COLD.getCode().equals(userId)) {
                // accountBO.savePlatAccount(
                // "SYS_ACOUNT_" + currency + "_COLD",
                // ESysUser.SYS_USER_COLD, "平台" + currency + "冷钱包",
                // currency);
                // } else if (ESysUser.SYS_USER_HB.getCode().equals(userId)) {
                // accountBO.savePlatAccount("SYS_ACOUNT_" + currency + "_HB",
                // ESysUser.SYS_USER_HB, "平台" + currency + "红包账户",
                // currency);
                // } else if (ESysUser.SYS_USER_LHLC.getCode().equals(userId)) {
                // accountBO.savePlatAccount(
                // "SYS_ACOUNT_" + currency + "_LHLC",
                // ESysUser.SYS_USER_LHLC, "平台" + currency + "量化理财账户",
                // currency);
                // }

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

        // accountBO.transAmount(fromAccount, toAccount, transAmount,
        // EJourBizTypeUser.AJ_TRANSFER_OUT.getCode(),
        // EJourBizTypeUser.AJ_TRANSFER_IN.getCode(),
        // EJourBizTypeUser.AJ_TRANSFER_OUT.getValue() + "对方账户："
        // + toAccount.getRealName(),
        // EJourBizTypeUser.AJ_TRANSFER_IN.getValue() + "对方账户："
        // + fromAccount.getRealName(),
        // "transfer_order");
    }

    private Account getAccount(String address, String currency) {
        Account account = null;
        Coin coin = coinBO.getCoin(currency);
        // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
        // if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
        // BtcAddress btcAddress = btcAddressBO
        // .getBtcAddress(EAddressType.X, address);
        // if (btcAddress == null) {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "接收地址不是平台内地址，不能进行内部转账");
        // }
        // account = accountBO.getAccount(btcAddress.getAccountNumber());
        // } else if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
        // EthAddress ethAddress = ethAddressBO
        // .getEthAddress(EAddressType.X, address);
        // if (ethAddress == null) {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "接收地址不是平台内地址，不能进行内部转账");
        // }
        // account = accountBO.getAccount(ethAddress.getAccountNumber());
        // }
        // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
        //
        // TokenAddress tokenAddress = null;
        // TokenAddress condition = new TokenAddress();
        // condition.setType(EAddressType.X.getCode());
        // condition.setAddress(address);
        // condition.setSymbol(coin.getSymbol());
        // List<TokenAddress> results = tokenAddressBO
        // .queryTokenAddressList(condition);
        // if (CollectionUtils.isNotEmpty(results)) {
        // tokenAddress = results.get(0);
        // }
        //
        // if (tokenAddress == null) {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "接收地址不是平台内地址，不能进行内部转账");
        // }
        // account = accountBO.getAccount(tokenAddress.getAccountNumber());
        // }
        return account;
    }
}
