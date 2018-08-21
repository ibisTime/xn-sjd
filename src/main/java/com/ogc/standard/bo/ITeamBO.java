package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Team;

/**
 * 战队表
 * @author: silver 
 * @since: 2018年8月21日 下午4:25:20 
 * @history:
 */
public interface ITeamBO extends IPaginableBO<Team> {

    public boolean isTeamExist(String code);

    // 添加战队
    public String saveTeam(String matchCode, String name, String logo,
            String description, String captain);

    // 设置权重
    public void refreshTeamWeight(String code, double weight, String updater);

    // 设置战队位置
    public void refreshTeamLocation(String code, String location,
            Integer orderNo, String updater);

    // 更新发帖数
    public void refreshTeamPostCount(String code, Integer postCount);

    // 更新战队人数
    public void refreshTeamMemberCount(String code, Integer memberCount);

    // 更新战队状态
    public void refreshTeamStatus(String code, String status);

    public List<Team> queryTeamList(Team condition);

    public Team getTeam(String code);

}
