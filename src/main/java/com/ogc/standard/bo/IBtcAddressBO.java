package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BtcXAddress;

public interface IBtcAddressBO extends IPaginableBO<BtcXAddress> {

    // 生成地址（有私钥）
    public String generateAddress(String userId);

    // 导入（保存）地址
    public int saveBtcAddress(String address, String privatekey, String userId);

    // 取用户的btc地址
    public BtcXAddress getBtcXAddressByUserId(String userId);

//    // 获取今日归集地址
//    public BtcXAddress getWBtcAddressToday();
//
//    // 更新状态
//    public int refreshStatus(BtcXAddress address, String status);
//
//    public BtcXAddress getBtcAddress(EAddressType type, String address);
//
//    public BtcXAddress getBtcAddressByUserId(String userId);
//
//    public BtcXAddress getBtcAddressByAccountNumber(String accountNumber);
//
//    public boolean isBtcAddressExist(String address);
//
    public List<BtcXAddress> queryBtcAddressList(BtcXAddress condition);

//    public BtcXAddress getBtcAddress(String code);
//
//    public int abandonAddress(BtcXAddress adddress, String updater,
//            String remark);

}
