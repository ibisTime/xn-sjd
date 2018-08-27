package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.IWanAddressBO;
import com.cdkj.coin.wallet.bo.IWanTransactionBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.ICollectionDAO;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.ECollectionStatus;
import com.cdkj.coin.wallet.enums.EGeneratePrefix;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.wanchain.WanAddress;

@Component
public class CollectionBOImpl extends PaginableBOImpl<Collection>
        implements ICollectionBO {

    private static final Logger logger = LoggerFactory
        .getLogger(CollectionBOImpl.class);

    @Autowired
    private ICollectionDAO collectionDAO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private IWanTransactionBO wanTransactionBO;

    @Override
    public String saveCollection(EOriginialCoin coin, String from, String to,
            BigDecimal value, String txHash, String refNo) {
        String code = null;
        Collection data = new Collection();
        code = OrderNoGenerater.generate(EGeneratePrefix.Collection.getCode());
        data.setCode(code);
        data.setCurrency(coin.getCode());
        data.setFromAddress(from);
        data.setToAddress(to);
        data.setAmount(value);
        data.setTxHash(txHash);
        data.setStatus(ECollectionStatus.Broadcast.getCode());
        data.setCreateDatetime(new Date());
        data.setRefNo(refNo);
        collectionDAO.insert(data);
        return code;
    }

    @Override
    public List<Collection> queryCollectionList(Collection condition) {
        return collectionDAO.selectList(condition);
    }

    @Override
    public Collection getCollection(String code) {
        Collection data = null;
        if (StringUtils.isNotBlank(code)) {
            Collection condition = new Collection();
            condition.setCode(code);
            data = collectionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "归集记录不存在");
            }
        }
        return data;
    }

    @Override
    public Collection getCollectionByTxHash(String txHash) {
        Collection collection = null;
        Collection condition = new Collection();
        condition.setTxHash(txHash);
        List<Collection> results = collectionDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            collection = results.get(0);
        }
        return collection;
    }

    @Override
    public Collection getCollectionByRefNo(String refNo) {
        Collection ethCollection = null;
        Collection condition = new Collection();
        condition.setRefNo(refNo);
        List<Collection> results = collectionDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            ethCollection = results.get(0);
        }
        return ethCollection;
    }

    @Override
    public int colectionNoticeETH(Collection data, BigDecimal txfee,
            Date ethDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(ethDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeETH(data);
        return count;
    }

    @Override
    public int colectionNoticeBTC(Collection data, BigDecimal txfee,
            Date confirmDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(confirmDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeBTC(data);
        return count;
    }

    @Override
    public int colectionNoticeSC(Collection data, String fromAddress,
            BigDecimal txfee, Date ethDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setFromAddress(fromAddress);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(ethDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeSC(data);
        return count;
    }

    @Override
    public EthAddress getAddressUseInfo(String toAddress, String currency) {
        Collection data = new Collection();
        data.setToAddress(toAddress);
        data.setCurrency(currency);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        return collectionDAO.selectAddressUseInfo(data);
    }

    @Override
    public BigDecimal getTotalCollect(String currency) {
        Collection condition = new Collection();
        condition.setCurrency(currency);
        return collectionDAO.selectTotalCollect(condition);
    }

    @Override
    public void doETHCollection(EthAddress ethAddress, String chargeCode) {

        String fromAddress = ethAddress.getAddress();

        // 获取今日归集地址
        EthAddress wEthAddress = ethAddressBO.getWEthAddressToday();
        String toAddress = wEthAddress.getAddress();

        // 预估矿工费用
        BigDecimal balance = ethAddress.getBalance();
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，以太坊平均价格=" + gasPrice + "，预计矿工费="
                + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        EthAddress secret = ethAddressBO
            .getEthAddressSecret(ethAddress.getCode());
        String txHash = ethTransactionBO.broadcast(fromAddress, secret,
            toAddress, value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        saveCollection(EOriginialCoin.ETH, fromAddress, toAddress, value,
            txHash, chargeCode);
    }

    @Override
    public void doWanCollection(WanAddress wanAddress, String chargeCode) {

        String fromAddress = wanAddress.getAddress();

        // 获取今日归集地址
        WanAddress wWanAddress = wanAddressBO.getWWanAddressToday();
        String toAddress = wWanAddress.getAddress();

        // 预估矿工费用
        BigDecimal balance = wanAddress.getBalance();
        BigDecimal gasPrice = wanTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，万维链平均价格=" + gasPrice + "，预计矿工费="
                + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        WanAddress secret = wanAddressBO
            .getWanAddressSecret(wanAddress.getCode());
        String txHash = wanTransactionBO.broadcast(fromAddress, secret,
            toAddress, value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        saveCollection(EOriginialCoin.WAN, fromAddress, toAddress, value,
            txHash, chargeCode);
    }

    @Override
    public int colectionNoticeWAN(Collection data, BigDecimal txfee,
            Date wanDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(wanDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeWAN(data);
        return count;
    }

}
