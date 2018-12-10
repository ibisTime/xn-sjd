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
import com.ogc.standard.dto.req.XN730090Req;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EAgentUserLevel;
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
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    @Transactional
    public String doRegister(String mobile, String loginPwd,
            String smsCaptcha) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "730070");

        // 注册用户
        String userId = agentUserBO.doRegister(mobile, loginPwd);

        // 保存公司
        companyBO.saveCompany(userId);

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

        return agentUser.getUserId();
    }

    @Override
    @Transactional
    public String doAddAgent(XN730072Req req) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(req.getMobile());

        // String level = null;
        // if (EBoolean.YES.getCode().equals(req.getIsTop())) {
        // req.setParentUserId("0");
        // level = EAgentUserLevel.FIRST.getCode();
        // } else {
        //
        // if (StringUtils.isNotBlank(req.getParentUserId())) {
        // AgentUser parentAgentUser = agentUserBO
        // .getAgentUser(req.getParentUserId());
        // checkAgent(parentAgentUser);
        // level = Integer.valueOf(parentAgentUser.getLevel()) + 1 + "";
        // }
        //
        // }

        String loginPwd = RandomUtil.generate6();
        String userId = agentUserBO.doAddAgentUser(req.getMobile(), loginPwd,
            req.getLevel(), req.getParentUserId());
        companyBO.saveCompany(req, userId);

        // 分配人民币账户
        accountBO.distributeAccount(userId, EAccountType.AGENT,
            ECurrency.CNY.getCode());

        // 发送短信
        smsOutBO.sendSmsOut(req.getMobile(),
            String.format(SysConstants.DO_ADD_USER_CN,
                PhoneUtil.hideMobile(req.getMobile()), loginPwd),
            ECaptchaType.AG_REG.getCode());

        return userId;
    }

    @Override
    @Transactional
    public void editAgent(XN730090Req req) {
        AgentUser agentUser = agentUserBO.getAgentUser(req.getUserId());

        if (!req.getMobile().equals(agentUser.getMobile())) {
            // 验证手机号是否存在
            agentUserBO.isMobileExist(req.getMobile());
        }

        agentUserBO.refreshAgentUser(req.getUserId(), req.getMobile(),
            req.getLevel(), req.getParentUserId(), req.getUpdater(),
            req.getRemark());

        companyBO.refreshCompany(req);
    }

    @Override
    @Transactional
    public void commitCompany(XN730073Req req) {
        AgentUser agentUser = agentUserBO.getAgentUser(req.getUserId());
        if (!EAgentUserType.Agent.getCode().equals(agentUser.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户不是代理商");
        }
        if (!EAgentUserStatus.TO_FILL.getCode().equals(agentUser.getStatus())
                && !EAgentUserStatus.APPROVE_NO.getCode()
                    .equals(agentUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户状态不是待提交资料或审核不通过，不能提交");
        }

        // 获取上级代理商信息
        String userReferee = null;
        String parentUserId = null;
        if (StringUtils.isNotBlank(req.getRefUserMobile())) {
            AgentUser refUserAgentUser = agentUserBO
                .getAgentUserByMobile(req.getRefUserMobile());
            userReferee = refUserAgentUser.getUserId();
            if (!EAgentUserType.Agent.getCode()
                .equals(refUserAgentUser.getType())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐用户不是代理商");
            }
            if (!EAgentUserStatus.NORMAL.getCode()
                .equals(refUserAgentUser.getStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐用户状态被锁定，请先解锁！");
            }
            if (EAgentUserLevel.FOUR.getCode()
                .equals(refUserAgentUser.getLevel())) {
                parentUserId = null;
            } else {
                parentUserId = refUserAgentUser.getUserId();
            }
        }

        // 更新资料
        agentUserBO.refreshToApprove(agentUser, userReferee, parentUserId,
            req.getUserId());
        companyBO.refreshCompany(req);
    }

    private void checkAgent(AgentUser parentAgentUser) {
        if (!EAgentUserType.Agent.getCode().equals(parentAgentUser.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "上级用户不是代理商");
        }
        if (StringUtils.isBlank(parentAgentUser.getLevel())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "上级用户代理等级不存在");
        }
        if (!EAgentUserStatus.NORMAL.getCode()
            .equals(parentAgentUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "上级用户状态被锁定，请先解锁！");
        }
        if (EAgentUserLevel.FOUR.getCode().equals(parentAgentUser.getLevel())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "上级用户是四级代理，不能往下添加");
        }
    }

    @Override
    public void approveAgentUser(String userId, String approveResult,
            String isTop, String parentUserId, String updater, String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);
        if (!EAgentUserStatus.TO_APPROVE.getCode()
            .equals(agentUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户不处于待审核状态！");
        }

        if (EBoolean.YES.getCode().equals(approveResult)) {
            pass(agentUser, isTop, parentUserId, updater, remark);
        } else {
            agentUserBO.refreshStatus(userId,
                EAgentUserStatus.APPROVE_NO.getCode(), updater, remark);
        }
    }

    private void pass(AgentUser agentUser, String isTop, String parentUserId,
            String updater, String remark) {
        if (StringUtils.isBlank(isTop)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "是否顶级不能为空");
        }

        String level = null;
        if (EBoolean.YES.getCode().equals(isTop)) {// 判断是否顶级
            level = EAgentUserLevel.FIRST.getCode();
            parentUserId = "0";
        } else {
            if (StringUtils.isBlank(parentUserId)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "上级用户编号不能为空");
            }
            AgentUser parentAgentUser = agentUserBO.getAgentUser(parentUserId);
            checkAgent(parentAgentUser);
            level = Integer.valueOf(parentAgentUser.getLevel()) + 1 + "";
        }

        agentUserBO.refreshPass(agentUser, level, parentUserId, updater,
            remark);
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
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "730079");

        // 修改密码
        agentUserBO.refreshLoginPwd(agentUser.getUserId(), newLoginPwd);

        // 发送短信
        smsOutBO.sendSmsOut(mobile,
            String.format(SysConstants.DO_RESET_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.LOGIN_PWD_RESET.getCode());
    }

    @Override
    public void setTradePwd(String userId, String tradePwd, String smsCaptcha) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(agentUser.getMobile(), smsCaptcha, "730082");

        // 修改支付密码
        agentUserBO.refreshTradePwd(userId, tradePwd);
    }

    @Override
    public void doModifyTradePwd(String userId, String newTradePwd,
            String oldTradePwd) {
        AgentUser condition = new AgentUser();
        condition.setUserId(userId);
        condition.setTradePwd(MD5Util.md5(oldTradePwd));
        List<AgentUser> list = agentUserBO.queryAgentUserList(condition);

        AgentUser agentUser = null;
        if (CollectionUtils.isNotEmpty(list)) {
            agentUser = list.get(0);
        } else {
            throw new BizException("li01008", "旧支付密码密码不正确");
        }
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新支付密码与原有支付密码重复");
        }

        agentUserBO.refreshTradePwd(userId, newTradePwd);

        // 发短信
        String mobile = agentUser.getMobile();
        smsOutBO.sendSmsOut(mobile,
            String.format(SysConstants.DO_MODIFY_TRADE_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.MODIFY_TRADE_PWD.getCode());
    }

    @Override
    public void resetTradePwd(String userId, String newTradePwd,
            String smsCaptcha) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(agentUser.getMobile(), smsCaptcha, "730084");

        // 修改支付密码
        agentUserBO.refreshTradePwd(userId, newTradePwd);
    }

    @Override
    public void doLogInOut(String userId, String updater, String remark) {
        AgentUser agentUser = agentUserBO.getAgentUser(userId);

        String status = null;
        if (EAgentUserStatus.Ren_Locked.getCode().equals(agentUser.getStatus())
                || EAgentUserStatus.Li_Locked.getCode()
                    .equals(agentUser.getStatus())) {
            status = EAgentUserStatus.NORMAL.getCode();
        } else {
            status = EAgentUserStatus.Ren_Locked.getCode();
        }

        agentUserBO.refreshStatus(userId, status, updater, remark);
    }

    @Override
    public String doAddSalesman(String parentUserId, String mobile,
            String loginPwd) {
        // 验证手机号是否存在
        agentUserBO.isMobileExist(mobile);

        // 注册用户
        return agentUserBO.doAddSalesman(parentUserId, mobile, loginPwd);
    }

    @Override
    public Paginable<AgentUser> queryAgentUserPage(int start, int limit,
            AgentUser condition) {
        Paginable<AgentUser> page = agentUserBO.getPaginable(start, limit,
            condition);
        if (null != page) {
            for (AgentUser data : page.getList()) {
                initAgentUser(data);
            }
        }
        return page;
    }

    @Override
    public List<AgentUser> queryAgentUserList(AgentUser condition) {
        List<AgentUser> list = agentUserBO.queryAgentUserList(condition);
        for (AgentUser data : list) {
            initAgentUser(data);
        }
        return list;
    }

    @Override
    public AgentUser getAgentUser(String userId) {
        AgentUser data = agentUserBO.getAgentUser(userId);
        initAgentUser(data);
        return data;
    }

    private void initAgentUser(AgentUser data) {
        if (EAgentUserType.Agent.getCode().equals(data.getType())) {
            Company company = companyBO.getCompanyByUserId(data.getUserId());
            data.setCompany(company);
            if (StringUtils.isNotBlank(data.getParentUserId())
                    && !EBoolean.NO.getCode().equals(data.getParentUserId())) {
                AgentUser parentAgentUser = agentUserBO
                    .getAgentUser(data.getParentUserId());
                data.setParentAgentUser(parentAgentUser);
            }
        }
    }

}
