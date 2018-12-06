/**
 * @Title IUserRelationBO.java 
 * @Package com.std.user.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午11:09:25 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserRelation;

/** 
 * 用户关系表
 * @author: xieyj 
 * @since: 2016年8月31日 上午11:09:25 
 * @history:
 */
public interface IUserRelationBO extends IPaginableBO<UserRelation> {
    public boolean isExistUserRelation(String userId, String toUser,
            String type, String status);

    public String saveUserRelation(String userId, String toUser, String type);

    public int refreshUserRelation(String userId, String toUser, String type);

    public void approveUserRelation(String code, String status, String remark);

    public int removeUserRelation(String userId, String toUser, String type);

    public List<UserRelation> queryUserRelationList(String userId,
            String toUser, String type);

    // 有多少人信任
    public long getRelationCount(String toUser, String type);

    // userId 信任 toUser
    public boolean checkReleation(String userId, String toUser, String type);

    public Paginable<UserRelation> queryMyPaginable(int start, int pageSize,
            UserRelation condition);

    public List<UserRelation> queryMyUserRelationList(UserRelation condition);

    public UserRelation getUserRelation(String code);

}
