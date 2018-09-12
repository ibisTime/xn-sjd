/**
 * @Title IUserExtDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 上午10:40:15 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.UserExt;

/** 
 * @author: dl 
 * @since: 2018年8月22日 上午10:40:15 
 * @history:
 */
public interface IUserExtDAO extends IBaseDAO<UserExt> {

    String NAMESPACE = IUserExtDAO.class.getName().concat(".");

    // 完善用户信息
    public int updateUserExt(UserExt data);

    // 详情查
    public UserExt getUserExt(UserExt data);

    // 改邮箱（绑定）
    public int bindEmail(UserExt data);

}
