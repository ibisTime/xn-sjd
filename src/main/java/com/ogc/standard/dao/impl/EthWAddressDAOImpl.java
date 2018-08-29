package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IEthWAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.EthWAddress;

@Repository("ethWAddressDAOImpl")
public class EthWAddressDAOImpl extends AMybatisTemplate
        implements IEthWAddressDAO {

    @Override
    public int insert(EthWAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethWAddress"), data);
    }

    @Override
    public int delete(EthWAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethWAddress"), data);
    }

    @Override
    public EthWAddress select(EthWAddress condition) {
        return super.select(NAMESPACE.concat("select_ethWAddress"), condition,
            EthWAddress.class);
    }

    @Override
    public long selectTotalCount(EthWAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethWAddress_count"), condition);
    }

    @Override
    public List<EthWAddress> selectList(EthWAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethWAddress"),
            condition, EthWAddress.class);
    }

    @Override
    public List<EthWAddress> selectList(EthWAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethWAddress"), start,
            count, condition, EthWAddress.class);
    }

    @Override
    public int updateAbandon(EthWAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

}
