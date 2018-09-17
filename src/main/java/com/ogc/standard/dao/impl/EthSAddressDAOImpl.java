package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IEthSAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.EthSAddress;

@Repository("ethSAddressDAOImpl")
public class EthSAddressDAOImpl extends AMybatisTemplate implements
        IEthSAddressDAO {

    @Override
    public int insert(EthSAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethSAddress"), data);
    }

    @Override
    public int delete(EthSAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethSAddress"), data);
    }

    @Override
    public EthSAddress select(EthSAddress condition) {
        return super.select(NAMESPACE.concat("select_ethSAddress"), condition,
            EthSAddress.class);
    }

    @Override
    public long selectTotalCount(EthSAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethSAddress_count"), condition);
    }

    @Override
    public List<EthSAddress> selectList(EthSAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethSAddress"),
            condition, EthSAddress.class);
    }

    @Override
    public List<EthSAddress> selectList(EthSAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethSAddress"), start,
            count, condition, EthSAddress.class);
    }

    @Override
    public EthSAddress selectSecret(EthSAddress condition) {
        return super.select(NAMESPACE.concat("select_ethSAddress_secret"),
            condition, EthSAddress.class);
    }

    @Override
    public int updateStatus(EthSAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateAbandon(EthSAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

}
