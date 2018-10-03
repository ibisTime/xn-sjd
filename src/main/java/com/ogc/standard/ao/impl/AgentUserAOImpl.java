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
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.RandomUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.req.XN730073Req;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EAgentUserStatus;
import com.ogc.standard.enums.EAgentUserType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECaptchaType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AgentUserAOImpl implements IAgentUserAO {

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String doRegister(String mobile, String loginPwd, String smsCaptcha) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "730070");

        // 注册用户
        String userId = agentUserBO.doRegister(mobile, loginPwd);

        // 分配人民币账户
        accountBO.distributeAccount(userId, EAccountType.AGENT,
            ECurrency.CNY.getCode());
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

        AgentUser agentUserCondition = new AgentUser();
        agentUserCondition.setMobile(loginName);
        agentUserCondition.setLoginPwd(MD5Util.md5(loginPwd));
        List<AgentUser> listAgentUser = agentUserBO
            .queryAgentUserList(agentUserCondition);
        AgentUser agentUser = null;
        if (CollectionUtils.isEmpty(listAgentUser)) {
            throw new BizException("xn805050", "登录密码错误");
        } else {
            agentUser = listAgentUser.get(0);
        }

        if (EAgentUserStatus.CANCEL.getCode().equals(agentUser.getStatus())) {
            throw new BizException("xn805050", "您的账号异常，请联系管理员处理");
        }
        return agentUser.getUserId();
    }

    @Override
    @Transactional
    public String doAddAgent(XN730072Req req) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(req.getMobile());
        String loginPwd = RandomUtil.generate6();
        String userId = agentUserBO.doAddAgentUser(req.getMobile(), loginPwd);
        companyBO.saveCompany(req, userId);

        // 发送短信
        smsOutBO.sendSmsOut(
            req.getMobile(),
            String.format(SysConstants.DO_ADD_USER_CN,
                PhoneUtil.hideMobile(req.getMobile())),
            ECaptchaType.AG_REG.getCode());

        return userId;
    }

    @Override
    @Transactional
    public void commitCompany(XN730073Req req) {
        AgentUser agentUser = agentUserBO.getAgentUser(req.getUserId());
        if (EAgentUserType.Agent.getCode().equals(agentUser.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "当前用户不是代理商");
        }
        if (!EAgentUserStatus.TO_FILL.getCode().equals(agentUser.getStatus())
                && !EAgentUserStatus.APPROVE_NO.getCode().equals(
                    agentUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户状态不是待提交资料或审核不通过，不能提交");
        }

        // 获取上级代理商信息
        String parentUserId = null;
        if (StringUtils.isNotBlank(req.getParentMobile())) {
            AgentUser parentAgentUser = agentUserBO.getAgentUserByMobile(req
                .getParentMobile());
            if (!EAgentUserType.Agent.getCode().equals(
                parentAgentUser.getType())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐用户不是代理商");
            }
            if (!EAgentUserStatus.NORMAL.getCode().equals(
                parentAgentUser.getStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐用户状态异常");
            }
        }

        // 更新资料
        agentUserBO.refreshToApprove(agentUser, parentUserId, req.getUserId(),
            "资料提交待审核");
        companyBO.refreshCompany(req);
    }

    @Override
    public void approveAgentUser(String userId, String approveResult,
            String updater, String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);
        if (!EAgentUserStatus.TO_APPROVE.getCode()
            .equals(agentUser.getStatus())) {
            throw new BizException("xn0000", "当前用户不处于待审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EAgentUserStatus.NORMAL.getCode();
        } else {
            status = EAgentUserStatus.APPROVE_NO.getCode();
        }
        agentUserBO.refreshStatus(userId, status, updater, remark);
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
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "730075");

        // 修改手机号
        agentUserBO.refreshMobile(userId, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(oldMobile, String.format(
            SysConstants.DO_CHANGE_MOBILE_CN, PhoneUtil.hideMobile(oldMobile),
            DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_1),
            newMobile), ECaptchaType.MOBILE_CHANGE.getCode());
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
        smsOutBO.sendSmsOut(
            agentUser.getMobile(),
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "730079");

        // 修改密码
        agentUserBO.refreshLoginPwd(agentUser.getUserId(), newLoginPwd);

        // 发送短信
        smsOutBO.sendSmsOut(
            mobile,
            String.format(SysConstants.DO_RESET_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.LOGIN_PWD_RESET.getCode());
    }

    @Override
    public void doLogInOut(String userId, String updater, String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        String status = null;
        if (EAgentUserStatus.CANCEL.getCode().equals(agentUser.getStatus())) {
            status = EAgentUserStatus.TO_APPROVE.getCode();
        } else {
            status = EAgentUserStatus.CANCEL.getCode();
        }

        agentUserBO.refreshStatus(userId, status, updater, remark);
    }

    @Override
    public String doAddSalesman(String mobile, String loginPwd) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 注册用户
        return agentUserBO.doAddSalesman(mobile, loginPwd);
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
    public AgentUser getAgentUser(String userId) {
        AgentUser data = agentUserBO.getAgentUser(userId);
        Company company = companyBO.getCompanyByUserId(userId);
        data.setCompany(company);

        return data;
    }
}
