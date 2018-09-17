package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.EthClient;
import com.ogc.standard.dao.IEthXAddressDAO;
import com.ogc.standard.domain.EthXAddress;

@Component
public class EthXAddressBOImpl extends PaginableBOImpl<EthXAddress> implements
        IEthXAddressBO {

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

    @Override
    public EthXAddress getEthXAddressByAddress(String address) {
        EthXAddress data = null;
        EthXAddress condition = new EthXAddress();
        condition.setAddress(address);
        List<EthXAddress> results = ethXAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public List<EthXAddress> queryNeedCollectAddressPage(
            BigDecimal balanceStart, String symbol, int start, int limit) {
        EthXAddress condition = new EthXAddress();
        condition.setBalance(balanceStart);
        condition.setSymbol(symbol);
        return ethXAddressDAO.selectNeedCollectList(condition, start, limit);
    }

    @Override
    public EthXAddress getEthXAddressSecret(Long id) {
        EthXAddress data = null;

        if (id != null) {
            EthXAddress condition = new EthXAddress();
            condition.setId(id);
            data = ethXAddressDAO.selectSecret(condition);
        }

        return data;
    }

}
