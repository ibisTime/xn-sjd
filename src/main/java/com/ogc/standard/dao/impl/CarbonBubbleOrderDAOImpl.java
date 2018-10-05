package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICarbonBubbleOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CarbonBubbleOrder;

@Repository("carbonBubbleOrderDAOImpl")
public class CarbonBubbleOrderDAOImpl extends AMybatisTemplate implements
        ICarbonBubbleOrderDAO {

    @Override
    public int insert(CarbonBubbleOrder data) {
        return super.insert(NAMESPACE.concat("insert_carbonBubbleOrder"), data);
    }

    @Override
    public int delete(CarbonBubbleOrder data) {
        return 0;
    }

    @Override
    public CarbonBubbleOrder select(CarbonBubbleOrder condition) {
        return super.select(NAMESPACE.concat("select_carbonBubbleOrder"),
            condition, CarbonBubbleOrder.class);
    }

    @Override
    public long selectTotalCount(CarbonBubbleOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_carbonBubbleOrder_count"), condition);
    }

    @Override
    public List<CarbonBubbleOrder> selectList(CarbonBubbleOrder condition) {
        return super.selectList(NAMESPACE.concat("select_carbonBubbleOrder"),
            condition, CarbonBubbleOrder.class);
    }

    @Override
    public List<CarbonBubbleOrder> selectList(CarbonBubbleOrder condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_carbonBubbleOrder"),
            start, count, condition, CarbonBubbleOrder.class);
    }

    @Override
    public void updateTakeCarbonBubble(CarbonBubbleOrder data) {
        super.update(NAMESPACE.concat("update_takeCarbonBubble"), data);
    }

}
