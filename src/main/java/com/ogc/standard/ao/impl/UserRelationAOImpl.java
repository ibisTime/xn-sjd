/**
 * @Title UserRelationAOImpl.java
 * @Package com.std.user.ao.impl
 * @Description
 * @author xieyj
 * @date 2016年8月31日 上午11:48:23
 * @version V1.0
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IUserRelationAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserRelationBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserRelation;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj
 * @since: 2016年8月31日 上午11:48:23
 * @history:
 */
@Service
public class UserRelationAOImpl implements IUserRelationAO {

    @Autowired
    IUserRelationBO userRelationBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    /**
     * @see com.std.user.ao.IUserRelationAO#queryUserRelationPage(int, int, com.std.user.domain.UserRelation)
     */
    @Override
    public Paginable<UserRelation> queryUserRelationPage(int start, int limit,
            UserRelation condition) {
        return userRelationBO.getPaginable(start, limit, condition);
    }

    /**
     * @see com.std.user.ao.IUserRelationAO#followUser(java.lang.String, java.lang.String)
     */
    @Transactional
    @Override
    public void followUser(String userId, String toUserId, String type) {
        userBO.getUser(userId);
        userBO.getUser(toUserId);
        if (userId.equals(toUserId)) {
            throw new BizException("xn702001", "用户不能和自己建立关系");
        }
        // 判断两者关系是否存在
        if (userRelationBO.isExistUserRelation(userId, toUserId, type)) {
            throw new BizException("xn702001", "用户关系已建立");
        }

        userRelationBO.saveUserRelation(userId, toUserId, type);

    }

    /**
     * @see com.std.user.ao.IUserRelationAO#unfollowUser(java.lang.String, java.lang.String)
     */
    @Override
    public void unfollowUser(String userId, String toUserId, String type) {
        userBO.getUser(userId);
        userBO.getUser(toUserId);
        // 判断两者关系是否存在
        if (!userRelationBO.isExistUserRelation(userId, toUserId, type)) {
            throw new BizException("xn702001", "用户关系未建立，无法解除");
        }
        userRelationBO.removeUserRelation(userId, toUserId, type);
    }

    @Override
    public boolean isExistUserRelation(String userId, String toUser,
            String type) {
        List<UserRelation> userRelationList = userRelationBO
            .queryUserRelationList(userId, toUser, type);
        boolean flag = false;
        if (CollectionUtils.isNotEmpty(userRelationList)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Paginable<UserRelation> queryMyUserRelationPage(int start, int limit,
            UserRelation condition) {
        Map<String, String> map = sysConfigBO
            .getConfigsMap(ESysConfigType.WEIGHT.getCode());
        BigDecimal weightRate1 = new BigDecimal(
            map.get(SysConstants.WEIGHT_RATE1));
        BigDecimal weightRate2 = new BigDecimal(
            map.get(SysConstants.WEIGHT_RATE2));

        condition.setWeightRate1(weightRate1);
        condition.setWeightRate2(weightRate2);
        Paginable<UserRelation> page = userRelationBO.queryMyPaginable(start,
            limit, condition);

        List<UserRelation> list = page.getList();
        for (UserRelation userRelation : list) {
            if (condition.getUserId().equals(userRelation.getToUser())) {
                userRelation.setMySelf(EBoolean.YES.getCode());
            } else {
                userRelation.setMySelf(EBoolean.NO.getCode());
            }
            User toUserInfo = userBO.getUser(userRelation.getToUser());
            userRelation.setToUserInfo(toUserInfo);
        }
        return page;
    }

    @Override
    public UserRelation getMyUserRelation(UserRelation condition) {
        Map<String, String> map = sysConfigBO
            .getConfigsMap(ESysConfigType.WEIGHT.getCode());
        BigDecimal weightRate1 = new BigDecimal(
            map.get(SysConstants.WEIGHT_RATE1));
        BigDecimal weightRate2 = new BigDecimal(
            map.get(SysConstants.WEIGHT_RATE2));
        condition.setWeightRate1(weightRate1);
        condition.setWeightRate2(weightRate2);
        List<UserRelation> list = userRelationBO
            .queryMyUserRelationList(condition);
        UserRelation result = null;
        for (UserRelation userRelation : list) {
            if (condition.getUserId().equals(userRelation.getToUser())) {
                userRelation.setMySelf(EBoolean.YES.getCode());
                User toUserInfo = userBO.getUser(userRelation.getToUser());
                userRelation.setToUserInfo(toUserInfo);
                result = userRelation;
            }
        }
        return result;
    }
}
