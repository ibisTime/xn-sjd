package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IEthMAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.EthMAddress;

@Repository("ethMAddressDAOImpl")
public class EthMAddressDAOImpl extends AMybatisTemplate
        implements IEthMAddressDAO {

    @Override
    public int insert(EthMAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethMAddress"), data);
    }

    @Override
    public int delete(EthMAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethMAddress"), data);
    }

    @Override
    public EthMAddress select(EthMAddress condition) {
        return super.select(NAMESPACE.concat("select_ethMAddress"), condition,
            EthMAddress.class);
    }

    @Override
    public long selectTotalCount(EthMAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethMAddress_count"), condition);
    }

    @Override
    public List<EthMAddress> selectList(EthMAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethMAddress"),
            condition, EthMAddress.class);
    }

    @Override
    public List<EthMAddress> selectList(EthMAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethMAddress"), start,
            count, condition, EthMAddress.class);
    }

    @Override
    public EthMAddress selectSecret(EthMAddress condition) {
        return super.select(NAMESPACE.concat("select_ethMAddress_secret"),
            condition, EthMAddress.class);
    }

    @Override
    public int updateStatus(EthMAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateAbandon(EthMAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

}
