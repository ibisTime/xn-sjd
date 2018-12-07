/**
 * @Title UserRelationBOImpl.java
 * @Package com.std.user.bo.impl
 * @Description
 * @author xieyj
 * @date 2016年8月31日 上午11:17:49
 * @version V1.0
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IUserRelationBO;
import com.ogc.standard.bo.base.Page;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IUserRelationDAO;
import com.ogc.standard.domain.UserRelation;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EUserRelationStatus;

/**
 * 用户关系BO
 * @author: xieyj
 * @since: 2016年8月31日 上午11:17:49 
 * @history:
 */
@Component
public class UserRelationBOImpl extends PaginableBOImpl<UserRelation>
        implements IUserRelationBO {
    @Autowired
    private IUserRelationDAO userRelationDAO;

    /**
     * @see com.std.user.bo.IUserRelationBO#isExistUserRelation(java.lang.String, java.lang.String)
     */
    @Override
    public boolean isExistUserRelation(String userId, String toUser,
            String type, String status) {
        UserRelation condition = new UserRelation();
        condition.setUserId(userId);
        condition.setToUser(toUser);
        condition.setType(type);
        condition.setStatus(status);
        long count = userRelationDAO.selectTotalCount(condition);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * @see com.std.user.bo.IUserRelationBO#saveUserRelation(java.lang.String, java.lang.String)
     */
    @Override
    public String saveUserRelation(String userId, String toUser, String type) {
        String code = null;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(toUser)) {
            UserRelation data = new UserRelation();
            code = OrderNoGenerater.generate("UR");
            data.setCode(code);
            data.setUserId(userId);
            data.setToUser(toUser);
            data.setType(type);
            data.setStatus(EUserRelationStatus.TO_APPROVE.getCode());
            data.setCreateDatetime(new Date());
            userRelationDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshUserRelation(String userId, String toUser, String type) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(toUser)) {
            UserRelation data = new UserRelation();
            data.setUserId(userId);
            data.setToUser(toUser);
            data.setType(type);
            data.setStatus(EBoolean.NO.getCode());
            count = userRelationDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public void approveUserRelation(String code, String status, String remark) {
        UserRelation userRelation = new UserRelation();
        userRelation.setCode(code);
        userRelation.setStatus(status);
        userRelation.setRemark(remark);
        userRelationDAO.updateApprove(userRelation);
    }

    @Override
    public int removeUserRelation(String userId, String toUser, String type) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(toUser)) {
            UserRelation data = new UserRelation();
            data.setUserId(userId);
            data.setToUser(toUser);
            data.setType(type);
            count = userRelationDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<UserRelation> queryUserRelationList(String userId,
            String toUser, String type, String status) {
        UserRelation condition = new UserRelation();
        condition.setUserId(userId);
        condition.setToUser(toUser);
        condition.setType(type);
        condition.setStatus(status);
        return userRelationDAO.selectList(condition);
    }

    @Override
    public long getRelationCount(String toUser, String type) {
        UserRelation condition = new UserRelation();
        condition.setToUser(toUser);
        condition.setType(type);
        return getTotalCount(condition);
    }

    @Override
    public boolean checkReleation(String userId, String toUser, String type) {

        UserRelation condition = new UserRelation();
        condition.setUserId(userId);
        condition.setToUser(toUser);
        condition.setType(type);
        return this.userRelationDAO.selectTotalCount(condition) >= 1;
    }

    @Override
    public Paginable<UserRelation> queryMyPaginable(int start, int pageSize,
            UserRelation condition) {
        prepare(condition);
        long totalCount = userRelationDAO.selectMyTotalCount(condition);

        Paginable<UserRelation> page = new Page<UserRelation>(start, pageSize,
            totalCount);

        List<UserRelation> dataList = userRelationDAO.selectMyList(condition,
            page.getStart(), page.getPageSize());

        page.setList(dataList);
        return page;
    }

    @Override
    public List<UserRelation> queryMyUserRelationList(UserRelation condition) {
        return userRelationDAO.selectMyList(condition);
    }

    @Override
    public UserRelation getUserRelation(String code) {
        UserRelation condition = new UserRelation();
        condition.setCode(code);
        return userRelationDAO.select(condition);
    }

}
