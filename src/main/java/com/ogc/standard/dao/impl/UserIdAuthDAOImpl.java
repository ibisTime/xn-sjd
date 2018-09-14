/**
 * @Title UserIdAuthDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午5:09:03 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IUserIdAuthDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.UserIdAuth;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午5:09:03 
 * @history:
 */
@Repository("userIdAuthDAOImpl")
public class UserIdAuthDAOImpl extends AMybatisTemplate
        implements IUserIdAuthDAO {

    @Override
    public int insert(UserIdAuth data) {
        return super.insert(NAMESPACE.concat("insert_user_id_auth"), data);
    }

    @Override
    public int delete(UserIdAuth data) {
        return 0;
    }

    @Override
    public UserIdAuth select(UserIdAuth condition) {
        return super.select(NAMESPACE.concat("select_user_id_auth"), condition,
            UserIdAuth.class);
    }

    @Override
    public long selectTotalCount(UserIdAuth condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_apply_count"),
            condition);
    }

    @Override
    public List<UserIdAuth> selectList(UserIdAuth condition) {
        return super.selectList(NAMESPACE.concat("select_user_id_auth"),
            condition, UserIdAuth.class);
    }

    @Override
    public List<UserIdAuth> selectList(UserIdAuth condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_user_id_auth"), start,
            count, condition, UserIdAuth.class);
    }

    @Override
    public int updateresult(UserIdAuth data) {
        return super.update(NAMESPACE.concat("update_result"), data);
    }

}
