package com.ogc.standard.core;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;

import com.ogc.standard.common.PropertiesUtil;
import com.ogc.standard.enums.EBtcEnv;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午8:54:31 
 * @history:
 */
public class BtcClient {

    public static NetworkParameters getNetworkParameters() {
        NetworkParameters params = RegTestParams.get();
        if (EBtcEnv.PROD.getCode().equals(PropertiesUtil.Config.BTC_ENV)) {
            params = MainNetParams.get();
        }
        return params;
    }

    // 获取单个地址
    public static BtcAddressRes getSingleAddress() {
        NetworkParameters params = getNetworkParameters();
        ECKey key = new ECKey();
        String address = key.toAddress(params).toString();
        String privatekey = key.getPrivateKeyAsWiF(params);
        return new BtcAddressRes(address, privatekey);
    }

    // 地址格式校验，注意：正式环境和测试环境地址格式不一样
    public static boolean verifyAddress(String address) {
        NetworkParameters params = getNetworkParameters();
        try {
            Address.fromBase58(params, address);
            return true;
        } catch (AddressFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(getSingleAddress());
        System.out.println(verifyAddress("mpCH9Z8xiuK6s23GquDXLjSZMk3wmnu3AL"));
    }

}
