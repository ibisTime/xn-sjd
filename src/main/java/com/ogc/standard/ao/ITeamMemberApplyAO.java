package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.TeamMemberApply;

/**
 * @author: silver 
 * @since: 2018年8月21日 下午7:11:41 
 * @history:
 */
public interface ITeamMemberApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 加入战队申请
    public String teamMemberApply(String teamCode, String applyUser);

    // 审核申请
    public void approveTeamMemberApply(String code, String approveResult,
            String approver, String remark);

    public Paginable<TeamMemberApply> queryTeamMemberApplyPage(int start,
            int limit, TeamMemberApply condition);

    public List<TeamMemberApply> queryTeamMemberApplyList(
            TeamMemberApply condition);

    public TeamMemberApply getTeamMemberApply(String code);

}
