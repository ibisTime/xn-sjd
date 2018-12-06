/**
 * @Title IUserRelationDAO.java 
 * @Package com.std.user.dao 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午11:00:24 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.UserRelation;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午11:00:24 
 * @history:
 */
public interface IUserRelationDAO extends IBaseDAO<UserRelation> {
    String NAMESPACE = IUserRelationDAO.class.getName().concat(".");

    // 更新关系表状态
    public int updateStatus(UserRelation data);

    // 审核
    public int updateApprove(UserRelation data);

    public long selectMyTotalCount(UserRelation condition);

    public List<UserRelation> selectMyList(UserRelation condition, int start,
            int count);

    public List<UserRelation> selectMyList(UserRelation condition);

}
