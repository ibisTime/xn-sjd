package com.ogc.standard.ao.impl;

import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IChargeAO;

@Service
public class ChargeAOImpl implements IChargeAO {
    // @Autowired
    // private IAccountBO accountBO;
    //
    // @Autowired
    // private IChargeBO chargeBO;
    //
    // @Autowired
    // private IJourBO jourBO;
    //
    // @Autowired
    // private IEthTransactionBO ethTransactionBO;
    //
    // @Autowired
    // private ICollectBO collectBO;
    //
    // @Autowired
    // private ICoinBO coinBO;
    //
    // @Override
    // public String applyOrder(String accountNumber, BigDecimal amount,
    // String payCardInfo, String payCardNo, String applyUser,
    // String applyNote) {
    // if (amount.compareTo(BigDecimal.ZERO) == 0
    // || amount.compareTo(BigDecimal.ZERO) == -1) {
    // throw new BizException("xn000000", "充值金额需大于零");
    // }
    // Account account = accountBO.getAccount(accountNumber);
    // // 生成充值订单
    // String code = chargeBO.applyOrderOffline(account,
    // EJourBizTypeUser.AJ_CHARGE.getCode(), amount, payCardInfo,
    // payCardNo, applyUser, applyNote);
    // return code;
    // }
    //
    // @Override
    // @Transactional
    // public void payOrder(String code, String payUser, String payResult,
    // String payNote, String systemCode) {
    // Charge data = chargeBO.getCharge(code, systemCode);
    // if (!EChargeStatus.toPay.getCode().equals(data.getStatus())) {
    // throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
    // }
    // if (EBoolean.YES.getCode().equals(payResult)) {
    // payOrderYES(data, payUser, payNote);
    // } else {
    // payOrderNO(data, payUser, payNote);
    // }
    // }
    //
    // private void payOrderNO(Charge data, String payUser, String payNote) {
    // chargeBO.payOrder(data, false, payUser, payNote);
    // }
    //
    // private void payOrderYES(Charge data, String payUser, String payNote) {
    // chargeBO.payOrder(data, true, payUser, payNote);
    //
    // Account userAccount = accountBO.getAccount(data.getAccountNumber());
    //
    // Coin coin = coinBO.getCoin(data.getCurrency());
    // String symbol = coin.getSymbol();
    //
    // // 用户账户加钱
    // userAccount = accountBO.changeAmount(userAccount, data.getAmount(),
    // EChannelType.Offline, null, null, data.getCode(),
    // EJourBizTypeUser.AJ_CHARGE.getCode(), symbol + "线下充值");
    //
    // Account coldAccount = accountBO
    // .getAccount(ESystemAccount.getPlatColdAccount(symbol));
    // // 冷钱包加钱
    // // accountBO.changeAmount(coldAccount, data.getAmount(),
    // // EChannelType.Offline, null, null, data.getCode(),
    // // EJourBizTypeCold.AJ_INCOME.getCode(),
    // // symbol + "线下充值，充值账户：" + userAccount.getRealName());
    //
    // }
    //
    // @Override
    // public Paginable<Charge> queryChargePage(int start, int limit,
    // Charge condition) {
    // Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
    // return page;
    // }
    //
    // @Override
    // public List<Charge> queryChargeList(Charge condition) {
    // List<Charge> list = chargeBO.queryChargeList(condition);
    // return list;
    // }
    //
    // @Override
    // public Charge getCharge(String code, String systemCode) {
    // Charge charge = chargeBO.getCharge(code, systemCode);
    // return charge;
    // }
    //
    // @Override
    // public XN802707Res getChargeCheckInfo(String code) {
    // XN802707Res res = new XN802707Res();
    //
    // // 充值订单详情
    // Charge charge = chargeBO.getCharge(code, ESystemCode.TOKEN.getCode());
    //
    // // 充值对应流水记录
    // Jour jour = new Jour();
    // jour.setRefNo(charge.getCode());
    // jour.setKind(EJourKind.BALANCE.getCode());
    // List<Jour> jourList1 = jourBO.queryJourList(jour);
    //
    // Coin coin = coinBO.getCoin(charge.getCurrency());
    // if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
    // if (EOriginialCoin.ETH.getCode().equals(charge.getCurrency())) {
    // // 充值对应广播记录
    // EthTransaction ethTransaction = new EthTransaction();
    // ethTransaction.setRefNo(charge.getCode());
    // List<EthTransaction> resultList1 = ethTransactionBO
    // .queryEthTransactionList(ethTransaction);
    //
    // Collect collection = collectBO
    // .getCollectByRefNo(charge.getCode());
    // // 如果有归集
    // if (collection != null) {
    // // 归集对应流水
    // jour.setRefNo(collection.getCode());
    // List<Jour> jourList2 = jourBO.queryJourList(jour);
    // jourList1.addAll(jourList2);
    // // 归集对应广播记录
    // ethTransaction.setRefNo(collection.getCode());
    // List<EthTransaction> resultList2 = ethTransactionBO
    // .queryEthTransactionList(ethTransaction);
    // resultList1.addAll(resultList2);
    // res.setCollect(collection);
    // res.setEthTransList(resultList1);
    // }
    // res.setEthTransList(resultList1);
    // } else if (EOriginialCoin.BTC.getCode()
    // .equals(charge.getCurrency())) {
    // // 充值对应广播记录
    // BtcTransaction btcTransaction = new BtcTransaction();
    // btcTransaction.setRefNo(charge.getCode());
    // List<BtcTransaction> resultList1 = btcTransactionBO
    // .queryBtcTransactionList(btcTransaction);
    //
    // Collect collection = collectBO
    // .getCollectByRefNo(charge.getCode());
    // // 如果有归集
    // if (collection != null) {
    // // 归集对应流水
    // jour.setRefNo(collection.getCode());
    // List<Jour> jourList2 = jourBO.queryJourList(jour);
    // jourList1.addAll(jourList2);
    // // 归集对应广播记录
    // btcTransaction.setRefNo(collection.getCode());
    // List<BtcTransaction> resultList2 = btcTransactionBO
    // .queryBtcTransactionList(btcTransaction);
    // resultList1.addAll(resultList2);
    // res.setCollect(collection);
    // res.setBtcTransList(resultList1);
    // }
    //
    // res.setBtcTransList(resultList1);
    // }
    // } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
    // if (StringUtils.isNotBlank(charge.getRefNo())) {
    // // 充值对应广播记录
    // TokenTransaction condition = new TokenTransaction();
    // condition.setHash(charge.getRefNo().split("\\|\\|")[0]);
    // condition.setTokenLogIndex(
    // new BigInteger(charge.getRefNo().split("\\|\\|")[1]));
    // List<TokenTransaction> resultList1 = tokenTransactionBO
    // .queryTokenTransactionList(condition);
    // res.setTokenTransList(resultList1);
    // }
    // } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {
    // if (StringUtils.isNotBlank(charge.getRefNo())) {
    // // 充值对应广播记录
    // WTokenTransaction condition = new WTokenTransaction();
    // condition.setHash(charge.getRefNo().split("\\|\\|")[0]);
    // condition.setTokenLogIndex(
    // new BigInteger(charge.getRefNo().split("\\|\\|")[1]));
    // List<WTokenTransaction> resultList1 = wtokenTransactionBO
    // .queryWTokenTransactionList(condition);
    // res.setWtokenTransList(resultList1);
    // }
    // }
    //
    // res.setCharge(charge);
    // res.setJourList(jourList1);
    //
    // return res;
    // }
}
