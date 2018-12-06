/**
 * @Title IUserEctBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 上午11:18:45 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.req.XN805072Req;

/** 
 * @author: dl 
 * @since: 2018年8月22日 上午11:18:45 
 * @history:
 */
public interface IUserExtBO extends IPaginableBO<UserExt> {
    // 更新用户信息
    public void refreshUserExt(UserExt data);

    // 添加用户记录
    public String addUserExt(String userId);

    // 用户详情
    public UserExt getUserExt(String userId);

    public void isEmailExit(String email);

    // 绑定邮箱
    public void refreshEmail(UserExt data);

    // 个人认证
    public void personAuth(String userId, String idPic, String backIdPic,
            String introduce);

    // 企业认证
    public void companyAuth(XN805072Req req);

}
