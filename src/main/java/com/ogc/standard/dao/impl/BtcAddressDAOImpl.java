package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IBtcAddressDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.BtcXAddress;

@Repository("btcAddressDAOImpl")
public class BtcAddressDAOImpl extends AMybatisTemplate
        implements IBtcAddressDAO {

    @Override
    public int insert(BtcXAddress data) {
        return super.insert(NAMESPACE.concat("insert_btcAddress"), data);
    }

    @Override
    public int delete(BtcXAddress data) {
        return 0;
    }

    @Override
    public BtcXAddress select(BtcXAddress condition) {
        return super.select(NAMESPACE.concat("select_btcAddress"), condition,
            BtcXAddress.class);
    }

    @Override
    public long selectTotalCount(BtcXAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_btcAddress_count"), condition);
    }

    @Override
    public List<BtcXAddress> selectList(BtcXAddress condition) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"),
            condition, BtcXAddress.class);
    }

    @Override
    public List<BtcXAddress> selectList(BtcXAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"), start,
            count, condition, BtcXAddress.class);
    }

    @Override
    public int updateAbandon(BtcXAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

    @Override
    public int updateStatus(BtcXAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
