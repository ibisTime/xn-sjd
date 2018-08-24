package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Team;

/**
 * @author: silver 
 * @since: 2018年8月21日 下午4:46:39 
 * @history:
 */
public interface ITeamAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 编辑战队权重
    public void editTeamWeight(String code, double weight, String updater);

    // 编辑战队位置
    public void editTeamLocation(String code, String location, Integer orderNo,
            String updater);

    public Paginable<Team> queryTeamPage(int start, int limit, Team condition);

    public List<Team> queryTeamList(Team condition);

    // 返回用户是否加入该战队
    public Team getFrontTeam(String code, String userId);

    public Team getTeam(String code);

}
