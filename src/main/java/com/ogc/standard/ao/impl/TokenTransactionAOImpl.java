package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.ITokenTransactionAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.ICoinBO;
import com.cdkj.coin.wallet.bo.ITokenAddressBO;
import com.cdkj.coin.wallet.bo.ITokenTransactionBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.EGeneratePrefix;
import com.cdkj.coin.wallet.enums.EJourBizTypeCold;
import com.cdkj.coin.wallet.enums.EJourBizTypePlat;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.token.TokenAddress;
import com.cdkj.coin.wallet.token.TokenTransaction;

@Service
public class TokenTransactionAOImpl implements ITokenTransactionAO {

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private ITokenTransactionBO tokenTransactionBO;

    @Autowired
    private ICoinBO coinBO;

    @Override
    public Paginable<TokenTransaction> queryTokenTransactionPage(int start,
            int limit, TokenTransaction condition) {
        return tokenTransactionBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<TokenTransaction> queryTokenTransactionList(
            TokenTransaction condition) {
        return tokenTransactionBO.queryTokenTransactionList(condition);
    }

    @Override
    public TokenTransaction getTokenTransaction(Long id) {
        return tokenTransactionBO.getTokenTransaction(id);
    }

    @Override
    @Transactional
    public String chargeNotice(TokenTransaction tokenTransaction) {

        TokenAddress tokenAddress = tokenAddressBO
            .getTokenAddress(EAddressType.X, tokenTransaction.getTokenTo());
        if (tokenAddress == null) {
            throw new BizException("xn6250000", "充值地址不存在");
        }
        Charge condition = new Charge();
        condition.setRefNo(tokenTransaction.getHashAndLogIndex());
        if (chargeBO.getTotalCount(condition) > 0) {
            return "";
        }
        String symbol = tokenAddress.getSymbol();
        Account account = accountBO.getAccountByUser(tokenAddress.getUserId(),
            symbol);
        String payGroup = OrderNoGenerater
            .generate(EGeneratePrefix.PAY_GROUP.getCode());
        BigDecimal amount = tokenTransaction.getTokenValue();
        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, payGroup,
            tokenTransaction.getHashAndLogIndex(),
            EJourBizTypeUser.AJ_CHARGE.getCode(),
            symbol + "充值-来自地址：" + tokenTransaction.getTokenFrom(), amount,
            EChannelType.Online, account.getUserId(),
            tokenTransaction.getTokenFrom());
        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.Online,
            tokenTransaction.getHashAndLogIndex(), payGroup, code,
            EJourBizTypeUser.AJ_CHARGE.getCode(),
            symbol + "充值-来自地址：" + tokenTransaction.getTokenFrom());
        // 落地交易记录
        tokenTransactionBO.saveTokenTransaction(tokenTransaction);

        Coin coin = coinBO.getCoin(symbol);
        // 更新地址余额
        tokenAddressBO.refreshBalance(tokenAddress, coin.getContractAddress());

        // 如果是空投的充值，更新空投地址余额
        TokenAddress fromAddress = tokenAddressBO
            .getTokenAddress(EAddressType.H, tokenTransaction.getTokenFrom());
        if (tokenAddress != null) {
            // 更新地址余额
            tokenAddressBO.refreshBalance(fromAddress,
                coin.getContractAddress());
        }

