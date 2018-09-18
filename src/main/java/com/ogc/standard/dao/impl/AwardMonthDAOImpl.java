/**
 * @Title AwardMonthDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午1:33:49 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAwardMonthDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AwardMonth;

/** 
 * @author: taojian 
 * @since: 2018年9月17日 下午1:33:49 
 * @history:
 */
@Repository("awardMonthDAOImpl")
public class AwardMonthDAOImpl extends AMybatisTemplate
        implements IAwardMonthDAO {

    @Override
    public int insert(AwardMonth data) {
        return super.insert(NAMESPACE.concat("insert_award_month"), data);
    }

    @Override
    public int delete(AwardMonth data) {
        return 0;
    }

    @Override
    public AwardMonth select(AwardMonth condition) {
        return super.select(NAMESPACE.concat("select_award_month"), condition,
            AwardMonth.class);
    }

    @Override
    public long selectTotalCount(AwardMonth condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_award_month_count"), condition);
    }

    @Override
    public List<AwardMonth> selectList(AwardMonth condition) {
        return super.selectList(NAMESPACE.concat("select_award_month"),
            condition, AwardMonth.class);
    }

    @Override
    public List<AwardMonth> selectList(AwardMonth condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_award_month"), start,
            count, condition, AwardMonth.class);
    }

    @Override
    public int updateUnsettle(AwardMonth data) {
        return super.update(NAMESPACE.concat("update_award_unsettle"), data);
    }

    @Override
    public int updateSettle(AwardMonth data) {
        return super.update(NAMESPACE.concat("update_award_settle"), data);
    }

}
