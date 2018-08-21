package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ITeamAO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Team;
import com.ogc.standard.enums.ETeamMemberApplyStatus;
import com.ogc.standard.exception.BizException;

/**
 * 战队表
 * @author: silver 
 * @since: 2018年8月21日 下午9:44:16 
 * @history:
 */
@Service
public class TeamAOImpl implements ITeamAO {

    @Autowired
    private ITeamBO teamBO;

    @Autowired
    private ITeamMemberApplyBO teamMemberApplyBO;

    @Override
    public void editTeamWeight(String code, double weight, String updater) {
        teamBO.refreshTeamWeight(code, weight, updater);
    }

    @Override
    public void editTeamLocation(String code, String location, Integer orderNo,
            String updater) {
        teamBO.refreshTeamLocation(code, location, orderNo, updater);
    }

    @Override
    public Paginable<Team> queryTeamPage(int start, int limit, Team condition) {
        return teamBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Team> queryTeamList(Team condition) {
        return teamBO.queryTeamList(condition);
    }

    @Override
    public Team getTeam4User(String code, String userId) {
        if (StringUtils.isNotBlank(userId)) {
            List<String> statusList = new ArrayList<String>();
            statusList.add(ETeamMemberApplyStatus.APPROVED_YES.getCode());
            if (!teamMemberApplyBO.isTeamMemberApplyExist(code, userId,
                statusList)) {
                throw new BizException("xn000", "用户不属于此战队，无法查询！");
            }
        }

        return teamBO.getTeam(code);
    }

    @Override
    public Team getTeam(String code) {
        return teamBO.getTeam(code);
    }

}
