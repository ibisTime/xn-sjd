package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.TeamMemberApply;

/**
 * 战队成员申请表
 * @author: silver 
 * @since: 2018年8月21日 下午7:01:27 
 * @history:
 */
public interface ITeamMemberApplyBO extends IPaginableBO<TeamMemberApply> {

    public boolean isTeamMemberApplyExist(String code);

    // 人员是否已申请加入战队
    public boolean isTeamMemberApplyExist(String teamCode, String applyUser,
            List<String> statusList);

    // 战队成员申请
    public String saveTeamMemberApply(String teamCode, String applyUser);

    // 申请审核
    public void refreshApproveTeamMemberApply(String code, String status,
            String approver, String remark);

    // 用户加入的其他战队
    public TeamMemberApply getJoinedTeam(String userId);

    public List<TeamMemberApply> queryTeamMemberApplyList(
            TeamMemberApply condition);

    public TeamMemberApply getTeamMemberApply(String code);

}
