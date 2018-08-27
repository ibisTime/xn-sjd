package com.ogc.standard.bo.impl;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICollectBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.domain.Collect;

@Component
public class CollectBOImpl extends PaginableBOImpl<Collect>
        implements ICollectBO {

    // private static final Logger logger = LoggerFactory
    // .getLogger(CollectBOImpl.class);
    //
    // @Autowired
    // private ICollectDAO collectionDAO;
    //
    // @Autowired
    // private IEthXAddressBO ethXAddressBO;
    //
    // @Autowired
    // private IEthTransactionBO ethTransactionBO;
    //
    // @Override
    // public String saveCollect(EOriginialCoin coin, String from, String to,
    // BigDecimal value, String txHash, String refNo) {
    // String code = null;
    // Collect data = new Collect();
    // code = OrderNoGenerater.generate(EGeneratePrefix.Collect.getCode());
    // data.setCode(code);
    // data.setCurrency(coin.getCode());
    // data.setFromAddress(from);
    // data.setToAddress(to);
    // data.setAmount(value);
    // data.setTxHash(txHash);
    // data.setStatus(ECollectStatus.Broadcast.getCode());
    // data.setCreateDatetime(new Date());
    // data.setRefNo(refNo);
    // collectionDAO.insert(data);
    // return code;
    // }
    //
    // @Override
    // public List<Collect> queryCollectList(Collect condition) {
    // return collectionDAO.selectList(condition);
    // }
    //
    // @Override
    // public Collect getCollect(String code) {
    // Collect data = null;
    // if (StringUtils.isNotBlank(code)) {
    // Collect condition = new Collect();
    // condition.setCode(code);
    // data = collectionDAO.select(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "归集记录不存在");
    // }
    // }
    // return data;
    // }
    //
    // @Override
    // public Collect getCollectByTxHash(String txHash) {
    // Collect collection = null;
    // Collect condition = new Collect();
    // condition.setTxHash(txHash);
    // List<Collect> results = collectionDAO.selectList(condition);
    // if (CollectUtils.isNotEmpty(results)) {
    // collection = results.get(0);
    // }
    // return collection;
    // }
    //
    // @Override
    // public Collect getCollectByRefNo(String refNo) {
    // Collect ethCollect = null;
    // Collect condition = new Collect();
    // condition.setRefNo(refNo);
    // List<Collect> results = collectionDAO.selectList(condition);
    // if (CollectUtils.isNotEmpty(results)) {
    // ethCollect = results.get(0);
    // }
    // return ethCollect;
    // }
    //
    // @Override
    // public int colectionNoticeETH(Collect data, BigDecimal txfee,
    // Date ethDatetime) {
    // int count = 0;
    // data.setTxFee(txfee);
    // data.setStatus(ECollectStatus.Broadcast_YES.getCode());
    // data.setConfirmDatetime(ethDatetime);
    // data.setUpdateDatetime(new Date());
    // collectionDAO.updateNoticeETH(data);
    // return count;
    // }
    //
    // @Override
    // public int colectionNoticeBTC(Collect data, BigDecimal txfee,
    // Date confirmDatetime) {
    // int count = 0;
    // data.setTxFee(txfee);
    // data.setStatus(ECollectStatus.Broadcast_YES.getCode());
    // data.setConfirmDatetime(confirmDatetime);
    // data.setUpdateDatetime(new Date());
    // collectionDAO.updateNoticeBTC(data);
    // return count;
    // }
    //
    // @Override
    // public int colectionNoticeSC(Collect data, String fromAddress,
    // BigDecimal txfee, Date ethDatetime) {
    // int count = 0;
    // data.setTxFee(txfee);
    // data.setFromAddress(fromAddress);
    // data.setStatus(ECollectStatus.Broadcast_YES.getCode());
    // data.setConfirmDatetime(ethDatetime);
    // data.setUpdateDatetime(new Date());
    // collectionDAO.updateNoticeSC(data);
    // return count;
    // }
    //
    // @Override
    // public EthAddress getAddressUseInfo(String toAddress, String currency) {
    // Collect data = new Collect();
    // data.setToAddress(toAddress);
    // data.setCurrency(currency);
    // data.setStatus(ECollectStatus.Broadcast_YES.getCode());
    // return collectionDAO.selectAddressUseInfo(data);
    // }
    //
    // @Override
    // public BigDecimal getTotalCollect(String currency) {
    // Collect condition = new Collect();
    // condition.setCurrency(currency);
    // return collectionDAO.selectTotalCollect(condition);
    // }
    //
    // @Override
    // public void doETHCollect(EthAddress ethAddress, String chargeCode) {
    //
    // String fromAddress = ethAddress.getAddress();
    //
    // // 获取今日归集地址
    // EthAddress wEthAddress = ethAddressBO.getWEthAddressToday();
    // String toAddress = wEthAddress.getAddress();
    //
    // // 预估矿工费用
    // BigDecimal balance = ethAddress.getBalance();
    // BigDecimal gasPrice = ethTransactionBO.getGasPrice();
    // BigDecimal gasUse = new BigDecimal(21000);
    // BigDecimal txFee = gasPrice.multiply(gasUse);
    // BigDecimal value = balance.subtract(txFee);
    // logger.info("地址余额=" + balance + "，以太坊平均价格=" + gasPrice + "，预计矿工费="
    // + txFee + "，预计到账金额=" + value);
    // if (value.compareTo(BigDecimal.ZERO) <= 0) {
    // throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
    // }
    // // 归集广播
    // EthAddress secret = ethAddressBO
    // .getEthAddressSecret(ethAddress.getCode());
    // String txHash = ethTransactionBO.broadcast(fromAddress, secret,
    // toAddress, value);
    // if (StringUtils.isBlank(txHash)) {
    // throw new BizException("xn625000", "归集—交易广播失败");
    // }
    // // 归集记录落地
    // saveCollect(EOriginialCoin.ETH, fromAddress, toAddress, value, txHash,
    // chargeCode);
    // }
    //
    // @Override
    // public void doWanCollect(WanAddress wanAddress, String chargeCode) {
    //
    // String fromAddress = wanAddress.getAddress();
    //
    // // 获取今日归集地址
    // WanAddress wWanAddress = wanAddressBO.getWWanAddressToday();
    // String toAddress = wWanAddress.getAddress();
    //
    // // 预估矿工费用
    // BigDecimal balance = wanAddress.getBalance();
    // BigDecimal gasPrice = wanTransactionBO.getGasPrice();
    // BigDecimal gasUse = new BigDecimal(21000);
    // BigDecimal txFee = gasPrice.multiply(gasUse);
    // BigDecimal value = balance.subtract(txFee);
    // logger.info("地址余额=" + balance + "，万维链平均价格=" + gasPrice + "，预计矿工费="
    // + txFee + "，预计到账金额=" + value);
    // if (value.compareTo(BigDecimal.ZERO) <= 0) {
    // throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
    // }
    // // 归集广播
    // WanAddress secret = wanAddressBO
    // .getWanAddressSecret(wanAddress.getCode());
    // String txHash = wanTransactionBO.broadcast(fromAddress, secret,
    // toAddress, value);
    // if (StringUtils.isBlank(txHash)) {
    // throw new BizException("xn625000", "归集—交易广播失败");
    // }
    // // 归集记录落地
    // saveCollect(EOriginialCoin.WAN, fromAddress, toAddress, value, txHash,
    // chargeCode);
    // }
    //
    // @Override
    // public int colectionNoticeWAN(Collect data, BigDecimal txfee,
    // Date wanDatetime) {
    // int count = 0;
    // data.setTxFee(txfee);
    // data.setStatus(ECollectStatus.Broadcast_YES.getCode());
    // data.setConfirmDatetime(wanDatetime);
    // data.setUpdateDatetime(new Date());
    // collectionDAO.updateNoticeWAN(data);
    // return count;
    // }

}
