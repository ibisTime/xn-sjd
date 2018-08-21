package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ITeamMemberApplyAO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Team;
import com.ogc.standard.domain.TeamMemberApply;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ETeamMemberApplyStatus;
import com.ogc.standard.enums.ETeamStatus;
import com.ogc.standard.exception.BizException;

/**
 * 战队成员申请表
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

    @Override
    public String addTeamMemberApply(String teamCode, String applyUser) {
        Team team = teamBO.getTeam(teamCode);
        if (!ETeamStatus.TO_START.getCode().equals(team.getStatus())) {
            throw new BizException("xn000", "当前战队不在可申请加入状态！");
        }

        List<String> statusList = new ArrayList<String>();
        statusList.add(ETeamMemberApplyStatus.TO_APPROVE.getCode());
        statusList.add(ETeamMemberApplyStatus.APPROVED_YES.getCode());
        if (teamMemberApplyBO.isTeamMemberApplyExist(teamCode, applyUser,
            statusList)) {
            throw new BizException("xn000", "当前用户已申请加入该战队，请勿重复申请！");
        }

        return teamMemberApplyBO.saveTeamMemberApply(teamCode, applyUser);
    }

    @Override
    @Transactional
    public void approveTeamMemberApply(String code, String approveResult,
            String approver, String remark) {
        if (!teamMemberApplyBO.isTeamMemberApplyExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        TeamMemberApply teamMemberApply = teamMemberApplyBO
            .getTeamMemberApply(code);
        if (!ETeamMemberApplyStatus.TO_APPROVE.getCode()
            .equals(teamMemberApply.getStatus())) {
            throw new BizException("xn000", "当前记录不处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ETeamMemberApplyStatus.APPROVED_YES.getCode();

            // 审核成功时更新战队成员数量
            Team team = teamBO.getTeam(teamMemberApply.getTeamCode());
            if (null != team) {
                teamBO.refreshTeamMemberCount(team.getCode(),
                    team.getMemberCount() + 1);
            }
        } else {
            status = ETeamMemberApplyStatus.APPROVED_NO.getCode();
        }

        teamMemberApplyBO.refreshApproveTeamMemberApply(code, status, approver,
            remark);
    }

    @Override
    public Paginable<TeamMemberApply> queryTeamMemberApplyPage(int start,
            int limit, TeamMemberApply condition) {
        return teamMemberApplyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<TeamMemberApply> queryTeamMemberApplyList(
            TeamMemberApply condition) {
        return teamMemberApplyBO.queryTeamMemberApplyList(condition);
    }

    @Override
    public TeamMemberApply getTeamMemberApply(String code) {
        return teamMemberApplyBO.getTeamMemberApply(code);
    }
}
