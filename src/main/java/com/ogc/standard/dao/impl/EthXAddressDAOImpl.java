package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IEthXAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.EthXAddress;

@Repository("ethXAddressDAOImpl")
public class EthXAddressDAOImpl extends AMybatisTemplate implements
        IEthXAddressDAO {

    @Override
    public int insert(EthXAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethXAddress"), data);
    }

    @Override
    public int delete(EthXAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethXAddress"), data);
    }

    @Override
    public EthXAddress select(EthXAddress condition) {
        return super.select(NAMESPACE.concat("select_ethXAddress"), condition,
            EthXAddress.class);
    }

    @Override
    public long selectTotalCount(EthXAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethXAddress_count"), condition);
    }

    @Override
    public List<EthXAddress> selectList(EthXAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethXAddress"),
            condition, EthXAddress.class);
    }

    @Override
    public List<EthXAddress> selectList(EthXAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethXAddress"), start,
            count, condition, EthXAddress.class);
    }

    @Override
    public EthXAddress selectSecret(EthXAddress condition) {
        return super.select(NAMESPACE.concat("select_ethXAddress_secret"),
            condition, EthXAddress.class);
    }

    @Override
    public List<EthXAddress> selectNeedCollectList(EthXAddress condition,
            int start, int limit) {
        return super.selectList(NAMESPACE.concat("select_need_collect_list"),
            start, limit, condition, EthXAddress.class);
    }

}
