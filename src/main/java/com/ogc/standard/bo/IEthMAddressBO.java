package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthMAddress;

public interface IEthMAddressBO extends IPaginableBO<EthMAddress> {

    // 生成地址（有私钥）
    public String generate();

    // 弃用地址
    public int abandon(EthMAddress ethMAddress);

    public EthMAddress getEthMAddress(Long id);

    public EthMAddress getEthMAddressSecret(Long id);

    public EthMAddress getEthMAddressByAddress(String address);

}
