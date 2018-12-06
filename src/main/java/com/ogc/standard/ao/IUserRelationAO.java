/**
 * @Title IUserRelationAO.java
 * @Package com.std.user.ao
 * @Description
 * @author xieyj
 * @date 2016年8月31日 上午11:39:25
 * @version V1.0
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserRelation;

/**
 * @author: xieyj
 * @since: 2016年8月31日 上午11:39:25
 * @history:
 */
public interface IUserRelationAO {

    /**
     * 关注某人
     *
     * @param userId
     * @param toUser
     * @create: 2016年8月31日 上午11:47:10 xieyj
     * @history:
     */
    public void followUser(String userId, String toUser, String type);

    /**
     * 取消关注
     *
     * @param userId
     * @param toUser
     * @create: 2016年8月31日 上午11:47:30 xieyj
     * @history:
     */
    public void unfollowUser(String userId, String toUser, String type);

    public void approveUser(String code, String userId, String approveResult,
            String remark);

    /**
     * 用户关系
     *
     * @param userId
     * @param toUser
     * @return
     * @create: 2017年5月11日 上午11:08:50 asus
     * @history:
     */
    public boolean isExistUserRelation(String userId, String toUser,
            String type);

    /**
     * 分页查询
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2018年10月5日 下午3:10:54 xieyj
     * @history:
     */
    public Paginable<UserRelation> queryUserRelationPage(int start, int limit,
            UserRelation condition);

    // 分页查询我的好友排行榜
    public Paginable<UserRelation> queryMyUserRelationPage(int start, int limit,
            UserRelation condition);

    // 查询我的排行榜
    public UserRelation getMyUserRelation(UserRelation condition);
}
