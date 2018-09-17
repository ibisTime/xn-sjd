package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressBO extends IPaginableBO<EthXAddress> {

    // 生成地址（有私钥）
    public String generateAddress(String userId);

    public EthXAddress getEthXAddressByAddress(String address);

    public EthXAddress getEthXAddressByUserId(String userId);

    public EthXAddress getEthXAddressSecret(Long id);

    // 获取符和归集的地址
    public List<EthXAddress> queryNeedCollectAddressPage(
            BigDecimal balanceStart, String symbol, int start, int limit);
}
