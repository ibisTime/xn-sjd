package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IEthWAddressBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IEthWAddressDAO;
import com.ogc.standard.domain.EthWAddress;
import com.ogc.standard.enums.EWAddressStatus;

@Component
public class EthWAddressBOImpl extends PaginableBOImpl<EthWAddress>
        implements IEthWAddressBO {

    @Autowired
    private IEthWAddressDAO ethWAddressDAO;

    @Override
    public void saveEthWAddress(String address) {

        EthWAddress data = new EthWAddress();
        data.setAddress(address);
        data.setStatus(EWAddressStatus.VALID.getCode());
        data.setCreateDatetime(new Date());
        ethWAddressDAO.insert(data);

    }

    @Override
    public EthWAddress getEthWAddressByAddress(String address) {
        EthWAddress data = null;
        EthWAddress condition = new EthWAddress();
        condition.setAddress(address);
        List<EthWAddress> results = ethWAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public int abandon(EthWAddress ethWAddress) {
        int count = 0;
        if (ethWAddress != null) {
            Date now = new Date();
            ethWAddress.setStatus(EWAddressStatus.INVALID.getCode());
            ethWAddress.setAbandonDatetime(now);
            ethWAddressDAO.updateAbandon(ethWAddress);
        }
        return count;
    }

    @Override
    public EthWAddress getEthWAddress(Long id) {
        EthWAddress data = null;

        if (id != null) {
            EthWAddress condition = new EthWAddress();
            condition.setId(id);
            data = ethWAddressDAO.select(condition);
        }

        return data;
    }

}
