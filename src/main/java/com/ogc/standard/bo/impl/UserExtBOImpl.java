package com.ogc.standard.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IUserExtBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IUserExtDAO;
import com.ogc.standard.domain.UserExt;

@Component
public class UserExtBOImpl extends PaginableBOImpl<UserExt>
        implements IUserExtBO {
    @Autowired
    private IUserExtDAO userExtDAO;

    @Override
    public long getTotalCount(UserExt condition) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Paginable<UserExt> getPaginable(int start, UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Paginable<UserExt> getPaginable(int start, int pageSize,
            UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void refreshUserExt(UserExt data) {
        userExtDAO.updateUserExt(data);
    }

    @Override
    public String addUserExt(String userId) {
        UserExt data = new UserExt();
        data.setUserId(userId);
        userExtDAO.insert(data);
        return userId;

    }

    @Override
    public UserExt getUserExt(String userId) {
        UserExt condition = new UserExt();
        condition.setUserId(userId);
        return userExtDAO.getUserExt(condition);
    }

}
