package com.ogc.standard.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICollectDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Collect;
import com.ogc.standard.domain.EthWAddress;

@Repository("collectDAOImpl")
public class CollectDAOImpl extends AMybatisTemplate implements ICollectDAO {

    @Override
    public int insert(Collect data) {
        return super.insert(NAMESPACE.concat("insert_collect"), data);
    }

    @Override
    public int delete(Collect data) {
        return super.delete(NAMESPACE.concat("delete_collect"), data);
    }

    @Override
    public Collect select(Collect condition) {
        return super.select(NAMESPACE.concat("select_collect"), condition,
            Collect.class);
    }

    @Override
    public long selectTotalCount(Collect condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_collect_count"),
            condition);
    }

    @Override
    public List<Collect> selectList(Collect condition) {
        return super.selectList(NAMESPACE.concat("select_collect"), condition,
            Collect.class);
    }

    @Override
    public List<Collect> selectList(Collect condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_collect"), start,
            count, condition, Collect.class);
    }

    @Override
    public int updateNoticeETH(Collect data) {
        return super.update(NAMESPACE.concat("update_notice_eth"), data);
    }

    @Override
    public int updateNoticeWAN(Collect data) {
        return super.update(NAMESPACE.concat("update_notice_wan"), data);
    }

    @Override
    public int updateNoticeSC(Collect data) {
        return super.update(NAMESPACE.concat("update_notice_sc"), data);
    }

    @Override
    public int updateNoticeBTC(Collect data) {
        return super.update(NAMESPACE.concat("update_notice_btc"), data);
    }

    @Override
    public EthWAddress selectAddressUseInfo(Collect data) {
        return super.select(NAMESPACE.concat("select_addressUseInfo"), data,
            EthWAddress.class);
    }

    @Override
    public BigDecimal selectTotalCollect(Collect data) {
        return super.select(NAMESPACE.concat("select_totalCollect"), data,
            BigDecimal.class);
    }

}
