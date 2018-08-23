package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Group;

public interface IGroupDAO extends IBaseDAO<Group> {
    String NAMESPACE = IGroupDAO.class.getName().concat(".");

    /**
     * 更新关注人数
     * @param data
     * @return 
     * @create: 2018年8月21日 下午9:34:56 lei
     * @history:
     */
    public int updateGroupFollowNumber(Group data);
}
