package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.NotifyUser;

public interface INotifyUserBO extends IPaginableBO<NotifyUser> {

    public String saveNotifyUser(String type, String name, String mobile,
            String updater, String remark);

    public void removeNotifyUser(String code);

    public void refreshNotifyUser(String code, String type, String name,
            String mobile, String updater, String remark);

    public List<NotifyUser> queryNotifyUserListByType(String type);

    public List<NotifyUser> queryNotifyUserList(NotifyUser condition);

    public NotifyUser getNotifyUser(String code);

}
