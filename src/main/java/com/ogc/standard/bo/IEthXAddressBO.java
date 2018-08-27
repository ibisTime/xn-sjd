package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressBO extends IPaginableBO<EthXAddress> {

    // // 生成地址（有私钥）
    // public String generateAddress(String userId, String accountNumber);
    //
    // // 导入（保存）地址
    // public String saveEthXAddress(String userId, String accountNumber,
    // String address, String password, BigDecimal balance, String status,
    // String keystoreName, String keystoreContent);
    //
    // // 获取今日归集地址
    // public EthXAddress getWEthXAddressToday();
    //
    // // 查询以太坊地址余额
    // public BigDecimal getEthBalance(String address);
    //
    // // 更新余额
    // public int refreshBalance(EthXAddress address);
    //
    // // 更新状态
    // public int refreshStatus(EthXAddress address, String status);
    //
    // public EthXAddress getEthXAddressByAddress(String address);
    //
    // public EthXAddress getEthXAddressByUserId(String userId);
    //
    // public EthXAddress getEthXAddressByAccountNumber(String accountNumber);
    //
    // public boolean isEthXAddressExist(String address);
    //
    // public EthXAddress getEthXAddressSecret(String code);
    //
    // public List<EthXAddress> queryEthXAddressList(EthXAddress condition);
    //
    // public EthXAddress getEthXAddress(String code);
    //
    // public int abandonAddress(EthXAddress ethAddress);
    //
    // public BigDecimal getTotalBalance();
    //
    // public List<EthXAddress> queryManualCollectAddressPage(
    // BigDecimal balanceStart, int start, int limit);

}
