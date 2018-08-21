package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IMatchDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Match;

/**
 * 赛事表
 * @author: silver 
 * @since: 2018年8月21日 上午10:42:40 
 * @history:
 */

@Repository("matchDAOImpl")
public class MatchDAOImpl extends AMybatisTemplate implements IMatchDAO {

    @Override
    public int insert(Match data) {
        return super.insert(NAMESPACE.concat("insert_match"), data);
    }

    @Override
    public int delete(Match data) {
        return super.delete(NAMESPACE.concat("delete_match"), data);
    }

    @Override
    public int update(Match data) {
        return super.update(NAMESPACE.concat("update_match"), data);
    }

    @Override
    public int updateRelease(Match data) {
        return super.update(NAMESPACE.concat("update_releaseMatch"), data);
    }

    @Override
    public int updateStartMatch(Match data) {
        return super.update(NAMESPACE.concat("update_startMatch"), data);
    }

    @Override
    public int updateEndMatch(Match data) {
        return super.update(NAMESPACE.concat("update_endMatch"), data);
    }

    @Override
    public Match select(Match condition) {
        return super.select(NAMESPACE.concat("select_match"), condition,
            Match.class);
    }

    @Override
    public long selectTotalCount(Match condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_match_count"),
            condition);
    }

    @Override
    public List<Match> selectList(Match condition) {
        return super.selectList(NAMESPACE.concat("select_match"), condition,
            Match.class);
    }

    @Override
    public List<Match> selectList(Match condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_match"), start, count,
            condition, Match.class);
    }

}
