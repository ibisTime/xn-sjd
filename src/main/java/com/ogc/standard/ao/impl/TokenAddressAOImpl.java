/**
 * @Title TokenAddressAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:43:34 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.ITokenAddressAO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.ICoinBO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.ITokenAddressBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.CoinUtil;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.dto.res.XN802906Res;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.ECoinType;
import com.cdkj.coin.wallet.enums.EHAddressStatus;
import com.cdkj.coin.wallet.enums.ESysUser;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWAddressStatus;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.token.TokenAddress;
import com.cdkj.coin.wallet.token.TokenClient;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:43:34 
 * @history:
 */
@Service
public class TokenAddressAOImpl implements ITokenAddressAO {

    private static Logger logger = Logger.getLogger(TokenAddressAOImpl.class);

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private ICollectionBO collectionBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private ICtqBO ctqBO;

    @Autowired
    private ICoinBO coinBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Override
    public void addTokenAddress(String address, String label, String userId,
            String smsCaptcha, String isCerti, String tradePwd,
            String googleCaptcha, String symbol) {

        // 地址有效性校验
        if (!WalletUtils.isValidAddress(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址" + address + "不符合以太坊规则，请仔细核对");
        }

        List<String> typeList = new ArrayList<String>();
        typeList.add(EAddressType.X.getCode());
        typeList.add(EAddressType.M.getCode());
        typeList.add(EAddressType.W.getCode());

        TokenAddress condition = new TokenAddress();
        condition.setAddress(address);
        condition.setTypeList(typeList);

        if (tokenAddressBO.getTotalCount(condition) > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "提现地址已经在本平台被使用，请仔细核对！");
        }

        String status = EYAddressStatus.NORMAL.getCode();
        // 是否设置为认证账户
        if (EBoolean.YES.getCode().equals(isCerti)) {
            status = EYAddressStatus.CERTI.getCode();
        }

        tokenAddressBO.saveTokenAddress(EAddressType.Y, userId, address, label,
            null, BigDecimal.ZERO, status, null, null, symbol);

    }

