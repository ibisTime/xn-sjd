package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

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
import com.ogc.standard.enums.EAgentUserKind;
import com.ogc.standard.enums.EAgentUserStatus;
import com.ogc.standard.enums.ELanguage;
import com.ogc.standard.exception.BizException;

@Component
public class AgentUserBOImpl extends PaginableBOImpl<AgentUser>
        implements IAgentUserBO {

    @Autowired
    private IAgentUserDAO agentUserDAO;

    @Override
    public boolean isAgentUserExist(String userId) {
        AgentUser condition = new AgentUser();
        condition.setUserId(userId);
        if (agentUserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            PhoneUtil.checkMobile(mobile);
            AgentUser condition = new AgentUser();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("000001", ELanguage.zh_CN);
            }
        }
    }

    @Override
    public boolean isLoginNameExist(String loginName) {
        AgentUser condition = new AgentUser();
        condition.setLoginName(loginName);
        if (agentUserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String doRegister(String mobile, String loginPwd) {
        AgentUser agentUser = new AgentUser();

        String userId = OrderNoGenerater.generate("AU");
        agentUser.setUserId(userId);
        agentUser.setMobile(mobile);
        agentUser.setLoginName(mobile);
        agentUser.setStatus(EAgentUserStatus.TO_APPROVE.getCode());

        if (StringUtils.isNotBlank(loginPwd)) {
            agentUser.setLoginPwd(MD5Util.md5(loginPwd));
            agentUser
                .setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        }

        agentUser.setType(EAgentUserKind.Agent.getCode());
        agentUser.setCreateDatetime(new Date());
        agentUserDAO.insert(agentUser);
        return userId;
    }

    @Override
    public String doAddSalesman(String mobile, String loginPwd) {
        AgentUser agentUser = new AgentUser();

        String userId = OrderNoGenerater.generate("AU");
        agentUser.setUserId(userId);
        agentUser.setMobile(mobile);
        agentUser.setLoginName(mobile);
        agentUser.setStatus(EAgentUserStatus.TO_APPROVE.getCode());

        if (StringUtils.isNotBlank(loginPwd)) {
            agentUser.setLoginPwd(MD5Util.md5(loginPwd));
            agentUser
                .setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        }

        agentUser.setType(EAgentUserKind.Salesman.getCode());
        agentUser.setCreateDatetime(new Date());
        agentUserDAO.insert(agentUser);
        return userId;
    }

    @Override
    public void doAddAgentUser(AgentUser data) {
        agentUserDAO.insert(data);
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(tradePwd)) {
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
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
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
        if (StringUtils.isNotBlank(userId)
                && StringUtils.isNotBlank(loginPwd)) {
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
    public void refreshLevel(String userId, String level) {
        AgentUser data = new AgentUser();
        data.setUserId(userId);
        data.setLevel(level);
        data.setUpdateDatetime(new Date());
        agentUserDAO.updateLevel(data);
    }

    @Override
    public AgentUser getUserByMobile(String mobile) {
        AgentUser data = null;
        if (StringUtils.isNotBlank(mobile)) {
            AgentUser condition = new AgentUser();
            condition.setMobile(mobile);
            data = agentUserDAO.select(condition);
        }
        return data;
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
            data = agentUserDAO.select(condition);
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
