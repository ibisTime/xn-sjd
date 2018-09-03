package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IEthMAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.EthClient;
import com.ogc.standard.dao.IEthMAddressDAO;
import com.ogc.standard.domain.EthMAddress;
import com.ogc.standard.domain.EthXAddress;
import com.ogc.standard.enums.EMAddressStatus;

@Component
public class EthMAddressBOImpl extends PaginableBOImpl<EthMAddress>
        implements IEthMAddressBO {

    @Autowired
    private IEthMAddressDAO ethMAddressDAO;

    @Override
    public String generate() {

        EthXAddress newAddress = EthClient.newAccount();

        EthMAddress data = new EthMAddress();
        data.setAddress(newAddress.getAddress());
        data.setKeystorePwd(newAddress.getKeystorePwd());
        data.setKeystoreName(newAddress.getKeystoreName());
        data.setKeystoreContent(newAddress.getKeystoreContent());
        data.setStatus(EMAddressStatus.VALID.getCode());
        data.setCreateDatetime(new Date());
        ethMAddressDAO.insert(data);

        return newAddress.getAddress();

    }

    @Override
    public int abandon(EthMAddress ethMAddress) {
        int count = 0;
        if (ethMAddress != null) {
            Date now = new Date();
            ethMAddress.setStatus(EMAddressStatus.INVALID.getCode());
            ethMAddress.setAbandonDatetime(now);
            ethMAddressDAO.updateAbandon(ethMAddress);
        }
        return count;
    }

    @Override
    public EthMAddress getEthMAddress(Long id) {
        EthMAddress data = null;

        if (id != null) {
            EthMAddress condition = new EthMAddress();
            condition.setId(id);
            data = ethMAddressDAO.select(condition);
        }

        return data;
    }

    @Override
    public EthMAddress getEthMAddressSecret(Long id) {
        EthMAddress data = null;

        if (id != null) {
            EthMAddress condition = new EthMAddress();
            condition.setId(id);
            data = ethMAddressDAO.selectSecret(condition);
        }

        return data;
    }

    @Override
    public EthMAddress getEthMAddressByAddress(String address) {
        EthMAddress data = null;
        EthMAddress condition = new EthMAddress();
        condition.setAddress(address);
        List<EthMAddress> results = ethMAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

}
