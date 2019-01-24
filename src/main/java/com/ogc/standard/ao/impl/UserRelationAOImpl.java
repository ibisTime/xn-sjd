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
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserRelationBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserRelation;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.EUserRelationStatus;
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
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    /**
     * @see com.std.user.ao.IUserRelationAO#queryUserRelationPage(int, int, com.std.user.domain.UserRelation)
     */
    @Override
    public Paginable<UserRelation> queryUserRelationPage(int start, int limit,
            UserRelation condition) {
        Paginable<UserRelation> page = userRelationBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (UserRelation userRelation : page.getList()) {
                User toUserInfo = userBO
                    .getUserUnCheck(userRelation.getToUser());
                userRelation.setToUserInfo(toUserInfo);

                User fromUserInfo = userBO
                    .getUserUnCheck(userRelation.getUserId());
                userRelation.setFromUserInfo(fromUserInfo);
            }
        }

        return page;
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

        // 判断两者关系是否已申请
        if (userRelationBO.isExistUserRelation(userId, toUserId, type,
            EUserRelationStatus.TO_APPROVE.getCode())) {
            throw new BizException("xn702001", "好友申请已发起，请勿重复申请");
        }

        // 判断两者关系是否存在
        if (userRelationBO.isExistUserRelation(userId, toUserId, type,
            EUserRelationStatus.APPROVE_YES.getCode())) {
            throw new BizException("xn702001", "用户关系已建立");
        }

        userRelationBO.saveUserRelation(userId, toUserId, type);

    }

    /**
     * @see com.std.user.ao.IUserRelationAO#unfollowUser(java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public void unfollowUser(String userId, String toUserId, String type) {
        userBO.getUser(userId);
        userBO.getUser(toUserId);
        // 判断两者关系是否存在
        if (!userRelationBO.isExistUserRelation(userId, toUserId, type,
            EUserRelationStatus.APPROVE_YES.getCode())) {
            throw new BizException("xn702001", "用户关系未建立，无法解除");
        }

        User user = userBO.getUser(userId);
        userBO.refreshFriendCount(userId, user.getFriendCount() - 1);

        userRelationBO.removeUserRelation(userId, toUserId, type);
    }

    @Override
    @Transactional
    public void approveUser(String code, String userId, String approveResult,
            String remark) {
        UserRelation userRelation = userRelationBO.getUserRelation(code);
        if (!EUserRelationStatus.TO_APPROVE.getCode()
            .equals(userRelation.getStatus())) {
            throw new BizException("xn702001", "用户关系未处于可审核状态");
        }

        String status = EUserRelationStatus.APPROVE_NO.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EUserRelationStatus.APPROVE_YES.getCode();

            User user = userBO.getUser(userId);
            userBO.refreshFriendCount(userId, user.getFriendCount() + 1);
        }

        userRelationBO.approveUserRelation(code, status, remark);
    }

    @Override
    public boolean isExistUserRelation(String userId, String toUser,
            String type) {
        List<UserRelation> userRelationList = userRelationBO
            .queryUserRelationList(userId, toUser, type,
                EUserRelationStatus.APPROVE_YES.getCode());
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

            // 好友碳泡泡数量
            BigDecimal takeableTppAmount = carbonBubbleOrderBO
                .takeableTppAmount(userRelation.getToUser());
            userRelation.setTakeableTppAmount(takeableTppAmount);
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
