package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ITeamDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Team;

/**
 * 战队表
 * @author: silver 
 * @since: 2018年8月21日 下午4:21:32 
 * @history:
 */
@Repository("teamDAOImpl")
public class TeamDAOImpl extends AMybatisTemplate implements ITeamDAO {

    @Override
    public int insert(Team data) {
        return super.insert(NAMESPACE.concat("insert_team"), data);
    }

    @Override
    public int delete(Team data) {
        return super.delete(NAMESPACE.concat("delete_team"), data);
    }

    @Override
    public int updateWeight(Team data) {
        return super.update(NAMESPACE.concat("update_teamWeight"), data);
    }

    @Override
    public int updateLocation(Team data) {
        return super.update(NAMESPACE.concat("update_teamLocation"), data);
    }

    @Override
    public int updatePostCount(Team data) {
        return super.update(NAMESPACE.concat("update_teamPostCount"), data);
    }

    @Override
    public int updateMemberCount(Team data) {
        return super.update(NAMESPACE.concat("update_teamMemberCount"), data);
    }

    @Override
    public int updateStatus(Team data) {
        return super.update(NAMESPACE.concat("update_teamStatus"), data);
    }

    @Override
    public Team select(Team condition) {
        return super.select(NAMESPACE.concat("select_team"), condition,
            Team.class);
    }

    @Override
    public long selectTotalCount(Team condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_team_count"),
            condition);
    }

    @Override
    public List<Team> selectList(Team condition) {
        return super.selectList(NAMESPACE.concat("select_team"), condition,
            Team.class);
    }

    @Override
    public List<Team> selectList(Team condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_team"), start, count,
            condition, Team.class);
    }

}
