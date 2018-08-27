package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;

import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.EthClient;
import com.ogc.standard.dao.IEthXAddressDAO;
import com.ogc.standard.domain.EthXAddress;

@Component
public class EthXAddressBOImpl extends PaginableBOImpl<EthXAddress>
        implements IEthXAddressBO {

    private static Logger logger = Logger.getLogger(EthXAddressBOImpl.class);

    private static Admin admin = EthClient.getClient();

    @Autowired
    private IEthXAddressDAO ethXAddressDAO;

    @Override
    public String generateAddress(String userId) {

        String address = null;

        EthXAddress dbAddress = getEthXAddressByUserId(userId);
        if (dbAddress != null) {
            address = dbAddress.getAddress();
        } else {

            EthXAddress newAddress = EthClient.newAccount();

            EthXAddress data = new EthXAddress();
            data.setAddress(newAddress.getAddress());
            data.setKeystorePwd(newAddress.getKeystorePwd());
            data.setKeystoreName(newAddress.getKeystoreName());
            data.setKeystoreContent(newAddress.getKeystoreContent());
            data.setUserId(userId);
            data.setCreateDatetime(new Date());
            ethXAddressDAO.insert(data);

            address = newAddress.getAddress();

        }

        return address;
    }

    //
    // @Override
    // public List<EthXAddress> queryEthXAddressList(EthXAddress condition) {
    // return ethXAddressDAO.selectList(condition);
    // }
    //
    // @Override
    // public EthXAddress getWEthXAddressToday() {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(EAddressType.W.getCode());
    // condition.setStatus(EPersonalAddressStatus.NORMAL.getCode());
    // condition.setOrder("create_datetime", "desc");
    // List<EthXAddress> wList = ethXAddressDAO.selectList(condition);
    // if (CollectionUtils.isEmpty(wList)) {
    // throw new BizException("xn625000", "未找到可用的归集地址");
    // }
    // return wList.get(0);
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(String code) {
    // EthXAddress data = null;
    // if (StringUtils.isNotBlank(code)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setCode(code);
    // data = ethXAddressDAO.select(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "以太坊地址不存在");
    // }
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressSecret(String code) {
    // EthXAddress data = null;
    // if (StringUtils.isNotBlank(code)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setCode(code);
    // data = ethXAddressDAO.selectSecret(condition);
    // if (data == null) {
    // throw new BizException("xn0000", "以太坊地址不存在");
    // }
    // }
    // return data;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(EAddressType type, String address) {
    // EthXAddress data = null;
    // EthXAddress condition = new EthXAddress();
    // condition.setType(type.getCode());
    // condition.setAddress(address);
    // List<EthXAddress> results = ethXAddressDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // data = results.get(0);
    // }
    // return data;
    // }

    @Override
    public EthXAddress getEthXAddressByUserId(String userId) {
        EthXAddress data = null;
        EthXAddress condition = new EthXAddress();
        condition.setUserId(userId);
        List<EthXAddress> results = ethXAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    // @Override
    // public EthXAddress getEthXAddressByAccountNumber(String accountNumber) {
    // EthXAddress data = null;
    // EthXAddress condition = new EthXAddress();
    // condition.setAccountNumber(accountNumber);
    // List<EthXAddress> results = ethXAddressDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(results)) {
    // data = results.get(0);
    // }
    // return data;
    // }
    //
    // @Override
    // public BigDecimal getEthBalance(String address) {
    // try {
    // DefaultBlockParameter defaultBlockParameter =
    // DefaultBlockParameterName.LATEST;
    // EthGetBalance ethGetBalance = admin
    // .ethGetBalance(address, defaultBlockParameter).send();
    // if (ethGetBalance != null) {
    // return new BigDecimal(ethGetBalance.getBalance().toString());
    // } else {
    // throw new BizException("xn625000", "以太坊余额查询失败");
    // }
    // } catch (Exception e) {
    // throw new BizException("xn625000",
    // "以太坊余额查询异常，原因：" + e.getMessage());
    // }
    // }
    //
    // @Override
    // public int abandonAddress(EthXAddress ethAddress) {
    // int count = 0;
    // if (ethAddress != null) {
    // Date now = new Date();
    // ethAddress.setStatus(EWAddressStatus.INVALID.getCode());
    // ethAddress.setAbandonDatetime(now);
    // ethAddress.setUpdateDatetime(now);
    // ethXAddressDAO.updateAbandon(ethAddress);
    // }
    // return count;
    // }
    //
    // @Override
    // public int refreshBalance(EthXAddress address) {
    // int count = 0;
    // if (address != null) {
    // address.setBalance(getEthBalance(address.getAddress()));
    // address.setUpdateDatetime(new Date());
    // ethXAddressDAO.updateBalance(address);
    // }
    // return count;
    // }
    //
    // @Override
    // public int refreshStatus(EthXAddress address, String status) {
    // int count = 0;
    // if (address != null) {
    // address.setStatus(status);
    // address.setUpdateDatetime(new Date());
    // ethXAddressDAO.updateStatus(address);
    // }
    // return count;
    // }
    //
    // @Override
    // public boolean isEthXAddressExist(String address) {
    // boolean flag = false;
    // if (StringUtils.isNotBlank(address)) {
    // EthXAddress condition = new EthXAddress();
    // condition.setAddress(address);
    // if (ethXAddressDAO.selectTotalCount(condition) > 0) {
    // flag = true;
    // }
    // }
    // return flag;
    // }
    //
    // @Override
    // public BigDecimal getTotalBalance(EAddressType type) {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(type.getCode());
    // return ethXAddressDAO.selectTotalBalance(condition);
    // }
    //
    // @Override
    // public List<EthXAddress> queryManualCollectAddressPage(
    // BigDecimal balanceStart, int start, int limit) {
    // EthXAddress condition = new EthXAddress();
    // condition.setType(EAddressType.X.getCode());
    // condition.setBalanceStart(balanceStart);
    // return ethXAddressDAO.selectList(condition, start, limit);
    // }
    //
    // @Override
    // public String generateAddress(String userId, String accountNumber) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public String saveEthXAddress(String userId, String accountNumber,
    // String address, String password, BigDecimal balance, String status,
    // String keystoreName, String keystoreContent) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getWEthXAddressToday() {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public int refreshBalance(EthXAddress address) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public int refreshStatus(EthXAddress address, String status) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByAddress(String address) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByUserId(String userId) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressByAccountNumber(String accountNumber) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public boolean isEthXAddressExist(String address) {
    // // TODO Auto-generated method stub
    // return false;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddressSecret(String code) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public List<EthXAddress> queryEthXAddressList(EthXAddress condition) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public EthXAddress getEthXAddress(String code) {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public int abandonAddress(EthXAddress ethAddress) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public BigDecimal getTotalBalance() {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
