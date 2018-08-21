package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.TeamMemberApply;

/**
 * 战队成员申请表
 * @author: silver 
 * @since: 2018年8月21日 下午6:59:16 
 * @history:
 */
public interface ITeamMemberApplyDAO extends IBaseDAO<TeamMemberApply> {
    String NAMESPACE = ITeamMemberApplyDAO.class.getName().concat(".");

    // 审核
    public int updateApproveApply(TeamMemberApply data);
}
