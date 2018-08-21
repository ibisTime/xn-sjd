package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ITeamMemberApplyDAO;
import com.ogc.standard.domain.TeamMemberApply;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ETeamMemberApplyStatus;
import com.ogc.standard.exception.BizException;

/**
 * 战队成员申请表
 * @author: silver 
 * @since: 2018年8月21日 下午7:03:34 
 * @history:
 */
@Component
public class TeamMemberApplyBOImpl extends PaginableBOImpl<TeamMemberApply>
        implements ITeamMemberApplyBO {

    @Autowired
    private ITeamMemberApplyDAO teamMemberApplyDAO;

    @Override
    public boolean isTeamMemberApplyExist(String code) {
        TeamMemberApply condition = new TeamMemberApply();
        condition.setCode(code);
        if (teamMemberApplyDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTeamMemberApplyExist(String teamCode, String applyUser,
            List<String> statusList) {
        TeamMemberApply condition = new TeamMemberApply();
        condition.setTeamCode(teamCode);
        condition.setApplyUser(applyUser);
        condition.setStatusList(statusList);

        if (teamMemberApplyDAO.selectTotalCount(condition) > 0) {
            return true;
        }

        return false;
    }

    @Override
    public String saveTeamMemberApply(String teamCode, String applyUser) {
        TeamMemberApply data = new TeamMemberApply();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TeamMemberApply.getCode());
        data.setCode(code);
        data.setTeamCode(teamCode);
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setStatus(ETeamMemberApplyStatus.TO_APPROVE.getCode());

        teamMemberApplyDAO.insert(data);
        return code;

    }

    @Override
    public void refreshApproveTeamMemberApply(String code, String status,
            String approver, String remark) {
        TeamMemberApply data = new TeamMemberApply();

        data.setCode(code);
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(remark);

        teamMemberApplyDAO.updateApproveApply(data);
    }

    @Override
    public List<TeamMemberApply> queryTeamMemberApplyList(
            TeamMemberApply condition) {
        return teamMemberApplyDAO.selectList(condition);
    }

    @Override
    public TeamMemberApply getTeamMemberApply(String code) {
        TeamMemberApply data = null;
        if (StringUtils.isNotBlank(code)) {
            TeamMemberApply condition = new TeamMemberApply();
            condition.setCode(code);
            data = teamMemberApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "申请记录不存在");
            }
        }
        return data;
    }

}
