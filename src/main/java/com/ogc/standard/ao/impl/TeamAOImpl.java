package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ITeamAO;
import com.ogc.standard.bo.IMatchBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Match;
import com.ogc.standard.domain.Team;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ETeamMemberApplyStatus;

/**
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

    @Autowired
    private IMatchBO matchBO;

    @Autowired
    private IUserBO userBO;

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
        Paginable<Team> page = teamBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Team team : page.getList()) {
                initTeam(team);
            }
        }
        return page;
    }

    @Override
    public List<Team> queryTeamList(Team condition) {
        return teamBO.queryTeamList(condition);
    }

    @Override
    public Team getFrontTeam(String code, String userId) {
        Team team = teamBO.getTeam(code);

        if (StringUtils.isNotBlank(userId)) {
            List<String> statusList = new ArrayList<String>();
            statusList.add(ETeamMemberApplyStatus.APPROVED_YES.getCode());
            if (teamMemberApplyBO.isTeamMemberApplyExist(code, userId,
                statusList)) {
                team.setIsUserBelongTeam(EBoolean.YES.getCode());
            } else {
                team.setIsUserBelongTeam(EBoolean.NO.getCode());
            }
        }

        initTeam(team);

        return team;
    }

    @Override
    public Team getTeam(String code) {
        Team team = teamBO.getTeam(code);

        initTeam(team);

        return team;
    }

    private void initTeam(Team team) {
        // 赛事信息
        Match match = matchBO.getMatch(team.getMatchCode());
        team.setMatch(match);

        // 队长信息
        User captainInfo = userBO.getUser(team.getCaptain());
        team.setCaptainInfo(captainInfo);
    }

}