        return code;
    }

    @Override
    public void withdrawNotice(TokenTransaction tokenTransaction) {
        // 根据交易hash查询取现订单
        Withdraw withdraw = withdrawBO
            .getWithdrawByChannelOrder(tokenTransaction.getHash());
        if (withdraw == null) {
            return;
        }
        if (!EWithdrawStatus.Broadcast.getCode().equals(withdraw.getStatus())) {
            return;
        }

        // 计算矿工费
        BigDecimal gasPrice = tokenTransaction.getGasPrice();
        BigDecimal gasUse = new BigDecimal(
            tokenTransaction.getGasUsed().toString());
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 取现订单更新
        withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
            tokenTransaction.getTokenFrom(), "广播成功", tokenTransaction.getHash(),
            tokenTransaction.getHash(), txFee);

        // 落地交易记录
        tokenTransactionBO.saveTokenTransaction(tokenTransaction);

        // 更新地址余额
        String symbol = withdraw.getCurrency();
        Coin coin = coinBO.getCoin(symbol);
        TokenAddress from = tokenAddressBO.getTokenAddress(EAddressType.M,
            tokenTransaction.getTokenFrom());
        tokenAddressBO.refreshBalance(from, coin.getContractAddress());

        // 修改散取地址状态为可使用
        tokenAddressBO.refreshStatus(from, EMAddressStatus.NORMAL.getCode());

        Account userAccount = accountBO.getAccount(withdraw.getAccountNumber());
        // 取现金额解冻
        userAccount = accountBO.unfrozenAmount(userAccount,
            withdraw.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getValue(),
            withdraw.getCode());
        // 取现金额扣减
        userAccount = accountBO.changeAmount(userAccount,
            withdraw.getAmount().subtract(withdraw.getFee()).negate(),
            EChannelType.Online, tokenTransaction.getHash(), symbol,
            withdraw.getCode(), EJourBizTypeUser.AJ_WITHDRAW.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            // 取现手续费扣减
            userAccount = accountBO.changeAmount(userAccount,
                withdraw.getFee().negate(), EChannelType.Online,
                tokenTransaction.getHash(), symbol, withdraw.getCode(),
                EJourBizTypeUser.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
        }

        // 平台盈亏账户记入取现手续费
        Account sysAccount = accountBO
            .getAccount(ESystemAccount.getPlatAccount(symbol));
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            sysAccount = accountBO.changeAmount(sysAccount, withdraw.getFee(),
                EChannelType.Online, tokenTransaction.getHash(), symbol,
                withdraw.getCode(), EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());
        }
        // 平台盈亏账户记入取现矿工费
        // sysAccount = accountBO.changeAmount(sysAccount, txFee.negate(),
        // EChannelType.Online, tokenTransaction.getHash(), symbol,
        // withdraw.getCode(), EJourBizTypePlat.AJ_WFEE.getCode(),
        // EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
        // + withdraw.getPayCardNo());
    }

    @Override
    public void depositNotice(TokenTransaction tokenTransaction) {
        TokenAddress mAddress = tokenAddressBO.getTokenAddress(EAddressType.M,
            tokenTransaction.getTokenTo());
        String symbol = mAddress.getSymbol();
        Coin coin = coinBO.getCoin(symbol);
        // 平台冷钱包减钱
        BigDecimal amount = tokenTransaction.getTokenValue();
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.getPlatColdAccount(symbol));
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.Online, tokenTransaction.getHashAndLogIndex(), symbol,
            tokenTransaction.getHashAndLogIndex(),
            EJourBizTypeCold.AJ_PAY.getCode(),
            symbol + "定存至取现地址(M):" + tokenTransaction.getTokenTo());
        // 更新散取地址余额
        tokenAddressBO.refreshBalance(mAddress, coin.getContractAddress());
        // 落地交易记录
        tokenTransactionBO.saveTokenTransaction(tokenTransaction);

    }

    @Override
    public void addTokenTransaction(TokenTransaction tokenTransaction) {
        tokenTransactionBO.saveTokenTransaction(tokenTransaction);
    }

    @Override
    public void addKongtouNotice(TokenTransaction tokenTransaction) {
        TokenAddress mAddress = tokenAddressBO.getTokenAddress(EAddressType.H,
            tokenTransaction.getTokenTo());
        String symbol = mAddress.getSymbol();
        Coin coin = coinBO.getCoin(symbol);
        // 更新空投地址余额
        tokenAddressBO.refreshBalance(mAddress, coin.getContractAddress());
        // 更新空投地址总量
        tokenAddressBO.refreshInitialBalance(mAddress,
            tokenTransaction.getTokenValue());
        // 落地交易记录
        tokenTransactionBO.saveTokenTransaction(tokenTransaction);
    }
}
