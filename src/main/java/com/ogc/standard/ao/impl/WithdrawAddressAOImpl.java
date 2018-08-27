package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.IWithdrawAddressAO;
import com.cdkj.coin.wallet.bitcoin.BtcClient;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.ICoinBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.ITokenAddressBO;
import com.cdkj.coin.wallet.bo.IWanAddressBO;
import com.cdkj.coin.wallet.bo.IWithdrawAddressBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.domain.WithdrawAddress;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.ECoinType;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.token.TokenAddress;

@Service
public class WithdrawAddressAOImpl implements IWithdrawAddressAO {

    @Autowired
    private IWithdrawAddressBO withdrawAddressBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Autowired
    private ITokenAddressBO tokenAddressBO;

    @Autowired
    private ICoinBO coinBO;

    @Override
    public String addWithdrawAddress(String currency, String address,
            String label, String userId, String isCerti) {

        // 地址有效性校验
        Coin coin = coinBO.getCoin(currency);
        verifyAddress(coin, address);

        // 地址是否已经存在
        WithdrawAddress condition = new WithdrawAddress();
        condition.setCurrency(currency);
        condition.setUserId(userId);
        condition.setAddress(address);
        if (withdrawAddressBO.getTotalCount(condition) > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "请勿重复添加地址");
        }

        // 是否设置为认证地址
        String status = EYAddressStatus.NORMAL.getCode();
        if (EBoolean.YES.getCode().equals(isCerti)) {
            status = EYAddressStatus.CERTI.getCode();
        }

        return withdrawAddressBO.saveWithdrawAddress(currency, address, label,
            userId, status);
    }

    private void verifyAddress(Coin coin, String address) {
        if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
            if (EOriginialCoin.ETH.getCode().equals(coin.getSymbol())) {
                // 地址有效性校验
                if (!WalletUtils.isValidAddress(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "地址" + address + "不符合" + EOriginialCoin.ETH.getCode()
                                + "规则，请仔细核对");
                }
                if (ethAddressBO.isEthAddressExist(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "Bcoin平台使用的地址，请仔细检查新增地址是否是您的"
                                + EOriginialCoin.ETH.getCode() + "私有地址");
                }
            } else if (EOriginialCoin.BTC.getCode().equals(coin.getSymbol())) {
                // 地址有效性校验
                if (!BtcClient.verifyAddress(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "地址" + address + "不符合" + EOriginialCoin.BTC.getCode()
                                + "规则，请仔细核对");
                }
                if (btcAddressBO.isBtcAddressExist(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "平台已使用的地址，请仔细检查新增地址是否是您的" + EOriginialCoin.BTC.getCode()
                                + "私有地址");
                }
            } else if (EOriginialCoin.WAN.getCode().equals(coin.getSymbol())) {
                // 地址有效性校验
                if (!WalletUtils.isValidAddress(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "地址" + address + "不符合" + EOriginialCoin.WAN.getCode()
                                + "规则，请仔细核对");
                }
                if (wanAddressBO.isWanAddressExist(address)) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "平台已使用的地址，请仔细检查新增地址是否是您的" + EOriginialCoin.WAN.getCode()
                                + "私有地址");
                }
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "不支持的币种");
            }
        } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {
            if (!WalletUtils.isValidAddress(address)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址不符合" + EOriginialCoin.ETH.getCode() + "规则，请仔细核对");
            }

            TokenAddress condition = new TokenAddress();
            condition.setAddress(address);
            if (tokenAddressBO.getTotalCount(condition) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址已经在本平台被使用，请仔细核对！");
            }
        }

    }

    @Override
    public int dropWithdrawAddress(String code) {
        return withdrawAddressBO.removeWithdrawAddress(code);
    }

    @Override
    public Paginable<WithdrawAddress> queryWithdrawAddressPage(int start,
            int limit, WithdrawAddress condition) {
        return withdrawAddressBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WithdrawAddress> queryWithdrawAddressList(
            WithdrawAddress condition) {
        return withdrawAddressBO.queryWithdrawAddressList(condition);
    }

    @Override
    public WithdrawAddress getWithdrawAddress(String code) {
        return withdrawAddressBO.getWithdrawAddress(code);
    }
}
