package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.INotifyUserAO;
import com.ogc.standard.bo.INotifyUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.NotifyUser;
import com.ogc.standard.enums.ENotifyUserType;

@Service
public class NotifyUserAOImpl implements INotifyUserAO {

    @Autowired
    private INotifyUserBO notifyUserBO;

    @Override
    public String addNotifyUser(String name, String mobile, String updater,
            String remark) {
        return notifyUserBO.saveNotifyUser(
            ENotifyUserType.OWNER_APPROVE.getCode(), name, mobile, updater,
            remark);
    }

    @Override
    public void dropNotifyUser(String code) {
        notifyUserBO.removeNotifyUser(code);
    }

    @Override
    public void editNotifyUser(String code, String name, String mobile,
            String updater, String remark) {
        notifyUserBO.refreshNotifyUser(code,
            ENotifyUserType.OWNER_APPROVE.getCode(), name, mobile, updater,
            remark);
    }

    @Override
    public Paginable<NotifyUser> queryNotifyUserPage(int start, int limit,
            NotifyUser condition) {
        return notifyUserBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<NotifyUser> queryNotifyUserList(NotifyUser condition) {
        return notifyUserBO.queryNotifyUserList(condition);
    }

    @Override
    public NotifyUser getNotifyUser(String code) {
        return notifyUserBO.getNotifyUser(code);
    }

}
