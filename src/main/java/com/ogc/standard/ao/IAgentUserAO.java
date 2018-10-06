package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.req.XN730073Req;

public interface IAgentUserAO {
    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 注册用户
    public String doRegister(String mobile, String loginPwd, String smsCaptcha);

    // 用户登录
    public String doLogin(String loginName, String loginPwd);

    // 代注册分销商
    public String doAddAgent(XN730072Req req);

    // 填写资料
    public void commitCompany(XN730073Req req);

    // 审核用户
    public void approveAgentUser(String userId, String approveResult,
            String level, String parentUserId, String updater, String remark);

    // 修改手机号
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha);

    // 修改头像
    public void modifyPhoto(String userId, String photo);

    // 根据旧密码修改密码
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd);

    // 管理员重置用户密码
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd);

    // 自助修改登录密码
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd);

    // 设置支付密码
    public void setTradePwd(String userId, String tradePwd, String smsCaptcha);

    // 修改支付密码
    public void doModifyTradePwd(String userId, String newTradePwd,
            String oldTradePwd);

    // 重置支付密码
    public void resetTradePwd(String userId, String newTradePwd,
            String smsCaptcha);

    // 注销/激活用户
    public void doLogInOut(String userId, String updater, String remark);

    // 新增业务员
    public String doAddSalesman(String mobile, String loginPwd);

    public Paginable<AgentUser> queryAgentUserPage(int start, int limit,
            AgentUser condition);

    public List<AgentUser> queryAgentUserList(AgentUser condition);

    public AgentUser getAgentUser(String userId);

}
