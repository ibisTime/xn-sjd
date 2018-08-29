package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressBO extends IPaginableBO<EthXAddress> {

    // 生成地址（有私钥）
    public String generateAddress(String userId);

    public EthXAddress getEthXAddressByAddress(String address);

    public EthXAddress getEthXAddressByUserId(String userId);

}