    @Override
    public void abandonAddress(String code) {
        TokenAddress tokenAddress = tokenAddressBO.getTokenAddress(code);
        if (EAddressType.X.getCode().equals(tokenAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "分发地址不能弃用");
        }
        if (EWAddressStatus.INVALID.getCode()
            .equals(tokenAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        tokenAddressBO.abandonAddress(tokenAddress);
    }

    @Override
    public EAddressType getType(String address, String symbol) {
        EAddressType type = EAddressType.Y;
        TokenAddress condition = new TokenAddress();
        condition.setAddress(address);
        condition.setSymbol(symbol);
        List<TokenAddress> results = tokenAddressBO
            .queryTokenAddressList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            TokenAddress tokenAddress = results.get(0);
            type = EAddressType.getAddressType(tokenAddress.getType());
        }
        return type;
    }

    @Override
    @Transactional
    public String generateMAddress(String symbol) {
        Coin coin = coinBO.getCoin(symbol);
        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "只有token币种才能调用此方法");
        }
        String address = tokenAddressBO.generateAddress(EAddressType.M,
            ESysUser.SYS_USER.getCode(), ESystemAccount.getPlatAccount(symbol),
            symbol);
        // 通知橙提取
        ctqBO.uploadTokenAddress(address, symbol);
        return address;
    }

    @Override
    public String generateHAddress(String symbol) {
        Coin coin = coinBO.getCoin(symbol);
        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "只有token币种才能调用此方法");
        }
        String address = tokenAddressBO.generateAddress(EAddressType.H,
            ESysUser.SYS_USER.getCode(), ESystemAccount.getPlatAccount(symbol),
            symbol);
        // 通知橙提取
        ctqBO.uploadTokenAddress(address, symbol);
        return address;
    }

    @Override
    public String importWAddress(String address, String symbol) {
        if (tokenAddressBO.isTokenAddressExist(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址" + address + "已经在平台内被使用，请仔细核对");
        }
        // 地址有效性校验
        if (!WalletUtils.isValidAddress(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址" + address + "不符合以太坊规则，请仔细核对");
        }
        return tokenAddressBO.saveTokenAddress(EAddressType.W,
            ESysUser.SYS_USER_COLD.getCode(),
            ESystemAccount.getPlatColdAccount(symbol), address, null,
            BigDecimal.ZERO, EWAddressStatus.NORMAL.getCode(), null, null,
            symbol);
    }

    @Override
    @Transactional
    public Paginable<TokenAddress> queryTokenAddressPage(int start, int limit,
            TokenAddress condition) {
        Paginable<TokenAddress> results = tokenAddressBO.getPaginable(start,
            limit, condition);
        for (TokenAddress tokenAddress : results.getList()) {
            // 归集地址统计
            if (EAddressType.W.getCode().equals(tokenAddress.getType())) {
                EthAddress xAddress = collectionBO.getAddressUseInfo(
                    tokenAddress.getAddress(), tokenAddress.getSymbol());
                tokenAddress.setUseCount(xAddress.getUseCount());
                tokenAddress.setUseAmount(xAddress.getUseAmount());
            }
            // 散取地址统计
            if (EAddressType.M.getCode().equals(tokenAddress.getType())) {
                EthAddress xAddress = withdrawBO.getAddressUseInfo(
                    tokenAddress.getAddress(), tokenAddress.getSymbol());
                tokenAddress.setUseCount(xAddress.getUseCount());
                tokenAddress.setUseAmount(xAddress.getUseAmount());
            }
            // 空投地址统计
            if (EAddressType.H.getCode().equals(tokenAddress.getType())) {
                EthAddress xAddress = chargeBO.getAddressUseInfo(
                    tokenAddress.getAddress(), tokenAddress.getSymbol());
                tokenAddress.setUseCount(xAddress.getUseCount());
                tokenAddress.setUseAmount(xAddress.getUseAmount());
            }
        }
        return results;
    }

    @Override
    public TokenAddress getTokenAddress(String code) {
        return tokenAddressBO.getTokenAddress(code);
    }

    @Override
    public BigDecimal getTotalBalance(EAddressType type, String symbol) {
        return tokenAddressBO.getTotalBalance(type, symbol);
    }

    @Override
    public String transfer(String code, String toUserId, BigDecimal value) {

        TokenAddress fromAddress = tokenAddressBO.getTokenAddressSecret(code);

        if (!EHAddressStatus.NORMAL.getCode().equals(fromAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该状态的地址不可使用");
        }

        TokenAddress toAddress = tokenAddressBO
            .getTokenAddressByUserId(toUserId, fromAddress.getSymbol());

        Coin coin = coinBO.getCoin(fromAddress.getSymbol());
        BigDecimal balance = TokenClient.getBalance(fromAddress.getAddress(),
            coin.getContractAddress());
        BigDecimal amount = CoinUtil.toMinUnit(value, coin.getUnit());
        if (balance.compareTo(amount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "token余额不足");
        }

        // 预估矿工费用
        BigDecimal gasPrice = TokenClient.getGasPrice();
        BigDecimal gasUse = new BigDecimal(210000);
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 查询散取地址余额
        BigDecimal ethBalance = TokenClient
            .getEthBalance(fromAddress.getAddress());
        logger.info(
            "地址" + fromAddress.getAddress() + "余额：" + ethBalance.toString());
        if (ethBalance.compareTo(txFee) < 0) {
            throw new BizException("xn625000",
                "空投地址" + fromAddress.getAddress() + "ETH余额不足以支付空投矿工费！");
        }

        return TokenClient.transfer(fromAddress, toAddress.getAddress(), amount,
            coin.getContractAddress());
    }

    @Override
    public XN802906Res getKongtouInfo(String symbol) {

        XN802906Res res = new XN802906Res();

        TokenAddress condition = new TokenAddress();
        condition.setSymbol(symbol);
        condition.setType(EAddressType.H.getCode());
        List<TokenAddress> tokenAddresses = tokenAddressBO
            .queryTokenAddressList(condition);
        BigDecimal totalCount = BigDecimal.ZERO;
        BigDecimal leftCount = BigDecimal.ZERO;
        for (TokenAddress tokenAddress : tokenAddresses) {
            totalCount = totalCount.add(tokenAddress.getInitialBalance());
            leftCount = leftCount.add(tokenAddress.getBalance());
        }

        res.setTotalCount(totalCount.toString());
        res.setUseCount(totalCount.subtract(leftCount).toString());

        if (totalCount.compareTo(BigDecimal.ZERO) > 0) {
            res.setUseRate(totalCount.subtract(leftCount).divide(totalCount)
                .scaleByPowerOfTen(2).setScale(8, RoundingMode.DOWN)
                .toString());
        } else {
            res.setUseRate("0.00");
        }

        return res;
    }

    public static void main(String[] args) {
        BigDecimal totalCount = new BigDecimal("1000000");
        BigDecimal leftCount = new BigDecimal("14.0975");
        System.out.println(leftCount.divide(totalCount).scaleByPowerOfTen(2)
            .setScale(8, RoundingMode.UP).toString());
    }

}
