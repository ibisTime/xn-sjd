/**
 * @Title EthTransactionAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:33:42 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IEthTransactionAO;
import com.ogc.standard.bo.IEthTransactionBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.EthTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:33:42 
 * @history:
 */
@Service
public class EthTransactionAOImpl implements IEthTransactionAO {

    // @Autowired
    // private IChargeBO chargeBO;
    //
    // @Autowired
    // private IWithdrawBO withdrawBO;
    //
    // @Autowired
    // private IAccountBO accountBO;
    //
    // @Autowired
    // private IEthAddressBO ethAddressBO;
    //
    @Autowired
    private IEthTransactionBO ethTransactionBO;

    //
    // @Autowired
    // private ICollectBO collectBO;
    //
    // @Autowired
    // private ICoinBO coinBO;
    //
    // @Override
    // @Transactional
    // public String chargeNotice(CtqEthTransaction ctqEthTransaction) {
    // EthAddress ethAddress = ethAddressBO.getEthAddress(EAddressType.X,
    // ctqEthTransaction.getTo());
    // if (ethAddress == null) {
    // throw new BizException("xn6250000", "充值地址不存在");
    // }
    // Charge condition = new Charge();
    // condition.setRefNo(ctqEthTransaction.getHash());
    // if (chargeBO.getTotalCount(condition) > 0) {
    // return "";
    // }
    // Account account = accountBO.getAccountByUser(ethAddress.getUserId(),
    // EOriginialCoin.ETH.getCode());
    // String payGroup = OrderNoGenerater.generate("PG");
    // BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
    // // 充值订单落地
    // String code = chargeBO.applyOrderOnline(account, payGroup,
    // ctqEthTransaction.getHash(), EJourBizTypeUser.AJ_CHARGE.getCode(),
    // "ETH充值-来自地址：" + ctqEthTransaction.getFrom(), amount,
    // EChannelType.Online, account.getUserId(),
    // ctqEthTransaction.getFrom());
    // // 账户加钱
    // accountBO.changeAmount(account, amount, EChannelType.Online,
    // ctqEthTransaction.getHash(), payGroup, code,
    // EJourBizTypeUser.AJ_CHARGE.getCode(),
    // "ETH充值-来自地址：" + ctqEthTransaction.getFrom());
    // // 落地交易记录
    // ethTransactionBO.saveEthTransaction(ctqEthTransaction, code);
    // // 更新地址余额
    // ethAddressBO.refreshBalance(ethAddress);
    // return code;
    // }
    //
    // @Override
    // @Transactional
    // public void withdrawNotice(CtqEthTransaction ctqEthTransaction) {
    // // 根据交易hash查询取现订单
    // Withdraw withdraw = withdrawBO
    // .getWithdrawByChannelOrder(ctqEthTransaction.getHash());
    // if (withdraw == null) {
    // return;
    // }
    // // 计算矿工费
    // BigDecimal gasPrice = new BigDecimal(ctqEthTransaction.getGasPrice());
    // BigDecimal gasUse = new BigDecimal(
    // ctqEthTransaction.getGas().toString());
    // BigDecimal txFee = gasPrice.multiply(gasUse);
    // // 取现订单更新
    // withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
    // ctqEthTransaction.getFrom(), "广播成功", ctqEthTransaction.getHash(),
    // ctqEthTransaction.getHash(), txFee);
    //
    // // 落地交易记录
    // ethTransactionBO.saveEthTransaction(ctqEthTransaction,
    // withdraw.getCode());
    //
    // // 更新地址余额
    // EthAddress from = ethAddressBO.getEthAddress(EAddressType.M,
    // ctqEthTransaction.getFrom());
    // EthAddress to = ethAddressBO.getEthAddress(EAddressType.W,
    // ctqEthTransaction.getTo());
    // ethAddressBO.refreshBalance(from);
    // ethAddressBO.refreshBalance(to);
    //
    // // 修改散取地址状态为可使用
    // ethAddressBO.refreshStatus(from, EMAddressStatus.NORMAL.getCode());
    //
    // Account userAccount = accountBO.getAccount(withdraw.getAccountNumber());
    // // 取现金额解冻
    // userAccount = accountBO.unfrozenAmount(userAccount,
    // withdraw.getAmount(),
    // EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(),
    // EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getValue(),
    // withdraw.getCode());
    // // 取现金额扣减
    // userAccount = accountBO.changeAmount(userAccount,
    // withdraw.getAmount().subtract(withdraw.getFee()).negate(),
    // EChannelType.Online, ctqEthTransaction.getHash(), "ETH",
    // withdraw.getCode(), EJourBizTypeUser.AJ_WITHDRAW.getCode(),
    // EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
    // + withdraw.getPayCardNo());
    // if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
    // // 取现手续费扣减
    // userAccount = accountBO.changeAmount(userAccount,
    // withdraw.getFee().negate(), EChannelType.Online,
    // ctqEthTransaction.getHash(), "ETH", withdraw.getCode(),
    // EJourBizTypeUser.AJ_WITHDRAWFEE.getCode(),
    // EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
    // }
    //
    // // 平台盈亏账户记入取现手续费
    // Account sysAccount = accountBO
    // .getAccount(ESystemAccount.SYS_ACOUNT_ETH.getCode());
    // if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
    // sysAccount = accountBO.changeAmount(sysAccount, withdraw.getFee(),
    // EChannelType.Online, ctqEthTransaction.getHash(), "ETH",
    // withdraw.getCode(), EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
    // EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
    // + withdraw.getPayCardNo());
    // }
    // // 平台盈亏账户记入取现矿工费
    // sysAccount = accountBO.changeAmount(sysAccount, txFee.negate(),
    // EChannelType.Online, ctqEthTransaction.getHash(), "ETH",
    // withdraw.getCode(), EJourBizTypePlat.AJ_WFEE.getCode(),
    // EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
    // + withdraw.getPayCardNo());
    //
    // }
    //
    // @Override
    // @Transactional
    // public void collection(String address, String chargeCode) {
    // // 获取地址信息
    // EthAddress xEthAddress = ethAddressBO.getEthAddress(EAddressType.X,
    // address);
    // if (xEthAddress == null) {
    // throw new BizException("xn625000", "该地址不能归集");
    // }
    //
    // Coin coin = coinBO.getCoin(EOriginialCoin.ETH.getCode());
    // BigDecimal limit = coin.getCollectStart();
    //
    // BigDecimal balance = ethAddressBO.getEthBalance(address);
    // // 余额大于配置值时，进行归集
    // if (balance.compareTo(limit) < 0) {
    // throw new BizException("xn625000", "未达到归集条件，无需归集");
    // }
    //
    // // 开始归集
    // collectBO.doETHCollection(xEthAddress, chargeCode);
    //
    // }
    //
    // @Override
    // @Transactional
    // public void collectionNotice(CtqEthTransaction ctqEthTransaction) {
    // // 根据交易hash查询归集记录
    // Collection collection = collectBO
    // .getCollectionByTxHash(ctqEthTransaction.getHash());
    // if (collection == null) {
    // return;
    // }
    // if (!ECollectionStatus.Broadcast.getCode()
    // .equals(collection.getStatus())) {
    // return;
    // }
    // // 归集订单状态更新
    // BigDecimal gasPrice = new BigDecimal(ctqEthTransaction.getGasPrice());
    // BigDecimal gasUse = new BigDecimal(
    // ctqEthTransaction.getGas().toString());
    // BigDecimal txFee = gasPrice.multiply(gasUse);
    // collectBO.colectionNoticeETH(collection, txFee,
    // ctqEthTransaction.getBlockCreateDatetime());
    // // 平台冷钱包加钱
    // Account coldAccount = accountBO
    // .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode());
    // BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
    // accountBO.changeAmount(coldAccount, amount, EChannelType.Online,
    // ctqEthTransaction.getHash(), "ETH", collection.getCode(),
    // EJourBizTypeCold.AJ_INCOME.getCode(),
    // "归集-来自地址：" + collection.getFromAddress());
    // // 平台盈亏账户记入矿工费
    // Account sysAccount = accountBO
    // .getAccount(ESystemAccount.SYS_ACOUNT_ETH.getCode());
    // accountBO.changeAmount(sysAccount, txFee.negate(), EChannelType.Online,
    // ctqEthTransaction.getHash(), "ETH", collection.getCode(),
    // EJourBizTypePlat.AJ_MFEE.getCode(),
    // "归集地址：" + collection.getFromAddress());
    // // 落地交易记录
    // ethTransactionBO.saveEthTransaction(ctqEthTransaction,
    // collection.getCode());
    // // 更新地址余额
    // EthAddress from = ethAddressBO.getEthAddress(EAddressType.X,
    // collection.getFromAddress());
    // EthAddress to = ethAddressBO.getEthAddress(EAddressType.W,
    // collection.getToAddress());
    // ethAddressBO.refreshBalance(from);
    // ethAddressBO.refreshBalance(to);
    // }
    //
    @Override
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition) {
        return ethTransactionBO.getPaginable(start, limit, condition);
    }
    //
    // @Override
    // public void depositNotice(CtqEthTransaction ctqEthTransaction) {
    // // 平台冷钱包减钱
    // BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
    // Account coldAccount = accountBO
    // .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode());
    // coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
    // EChannelType.Online, ctqEthTransaction.getHash(), "ETH",
    // ctqEthTransaction.getHash(), EJourBizTypeCold.AJ_PAY.getCode(),
    // "ETH定存至取现地址(M):" + ctqEthTransaction.getTo());
    // // 更新散取地址余额
    // EthAddress to = ethAddressBO.getEthAddress(EAddressType.M,
    // ctqEthTransaction.getTo());
    // ethAddressBO.refreshBalance(to);
    //
    // }

}
