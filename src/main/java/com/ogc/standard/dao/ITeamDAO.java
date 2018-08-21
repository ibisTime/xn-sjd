package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Team;

/**
 * 战队表
 * @author: silver 
 * @since: 2018年8月21日 下午4:21:25 
 * @history:
 */
public interface ITeamDAO extends IBaseDAO<Team> {
    String NAMESPACE = ITeamDAO.class.getName().concat(".");

    // 设置权重
    public int updateWeight(Team data);

    // 设置位置
    public int updateLocation(Team data);

    // 更新发帖数
    public int updatePostCount(Team data);

    // 更新战队人数
    public int updateMemberCount(Team data);

    // 更新状态
    public int updateStatus(Team data);
}
