/**
 * @Title UserExtDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 上午10:45:08 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IUserExtDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.UserExt;

/** 
 * @author: dl 
 * @since: 2018年8月22日 上午10:45:08 
 * @history:
 */
@Repository("userExtDAOImpl")
public class UserExtDAOImpl extends AMybatisTemplate implements IUserExtDAO {

    @Override
    public int insert(UserExt data) {
        return super.insert(NAMESPACE.concat("insert_userExt"), data);
    }

    @Override
    public int delete(UserExt data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public UserExt select(UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long selectTotalCount(UserExt condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_ext_count"),
            condition);
    }

    @Override
    public List<UserExt> selectList(UserExt condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserExt> selectList(UserExt condition, int start, int count) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateUserExt(UserExt data) {
        return super.update(NAMESPACE.concat("update_userExt"), data);
    }

    @Override
    public UserExt getUserExt(UserExt data) {
        return super.select(NAMESPACE.concat("select_userExt"), data,
            UserExt.class);
    }

    @Override
    public int bindEmail(UserExt data) {
        return super.update(NAMESPACE.concat("bind_email"), data);
    }

}
