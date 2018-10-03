package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAgentUserDAO;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.enums.EAgentUserStatus;
import com.ogc.standard.enums.EAgentUserType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class AgentUserBOImpl extends PaginableBOImpl<AgentUser> implements
        IAgentUserBO {

    @Autowired
    private IAgentUserDAO agentUserDAO;

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            PhoneUtil.checkMobile(mobile);
            AgentUser condition = new AgentUser();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("000001", "手机号已存在");
            }
        }
    }

    @Override
    public String doRegister(String mobile, String loginPwd) {
        AgentUser agentUser = new AgentUser();
        String userId = OrderNoGenerater.generate("AU");
        agentUser.setUserId(userId);
        agentUser.setMobile(mobile);
        agentUser.setLoginName(mobile);
        agentUser.setStatus(EAgentUserStatus.TO_FILL.getCode());
        agentUser.setLoginPwd(MD5Util.md5(loginPwd));

        agentUser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        agentUser.setType(EAgentUserType.Agent.getCode());
        agentUser.setCreateDatetime(new Date());
        agentUserDAO.insert(agentUser);
        return userId;
    }

    @Override
    public String doAddSalesman(String mobile, String loginPwd) {
        AgentUser agentUser = new AgentUser();
        String userId = OrderNoGenerater.generate(EGeneratePrefix.AgentUser
            .getCode());
        agentUser.setUserId(userId);
        agentUser.setType(EAgentUserType.Salesman.getCode());
        agentUser.setMobile(mobile);

        agentUser.setLoginName(mobile);
        agentUser.setLoginPwd(MD5Util.md5(loginPwd));
        agentUser.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        agentUser.setCreateDatetime(new Date());
        agentUser.setStatus(EAgentUserStatus.NORMAL.getCode());

        agentUserDAO.insert(agentUser);
        return userId;
    }

    @Override
    public String doAddAgentUser(String mobile, String loginPwd) {
        AgentUser data = new AgentUser();
        String userId = OrderNoGenerater.generate(EGeneratePrefix.AgentUser
            .getCode());
        data.setUserId(userId);
        data.setType(EAgentUserType.Agent.getCode());
        data.setLoginName(mobile);

        data.setMobile(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setCreateDatetime(new Date());
        data.setStatus(EAgentUserStatus.NORMAL.getCode());

        agentUserDAO.insert(data);
        return userId;
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
            AgentUser agentUser = this.getAgentUser(userId);
            if (StringUtils.isBlank(agentUser.getTradePwdStrength())) {
                throw new BizException("jd00001", "请您先设置支付密码！");
            }
            AgentUser condition = new AgentUser();
            condition.setUserId(userId);
            condition.setTradePwd(MD5Util.md5(tradePwd));
            if (this.getTotalCount(condition) != 1) {
                throw new BizException("jd00001", "支付密码错误");
            }
        } else {
            throw new BizException("jd00001", "支付密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            AgentUser condition = new AgentUser();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd, String alertStr) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            AgentUser condition = new AgentUser();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", alertStr + "错误");
            }
        } else {
            throw new BizException("jd00001", alertStr + "错误");
        }
    }

    @Override
    public void refreshRealName(String userId, String realName) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateRealName(data);
    }

    @Override
    public void refreshLoginPwd(String userId, String loginPwd) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateLoginPwd(data);
    }

    @Override
    public void refreshTradePwd(String userId, String tradePwd) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setLoginPwd(MD5Util.md5(tradePwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(tradePwd));
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateTradePwd(data);
    }

    @Override
    public void refreshMobile(String userId, String mobile) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setMobile(mobile);
        data.setLoginName(mobile);
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateMobile(data);
    }

    @Override
    public void refreshToApprove(AgentUser data, String parentUserId,
            String updater, String remark) {
        data.setStatus(EAgentUserStatus.TO_APPROVE.getCode());
        data.setParentUserId(parentUserId);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        agentUserDAO.updateToApprove(data);
    }

    @Override
    public void refreshStatus(String userId, String status, String updater,
            String remark) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateStatus(data);
    }

    @Override
    public void refreshPhoto(String userId, String photo) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setPhoto(photo);
        data.setUpdateDatetime(new Date());
        agentUserDAO.updatePhoto(data);
    }

    @Override
    public List<AgentUser> queryAgentUserList(AgentUser condition) {
        return agentUserDAO.selectList(condition);
    }

    @Override
    public AgentUser getAgentUserByMobile(String mobile) {
        AgentUser data = null;
        if (StringUtils.isNotBlank(mobile)) {
            AgentUser condition = new AgentUser();
            condition.setMobile(mobile);
            List<AgentUser> list = agentUserDAO.selectList(condition);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理用户"
                        + mobile + "不存在！");
            }
            data = list.get(0);
        }
        return data;
    }

    @Override
    public AgentUser getAgentUser(String userId) {
        AgentUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            AgentUser condition = new AgentUser();
            condition.setUserId(userId);
            data = agentUserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代理用户不存在！");
            }
        }
        return data;
    }
}
