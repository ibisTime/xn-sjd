package com.ogc.standard.ao.impl;

import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAccountAO;

@Service
public class AccountAOImpl implements IAccountAO {

    // @Autowired
    // private IAccountBO accountBO;
    //
    // @Autowired
    // private IEthXAddressBO ethXAddressBO;
    //
    // // @Autowired
    // // private ICtqBO ctqBO;
    //
    // @Autowired
    // private ICoinBO coinBO;
    //
    // @Override
    // @Transactional
    // public void distributeAccount(String userId, String realName,
    // String systemCode, String companyCode) {
    //
    // // 获取平台开启的币种列表
    // List<Coin> coins = coinBO.queryOpenCoinList();
    // // 获取用户已经创建的虚拟账户
    // List<Account> accounts = accountBO.queryAccountList(userId);
    // // 移除已经创建账户的币种
    // coins = removeExistAccountCoins(coins, accounts);
    //
    // if (CollectionUtils.isNotEmpty(coins)) {
    // for (Coin coin : coins) {
    // String accountId = accountBO.distributeAccount(userId, realName,
    // EAccountType.Customer, coin.getSymbol(), systemCode,
    // companyCode);
    // generateAddress(userId, accountId, coin);
    // }
    // }
    // }
    //
    // private List<Coin> removeExistAccountCoins(List<Coin> coins,
    // List<Account> accounts) {
    // List<Coin> results = new ArrayList<>();
    // for (Coin coin : coins) {
    // boolean isExist = false;
    // for (Account account : accounts) {
    // if (account.getCurrency().equals(coin.getSymbol())) {
    // isExist = true;
    // }
    // }
    // if (!isExist) {
    // results.add(coin);
    // }
    // }
    // return results;
    // }
    //
    // private String generateAddress(String userId, String accountId, Coin
    // coin) {
    //
    // String address = null;
    //
    // // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
    // // if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
    // // address = ethAddressBO.generateAddress(EAddressType.X, userId,
    // // accountId);
    // // ctqBO.uploadEthAddress(address, EAddressType.X.getCode());
    // // } else if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
    // // address = btcAddressBO.generateAddress(EAddressType.X, userId,
    // // accountId, "system", "注册时自动分配");// 生成比特币地址
    // // ctqBO.uploadBtcAddress(address);// 上传比特币地址至橙提取
    // // } else if (EOriginialCoin.WAN.getCode().equals(coin.getSymbol())) {
    // // address = wanAddressBO.generateAddress(EAddressType.X, userId,
    // // accountId);
    // // ctqBO.uploadWanAddress(address, EAddressType.X.getCode());
    // // } else {
    // // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // // "不支持的币种" + coin.getSymbol());
    // // }
    // // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
    // // address = tokenAddressBO.generateAddress(EAddressType.X, userId,
    // // accountId, coin.getSymbol());
    // // ctqBO.uploadTokenAddress(address, coin.getSymbol());
    // // } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {
    // // address = wtokenAddressBO.generateAddress(EAddressType.X, userId,
    // // accountId, coin.getSymbol());
    // // ctqBO.uploadWanTokenAddress(address, coin.getSymbol());
    // // }
    //
    // return address;
    // }
    //
    // @Override
    // public void editAccountName(String userId, String realName) {
    // // 验证用户名和系统编号
    // Account condition = new Account();
    // condition.setUserId(userId);
    // List<Account> accountList = accountBO.queryAccountList(condition);
    // if (CollectionUtils.isEmpty(accountList)) {
    // new BizException("XN0000", "该用户无对应账号");
    // }
    // accountBO.refreshAccountName(userId, realName);
    // }
    //
    // @Override
    // public Paginable<Account> queryAccountPage(int start, int limit,
    // Account condition) {
    // return accountBO.getPaginable(start, limit, condition);
    // }
    //
    // @Override
    // public Account getAccount(String accountNumber) {
    // return accountBO.getAccount(accountNumber);
    // }
    //
    // @Override
    // @Transactional
    // public List<Account> getAccountByUserId(String userId, String currency) {
    // List<Account> accounts = new ArrayList<>();
    // if (StringUtils.isBlank(currency)) {
    // List<Coin> coins = coinBO.queryOpenCoinList();
    // for (Coin coin : coins) {
    // Account account = accountBO.getAccountByUser(userId,
    // coin.getSymbol());
    // setCoinAddress(coin, account);
    // accounts.add(account);
    // }
    // } else {
    // Account account = accountBO.getAccountByUser(userId, currency);
    // Coin coin = coinBO.getCoin(currency);
    // setCoinAddress(coin, account);
    // accounts.add(account);
    // }
    // return accounts;
    // }
    //
    // private void setCoinAddress(Coin coin, Account account) {
    //
    // // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
    // // if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
    // // // 获取ETH地址
    // // EthAddress ethAddress = ethAddressBO
    // // .getEthAddressByAccountNumber(account.getAccountNumber());
    // // account.setCoinAddress(ethAddress.getAddress());
    // //
    // // } else if (EOriginialCoin.BTC.getCode()
    // // .equals(account.getCurrency())) {
    // // // 获取BTC地址
    // // BtcAddress btcAddress = btcAddressBO
    // // .getBtcAddressByAccountNumber(account.getAccountNumber());
    // // account.setCoinAddress(btcAddress.getAddress());
    // //
    // // } else if (EOriginialCoin.WAN.getCode()
    // // .equals(account.getCurrency())) {
    // // // 获取WAN地址
    // // WanAddress wanAddress = wanAddressBO
    // // .getWanAddressByAccountNumber(account.getAccountNumber());
    // // account.setCoinAddress(wanAddress.getAddress());
    // // }
    // // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
    // // // 获取token地址
    // // TokenAddress tokenAddress = tokenAddressBO
    // // .getTokenAddressByAccountNumber(account.getAccountNumber());
    // // account.setCoinAddress(tokenAddress.getAddress());
    // // } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {
    // // // 获取token地址
    // // WTokenAddress wtokenAddress = wtokenAddressBO
    // // .getWTokenAddressByAccountNumber(account.getAccountNumber());
    // // account.setCoinAddress(wtokenAddress.getAddress());
    // // }
    // }
    //
    // @Override
    // public List<Account> queryAccountList(Account condition) {
    // return accountBO.queryAccountList(condition);
    // }
    //
    // @Override
    // @Transactional
    // public void transAmount(String fromUserId, String fromCurrency,
    // String toUserId, String toCurrency, BigDecimal transAmount,
    // String fromBizType, String toBizType, String fromBizNote,
    // String toBizNote, String refNo) {
    // // 检查平台账户是否已存在，如不存在就创建
    // checkSystemAccount(fromUserId, fromCurrency);
    // checkSystemAccount(toUserId, toCurrency);
    // accountBO.transAmount(fromUserId, fromCurrency, toUserId, toCurrency,
    // transAmount, fromBizType, toBizType, fromBizNote, toBizNote, refNo);
    // }
    //
    // private void checkSystemAccount(String userId, String currency) {
    // ESysUser sysUser = ESysUser.getDirectionMap().get(userId);
    // Coin coin = coinBO.getCoin(currency);
    // if (sysUser != null && coin != null) {
    // Account condition = new Account();
    // condition.setUserId(userId);
    // condition.setCurrency(currency);
    // if (accountBO.getTotalCount(condition) <= 0) {
    // //
    // // if (ESysUser.SYS_USER.getCode().equals(userId)) {
    // // accountBO.savePlatAccount("SYS_ACOUNT_" + currency,
    // // ESysUser.SYS_USER, "平台" + currency + "盈亏账户", currency);
    // // } else if (ESysUser.SYS_USER_COLD.getCode().equals(userId)) {
    // // accountBO.savePlatAccount(
    // // "SYS_ACOUNT_" + currency + "_COLD",
    // // ESysUser.SYS_USER_COLD, "平台" + currency + "冷钱包",
    // // currency);
    // // } else if (ESysUser.SYS_USER_HB.getCode().equals(userId)) {
    // // accountBO.savePlatAccount("SYS_ACOUNT_" + currency + "_HB",
    // // ESysUser.SYS_USER_HB, "平台" + currency + "红包账户",
    // // currency);
    // // } else if (ESysUser.SYS_USER_LHLC.getCode().equals(userId)) {
    // // accountBO.savePlatAccount(
    // // "SYS_ACOUNT_" + currency + "_LHLC",
    // // ESysUser.SYS_USER_LHLC, "平台" + currency + "量化理财账户",
    // // currency);
    // // }
    //
    // }
    // }
    // }
    //
    // @Override
    // public Account frozenAmount(String userId, String currency,
    // BigDecimal freezeAmount, String bizType, String bizNote,
    // String refNo) {
    // Account dbAccount = accountBO.getAccountByUser(userId, currency);
    // return accountBO.frozenAmount(dbAccount, freezeAmount, bizType, bizNote,
    // refNo);
    // }
    //
    // @Override
    // public Account unfrozenAmount(String userId, String currency,
    // BigDecimal unfreezeAmount, String bizType, String bizNote,
    // String refNo) {
    // Account dbAccount = accountBO.getAccountByUser(userId, currency);
    // return accountBO.unfrozenAmount(dbAccount, unfreezeAmount, bizType,
    // bizNote, refNo);
    // }
    //
    // @Override
    // public void transAmount(String fromAddress, String toAddress,
    // BigDecimal transAmount, String currency) {
    // Account fromAccount = getAccount(fromAddress, currency);
    // Account toAccount = getAccount(toAddress, currency);
    // if (fromAccount == null || toAccount == null) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(), "账户未找到");
    // }
    //
    // if (transAmount.compareTo(BigDecimal.ZERO) <= 0) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(), "转账金额需大于0");
    // }
    //
    // // 账户可用余额是否充足
    // if (fromAccount.getAmount().subtract(fromAccount.getFrozenAmount())
    // .compareTo(transAmount) == -1) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "个人账户可用余额不足");
    // }
    //
    // // accountBO.transAmount(fromAccount, toAccount, transAmount,
    // // EJourBizTypeUser.AJ_TRANSFER_OUT.getCode(),
    // // EJourBizTypeUser.AJ_TRANSFER_IN.getCode(),
    // // EJourBizTypeUser.AJ_TRANSFER_OUT.getValue() + "对方账户："
    // // + toAccount.getRealName(),
    // // EJourBizTypeUser.AJ_TRANSFER_IN.getValue() + "对方账户："
    // // + fromAccount.getRealName(),
    // // "transfer_order");
    // }
    //
    // private Account getAccount(String address, String currency) {
    // Account account = null;
    // Coin coin = coinBO.getCoin(currency);
    // // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
    // // if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
    // // BtcAddress btcAddress = btcAddressBO
    // // .getBtcAddress(EAddressType.X, address);
    // // if (btcAddress == null) {
    // // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // // "接收地址不是平台内地址，不能进行内部转账");
    // // }
    // // account = accountBO.getAccount(btcAddress.getAccountNumber());
    // // } else if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
    // // EthAddress ethAddress = ethAddressBO
    // // .getEthAddress(EAddressType.X, address);
    // // if (ethAddress == null) {
    // // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // // "接收地址不是平台内地址，不能进行内部转账");
    // // }
    // // account = accountBO.getAccount(ethAddress.getAccountNumber());
    // // }
    // // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
    // //
    // // TokenAddress tokenAddress = null;
    // // TokenAddress condition = new TokenAddress();
    // // condition.setType(EAddressType.X.getCode());
    // // condition.setAddress(address);
    // // condition.setSymbol(coin.getSymbol());
    // // List<TokenAddress> results = tokenAddressBO
    // // .queryTokenAddressList(condition);
    // // if (CollectionUtils.isNotEmpty(results)) {
    // // tokenAddress = results.get(0);
    // // }
    // //
    // // if (tokenAddress == null) {
    // // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // // "接收地址不是平台内地址，不能进行内部转账");
    // // }
    // // account = accountBO.getAccount(tokenAddress.getAccountNumber());
    // // }
    // return account;
    // }
}
