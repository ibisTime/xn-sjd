/**
 * @Title UserRelationAOImpl.java
 * @Package com.std.user.ao.impl
 * @Description
 * @author xieyj
 * @date 2016年8月31日 上午11:48:23
 * @version V1.0
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IUserRelationAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserRelationBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserRelation;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.ESystemCode;
import com.ogc.standard.enums.EUserReleationType;
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

    /**
     * @see com.std.user.ao.IUserRelationAO#queryUserRelationPage(int, int, com.std.user.domain.UserRelation)
     */
    @Override
    public Paginable<UserRelation> queryUserRelationPage(int start, int limit,
            UserRelation condition) {
        Paginable<UserRelation> page = userRelationBO.getPaginable(start, limit,
            condition);
        for (UserRelation userRelation : page.getList()) {

            if (EUserReleationType.TRUST.getCode()
                .equals(userRelation.getType())) {
                if (StringUtils.isNotBlank(condition.getUserId())) {

                    // 查询——我信任的
                    User toUser = userBO.getUser(userRelation.getToUser());
                    userRelation.setToUserInfo(toUser);

                } else {

                    // 查询——信任我的
                    User fromUser = userBO.getUser(userRelation.getUserId());
                    userRelation.setFromUserInfo(fromUser);

                }
            }

            if (EUserReleationType.FRIEND.getCode()
                .equals(userRelation.getType())) {
                if (StringUtils.isNotBlank(condition.getFriendUserId())) {
                    // 被关注人
                    String toUserId = userRelation.getToUser();
                    if (condition.getFriendUserId().equals(toUserId)) {
                        toUserId = userRelation.getUserId();
                    }

                    User toUser = userBO.getUser(toUserId);
                    userRelation.setToUserInfo(toUser);

                    // 证书个数
                    long certificateCount = adoptOrderTreeBO
                        .getCountByCurrentHolder(toUserId);
                    userRelation.setCertificateCount(certificateCount);

                    // 碳泡泡余额
                    Account tppAccount = accountBO.getAccountByUser(toUserId,
                        ECurrency.TPP.getCode());
                    userRelation.setTppAmount(tppAccount.getAmount());
                }
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
        // 判断两者关系是否存在
        if (userRelationBO.isExistUserRelation(userId, toUserId, type)) {
            throw new BizException("xn702001", "用户关系已建立");
        }

        if (type.equals(EUserReleationType.BLACKLIST.getCode())) {

            // 1.如果是拉黑操作，先取消信任
            this.checkIsExitOppositionReleationThenRemove(userId, toUserId,
                EUserReleationType.TRUST.getCode());

        } else if (type.equals(EUserReleationType.TRUST.getCode())) {

            // 2.如果是信任操作，先取消拉黑
            this.checkIsExitOppositionReleationThenRemove(userId, toUserId,
                EUserReleationType.BLACKLIST.getCode());

        }

        userRelationBO.saveUserRelation(userId, toUserId, type,
            ESystemCode.BZ.getCode());

    }

    // 判断是否存在 "对立" 关系，并解除
    private void checkIsExitOppositionReleationThenRemove(String userId,
            String toUserId, String type) {

        if (userRelationBO.checkReleation(userId, toUserId, type)) {

            int count = userRelationBO.removeUserRelation(userId, toUserId,
                type);
            if (count <= 0) {
                throw new BizException("xn000", "解除原关系失败");
            }

        }
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
}
