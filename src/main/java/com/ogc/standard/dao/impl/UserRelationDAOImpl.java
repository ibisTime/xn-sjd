/**
 * @Title UserRelationDAOImpl.java 
 * @Package com.std.user.dao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午11:01:42 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IUserRelationDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.UserRelation;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午11:01:42 
 * @history:
 */
@Repository("userRelationDAOImpl")
public class UserRelationDAOImpl extends AMybatisTemplate
        implements IUserRelationDAO {

    /** 
     * @see com.std.user.dao.IUserRelationDAO#updateStatus()
     */
    @Override
    public int updateStatus(UserRelation data) {
        return super.update(NAMESPACE.concat("update_userRelationStatus"),
            data);
    }

    @Override
    public int updateApprove(UserRelation data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserRelation data) {
        return super.insert(NAMESPACE.concat("insert_userRelation"), data);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserRelation data) {
        return super.delete(NAMESPACE.concat("delete_userRelation"), data);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserRelation select(UserRelation condition) {
        return super.select(NAMESPACE.concat("select_userRelation"), condition,
            UserRelation.class);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserRelation condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userRelation_count"), condition);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserRelation> selectList(UserRelation condition) {
        return super.selectList(NAMESPACE.concat("select_userRelation"),
            condition, UserRelation.class);
    }

    /** 
     * @see com.std.user.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserRelation> selectList(UserRelation condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userRelation"), start,
            count, condition, UserRelation.class);
    }

    @Override
    public long selectMyTotalCount(UserRelation condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_myUserRelation_count"), condition);
    }

    @Override
    public List<UserRelation> selectMyList(UserRelation condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_myUserRelation_list"),
            start, count, condition, UserRelation.class);
    }

    @Override
    public List<UserRelation> selectMyList(UserRelation condition) {
        return super.selectList(NAMESPACE.concat("select_myUserRelation_list"),
            condition, UserRelation.class);
    }

}
