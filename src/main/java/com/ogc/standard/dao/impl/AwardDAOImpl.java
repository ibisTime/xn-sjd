/**
 * @Title AwardDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午4:48:47 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAwardDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Award;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午4:48:47 
 * @history:
 */
@Repository("awardDAOImpl")
public class AwardDAOImpl extends AMybatisTemplate implements IAwardDAO {

    @Override
    public int insert(Award data) {
        return super.insert(NAMESPACE.concat("insert_award"), data);
    }

    @Override
    public int delete(Award data) {
        return 0;
    }

    @Override
    public Award select(Award condition) {
        return super.select(NAMESPACE.concat("select_award"), condition,
            Award.class);
    }

    @Override
    public long selectTotalCount(Award condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_award_count"),
            condition);
    }

    @Override
    public List<Award> selectList(Award condition) {
        return super.selectList(NAMESPACE.concat("select_award"), condition,
            Award.class);
    }

    @Override
    public List<Award> selectList(Award condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_award"), start, count,
            condition, Award.class);
    }

    @Override
    public int updateStauts(Award data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
