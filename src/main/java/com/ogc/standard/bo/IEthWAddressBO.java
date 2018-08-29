package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthWAddress;

public interface IEthWAddressBO extends IPaginableBO<EthWAddress> {

    // 导入（保存）地址
    public void saveEthWAddress(String address);

    public EthWAddress getEthWAddressByAddress(String address);

    // 弃用地址
    public int abandon(EthWAddress ethWAddress);

    public EthWAddress getEthWAddress(Long id);

}
