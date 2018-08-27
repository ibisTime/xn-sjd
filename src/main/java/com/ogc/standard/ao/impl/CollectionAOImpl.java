package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.ICollectionAO;
import com.cdkj.coin.wallet.bitcoin.BitcoinOfflineRawTxBuilder;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.OfflineTxInput;
import com.cdkj.coin.wallet.bitcoin.OfflineTxOutput;
import com.cdkj.coin.wallet.bitcoin.util.BtcBlockExplorer;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IWanAddressBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.AmountUtil;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBtcUtxoRefType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.wanchain.WanAddress;

@Service
public class CollectionAOImpl implements ICollectionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(CollectionAOImpl.class);

    @Autowired
    private ICollectionBO collectionBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IWanAddressBO wanAddressBO;

    @Autowired
    private BtcBlockExplorer btcBlockExplorer;

    @Autowired
    private IBtcUtxoBO btcUtxoBO;

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Override
    public Paginable<Collection> queryCollectionPage(int start, int limit,
            Collection condition) {
        return collectionBO.getPaginable(start, limit, condition);
    }

    @Override
    public Collection getCollection(String code) {
        return collectionBO.getCollection(code);
    }

    @Override
    public BigDecimal getTotalCollect(String currency) {
        return collectionBO.getTotalCollect(currency);
    }

    /** 
     * @see com.cdkj.coin.wallet.ao.ICollectionAO#collect(java.math.BigDecimal, java.lang.String, java.lang.String)
     */
    @Override
    public void collect(BigDecimal balanceStart, String currency,
            String refNo) {
        if (EOriginialCoin.ETH.getCode().equals(currency)) {
            doCollectionManualETH(balanceStart);
        } else if (EOriginialCoin.WAN.getCode().equals(currency)) {
            doCollectionManualWAN(balanceStart);
        } else if (EOriginialCoin.BTC.getCode().equals(currency)) {
            doCollectBTC(balanceStart, refNo);
        }
    }

    private void doCollectBTC(BigDecimal balanceStart, String refNo) {

        if (balanceStart.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn0000", "阀值必须大于等于0");
        }

        // 获取分发地址的UTXO总额，判断是否满足归集条件
        BigDecimal enableCount = btcUtxoBO
            .getTotalEnableUTXOCount(EAddressType.X);
        if (enableCount.compareTo(balanceStart) < 0) {
            throw new BizException("xn0000",
                "归集触发，UTXO总量" + AmountUtil.fromBtc(enableCount).toString()
                        + "，未达到归集阀值" + balanceStart + "，无需归集");
        }

        // 获取今日归集地址
        String toAddress = btcAddressBO.getWBtcAddressToday().getAddress();

        // 降序遍历可使用的M类地址UTXO，组装Input
        BitcoinOfflineRawTxBuilder rawTxBuilder = new BitcoinOfflineRawTxBuilder();

        List<BtcUtxo> inputBtcUtxoList = new ArrayList<BtcUtxo>();
        BigDecimal realAmount = buildRawTx(toAddress, rawTxBuilder,
            inputBtcUtxoList);

        // 广播
        broadcastCollect(refNo, toAddress, rawTxBuilder, inputBtcUtxoList,
            realAmount);

    }

    public static void main(String[] args) {
        List<String> test = new ArrayList<String>();
        build(test);
        System.out.println(test);
    }

    private static void build(List<String> test) {
        test.add("aa");
        test.add("bb");
    }

    /** 
     * @param refNo
     * @param toAddress
     * @param rawTxBuilder
     * @param inputBtcUtxoList
     * @param realAmount 
     * @create: 2018年2月24日 下午7:38:37 xieyj
     * @history: 
     */
    private void broadcastCollect(String refNo, String toAddress,
            BitcoinOfflineRawTxBuilder rawTxBuilder,
            List<BtcUtxo> inputBtcUtxoList, BigDecimal realAmount) {
        // 归集广播
        try {
            String signResult = rawTxBuilder.offlineSign();
            // 广播
            String trueTxid = btcBlockExplorer.broadcastRawTx(signResult);
            if (trueTxid != null) {

                // 归集记录落地
                String collectionCode = collectionBO.saveCollection(
                    EOriginialCoin.BTC, JsonUtil.Object2Json(inputBtcUtxoList),
                    toAddress, realAmount, trueTxid, refNo);
                if (CollectionUtils.isNotEmpty(inputBtcUtxoList)) {
                    for (BtcUtxo data : inputBtcUtxoList) {
                        btcUtxoBO.refreshBroadcast(data, EBtcUtxoStatus.USING,
                            EBtcUtxoRefType.COLLECTION, collectionCode);
                    }
                }

            } else {
                throw new BizException(EBizErrorCode.UTXO_COLLECTION_ERROR);
            }
        } catch (Exception e) {
            throw new BizException("-100", e.getMessage());
        }
    }

    private BigDecimal buildRawTx(String toAddress,
            BitcoinOfflineRawTxBuilder rawTxBuilder,
            List<BtcUtxo> inputBtcUtxoList) {

        BigDecimal shouldCollectCount = BigDecimal.ZERO;

        int start = 1;
        int limit = 100;
        while (true) {
            List<BtcUtxo> list = btcUtxoBO.queryEnableUtxoList(start, limit,
                EAddressType.X);
            if (CollectionUtils.isNotEmpty(list)) {
                for (BtcUtxo utxo : list) {
                    String txid = utxo.getTxid();
                    Integer vout = utxo.getVout();
                    // 应归集总额
                    shouldCollectCount = shouldCollectCount
                        .add(utxo.getCount());
                    BtcAddress btcAddress = btcAddressBO
                        .getBtcAddress(EAddressType.X, utxo.getAddress());
                    // 构造签名交易，输入
                    OfflineTxInput offlineTxInput = new OfflineTxInput(txid,
                        vout, utxo.getScriptPubKey(),
                        btcAddress.getPrivatekey());
                    rawTxBuilder.in(offlineTxInput);
                    inputBtcUtxoList.add(utxo);
                }
            } else {
                break;
            }
            start++;// 不够再遍历
        }
        // 组装Output，设置找零账户
        // 如何估算手续费，先预先给一个size,然后拿这个size进行签名
        // 对签名的数据进行解码，拿到真实大小，然后进行矿工费的修正
        int preSize = BitcoinOfflineRawTxBuilder
            .calculateSize(inputBtcUtxoList.size(), 1);
        int feePerByte = btcBlockExplorer.getFee();
        // 计算出手续费
        int preFee = preSize * feePerByte;

        // 构造输出，归集无需找零，只要算出矿工费，其余到转到归集地址
        BigDecimal realAmount = shouldCollectCount
            .subtract(BigDecimal.valueOf(preFee));
        OfflineTxOutput offlineTxOutput = new OfflineTxOutput(toAddress,
            AmountUtil.fromBtc(realAmount));
        rawTxBuilder.out(offlineTxOutput);

        logger.info("OTXO总额=" + shouldCollectCount + "，比特币平均费率=" + feePerByte
                + "，预计矿工费=" + preFee + "，预计到账金额=" + realAmount);
        return realAmount;
    }

    private void doCollectionManualETH(BigDecimal balanceStart) {
        int start = 0;
        int limit = 10;
        while (true) {
            // 取出符合条件的地址列表
            List<EthAddress> ethAddresseList = ethAddressBO
                .queryManualCollectionAddressPage(balanceStart, start, limit);
            if (CollectionUtils.isEmpty(ethAddresseList)) {
                break;
            }
            // 开始归集逻辑
            for (EthAddress ethAddress : ethAddresseList) {
                try {
                    collectionBO.doETHCollection(ethAddress, null);
                } catch (Exception e) {
                    logger.info("地址" + ethAddress.getAddress() + "手动归集失败，原因："
                            + e.getMessage());
                }
            }
            start++;
        }

    }

    private void doCollectionManualWAN(BigDecimal balanceStart) {
        int start = 0;
        int limit = 10;
        while (true) {
            // 取出符合条件的地址列表
            List<WanAddress> wanAddresseList = wanAddressBO
                .queryManualCollectionAddressPage(balanceStart, start, limit);
            if (CollectionUtils.isEmpty(wanAddresseList)) {
                break;
            }
            // 开始归集逻辑
            for (WanAddress wanAddress : wanAddresseList) {
                try {
                    collectionBO.doWanCollection(wanAddress, null);
                } catch (Exception e) {
                    logger.info("地址" + wanAddress.getAddress() + "手动归集失败，原因："
                            + e.getMessage());
                }
            }
            start++;
        }

    }

}
