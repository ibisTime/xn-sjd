package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AgentUser;

/**
 * 代理用户
 * @author: silver 
 * @since: 2018年9月28日 上午11:45:00 
 * @history:
 */
public interface IAgentUserBO extends IPaginableBO<AgentUser> {

    // 用户是否存在
    public boolean isAgentUserExist(String userId);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    // 判断登录名是否存在
    public boolean isLoginNameExist(String loginName);

    // 注册用户
    public String doRegister(String mobile, String loginPwd);

    // 添加业务员
    public String doAddSalesman(String mobile, String loginPwd);

    // 添加用户
    public void doAddAgentUser(AgentUser data);

    // 验证支付密码:拿tradePwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkTradePwd(String userId, String tradePwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 修改真实姓名
    public void refreshRealName(String userId, String realName);

    // 修改登录密码
    public void refreshLoginPwd(String userId, String loginPwd);

    // 修改安全密码
    public void refreshTradePwd(String userId, String tradePwd);

    // 修改手机号
    public void refreshMobile(String userId, String mobile);

    // 修改状态
    public void refreshStatus(String userId, String status, String updater,
            String remark);

    // 修改头像
    public void refreshPhoto(String userId, String photo);

    // 修改等级
    public void refreshLevel(String userId, String level);

    public List<AgentUser> queryAgentUserList(AgentUser condition);

    public AgentUser getAgentUser(String userId);

    public AgentUser getAgentUserByMobile(String mobile);
}
