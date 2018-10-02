package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.dto.res.XN627300Res;

@Component
public interface IAgentUserAO {
    static final String DEFAULT_ORDER_COLUMN = "user_id";

    // 注册用户
    public String doRegister(String mobile, String loginPwd, String smsCaptcha);

    // 用户登录
    public XN627300Res doLogin(String loginName, String loginPwd);

    // 申请分销商
    public String applyAgent(String mobile, String realName, String loginPwd,
            String photo);

    // 审核用户
    public void approveAgentUser(String userId, String approveResult,
            String remark, String updater);

    // 修改重提
    public void reApproveAgentUser(String userId, String updater,
            String remark);

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

    // 注销/激活用户
    public void doLogInOut(String userId, String updater, String remark);

    // 新增业务员
    public String doAddSalesman(String mobile, String loginPwd);

    public Paginable<AgentUser> queryAgentUserPage(int start, int limit,
            AgentUser condition);

    public List<AgentUser> queryAgentUserList(AgentUser condition);

    public AgentUser getAgentUser(String userId);

}
