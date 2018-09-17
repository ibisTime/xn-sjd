package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthSAddress;

public interface IEthSAddressBO extends IPaginableBO<EthSAddress> {

    // 生成地址（有私钥）
    public String generate();

    // 弃用地址
    public int abandon(EthSAddress EthSAddress);

    public EthSAddress getEthSAddress(Long id);

    public EthSAddress getEthSAddressSecret(Long id);

    public EthSAddress getEthSAddressByAddress(String address);

    // 获取可使用的地址，不包括弃用的地址
    public EthSAddress getNormalEthSAddressByAddress(String address);

    // 获取可使用，不在广播中的地址
    public EthSAddress getEnableSEthAddress();

    public long getTotalCount(EthSAddress ethSAddress);

    public int refreshStatus(EthSAddress ethSAddress, String status);
}
