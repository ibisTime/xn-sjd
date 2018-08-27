/**
 * @Title EthXAddressAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:43:34 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IEthXAddressAO;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:43:34 
 * @history:
 */
@Service
public class EthXAddressAOImpl implements IEthXAddressAO {
    //
    // @Autowired
    // private IEthXAddressBO ethAddressBO;
    //
    // @Autowired
    // private ICollectBO collectionBO;
    //
    // @Autowired
    // private IWithdrawBO withdrawBO;
    //
    // @Autowired
    // private ICtqBO ctqBO;
    //
    // @Override
    // public void addEthXAddress(String address, String label, String userId,
    // String smsCaptcha, String isCerti, String tradePwd,
    // String googleCaptcha) {
    //
    // // 地址有效性校验
    // if (!WalletUtils.isValidAddress(address)) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "地址" + address + "不符合以太坊规则，请仔细核对");
    // }
    //
    // List<String> typeList = new ArrayList<String>();
    // typeList.add(EAddressType.X.getCode());
    // typeList.add(EAddressType.M.getCode());
    // typeList.add(EAddressType.W.getCode());
    //
    // EthXAddress condition = new EthXAddress();
    // condition.setAddress(address);
    // condition.setTypeList(typeList);
    //
    // if (ethAddressBO.getTotalCount(condition) > 0) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "提现地址已经在本平台被使用，请仔细核对！");
    // }
    //
    // String status = EPersonalAddressStatus.NORMAL.getCode();
    // // 是否设置为认证账户
    // if (EBoolean.YES.getCode().equals(isCerti)) {
    // status = EPersonalAddressStatus.CERTI.getCode();
    // }
    //
    // ethAddressBO.saveEthXAddress(EAddressType.Y, userId, address, label,
    // null, BigDecimal.ZERO, status, null, null);
    //
    // }
    //
    // @Override
    // public void abandonAddress(String code) {
    // EthXAddress ethAddress = ethAddressBO.getEthXAddress(code);
    // if (EWAddressStatus.INVALID.getCode().equals(ethAddress.getStatus())) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "地址已失效，无需重复弃用");
    // }
    // ethAddressBO.abandonAddress(ethAddress);
    // }
    //
    // @Override
    // public EAddressType getType(String address) {
    // EAddressType type = EAddressType.Y;
    // EthXAddress condition = new EthXAddress();
    // condition.setAddress(address);
    // List<EthXAddress> results = ethAddressBO
    // .queryEthXAddressList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // EthXAddress ethAddress = results.get(0);
    // type = EAddressType.getAddressType(ethAddress.getType());
    // }
    // return type;
    // }
    //
    // @Override
    // public String generateMAddress() {
    // String address = ethAddressBO.generateAddress(EAddressType.M,
    // ESysUser.SYS_USER.getCode(),
    // ESystemAccount.SYS_ACOUNT_ETH.getCode());
    // // 通知橙提取
    // ctqBO.uploadEthXAddress(address, EAddressType.M.getCode());
    // return address;
    // }
    //
    // @Override
    // public String importWAddress(String address) {
    // if (ethAddressBO.isEthXAddressExist(address)) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "地址" + address + "已经在平台内被使用，请仔细核对");
    // }
    // // 地址有效性校验
    // if (!WalletUtils.isValidAddress(address)) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "地址" + address + "不符合以太坊规则，请仔细核对");
    // }
    // return ethAddressBO.saveEthXAddress(EAddressType.W,
    // ESysUser.SYS_USER_COLD.getCode(),
    // ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode(), address, null,
    // BigDecimal.ZERO, EWAddressStatus.NORMAL.getCode(), null, null);
    // }
    //
    // @Override
    // @Transactional
    // public Paginable<EthXAddress> queryEthXAddressPage(int start, int limit,
    // EthXAddress condition) {
    // Paginable<EthXAddress> results = ethAddressBO.getPaginable(start, limit,
    // condition);
    // for (EthXAddress ethAddress : results.getList()) {
    // // 归集地址统计
    // if (EAddressType.W.getCode().equals(ethAddress.getType())) {
    // EthXAddress xAddress = collectionBO.getAddressUseInfo(
    // ethAddress.getAddress(), EOriginialCoin.ETH.getCode());
    // ethAddress.setUseCount(xAddress.getUseCount());
    // ethAddress.setUseAmount(xAddress.getUseAmount());
    // }
    // // 散取地址统计
    // if (EAddressType.M.getCode().equals(ethAddress.getType())) {
    // EthXAddress xAddress = withdrawBO.getAddressUseInfo(
    // ethAddress.getAddress(), EOriginialCoin.ETH.getCode());
    // ethAddress.setUseCount(xAddress.getUseCount());
    // ethAddress.setUseAmount(xAddress.getUseAmount());
    // }
    // }
    // return results;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(String code) {
    // return ethAddressBO.getEthXAddress(code);
    // }
    //
    // @Override
    // public BigDecimal getTotalBalance(EAddressType type) {
    // return ethAddressBO.getTotalBalance(type);
    // }

}
