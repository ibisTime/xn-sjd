package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Match;

/**
 * 赛事表
 * @author: silver 
 * @since: 2018年8月21日 上午10:42:19 
 * @history:
 */
public interface IMatchDAO extends IBaseDAO<Match> {
    String NAMESPACE = IMatchDAO.class.getName().concat(".");

    // 修改赛事
    public int update(Match data);

    // 发布赛事
    public int updateRelease(Match data);

    // 开始赛事
    public int updateStartMatch(Match data);

    // 结束赛事
    public int updateEndMatch(Match data);

}
