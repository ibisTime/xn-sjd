package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IBtcTransactionDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.BtcTransaction;

@Repository("btcTransactionDAOImpl")
public class BtcTransactionDAOImpl extends AMybatisTemplate
        implements IBtcTransactionDAO {

    @Override
    public int insert(BtcTransaction data) {
        return super.insert(NAMESPACE.concat("insert_btcTransaction"), data);
    }

    @Override
    public int delete(BtcTransaction data) {
        return 0;
    }

    @Override
    public BtcTransaction select(BtcTransaction condition) {
        return super.select(NAMESPACE.concat("select_btcTransaction"),
            condition, BtcTransaction.class);
    }

    @Override
    public long selectTotalCount(BtcTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_btcTransaction_count"), condition);
    }

    @Override
    public List<BtcTransaction> selectList(BtcTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_btcTransaction"),
            condition, BtcTransaction.class);
    }

    @Override
    public List<BtcTransaction> selectList(BtcTransaction condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_btcTransaction"),
            start, count, condition, BtcTransaction.class);
    }

}
