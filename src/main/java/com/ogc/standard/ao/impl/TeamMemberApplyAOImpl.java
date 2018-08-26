package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ITeamMemberApplyAO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Team;
import com.ogc.standard.domain.TeamMemberApply;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ETeamMemberApplyStatus;
import com.ogc.standard.enums.ETeamStatus;
import com.ogc.standard.exception.BizException;

/**
 * @author: silver 
 * @since: 2018年8月21日 下午7:13:58 
 * @history:
 */
@Service
public class TeamMemberApplyAOImpl implements ITeamMemberApplyAO {

    @Autowired
    private ITeamMemberApplyBO teamMemberApplyBO;

    @Autowired
    private ITeamBO teamBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IGroupBO groupBO;

    @Override
    public String teamMemberApply(String teamCode, String applyUser) {
        Team team = teamBO.getTeam(teamCode);
        if (!ETeamStatus.TO_START.getCode().equals(team.getStatus())) {
            throw new BizException("xn000", "当前战队未处于可申请加入状态！");
        }

        // 判断是否已申请加入该战队
        List<String> statusList = new ArrayList<String>();
        statusList.add(ETeamMemberApplyStatus.TO_APPROVE.getCode());
        statusList.add(ETeamMemberApplyStatus.APPROVED_YES.getCode());
        if (teamMemberApplyBO.isTeamMemberApplyExist(teamCode, applyUser,
            statusList)) {
            throw new BizException("xn000", "当前用户已申请加入该战队，请勿重复申请！");
        }

        // 用户是否已加入其他战队
        if (null != teamMemberApplyBO.getJoinedTeam(applyUser)) {
            throw new BizException("xn000", "用户已加入其他战队！");
        }

        return teamMemberApplyBO.saveTeamMemberApply(teamCode, applyUser);
    }

    @Override
    @Transactional
    public void approveTeamMemberApply(String code, String approveResult,
            String approver, String remark) {
        TeamMemberApply teamMemberApply = teamMemberApplyBO
            .getTeamMemberApply(code);
        if (!ETeamMemberApplyStatus.TO_APPROVE.getCode().equals(
            teamMemberApply.getStatus())) {
            throw new BizException("xn000", "当前记录不处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ETeamMemberApplyStatus.APPROVED_YES.getCode();

            // 审核成功时更新战队成员数量
            Team team = teamBO.getTeam(teamMemberApply.getTeamCode());
            teamBO.refreshTeamMemberCount(team.getCode(),
                team.getMemberCount() + 1);

            // 添加组合
            // groupBO.saveGroup(team.getMatchCode(), team.getCode(),
            // teamMemberApply.getApplyUser(), new BigDecimal(0));
        } else {
            status = ETeamMemberApplyStatus.APPROVED_NO.getCode();
        }

        teamMemberApplyBO.refreshApproveTeamMemberApply(code, status, approver,
            remark);
    }

    @Override
    public Paginable<TeamMemberApply> queryTeamMemberApplyPage(int start,
            int limit, TeamMemberApply condition) {
        Paginable<TeamMemberApply> page = teamMemberApplyBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (TeamMemberApply teamMemberApply : page.getList()) {
                initTeamMemberApply(teamMemberApply);
            }
        }

        return page;
    }

    @Override
    public List<TeamMemberApply> queryTeamMemberApplyList(
            TeamMemberApply condition) {
        return teamMemberApplyBO.queryTeamMemberApplyList(condition);
    }

    @Override
    public TeamMemberApply getTeamMemberApply(String code) {
        TeamMemberApply teamMemberApply = teamMemberApplyBO
            .getTeamMemberApply(code);

        initTeamMemberApply(teamMemberApply);

        return teamMemberApply;
    }

    private void initTeamMemberApply(TeamMemberApply teamMemberApply) {
        // 战队信息
        Team team = teamBO.getTeam(teamMemberApply.getTeamCode());
        teamMemberApply.setTeam(team);

        // 申请人
        User applyUserInfo = userBO.getUser(teamMemberApply.getApplyUser());
        teamMemberApply.setApplyUserInfo(applyUserInfo);
    }
}
