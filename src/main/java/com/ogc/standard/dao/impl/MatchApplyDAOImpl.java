package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IMatchApplyDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.MatchApply;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:27:59 
 * @history:
 */
@Repository("matchApplyDAOImpl")
public class MatchApplyDAOImpl extends AMybatisTemplate
        implements IMatchApplyDAO {

    @Override
    public int insert(MatchApply data) {
        return super.insert(NAMESPACE.concat("insert_matchApply"), data);
    }

    @Override
    public int delete(MatchApply data) {
        return super.delete(NAMESPACE.concat("delete_matchApply"), data);
    }

    @Override
    public int updateApprove(MatchApply data) {
        return super.update(NAMESPACE.concat("update_approveMatchApply"), data);
    }

    @Override
    public MatchApply select(MatchApply condition) {
        return super.select(NAMESPACE.concat("select_matchApply"), condition,
            MatchApply.class);
    }

    @Override
    public long selectTotalCount(MatchApply condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_matchApply_count"), condition);
    }

    @Override
    public List<MatchApply> selectList(MatchApply condition) {
        return super.selectList(NAMESPACE.concat("select_matchApply"),
            condition, MatchApply.class);
    }

    @Override
    public List<MatchApply> selectList(MatchApply condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_matchApply"), start,
            count, condition, MatchApply.class);
    }

}
