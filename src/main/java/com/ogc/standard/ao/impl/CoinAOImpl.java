package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.ICoinAO;
import com.cdkj.coin.wallet.bitcoin.BtcAddressProperties;
import com.cdkj.coin.wallet.bitcoin.util.BtcBlockExplorer;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.ICoinBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.CoinUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.dto.req.XN802250Req;
import com.cdkj.coin.wallet.dto.req.XN802252Req;
import com.cdkj.coin.wallet.dto.req.XN802270DetailReq;
import com.cdkj.coin.wallet.dto.req.XN802270Req;
import com.cdkj.coin.wallet.dto.res.XN802270DetailRes;
import com.cdkj.coin.wallet.dto.res.XN802270Res;
import com.cdkj.coin.wallet.enums.ECoinStatus;
import com.cdkj.coin.wallet.enums.ECoinType;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.ethereum.EthClient;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.token.TokenClient;
import com.cdkj.coin.wallet.wanchain.WanClient;
import com.cdkj.coin.wallet.wanchain.WanTokenClient;

@Service
public class CoinAOImpl implements ICoinAO {

    @Autowired
    private ICoinBO coinBO;

    @Autowired
    private ICtqBO ctqBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    BtcBlockExplorer btcBlockExplorer;

    @Override
    @Transactional
    public void addCoinAndPublish(XN802250Req req) {

        if (ECoinType.ETH_TOKEN.getCode().equals(req.getType())) {
            addEthToken(req);
        } else if (ECoinType.WAN_TOKEN.getCode().equals(req.getType())) {
            addWanToken(req);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "暂不支持此类型的币种");
        }

    }

    private void addWanToken(XN802250Req req) {

        if (!WalletUtils.isValidAddress(req.getContractAddress())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "合约地址不符合" + EOriginialCoin.WAN.getCode() + "规则，请仔细核对");
        }

        // 检查币种符号是否已经在平台内存在
        if (coinBO.isSymbolExist(req.getSymbol())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "币种符号" + req.getSymbol() + "已经被使用");
        }
        // 检查币种符号是否已经在平台内存在
        if (coinBO.isContractAddressExist(ECoinType.WAN_TOKEN.getCode(),
            req.getContractAddress())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "合约地址" + req.getContractAddress() + "已经被使用");
        }

        // 分配该币种盈亏账户和冷钱包账户
        accountBO.distributePlatAccount(req.getSymbol());

        // 落地币种信息
        Coin data = new Coin();

        data.setSymbol(req.getSymbol());
        data.setEname(req.getEname());
        data.setCname(req.getCname());
        data.setType(ECoinType.WAN_TOKEN.getCode());
        data.setUnit(StringValidater.toInteger(req.getUnit()));

        data.setIcon(req.getIcon());
        data.setPic1(req.getPic1());
        data.setPic2(req.getPic2());
        data.setPic3(req.getPic3());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setCollectStart(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getCollectStart()),
            StringValidater.toInteger(req.getUnit())));
        data.setWithdrawFee(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getWithdrawFee()),
            StringValidater.toInteger(req.getUnit())));
        data.setContractAddress(req.getContractAddress());
        data.setContractABI(req.getContractABI());
        data.setStatus(ECoinStatus.PUBLISHED.getCode());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        coinBO.saveCoin(data);

        // 上传合约地址
        ctqBO.uploadWanContractAddress(req.getSymbol(),
            req.getContractAddress());
    }

    private void addEthToken(XN802250Req req) {

        if (!WalletUtils.isValidAddress(req.getContractAddress())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "合约地址不符合" + EOriginialCoin.ETH.getCode() + "规则，请仔细核对");
        }

        // 检查币种符号是否已经在平台内存在
        if (coinBO.isSymbolExist(req.getSymbol())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "币种符号" + req.getSymbol() + "已经被使用");
        }
        // 检查币种符号是否已经在平台内存在
        if (coinBO.isContractAddressExist(ECoinType.ETH_TOKEN.getCode(),
            req.getContractAddress())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "合约地址" + req.getContractAddress() + "已经被使用");
        }

        // 分配该币种盈亏账户和冷钱包账户
        accountBO.distributePlatAccount(req.getSymbol());

        // 落地币种信息
        Coin data = new Coin();

        data.setSymbol(req.getSymbol());
        data.setEname(req.getEname());
        data.setCname(req.getCname());
        data.setType(ECoinType.ETH_TOKEN.getCode());
        data.setUnit(StringValidater.toInteger(req.getUnit()));

        data.setIcon(req.getIcon());
        data.setPic1(req.getPic1());
        data.setPic2(req.getPic2());
        data.setPic3(req.getPic3());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setCollectStart(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getCollectStart()),
            StringValidater.toInteger(req.getUnit())));
        data.setWithdrawFee(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getWithdrawFee()),
            StringValidater.toInteger(req.getUnit())));
        data.setContractAddress(req.getContractAddress());
        data.setContractABI(req.getContractABI());
        data.setStatus(ECoinStatus.PUBLISHED.getCode());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        coinBO.saveCoin(data);

        // 上传合约地址
        ctqBO.uploadContractAddress(req.getSymbol(), req.getContractAddress());

    }

    @Override
    public int editCoin(XN802252Req req) {

        Coin data = coinBO.getCoin(req.getSymbol());

        data.setSymbol(req.getSymbol());
        data.setEname(req.getEname());
        data.setCname(req.getCname());
        data.setIcon(req.getIcon());
        data.setPic1(req.getPic1());

        data.setPic2(req.getPic2());
        data.setPic3(req.getPic3());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        data.setCollectStart(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getCollectStart()),
            data.getUnit()));
        data.setWithdrawFee(CoinUtil.toMinUnit(
            StringValidater.toBigDecimal(req.getWithdrawFee()),
            data.getUnit()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        return coinBO.refreshCoin(data);
    }

    @Override
    public void publish(String symbol, String updater, String remark) {

        Coin coin = coinBO.getCoin(symbol);
        if (ECoinStatus.PUBLISHED.getCode().equals(coin.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "币种已发布，请勿重复操作");
        }

        coinBO.refreshStatus(coin, ECoinStatus.PUBLISHED, updater, remark);

    }

    @Override
    public void revoke(String symbol, String updater, String remark) {

        Coin coin = coinBO.getCoin(symbol);
        if (!ECoinStatus.PUBLISHED.getCode().equals(coin.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "币种未发布，不能进行撤下操作");
        }
        // 检查币种类型数量，至少保留一个币种
        coinBO.checkCoinCount(coin.getType());

        coinBO.refreshStatus(coin, ECoinStatus.REVOKE, updater, remark);

    }

    @Override
    public Paginable<Coin> queryCoinPage(int start, int limit, Coin condition) {
        return coinBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Coin> queryCoinList(Coin condition) {
        return coinBO.queryCoinList(condition);
    }

    @Override
    public Coin getCoin(String symbol) {
        return coinBO.getCoin(symbol);
    }

    @Override
    public XN802270Res queryCoinBalance(XN802270Req req) {

        XN802270Res res = new XN802270Res();

        List<XN802270DetailRes> resultList = new ArrayList<>();

        for (XN802270DetailReq account : req.getAccountList()) {

            Coin coin = coinBO.getCoin(account.getSymbol());
            BigDecimal balance = BigDecimal.ZERO;
            if (ECoinType.ORIGINAL.getCode().equals(coin.getType())) {
                if (EOriginialCoin.ETH.getCode().equals(account.getSymbol())) {

                    balance = EthClient.getBalance(account.getAddress());

                } else if (EOriginialCoin.BTC.getCode()
                    .equals(account.getSymbol())) {

                    BtcAddressProperties btcAddressProperties = btcBlockExplorer
                        .getAddressProperties(account.getAddress());
                    balance = btcAddressProperties.getBalance();

                } else if (EOriginialCoin.WAN.getCode()
                    .equals(account.getSymbol())) {

                    balance = WanClient.getBalance(account.getAddress());

                }
            } else if (ECoinType.ETH_TOKEN.getCode().equals(coin.getType())) {

                balance = TokenClient.getBalance(account.getAddress(),
                    coin.getContractAddress());

            } else if (ECoinType.WAN_TOKEN.getCode().equals(coin.getType())) {

                balance = WanTokenClient.getBalance(account.getAddress(),
                    coin.getContractAddress());

            }
            XN802270DetailRes newAccount = new XN802270DetailRes();
            newAccount.setSymbol(account.getSymbol());
            newAccount.setAddress(account.getAddress());
            newAccount.setBalance(balance);
            resultList.add(newAccount);

        }

        res.setAccountList(resultList);

        return res;
    }

}
