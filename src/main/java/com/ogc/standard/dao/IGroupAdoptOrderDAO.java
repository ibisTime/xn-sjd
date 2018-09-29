package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupAdoptOrder;

//dao层 
public interface IGroupAdoptOrderDAO extends IBaseDAO<GroupAdoptOrder> {
    String NAMESPACE = IGroupAdoptOrderDAO.class.getName().concat(".");

    int update(GroupAdoptOrder data);

    int insertFirst(GroupAdoptOrder data);

    int insertUnFirst(GroupAdoptOrder data);

    void updateCancelGroupAdoptOrder(GroupAdoptOrder data);

    void updatePayGroupAdoptOrder(GroupAdoptOrder data);
}
