package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.INotifyUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.INotifyUserDAO;
import com.ogc.standard.domain.NotifyUser;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class NotifyUserBOImpl extends PaginableBOImpl<NotifyUser>
        implements INotifyUserBO {

    @Autowired
    private INotifyUserDAO notifyUserDAO;

    @Override
    public String saveNotifyUser(String type, String name, String mobile,
            String updater, String remark) {
        NotifyUser notifyUser = new NotifyUser();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.NOTIFY_USER.getCode());
        notifyUser.setCode(code);
        notifyUser.setType(type);
        notifyUser.setName(name);
        notifyUser.setMobile(mobile);
        notifyUser.setUpdateDatetime(new Date());
        notifyUser.setUpdater(updater);
        notifyUser.setRemark(remark);

        notifyUserDAO.insert(notifyUser);
        return code;
    }

    @Override
    public void removeNotifyUser(String code) {

        NotifyUser data = new NotifyUser();
        data.setCode(code);
        notifyUserDAO.delete(data);

    }

    @Override
    public void refreshNotifyUser(String code, String type, String name,
            String mobile, String updater, String remark) {
        NotifyUser notifyUser = new NotifyUser();

        notifyUser.setCode(code);
        notifyUser.setType(type);
        notifyUser.setName(name);
        notifyUser.setMobile(mobile);
        notifyUser.setUpdateDatetime(new Date());
        notifyUser.setUpdater(updater);
        notifyUser.setRemark(remark);

        notifyUserDAO.updateNotifyUser(notifyUser);
    }

    @Override
    public List<NotifyUser> queryNotifyUserListByType(String type) {
        NotifyUser condition = new NotifyUser();
        condition.setType(type);
        return notifyUserDAO.selectList(condition);
    }

    @Override
    public List<NotifyUser> queryNotifyUserList(NotifyUser condition) {
        return notifyUserDAO.selectList(condition);
    }

    @Override
    public NotifyUser getNotifyUser(String code) {
        NotifyUser data = null;
        if (StringUtils.isNotBlank(code)) {
            NotifyUser condition = new NotifyUser();
            condition.setCode(code);
            data = notifyUserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "审核通知人不存在");
            }
        }
        return data;
    }

}
