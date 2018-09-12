/**
 * @Title EthTransactionAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:33:42 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IEthTransactionAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.ICoinBO;
import com.ogc.standard.bo.ICollectBO;
import com.ogc.standard.bo.IDepositBO;
import com.ogc.standard.bo.IEthTransactionBO;
import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.ITokenEventBO;
import com.ogc.standard.bo.IWithdrawBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.CtqEthTransaction;
import com.ogc.standard.domain.EthTransaction;
import com.ogc.standard.domain.EthXAddress;
import com.ogc.standard.domain.TokenEvent;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EJourBizTypeCold;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EOriginialCoin;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:33:42 
 * @history:
 */
@Service
public class EthTransactionAOImpl implements IEthTransactionAO {

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IEthXAddressBO ethXAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private ITokenEventBO tokenEventBO;

    @Autowired
    private ICollectBO collectBO;

    @Autowired
    private ICoinBO coinBO;

    @Autowired
    private IDepositBO depositBO;

    @Override
    @Transactional
    public String ethChargeNotice(CtqEthTransaction ctqEthTransaction) {
        // 充值地址在X地址表中查询
        EthXAddress ethXAddress = ethXAddressBO
            .getEthXAddressByAddress(ctqEthTransaction.getTo());
        if (ethXAddress == null) {
            throw new BizException("xn6250000", "充值地址不存在");
        }
        Charge condition = new Charge();
        condition.setChannelOrder(ctqEthTransaction.getHash());
        // 充值记录已经存在
        if (chargeBO.getTotalCount(condition) > 0) {
            return "";
        }
        Account account = accountBO.getAccountByUser(ethXAddress.getUserId(),
            EOriginialCoin.ETH.getCode());
        BigDecimal amount = ctqEthTransaction.getValue();
        // 哪一条链
        String cardNoInfo = EOriginialCoin.ETH.getCode();
        String fromAddress = ctqEthTransaction.getFrom();
        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, cardNoInfo,
            fromAddress, amount, EChannelType.Online,
            ctqEthTransaction.getHash(),
            "ETH充值-来自地址：" + ctqEthTransaction.getFrom(),
            ctqEthTransaction.getHash());

        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.Online,
            ctqEthTransaction.getHash(), code,
            EJourBizTypeUser.AJ_CHARGE.getCode(), "ETH充值-来自地址："
                    + ctqEthTransaction.getFrom());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction);
        return code;
    }

    @Override
    @Transactional
    public String tokenChargeNotice(CtqEthTransaction ctqEthTransaction,
            TokenEvent tokenEvent) {
        if (null == tokenEvent) {
            return "";
        }
        // 充值地址在X地址表中查询
        EthXAddress ethXAddress = ethXAddressBO
            .getEthXAddressByAddress(tokenEvent.getTokenTo());
        if (ethXAddress == null) {
            throw new BizException("xn6250000", "充值地址不存在");
        }
        TokenEvent condition = new TokenEvent();
        condition.setHash(ctqEthTransaction.getHash());
        condition.setTokenLogIndex(tokenEvent.getTokenLogIndex());
        // 充值记录已经存在
        if (tokenEventBO.getTotalCount(condition) > 0) {
            return "";
        }
        String symbol = tokenEvent.getSymbol();
        Account account = accountBO.getAccountByUser(ethXAddress.getUserId(),
            symbol);
        BigDecimal amount = tokenEvent.getTokenValue();
        // 哪一条链
        String cardNoInfo = EOriginialCoin.ETH.getCode();
        String fromAddress = tokenEvent.getTokenFrom();
        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, cardNoInfo,
            fromAddress, amount, EChannelType.Online,
            ctqEthTransaction.getHash(), symbol + "充值-来自地址："
                    + ctqEthTransaction.getFrom(), ctqEthTransaction.getHash());

        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.Online,
            ctqEthTransaction.getHash(), code,
            EJourBizTypeUser.AJ_CHARGE.getCode(), symbol + "充值-来自地址："
                    + ctqEthTransaction.getFrom());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction);
        // 落地tokenEvent
        tokenEventBO.insertTokenEvent(tokenEvent);
        return code;
    }

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
    /*
     * @Override
     * @Transactional public void collection(String address, String chargeCode)
     * { // 获取地址信息 EthAddress xEthAddress =
     * ethAddressBO.getEthAddress(EAddressType.X, address); if (xEthAddress ==
     * null) { throw new BizException("xn625000", "该地址不能归集"); } Coin coin =
     * coinBO.getCoin(EOriginialCoin.ETH.getCode()); BigDecimal limit =
     * coin.getCollectStart(); BigDecimal balance =
     * ethAddressBO.getEthBalance(address); // 余额大于配置值时，进行归集 if
     * (balance.compareTo(limit) < 0) { throw new BizException("xn625000",
     * "未达到归集条件，无需归集"); } // 开始归集 collectBO.doETHCollection(xEthAddress,
     * chargeCode); }
     */

    /*
     * @Override
     * @Transactional public void collectionNotice(CtqEthTransaction
     * ctqEthTransaction) { // 根据交易hash查询归集记录 Collection collection = collectBO
     * .getCollectionByTxHash(ctqEthTransaction.getHash()); if (collection ==
     * null) { return; } if (!ECollectionStatus.Broadcast.getCode().equals(
     * collection.getStatus())) { return; } // 归集订单状态更新 BigDecimal gasPrice =
     * new BigDecimal(ctqEthTransaction.getGasPrice()); BigDecimal gasUse = new
     * BigDecimal(ctqEthTransaction.getGas() .toString()); BigDecimal txFee =
     * gasPrice.multiply(gasUse); collectBO.colectionNoticeETH(collection,
     * txFee, ctqEthTransaction.getBlockCreateDatetime()); // 平台冷钱包加钱 Account
     * coldAccount = accountBO
     * .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode()); BigDecimal
     * amount = new BigDecimal(ctqEthTransaction.getValue());
     * accountBO.changeAmount(coldAccount, amount, EChannelType.Online,
     * ctqEthTransaction.getHash(), "ETH", collection.getCode(),
     * EJourBizTypeCold.AJ_INCOME.getCode(), "归集-来自地址：" +
     * collection.getFromAddress()); // 平台盈亏账户记入矿工费 Account sysAccount =
     * accountBO.getAccount(ESystemAccount.SYS_ACOUNT_ETH .getCode());
     * accountBO.changeAmount(sysAccount, txFee.negate(), EChannelType.Online,
     * ctqEthTransaction.getHash(), "ETH", collection.getCode(),
     * EJourBizTypePlat.AJ_MFEE.getCode(), "归集地址：" +
     * collection.getFromAddress()); // 落地交易记录
     * ethTransactionBO.saveEthTransaction(ctqEthTransaction,
     * collection.getCode()); // 更新地址余额 EthAddress from =
     * ethAddressBO.getEthAddress(EAddressType.X, collection.getFromAddress());
     * EthAddress to = ethAddressBO.getEthAddress(EAddressType.W,
     * collection.getToAddress()); ethAddressBO.refreshBalance(from);
     * ethAddressBO.refreshBalance(to); }
     */

    @Override
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition) {
        return ethTransactionBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public void ethDepositNotice(CtqEthTransaction ctqEthTransaction) {
        // 平台冷钱包减钱
        BigDecimal amount = ctqEthTransaction.getValue();
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode());
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.Online, ctqEthTransaction.getHash(),
            ctqEthTransaction.getHash(), EJourBizTypeCold.AJ_PAY.getCode(),
            "ETH定存至取现地址(M):" + ctqEthTransaction.getTo());
        String symbol = EOriginialCoin.ETH.getCode();
        // 落地定存记录
        depositBO.saveDeposit(symbol, ctqEthTransaction.getFrom(),
            ctqEthTransaction.getTo(), ctqEthTransaction.getValue(),
            ctqEthTransaction.getHash(), ctqEthTransaction.getGasFee(),
            ctqEthTransaction.getBlockCreateDatetime());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction);
    }

    @Override
    @Transactional
    public void tokenDepositNotice(CtqEthTransaction ctqEthTransaction,
            TokenEvent tokenEvent) {
        // 平台冷钱包减钱
        BigDecimal amount = tokenEvent.getTokenValue();
        String symbol = tokenEvent.getSymbol();
        Account coldAccount = accountBO.getAccount(ESystemAccount
            .getPlatColdAccount(symbol));
        // 同一哈希可对应多条tokenEvent,拼接上logIndex保证唯一
        String hashAndLogIndex = ctqEthTransaction.getHash() + "||"
                + tokenEvent.getTokenLogIndex();
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.Online, hashAndLogIndex, hashAndLogIndex,
            EJourBizTypeCold.AJ_PAY.getCode(), symbol + "定存至取现地址(M):"
                    + tokenEvent.getTokenTo());

        // 落地定存记录
        depositBO.saveDeposit(symbol, tokenEvent.getTokenFrom(),
            tokenEvent.getTokenTo(), tokenEvent.getTokenValue(),
            ctqEthTransaction.getHash(), ctqEthTransaction.getGasFee(),
            ctqEthTransaction.getBlockCreateDatetime());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction);
    }

    @Override
    public void addTransaction(CtqEthTransaction ctqEthTransaction) {
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction);
        List<TokenEvent> tokenEventList = ctqEthTransaction.getTokenEventList();
        if (CollectionUtils.isNotEmpty(tokenEventList)) {
            tokenEventBO.insertEventsList(tokenEventList);
        }
    }
}
