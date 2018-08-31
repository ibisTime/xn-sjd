package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICollectAO;
import com.ogc.standard.bo.ICollectBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Collect;

@Service
public class CollectAOImpl implements ICollectAO {

    // private static final Logger logger = LoggerFactory
    // .getLogger(CollectAOImpl.class);
    //
    @Autowired
    private ICollectBO collectBO;

    // @Autowired
    // private IEthXAddressBO ethXAddressBO;
    //
    @Override
    public Paginable<Collect> queryCollectPage(int start, int limit,
            Collect condition) {
        return collectBO.getPaginable(start, limit, condition);
    }

    //
    @Override
    public Collect getCollect(String code) {
        return collectBO.getCollect(code);
    }
    //
    // @Override
    // public BigDecimal getTotalCollect(String currency) {
    // return collectionBO.getTotalCollect(currency);
    // }
    //
    // /**
    // * @see
    // com.ICollectAO.coin.wallet.ao.ICollectAO#collect(java.math.BigDecimal,
    // java.lang.String, java.lang.String)
    // */
    // @Override
    // public void collect(BigDecimal balanceStart, String currency,
    // String refNo) {
    // if (ECoinType.ETH.getCode().equals(currency)) {
    // doCollectManualETH(balanceStart);
    // }
    // }
    //
    // public static void main(String[] args) {
    // List<String> test = new ArrayList<String>();
    // build(test);
    // System.out.println(test);
    // }
    //
    // private static void build(List<String> test) {
    // test.add("aa");
    // test.add("bb");
    // }
    //
    // private void doCollectManualETH(BigDecimal balanceStart) {
    // int start = 0;
    // int limit = 10;
    // while (true) {
    // // 取出符合条件的地址列表
    // List<EthXAddress> ethXAddresseList = ethXAddressBO
    // .queryManualCollectAddressPage(balanceStart, start, limit);
    // if (CollectionUtils.isEmpty(ethXAddresseList)) {
    // break;
    // }
    // // 开始归集逻辑
    // for (EthXAddress ethAddress : ethXAddresseList) {
    // try {
    // collectionBO.doETHCollect(ethAddress, null);
    // } catch (Exception e) {
    // logger.info("地址" + ethAddress.getAddress() + "手动归集失败，原因："
    // + e.getMessage());
    // }
    // }
    // start++;
    // }
    //
    // }

}
