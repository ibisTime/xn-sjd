package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ITeamDAO;
import com.ogc.standard.domain.Team;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ETeamStatus;
import com.ogc.standard.exception.BizException;

/**
 * 战队表
 * @author: silver 
 * @since: 2018年8月21日 下午4:31:35 
 * @history:
 */
@Component
public class TeamBOImpl extends PaginableBOImpl<Team> implements ITeamBO {

    @Autowired
    private ITeamDAO teamDAO;

    @Override
    public boolean isTeamExist(String code) {
        Team condition = new Team();
        condition.setCode(code);
        if (teamDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveTeam(String matchCode, String name, String logo,
            String description, String captain) {
        Team data = new Team();

        String code = OrderNoGenerater.generate(EGeneratePrefix.Team.getCode());
        data.setCode(code);
        data.setMatchCode(matchCode);
        data.setName(name);
        data.setLogo(logo);

        data.setDescription(description);
        data.setCaptain(captain);
        data.setUpdateDatetime(new Date());
        data.setStatus(ETeamStatus.TO_START.getCode());

        teamDAO.insert(data);
        return code;
    }

    @Override
    public void refreshTeamWeight(String code, double weight, String updater) {
        Team team = new Team();
        team.setCode(code);
        team.setWeight(weight);
        team.setUpdater(updater);
        team.setUpdateDatetime(new Date());
        teamDAO.updateWeight(team);
    }

    @Override
    public void refreshTeamLocation(String code, String location,
            Integer orderNo, String updater) {
        Team team = new Team();
        team.setCode(code);
        team.setLocation(location);
        team.setOrderNo(orderNo);
        team.setUpdater(updater);
        team.setUpdateDatetime(new Date());
        teamDAO.updateLocation(team);
    }

    @Override
    public void refreshTeamPostCount(String code, Integer postCount) {
        Team team = new Team();
        team.setCode(code);
        team.setPostCount(postCount);
        teamDAO.updatePostCount(team);
    }

    @Override
    public void refreshTeamMemberCount(String code, Integer memberCount) {
        Team team = new Team();
        team.setCode(code);
        team.setMemberCount(memberCount);
        teamDAO.updateMemberCount(team);
    }

    @Override
    public void refreshTeamStatus(String code, String status) {
        Team team = new Team();
        team.setCode(code);
        team.setStatus(status);
        teamDAO.updateStatus(team);
    }

    @Override
    public List<Team> queryTeamList(Team condition) {
        return teamDAO.selectList(condition);
    }

    @Override
    public Team getTeam(String code) {
        Team data = null;
        if (StringUtils.isNotBlank(code)) {
            Team condition = new Team();
            condition.setCode(code);
            data = teamDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "战队记录不存在");
            }
        }
        return data;
    }

}
