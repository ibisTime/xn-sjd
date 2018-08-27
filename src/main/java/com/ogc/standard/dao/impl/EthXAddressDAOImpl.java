package com.ogc.standard.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IEthXAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.EthXAddress;

@Repository("ethAddressDAOImpl")
public class EthXAddressDAOImpl extends AMybatisTemplate
        implements IEthXAddressDAO {

    @Override
    public int insert(EthXAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethAddress"), data);
    }

    @Override
    public int delete(EthXAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethAddress"), data);
    }

    @Override
    public EthXAddress select(EthXAddress condition) {
        return super.select(NAMESPACE.concat("select_ethAddress"), condition,
            EthXAddress.class);
    }

    @Override
    public long selectTotalCount(EthXAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethAddress_count"), condition);
    }

    @Override
    public List<EthXAddress> selectList(EthXAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethAddress"),
            condition, EthXAddress.class);
    }

    @Override
    public List<EthXAddress> selectList(EthXAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethAddress"), start,
            count, condition, EthXAddress.class);
    }

    @Override
    public int updateAbandon(EthXAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

    @Override
    public int updateBalance(EthXAddress data) {
        return super.update(NAMESPACE.concat("update_balance"), data);
    }

    @Override
    public EthXAddress selectSecret(EthXAddress condition) {
        return super.select(NAMESPACE.concat("select_ethAddress_secret"),
            condition, EthXAddress.class);
    }

    @Override
    public BigDecimal selectTotalBalance(EthXAddress condition) {
        return super.select(NAMESPACE.concat("select_totalBalance"), condition,
            BigDecimal.class);
    }

    @Override
    public int updateStatus(EthXAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
