package com.ogc.standard.bo;

import java.util.Date;
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

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile);

    // 注册用户
    public String doRegister(String mobile, String loginPwd);

    // 添加业务员
    public String doAddSalesman(String parentUserId, String mobile,
            String loginPwd);

    // 添加用户
    public String doAddAgentUser(String mobile, String loginPwd, String level,
            String parentUserId);

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

    // 提交信息
    public void refreshToApprove(AgentUser agentUser, String userReferee,
            String parentUserId, String updater);

    // 审核通过
    public void refreshPass(AgentUser agentUser, String level,
            String parentUserId, String updater, String remark);

    // 修改状态
    public void refreshStatus(String userId, String status, String updater,
            String remark);

    // 修改头像
    public void refreshPhoto(String userId, String photo);

    public List<AgentUser> queryAgentUserList(AgentUser condition);

    public AgentUser getAgentUser(String userId);

    public AgentUser getAgentUserUnCheck(String userId);

    public AgentUser getAgentUserByMobile(String parentUserId);

    // 统计parentUserId下的用户总数
    public long getTotalCount(String parentUserId, Date createDatetimeStart,
            Date createDatetimeEnd);
}
