/**
 * @Title IUserBO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:17:37 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EUserStatus;

/** 
 * @author: dl 
 * @since: 2018年8月20日 上午10:58:15 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    // 判断昵称是否存在
    public void isNicknameExist(String nickname);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName);

    public String getUserId(String mobile);

    public User getUserUnCheck(String userId);

    public User getUserByMobile(String mobile);

    // 前端用户注册
    public String doRegister(String mobile, String nickname, String loginPwd,
            User refereeUser, String province, String city, String area);

    public String doAddUser(User data);

    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

    // 验证支付密码:拿tradePwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkTradePwd(String userId, String tradePwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 校验是否已经有人实名认证
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName);

    // 判断推荐人是否存在(手机号)
    public void checkUserReferee(String userReferee, String systemCode);

    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo);

    public int refreshRealName(String userId, String realName);

    public int refreshLoginPwd(String userId, String loginPwd);

    public int refreshTradePwd(String userId, String tradePwd);

    public int refreshMobile(String userId, String mobile);

    public User getUser(String userId);

    public List<User> getUsersByUserReferee(String userReferee);

    public User getUserByLoginName(String loginName, String systemCode);

    public List<User> queryUserList(User condition);

    public void refreshStatus(String userId, EUserStatus normal, String updater,
            String remark);

    public void refreshNickname(String userId, String nickname);

    public void refreshPhoto(String userId, String photo);

    public void refreshLevel(User data);

    public List<User> queryUserList(String mobile, String kind,
            String systemCode);

    public Long totalUser(User condition);

    // 修改定位信息
    public void refreshLocation(User data);

    // 修改推荐人
    public void refreshReferee(String userId, String userReferee,
            String updater);

}
