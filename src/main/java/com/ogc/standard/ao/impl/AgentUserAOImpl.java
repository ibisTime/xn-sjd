package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EAgentUserKind;
import com.ogc.standard.enums.EAgentUserStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECaptchaType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.exception.BizException;

@Service
public class AgentUserAOImpl implements IAgentUserAO {

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String doRegister(String mobile, String loginPwd,
            String smsCaptcha) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "730070");

        // 注册用户
        String userId = agentUserBO.doRegister(mobile, loginPwd);

        // 分配人民币账户
        accountBO.distributeAccount(userId, EAccountType.AGENT,
            ECurrency.CNY.getCode());

        // 分配积分账户
        accountBO.distributeAccount(userId, EAccountType.AGENT,
            ECurrency.JF.getCode());

        return userId;
    }

    @Override
    public String doLogin(String loginName, String loginPwd) {
        AgentUser condition = new AgentUser();
        condition.setMobile(loginName);
        List<AgentUser> agentUserList = agentUserBO
            .queryAgentUserList(condition);
        if (CollectionUtils.isEmpty(agentUserList)) {
            throw new BizException("xn805050", "登录名不存在");
        }

        AgentUser con = new AgentUser();
        con.setLoginPwd(MD5Util.md5(loginPwd));
        List<AgentUser> listAgentUser = agentUserBO.queryAgentUserList(con);
        if (CollectionUtils.isEmpty(listAgentUser)) {
            throw new BizException("xn805050", "登录密码错误");
        }

        String userId = null;
        for (AgentUser agentUser : listAgentUser) {
            if (loginName.equals(agentUser.getMobile())) {
                userId = agentUser.getUserId();
                break;
            }
        }

        if (userId == null) {
            throw new BizException("xn805050", "登录密码错误");
        }

        AgentUser agentUser = agentUserBO.getAgentUser(userId);
        if (!EAgentUserStatus.Partner.getCode().equals(agentUser.getStatus())) {
            throw new BizException("xn805050", "该账号未处于合伙关系中，无法登录！");
        }

        return userId;
    }

    @Override
    public String applyAgent(String mobile, String realName, String loginPwd,
            String photo) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        AgentUser agentUser = new AgentUser();
        String userId = OrderNoGenerater.generate("AU");
        agentUser.setUserId(userId);
        agentUser.setMobile(mobile);
        agentUser.setRealName(realName);
        agentUser.setPhoto(photo);

        if (StringUtils.isNotBlank(loginPwd)) {
            agentUser.setLoginPwd(MD5Util.md5(loginPwd));
            agentUser
                .setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        }

        agentUser.setType(EAgentUserKind.Agent.getCode());
        agentUser.setStatus(EAgentUserStatus.TO_APPROVE.getCode());
        agentUser.setCreateDatetime(new Date());

        agentUserBO.doAddAgentUser(agentUser);
        return userId;
    }

    @Override
    public void approveAgentUser(String userId, String approveResult,
            String remark, String updater) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);
        if (!EAgentUserStatus.TO_APPROVE.getCode()
            .equals(agentUser.getStatus())) {
            throw new BizException("xn0000", "该用户未处于可审核状态！");
        }

        String status = null;
        if (EBoolean.NO.getCode().equals(approveResult)) {
            status = EAgentUserStatus.APPROVE_NO.getCode();
        } else {
            status = EAgentUserStatus.Partner.getCode();
        }

        agentUserBO.refreshStatus(userId, status, updater, remark);
    }

    @Override
    public void reApproveAgentUser(String userId, String updater,
            String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);
        if (!EAgentUserStatus.APPROVE_NO.getCode()
            .equals(agentUser.getStatus())) {
            throw new BizException("xn00000", "用户未处于可重新审核状态！");
        }

        agentUserBO.refreshStatus(userId, EAgentUserStatus.Partner.getCode(),
            updater, remark);
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        String oldMobile = agentUser.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn00000", "新手机与原手机一致");
        }
        // 验证手机号
        agentUserBO.isMobileExist(newMobile);

        // 短信验证码是否正确（往新手机号发送）
        // smsOutBO.checkCaptcha(newMobile, smsCaptcha, "730075");

        // 修改手机号
        agentUserBO.refreshMobile(userId, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            String.format(SysConstants.DO_CHANGE_MOBILE_CN,
                PhoneUtil.hideMobile(oldMobile),
                DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_1),
                newMobile),
            ECaptchaType.MOBILE_CHANGE.getCode());
    }

    @Override
    public void modifyPhoto(String userId, String photo) {
        agentUserBO.refreshPhoto(userId, photo);
    }

    @Override
    @Transactional
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        // 验证当前登录密码是否正确
        agentUserBO.checkLoginPwd(userId, oldLoginPwd);
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("xn00000", "新登录密码不能与原有密码重复");
        }

        // 重置
        agentUserBO.refreshLoginPwd(userId, newLoginPwd);

        // 发送短信
        smsOutBO.sendSmsOut(agentUser.getMobile(),
            String.format(SysConstants.DO_MODIFY_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(agentUser.getMobile())),
            ECaptchaType.MODIFY_LOGIN_PWD.getCode());

    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd) {
        // 验证当前登录密码是否正确
        sysUserBO.checkLoginPwd(adminUserId, adminPwd);

        // 修改密码
        agentUserBO.refreshLoginPwd(userId, loginPwd);
    }

    @Override
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
        AgentUser agentUser = agentUserBO.getAgentUserByMobile(mobile);
        if (null == agentUser) {
            throw new BizException("xn00000", "用户不存在,请先注册");
        }

        // 短信验证码是否正确
        // smsOutBO.checkCaptcha(mobile, smsCaptcha, "730079");

        // 修改密码
        agentUserBO.refreshLoginPwd(agentUser.getUserId(), newLoginPwd);

        // 发送短信
        smsOutBO.sendSmsOut(mobile,
            String.format(SysConstants.DO_RESET_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.LOGIN_PWD_RESET.getCode());
    }

    @Override
    public void doLogInOut(String userId, String updater, String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        String status = null;
        if (EAgentUserStatus.Logout.getCode().equals(agentUser.getStatus())) {
            status = EAgentUserStatus.TO_APPROVE.getCode();
        } else {
            status = EAgentUserStatus.Logout.getCode();
        }

        agentUserBO.refreshStatus(userId, status, updater, remark);
    }

    @Override
    public String doAddSalesman(String mobile, String loginPwd) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 注册用户
        String userId = agentUserBO.doAddSalesman(mobile, loginPwd);

        return userId;
    }

    @Override
    public Paginable<AgentUser> queryAgentUserPage(int start, int limit,
            AgentUser condition) {
        return agentUserBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AgentUser> queryAgentUserList(AgentUser condition) {
        return agentUserBO.queryAgentUserList(condition);
    }

    @Override
    public AgentUser getAgentUser(String code) {
        return agentUserBO.getAgentUser(code);
    }
}
