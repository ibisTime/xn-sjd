/**
 * @Title UserEditApplyDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 上午11:30:29 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IUserFieldApproveDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.UserFieldApprove;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 上午11:30:29 
 * @history:
 */
@Repository("userFieldApproveDAOImpl")
public class UserFieldApproveDAOImpl extends AMybatisTemplate
        implements IUserFieldApproveDAO {

    @Override
    public int insert(UserFieldApprove data) {
        return super.insert(NAMESPACE.concat("insert_user_field_approve"),
            data);
    }

    @Override
    public int delete(UserFieldApprove data) {
        return 0;
    }

    @Override
    public UserFieldApprove select(UserFieldApprove condition) {
        return super.select(NAMESPACE.concat("select_user_field_approve"),
            condition, UserFieldApprove.class);
    }

    @Override
    public long selectTotalCount(UserFieldApprove condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_approve_count"),
            condition);
    }

    @Override
    public List<UserFieldApprove> selectList(UserFieldApprove condition) {
        return super.selectList(NAMESPACE.concat("select_user_field_approve"),
            condition, UserFieldApprove.class);
    }

    @Override
    public List<UserFieldApprove> selectList(UserFieldApprove condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_user_field_approve"),
            start, count, condition, UserFieldApprove.class);
    }

    @Override
    public int updateResult(UserFieldApprove data) {
        return super.update(NAMESPACE.concat("update_result"), data);
    }

}
