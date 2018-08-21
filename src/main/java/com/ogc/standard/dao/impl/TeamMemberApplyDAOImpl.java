package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ITeamMemberApplyDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.TeamMemberApply;

/**
 * 战队成员申请表
 * @author: silver 
 * @since: 2018年8月21日 下午7:00:21 
 * @history:
 */
@Repository("teamMemberApplyDAOImpl")
public class TeamMemberApplyDAOImpl extends AMybatisTemplate
        implements ITeamMemberApplyDAO {

    @Override
    public int insert(TeamMemberApply data) {
        return super.insert(NAMESPACE.concat("insert_teamMemberApply"), data);
    }

    @Override
    public int delete(TeamMemberApply data) {
        return super.delete(NAMESPACE.concat("delete_teamMemberApply"), data);
    }

    @Override
    public int updateApproveApply(TeamMemberApply data) {
        return super.update(NAMESPACE.concat("update_approveTeamMemberApply"),
            data);
    }

    @Override
    public TeamMemberApply select(TeamMemberApply condition) {
        return super.select(NAMESPACE.concat("select_teamMemberApply"),
            condition, TeamMemberApply.class);
    }

    @Override
    public long selectTotalCount(TeamMemberApply condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_teamMemberApply_count"), condition);
    }

    @Override
    public List<TeamMemberApply> selectList(TeamMemberApply condition) {
        return super.selectList(NAMESPACE.concat("select_teamMemberApply"),
            condition, TeamMemberApply.class);
    }

    @Override
    public List<TeamMemberApply> selectList(TeamMemberApply condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_teamMemberApply"),
            start, count, condition, TeamMemberApply.class);
    }

}
