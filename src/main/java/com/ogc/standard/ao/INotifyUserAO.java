package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.NotifyUser;

@Component
public interface INotifyUserAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addNotifyUser(String name, String mobile, String updater,
            String remark);

    public void dropNotifyUser(String code);

    public void editNotifyUser(String code, String name, String mobile,
            String updater, String remark);

    public Paginable<NotifyUser> queryNotifyUserPage(int start, int limit,
            NotifyUser condition);

    public List<NotifyUser> queryNotifyUserList(NotifyUser condition);

    public NotifyUser getNotifyUser(String code);

}
