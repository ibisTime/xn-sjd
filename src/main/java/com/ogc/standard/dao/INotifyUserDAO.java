package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.NotifyUser;

public interface INotifyUserDAO extends IBaseDAO<NotifyUser> {
    String NAMESPACE = INotifyUserDAO.class.getName().concat(".");

    public int updateNotifyUser(NotifyUser notifyUser);

}
