package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.ICoinDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.Coin;

@Repository("coinDAOImpl")
public class CoinDAOImpl extends AMybatisTemplate implements ICoinDAO {

    @Override
    public int insert(Coin data) {
        return super.insert(NAMESPACE.concat("insert_coin"), data);
    }

    @Override
    public int delete(Coin data) {
        return super.delete(NAMESPACE.concat("delete_coin"), data);
    }

    @Override
    public Coin select(Coin condition) {
        return super.select(NAMESPACE.concat("select_coin"), condition,
            Coin.class);
    }

    @Override
    public long selectTotalCount(Coin condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_coin_count"),
            condition);
    }

    @Override
    public List<Coin> selectList(Coin condition) {
        return super.selectList(NAMESPACE.concat("select_coin_list"), condition,
            Coin.class);
    }

    @Override
    public List<Coin> selectList(Coin condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_coin"), start, count,
            condition, Coin.class);
    }

    @Override
    public int update(Coin data) {
        return super.update(NAMESPACE.concat("update_coin"), data);
    }

    @Override
    public int updateStatus(Coin data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
