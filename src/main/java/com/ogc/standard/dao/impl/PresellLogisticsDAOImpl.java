package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPresellLogisticsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PresellLogistics;

@Repository("presellLogisticsDAOImpl")
public class PresellLogisticsDAOImpl extends AMybatisTemplate
        implements IPresellLogisticsDAO {

    @Override
    public int insert(PresellLogistics data) {
        return super.insert(NAMESPACE.concat("insert_presellLogistics"), data);
    }

    @Override
    public int delete(PresellLogistics data) {
        return super.delete(NAMESPACE.concat("delete_presellLogistics"), data);
    }

    @Override
    public int updateSendLogistisc(PresellLogistics data) {
        return super.update(NAMESPACE.concat("update_sendLogistics"), data);
    }

    @Override
    public int updateRceiveLogistisc(PresellLogistics data) {
        return super.update(NAMESPACE.concat("update_receiveLogistics"), data);
    }

    @Override
    public PresellLogistics select(PresellLogistics condition) {
        return super.select(NAMESPACE.concat("select_presellLogistics"),
            condition, PresellLogistics.class);
    }

    @Override
    public long selectTotalCount(PresellLogistics condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_presellLogistics_count"), condition);
    }

    @Override
    public List<PresellLogistics> selectList(PresellLogistics condition) {
        return super.selectList(NAMESPACE.concat("select_presellLogistics"),
            condition, PresellLogistics.class);
    }

    @Override
    public List<PresellLogistics> selectList(PresellLogistics condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_presellLogistics"),
            start, count, condition, PresellLogistics.class);
    }

}
