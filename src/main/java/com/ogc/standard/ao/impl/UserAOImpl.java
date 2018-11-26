/**
 * @Title UserAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午1:50:42 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ogc.standard.ao.IAccountAO;
import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserExtBO;
import com.ogc.standard.bo.IUserRelationBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.common.WechatConstant;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.req.XN802399Req;
import com.ogc.standard.dto.req.XN802400Req;
import com.ogc.standard.dto.req.XN805041Req;
import com.ogc.standard.dto.req.XN805042Req;
import com.ogc.standard.dto.req.XN805051Req;
import com.ogc.standard.dto.req.XN805070Req;
import com.ogc.standard.dto.req.XN805081Req;
import com.ogc.standard.dto.res.XN805051Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECaptchaType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EIDKind;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESex;
import com.ogc.standard.enums.ESignLogType;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.enums.EUserKind;
import com.ogc.standard.enums.EUserLevel;
import com.ogc.standard.enums.EUserPwd;
import com.ogc.standard.enums.EUserRefereeType;
import com.ogc.standard.enums.EUserReleationType;
import com.ogc.standard.enums.EUserStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import com.ogc.standard.http.PostSimulater;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午1:50:42 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {

    private static Logger logger = Logger.getLogger(UserAOImpl.class);

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISignLogBO signLogBO;

    @Autowired
    private ISignLogAO signLogAO;

    @Autowired
    private IUserExtBO userExtBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAccountAO accountAO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IUserRelationBO userRelationBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void doCheckMobile(String mobile) {
        userBO.isMobileExist(mobile);
    }

    @Override
    @Transactional
    public String doRegisterByMobile(XN805041Req req) {
        // 验证手机号是否存在
        userBO.isMobileExist(req.getMobile());

        // 验证短信验证码
        smsOutBO.checkCaptcha(req.getMobile(), req.getSmsCaptcha(),
            ECaptchaType.C_REG.getCode());

        // 获取推荐用户相对应的代理信息
        String agentId = null;
        String userReferee = null;
        if (StringUtils.isNotBlank(req.getUserRefereeType())) {
            if (EUserRefereeType.USER.getCode()
                .equals(req.getUserRefereeType())) {
                User refereeUser = userBO.getUserByMobile(req.getUserReferee());
                userReferee = refereeUser.getUserId();
                agentId = refereeUser.getAgentId();
            } else if (EUserRefereeType.AGENT.getCode().equals(
                req.getUserRefereeType())) {
                AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                    .getUserReferee());
                userReferee = agentUser.getUserId();
                agentId = agentUser.getUserId();
            } else if (EUserRefereeType.SALEMANS.getCode().equals(
                req.getUserRefereeType())) {
                AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                    .getUserReferee());
                userReferee = agentUser.getUserId();
                agentId = agentUser.getParentUserId();
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐类型不支持");
            }
        }

        // 注册用户
        String userId = userBO.doRegister(req.getMobile(), req.getNickname(),
            req.getLoginPwd(), agentId, userReferee, req.getUserRefereeType(),
            req.getProvince(), req.getCity(), req.getArea());

        // C端用户推荐C端用户时添加好友关系
        if (EUserRefereeType.USER.getCode().equals(req.getUserRefereeType())) {
            userRelationBO.saveUserRelation(userId, userReferee,
                EUserReleationType.FRIEND.getCode());
            userRelationBO.saveUserRelation(userReferee, userId,
                EUserReleationType.FRIEND.getCode());
        }

        // C端自行添加自己为好友
        userRelationBO.saveUserRelation(userId, userId,
            EUserReleationType.FRIEND.getCode());

        // ext中添加数据
        userExtBO.addUserExt(userId);

        // 分配账户
        accountAO.distributeAccount(userId);
        return userId;

    }

    @Override
    @Transactional
    public void doAssignRegistJf(String userId, String userReferee,
            String userRefereeType) {
        // 用户注册添加积分
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.REGISTER));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userJfAccount = accountBO.getAccountByUser(userId,
            ECurrency.JF.getCode());
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        // // 积分池不足时将剩余积分转给用户
        // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
        // quantity = sysJfAccount.getAmount();
        // }

        accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
            EJourBizTypeUser.REGIST.getCode(),
            EJourBizTypePlat.REGIST.getCode(),
            EJourBizTypeUser.REGIST.getValue(),
            EJourBizTypePlat.REGIST.getValue(), userId);

        // 推荐用户添加积分
        if (StringUtils.isNotBlank(userReferee)
                && EUserRefereeType.USER.getCode().equals(userRefereeType)) {
            User refereeUser = userBO.getUserByMobile(userReferee);
            Account userRefereeJfAccount = accountBO.getAccountByUser(
                refereeUser.getUserId(), ECurrency.JF.getCode());
            quantity = new BigDecimal(configMap.get(SysConstants.INVITE_USER));
            quantity = AmountUtil.mul(quantity, 1000L);

            // 积分池不足时将剩余积分转给用户
            // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
            // quantity = sysJfAccount.getAmount();
            // }

            accountBO.transAmount(sysJfAccount, userRefereeJfAccount, quantity,
                EJourBizTypeUser.INVITE_USER.getCode(),
                EJourBizTypePlat.INVITE_USER.getCode(),
                EJourBizTypeUser.INVITE_USER.getValue(),
                EJourBizTypePlat.INVITE_USER.getValue(),
                refereeUser.getUserId());
        }

    }

    @Override
    @Transactional
    public String doAddUser(XN805042Req req) {
        String userId = null;
        User user = new User();
        user.setUserId(userId);
        user.setKind(EUserKind.Customer.getCode());
        user.setLoginName(req.getMobile());
        user.setMobile(req.getMobile());
        if (StringUtils.isBlank(req.getLoginPwd())) {
            req.setLoginPwd(EUserPwd.InitPwd8.getCode());
        }
        user.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
            .getLoginPwd()));
        user.setLevel(EUserLevel.ZERO.getCode());
        user.setUserReferee(req.getUserReferee());
        user.setIdKind(req.getIdKind());
        user.setIdNo(req.getIdNo());
        user.setRealName(req.getRealName());
        user.setStatus(EUserStatus.NORMAL.getCode());
        user.setProvince(req.getProvince());
        user.setCity(req.getCity());
        user.setArea(req.getArea());
        user.setLatitude(req.getLatitude());
        user.setLongitude(req.getLongitude());
        Date date = new Date();
        user.setCreateDatetime(date);
        user.setUpdater(req.getUpdater());
        user.setRemark(req.getRemark());
        userId = userBO.doAddUser(user);

        // 在userExt中添加一条数据
        userExtBO.addUserExt(userId);
        // 分配账户
        accountAO.distributeAccount(userId);
        return userId;
    }

    @Override
    @Transactional
    public String doLogin(String loginName, String loginPwd, String client,
            String location) {
        User condition = new User();
        condition.setMobile(loginName);

        List<User> userList = userBO.queryUserList(condition);
        User condition1 = new User();
        condition1.setEmail(loginName);
        userList.addAll(userBO.queryUserList(condition1));
        if (CollectionUtils.isEmpty(userList)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        User con = new User();
        con.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> listUser = userBO.queryUserList(con);
        if (CollectionUtils.isEmpty(userList)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        String userId = null;
        for (User user : listUser) {
            if (loginName.equals(user.getMobile())
                    || loginName.equals(user.getEmail())) {
                userId = user.getUserId();
                break;
            }
        }
        if (userId == null) {
            throw new BizException("xn805050", "登录密码错误");
        }
        User user = userBO.getUser(userId);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050", "该账号"
                    + EUserStatus.getMap().get(user.getStatus()).getValue()
                    + "，请联系工作人员");
        }

        // 增加登陆日志
        SignLog data = new SignLog();
        data.setUserId(user.getUserId());
        data.setType(ESignLogType.LOGIN.getCode());
        data.setClient(client);
        data.setLocation(location);
        signLogBO.saveSignLog(data);

        return userId;
    }

    @Override
    public void doAssignLoginJF(String userId) {
        if (signLogBO.isFirstCheckIn(userId, ESignLogType.LOGIN.getCode())) {
            // 添加积分
            long continueLoginDay = signLogAO.keepCheckIn(userId,
                ESignLogType.LOGIN.getCode());// 连续登录天数

            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());
            BigDecimal quantity = new BigDecimal(
                configMap.get(SysConstants.SIGN_JF));
            BigDecimal continueLoginRate = new BigDecimal(
                configMap.get(SysConstants.CONTINUE_LOGIN_RATE));
            quantity = AmountUtil.mul(quantity, 1000L);
            quantity = AmountUtil.mul(quantity, continueLoginDay);// 连续签到天数
            quantity = AmountUtil.mul(quantity, continueLoginRate);// 连续签到比例

            Account userJfAccount = accountBO.getAccountByUser(userId,
                ECurrency.JF.getCode());
            Account sysJfAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

            // 积分池不足时将剩余积分转给用户
            // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
            // quantity = sysJfAccount.getAmount();
            // }

            String note = "获得" + AmountUtil.div(quantity, 1000L).intValue()
                    + "积分，已连续登录" + continueLoginDay + "天";
            accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
                EJourBizTypeUser.LOGIN.getCode(),
                EJourBizTypePlat.LOGIN.getCode(), note, note, userId);
        }
    }

    // 微信登录送积分
    private BigDecimal addLoginAmount(User user) {
        BigDecimal quantity = BigDecimal.ZERO;

        if (signLogBO.isFirstCheckIn(user.getUserId(),
            ESignLogType.LOGIN.getCode())) {
            // 添加积分
            long continueLoginDay = signLogAO.keepCheckIn(user.getUserId(),
                ESignLogType.LOGIN.getCode());// 连续登录天数

            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());
            BigDecimal continueLoginRate = new BigDecimal(
                configMap.get(SysConstants.CONTINUE_LOGIN_RATE));
            quantity = AmountUtil.mul(quantity, 1000L);
            quantity = AmountUtil.mul(quantity, continueLoginDay);// 连续签到天数
            quantity = AmountUtil.mul(quantity, continueLoginRate);// 连续签到比例

            Account userJfAccount = accountBO.getAccountByUser(
                user.getUserId(), ECurrency.JF.getCode());
            Account sysJfAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

            // 积分池不足时将剩余积分转给用户
            // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
            // quantity = sysJfAccount.getAmount();
            // }

            accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
                EJourBizTypeUser.LOGIN.getCode(),
                EJourBizTypePlat.LOGIN.getCode(),
                EJourBizTypeUser.LOGIN.getValue(),
                EJourBizTypePlat.LOGIN.getValue(), user.getUserId());
        }

        return quantity;
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha) {
        User user = userBO.getUser(userId);

        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 验证手机号
        userBO.isMobileExist(newMobile);

        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805061");
        userBO.refreshMobile(userId, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(oldMobile, String.format(
            SysConstants.DO_CHANGE_MOBILE_CN, PhoneUtil.hideMobile(oldMobile),
            DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_1),
            newMobile), ECaptchaType.MOBILE_CHANGE.getCode());

    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd) {
        User user = userBO.getUser(userId);

        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        userBO.isMobileExist(newMobile);
        // 验证支付密码
        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(userId, tradePwd);
        }
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805062");
        userBO.refreshMobile(userId, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(oldMobile, String.format(
            SysConstants.DO_CHANGE_MOBILE_CN, PhoneUtil.hideMobile(oldMobile),
            DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_1),
            newMobile), ECaptchaType.MOBILE_CHANGE.getCode());
    }

    @Override
    @Transactional
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
        String userId = userBO.getUserId(mobile);
        if (StringUtils.isBlank(userId)) {
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805063");
        userBO.refreshLoginPwd(userId, newLoginPwd);
        // 发送短信
        smsOutBO.sendSmsOut(
            mobile,
            String.format(SysConstants.DO_RESET_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.LOGIN_PWD_RESET.getCode());
    }

    @Override
    @Transactional
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        User user = userBO.getUser(userId);
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }

        // 重置
        userBO.refreshLoginPwd(userId, newLoginPwd);
        // 发送短信

        smsOutBO.sendSmsOut(
            user.getMobile(),
            String.format(SysConstants.DO_MODIFY_LOGIN_PWD_CN,
                PhoneUtil.hideMobile(user.getMobile())),
            ECaptchaType.MODIFY_LOGIN_PWD.getCode());

    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd) {
        // 验证当前登录密码是否正确
        sysUserBO.checkLoginPwd(adminUserId, adminPwd);
        userBO.refreshLoginPwd(userId, loginPwd);
    }

    @Override
    @Transactional
    public void doSetTradePwd(String userId, String tradePwd, String smsCaptcha) {
        User user = userBO.getUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805066");
        // 修改支付密码
        userBO.refreshTradePwd(userId, tradePwd);
        // 发送短信
        // String mobile = user.getMobile();
        // smsOutBO.sendInterSmsOut(user.getInterCode(), mobile,
        // "尊敬的" + PhoneUtil.hideMobile(mobile)
        // + "用户，您的资金密码设置成功。请妥善保管您的账户相关信息。",
        // user.getCompanyCode(), user.getSystemCode());
    }

    @Override
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha) {
        User user = userBO.getUser(userId);
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805067");
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发短信
        smsOutBO.sendSmsOut(
            mobile,
            String.format(SysConstants.DO_RESET_TRADE_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.RESET_TRADE_PWD.getCode());

    }

    @Override
    @Transactional
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo) {
        User user = userBO.getUser(userId);
        if (user.getIdKind() == null || user.getIdNo() == null) {
            throw new BizException("li01004", "请先实名认证");
        }
        // 证件是否正确
        if (!(user.getIdKind().equalsIgnoreCase(idKind) && user.getIdNo()
            .equalsIgnoreCase(idNo))) {
            throw new BizException("li01009", "身份证不符合");
        }
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805068");
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发短信
        smsOutBO.sendSmsOut(
            mobile,
            String.format(SysConstants.DO_RESET_TRADE_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.RESET_TRADE_PWD.getCode());
    }

    @Override
    @Transactional
    public void doModifyTradePwd(String userId, String oldTradePwd,
            String newTradePwd) {

        User conditon = new User();
        conditon.setUserId(userId);
        conditon.setTradePwd(MD5Util.md5(oldTradePwd));
        List<User> list = userBO.queryUserList(conditon);
        User user = null;
        if (CollectionUtils.isNotEmpty(list)) {
            user = list.get(0);
        } else {
            throw new BizException("li01008", "旧资金密码不正确");
        }
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新支付密码与原有支付密码重复");
        }
        userBO.refreshTradePwd(userId, newTradePwd);
        String mobile = user.getMobile();
        // 发短信
        smsOutBO.sendSmsOut(
            mobile,
            String.format(SysConstants.DO_MODIFY_TRADE_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.MODIFY_TRADE_PWD.getCode());
    }

    @Override
    @Transactional
    public void modifyPhoto(String userId, String photo) {

        // 添加积分
        User user = userBO.getUser(userId);
        if (null == user.getPhoto()) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());
            BigDecimal quantity = new BigDecimal(
                configMap.get(SysConstants.UPLOAD_PHOTO));
            quantity = AmountUtil.mul(quantity, 1000L);

            Account userJfAccount = accountBO.getAccountByUser(userId,
                ECurrency.JF.getCode());
            Account sysJfAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

            // 积分池不足时将剩余积分转给用户
            // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
            // quantity = sysJfAccount.getAmount();
            // }

            accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
                EJourBizTypeUser.UPLOAD_PHOTO.getCode(),
                EJourBizTypePlat.UPLOAD_PHOTO.getCode(),
                EJourBizTypeUser.UPLOAD_PHOTO.getValue(),
                EJourBizTypePlat.UPLOAD_PHOTO.getValue(), userId);
        }

        userBO.refreshPhoto(userId, photo);

    }

    @Override
    public void doModifyNickname(String userId, String nickname) {
        userBO.refreshNickname(userId, nickname);
    }

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        User user = userBO.getUserUnCheck(userId);
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = String.format(SysConstants.DO_LOCK_USER_CN,
                PhoneUtil.hideMobile(mobile));
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = String.format(SysConstants.DO_UNLOCK_USER_CN,
                PhoneUtil.hideMobile(mobile));
            userStatus = EUserStatus.NORMAL;

        }
        userBO.refreshStatus(userId, userStatus, updater, remark);
        smsOutBO.sendSmsOut(mobile, smsContent,
            ECaptchaType.ACTIVATE_OR_LOGOFF.getCode());
    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        for (User user : list) {
            // 推荐人转义
            initUserRef(user);
        }
        return page;
    }

    @Override
    public User doGetUser(String userId) {
        User user = userBO.getUser(userId);

        // 是否设置过交易密码
        if (StringUtils.isNotBlank(user.getTradePwdStrength())) {
            user.setTradepwdFlag(true);
        } else {
            user.setTradepwdFlag(false);
        }

        // 是否设置过登录密码
        if (StringUtils.isNotBlank(user.getLoginPwdStrength())) {
            user.setLoginPwdFlag(true);
        } else {
            user.setLoginPwdFlag(false);
        }

        // 是否绑定邮箱
        if (StringUtils.isNotBlank(user.getEmail())) {
            user.setEmailBindFlag(true);
        } else {
            user.setEmailBindFlag(false);
        }

        // 拉取ext数据
        UserExt data = userExtBO.getUserExt(userId);
        if (data != null) {
            user.setGender(data.getGender());
            user.setAge(data.getAge());
            user.setBirthday(data.getBirthday());
            user.setDiploma(data.getDiploma());
            user.setIntroduce(data.getIntroduce());
            user.setOccupation(data.getOccupation());
            user.setPdf(data.getPdf());
            user.setWorkTime(data.getWorkTime());
            user.setGradDatetime(data.getGradDatetime());
        }

        user.setUserExt(data);

        // 推荐人转义
        initUserRef(user);

        return user;
    }

    private void initUserRef(User user) {
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            String mobile = null;
            if (EUserRefereeType.AGENT.getCode().equals(
                user.getUserRefereeType())
                    || EUserRefereeType.SALEMANS.getCode().equals(
                        user.getUserRefereeType())) {
                AgentUser agentUser = agentUserBO.getAgentUser(user
                    .getUserReferee());
                mobile = agentUser.getMobile();
            } else if (EUserRefereeType.USER.getCode().equals(
                user.getUserRefereeType())) {
                User userReferee = userBO.getUser(user.getUserReferee());
                mobile = userReferee.getMobile();
            }
            // 拉取推荐人信息
            User refereeUser = new User();
            refereeUser.setMobile(mobile);
            user.setRefereeUser(refereeUser);
        }
    }

    @Override
    @Transactional
    public void doBindMobile(String isSendSms, String mobile,
            String smsCaptcha, String userId) {
        User user = userBO.getUser(userId);

        if (user.getMobile() != null) {
            throw new BizException("xn000000", "用户已绑定手机");
        }
        // 验证手机号
        userBO.isMobileExist(mobile);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805060");

        userBO.refreshMobile(userId, mobile);

        // 发送短信
        if (isSendSms.equals(EBoolean.YES.getCode())) {
            smsOutBO.sendSmsOut(mobile, String.format(
                SysConstants.DO_BIND_MOBILE_CN, PhoneUtil.hideMobile(mobile),
                DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_1),
                mobile), ECaptchaType.MOBILE_CHANGE.getCode());
        }

        // 添加积分
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.BIND_MOBILE));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userJfAccount = accountBO.getAccountByUser(userId,
            ECurrency.JF.getCode());
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        // 积分池不足时将剩余积分转给用户
        // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
        // quantity = sysJfAccount.getAmount();
        // }

        accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
            EJourBizTypeUser.BIND_MOBILE.getCode(),
            EJourBizTypePlat.BIND_MOBILE.getCode(),
            EJourBizTypeUser.BIND_MOBILE.getValue(),
            EJourBizTypePlat.BIND_MOBILE.getValue(), userId);

    }

    @Override
    public void doChangeLocation(XN805081Req req) {
        User data = userBO.getUser(req.getUserId());

        data.setAddress(req.getAdress());
        data.setArea(req.getArea());
        data.setCity(req.getCity());
        data.setLatitude(req.getLatitude());
        data.setLongitude(req.getLongitude());
        data.setProvince(req.getProvince());
        data.setRemark(req.getRemark());
        data.setUpdater(req.getUpdater());
        userBO.refreshLocation(data);
    }

    @Override
    public void doResetReferee(String userId, String userReferee, String updater) {
        userBO.refreshReferee(userId, userReferee, updater);
    }

    @Override
    public void doIdentify(String userId, String idKind, String idNo,
            String realName) {
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
    }

    @Override
    public void doTwoIdentify(String userId, String idKind, String idNo,
            String realName) {
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);

    }

    @Override
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile) {
        // 三方认证
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);

    }

    @Override
    @Transactional
    public void bindEmail(String captcha, String email, String userId) {
        smsOutBO.checkCaptcha(email, captcha, "805086");

        User data = userBO.getUser(userId);
        if (data.getEmail() != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户已绑定邮箱");
        }
        userBO.isEmailExist(email);

        userBO.refreshEmail(userId, email);

        // 添加积分
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.BIND_EMAIL));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userJfAccount = accountBO.getAccountByUser(userId,
            ECurrency.JF.getCode());
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        // 积分池不足时将剩余积分转给用户
        // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
        // quantity = sysJfAccount.getAmount();
        // }

        accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
            EJourBizTypeUser.BIND_email.getCode(),
            EJourBizTypePlat.BIND_email.getCode(),
            EJourBizTypeUser.BIND_email.getValue(),
            EJourBizTypePlat.BIND_email.getValue(), userId);
    }

    @Override
    @Transactional
    public void doModifyUserInfo(XN805070Req req) {

        // 添加积分
        User user = userBO.getUser(req.getUserId());
        if (null == user.getRealName()) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());
            BigDecimal quantity = new BigDecimal(
                configMap.get(SysConstants.COMPLETE_INFO));
            quantity = AmountUtil.mul(quantity, 1000L);

            Account userJfAccount = accountBO.getAccountByUser(req.getUserId(),
                ECurrency.JF.getCode());
            Account sysJfAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

            // 积分池不足时将剩余积分转给用户
            // if (quantity.compareTo(sysJfAccount.getAmount()) == 1) {
            // quantity = sysJfAccount.getAmount();
            // }

            accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
                EJourBizTypeUser.COMPLETE_INFO.getCode(),
                EJourBizTypePlat.COMPLETE_INFO.getCode(),
                EJourBizTypeUser.COMPLETE_INFO.getValue(),
                EJourBizTypePlat.COMPLETE_INFO.getValue(), req.getUserId());

        }

        userBO.refreshUserInfo(req.getUserId(), req.getNickname(),
            req.getRealName(), EIDKind.IDCard.getCode(), req.getIdNo());

        UserExt data = userExtBO.getUserExt(req.getUserId());
        data.setGender(req.getGender());
        data.setAge(req.getAge());
        data.setBirthday(req.getBirthday());
        userExtBO.refreshUserExt(data);

    }

    //
    @Override
    public XN805051Res doLoginWeChat(XN805051Req req) {
        // Step1：获取密码参数信息
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.WEIXIN_H5.getCode());

        String appId = null;
        String appSecret = null;
        if (ESysConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
            appId = configMap.get(SysConstants.WX_H5_ACCESS_KEY);
            appSecret = configMap.get(SysConstants.WX_H5_SECRET_KEY);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "登录类型不支持");
        }

        if (StringUtils.isBlank(appId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "参数appId配置获取失败，请检查配置");
        }
        if (StringUtils.isBlank(appSecret)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "参数appSecret配置获取失败，请检查配置");
        }

        // Step2：通过Authorization Code获取Access Token
        String accessToken = "";
        Map<String, String> res = new HashMap<>();
        Properties fromProperties = new Properties();
        fromProperties.put("grant_type", "authorization_code");
        fromProperties.put("appid", appId);
        fromProperties.put("secret", appSecret);
        fromProperties.put("code", req.getCode());
        logger.info("appId:" + appId + ",appSecret:" + appSecret + ",code:"
                + req.getCode());
        XN805051Res result = null;

        try {
            String response = PostSimulater.requestPostForm(
                WechatConstant.WX_TOKEN_URL, fromProperties);
            res = getMapFromResponse(response);
            accessToken = (String) res.get("access_token");
            if (res.get("error") != null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "微信登录失败原因：" + res.get("error"));
            }
            if (StringUtils.isBlank(accessToken)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "accessToken不能为空");
            }

            // Step3：使用Access Token来获取用户的OpenID
            String openId = (String) res.get("openid");
            // 获取unionid
            Map<String, String> wxRes = new HashMap<>();
            Properties queryParas = new Properties();
            queryParas.put("access_token", accessToken);
            queryParas.put("openid", openId);
            queryParas.put("lang", "zh_CN");
            wxRes = getMapFromResponse(PostSimulater.requestPostForm(
                WechatConstant.WX_USER_INFO_URL, queryParas));
            String unionId = (String) wxRes.get("unionid");
            String h5OpenId = null;
            if (ESysConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
                h5OpenId = (String) wxRes.get("openid");
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "微信现只支持公众号登录");
            }

            // Step4：根据openId，unionId从数据库中查询用户信息
            User dbUser = userBO.doGetUserByOpenId(h5OpenId);
            if (null != dbUser && StringUtils.isNotBlank(dbUser.getMobile())) {// 如果user存在，说明用户授权登录过，直接登录
                BigDecimal jfAmount = addLoginAmount(dbUser);// 每天登录送积分
                result = new XN805051Res(dbUser.getUserId(),
                    EBoolean.NO.getCode(), jfAmount);
            } else {
                if (StringUtils.isBlank(req.getMobile())) {// 手机号不为空判断
                    return new XN805051Res(null, EBoolean.YES.getCode());
                }

                String nickname = (String) wxRes.get("nickname");
                String photo = (String) wxRes.get("headimgurl");
                String gender = ESex.UNKNOWN.getCode();
                if (String.valueOf(wxRes.get("sex")).equals("1.0")) {
                    gender = ESex.MEN.getCode();
                } else if (String.valueOf(wxRes.get("sex")).equals("2.0")) {
                    gender = ESex.WOMEN.getCode();
                }

                // Step5：判断注册是否传手机号，有则注册，无则反馈
                result = doWxLoginRegMobile(req, unionId, h5OpenId, nickname,
                    photo, gender);
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                e.getMessage());
        }
        return result;
    }

    private XN805051Res doWxLoginReg(XN805051Req req, String unionId,
            String h5OpenId, String nickname, String photo, String gender) {
        XN805051Res result;

        userBO.doCheckOpenId(unionId, h5OpenId);

        // 获取推荐用户相对应的代理信息
        String agentId = null;
        String userReferee = null;
        if (StringUtils.isNotBlank(req.getUserRefereeKind())) {
            if (EUserRefereeType.USER.getCode()
                .equals(req.getUserRefereeKind())) {
                User refereeUser = userBO.getUserByMobile(req.getUserReferee());
                userReferee = refereeUser.getUserId();
                agentId = refereeUser.getAgentId();
            } else if (EUserRefereeType.AGENT.getCode().equals(
                req.getUserRefereeKind())) {
                AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                    .getUserReferee());
                userReferee = agentUser.getUserId();
                agentId = agentUser.getUserId();
            } else if (EUserRefereeType.SALEMANS.getCode().equals(
                req.getUserRefereeKind())) {
                AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                    .getUserReferee());
                userReferee = agentUser.getUserId();
                agentId = agentUser.getParentUserId();
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "推荐类型不支持");
            }
        }

        // 插入用户信息
        String userId = userBO.doRegister(unionId, h5OpenId, null,
            EUserPwd.InitPwd8.getCode(), userReferee, req.getUserRefereeKind(),
            agentId, req.getKind(), nickname, photo, gender);

        // C端自行添加自己为好友
        userRelationBO.saveUserRelation(userId, userId,
            EUserReleationType.FRIEND.getCode());

        // ext中添加数据
        userExtBO.addUserExt(userId);

        // 分配账户
        accountAO.distributeAccount(userId);

        result = new XN805051Res(userId, EBoolean.YES.getCode());
        return result;
    }

    private XN805051Res doWxLoginRegMobile(XN805051Req req, String unionId,
            String h5OpenId, String nickname, String photo, String gender) {
        // 判断是否需要验证码验证码,登录前一定要验证
        if (StringUtils.isBlank(req.getSmsCaptcha())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "请输入短信验证码");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(req.getMobile(), req.getSmsCaptcha(), "805051");

        String mobileUserId = userBO.getUserId(req.getMobile(), req.getKind());
        if (StringUtils.isBlank(mobileUserId)) {
            userBO.doCheckOpenId(unionId, h5OpenId);

            // 获取推荐用户相对应的代理信息
            String agentId = null;
            String userReferee = null;
            if (StringUtils.isNotBlank(req.getUserRefereeKind())) {
                if (EUserRefereeType.USER.getCode().equals(
                    req.getUserRefereeKind())) {
                    User refereeUser = userBO.getUserByMobile(req
                        .getUserReferee());
                    userReferee = refereeUser.getUserId();
                    agentId = refereeUser.getAgentId();
                } else if (EUserRefereeType.AGENT.getCode().equals(
                    req.getUserRefereeKind())) {
                    AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                        .getUserReferee());
                    userReferee = agentUser.getUserId();
                    agentId = agentUser.getUserId();
                } else if (EUserRefereeType.SALEMANS.getCode().equals(
                    req.getUserRefereeKind())) {
                    AgentUser agentUser = agentUserBO.getAgentUserByMobile(req
                        .getUserReferee());
                    userReferee = agentUser.getUserId();
                    agentId = agentUser.getParentUserId();
                } else {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "推荐类型不支持");
                }
            }

            // 插入用户信息
            String userId = userBO.doRegister(unionId, h5OpenId,
                req.getMobile(), EUserPwd.InitPwd8.getCode(), userReferee,
                req.getUserRefereeKind(), agentId, req.getKind(), nickname,
                photo, gender);
            // 分配账户
            accountAO.distributeAccount(userId);
            mobileUserId = userId;
        } else {
            userBO.refreshWxInfo(mobileUserId, unionId, h5OpenId, nickname,
                photo, gender);
        }

        return new XN805051Res(mobileUserId, EBoolean.NO.getCode());
    }

    @Override
    public void upgradeUserLevel(String userId) {

        // 获取用户等级配置
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.USER_LEVEL.getCode());
        BigDecimal userLevel0 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_0));
        BigDecimal userLevel1 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_1));
        BigDecimal userLevel2 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_2));
        BigDecimal userLevel3 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_3));
        BigDecimal userLevel4 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_4));
        BigDecimal userLevel5 = new BigDecimal(
            configMap.get(SysConstants.USER_LEVEL_5));

        // 判断用户等级
        String userLevel = null;
        Account userJFAccount = accountBO.getAccountByUser(userId,
            ECurrency.JF.getCode());
        BigDecimal jfAmount = userJFAccount.getTotalAmount();
        jfAmount = AmountUtil.div(jfAmount, 1000L);

        if (jfAmount.compareTo(userLevel5) != -1) {
            userLevel = EUserLevel.FIVE.getCode();
        } else if (jfAmount.compareTo(userLevel4) != -1) {
            userLevel = EUserLevel.FOUR.getCode();
        } else if (jfAmount.compareTo(userLevel3) != -1) {
            userLevel = EUserLevel.THREE.getCode();
        } else if (jfAmount.compareTo(userLevel2) != -1) {
            userLevel = EUserLevel.TWO.getCode();
        } else if (jfAmount.compareTo(userLevel1) != -1) {
            userLevel = EUserLevel.ONE.getCode();
        } else if (jfAmount.compareTo(userLevel0) != -1) {
            userLevel = EUserLevel.ZERO.getCode();
        }

        userBO.refreshLevel(userId, userLevel);
    }

    @Override
    public void personAuth(String userId, String realName, String idNo,
            String idPic, String introduce) {
        userBO.refreshIdentity(userId, realName, null, idNo);

        userExtBO.personAuth(userId, idPic, introduce);
    }

    @Override
    public void companyAuth(String userId, String companyName,
            String companyIntroduce, String bussinessLicenseId,
            String bussinessLicense) {
        userExtBO.companyAuth(userId, companyName, companyIntroduce,
            bussinessLicenseId, bussinessLicense);
    }

    @Override
    public User getUserByMobile(String mobile) {
        return userBO.getUserByMobile(mobile);
    }

    @Override
    public Paginable<User> queryFirstRefPage(XN802399Req req, int start,
            int limit) {
        User user = userBO.getUser(req.getUserId());
        User condition = new User();
        condition.setUserReferee(user.getMobile());
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        return page;
    }

    @Override
    public Paginable<User> querySecondRefPage(XN802400Req req, int start,
            int limit) {
        User user = userBO.getUser(req.getUserId());
        User condition = new User();
        condition.setUserReferee(user.getMobile());
        List<User> userList = userBO.queryUserList(condition);
        // 获得userRefereeList
        List<String> userRefereeList = new ArrayList<String>();
        for (User refUser : userList) {
            userRefereeList.add(refUser.getMobile());
        }
        User refCondition = new User();
        refCondition.setUserRefereeList(userRefereeList);
        Paginable<User> page = userBO.getPaginable(start, limit, refCondition);
        return page;
    }

    /**
     * @param response  可能是Json & Jsonp字符串 & urlParas
     *          eg：urlParas：access_token=xxx&expires_in=7776000&refresh_token=xxx
     * @return
     */
    public Map<String, String> getMapFromResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return new HashMap<>();
        }

        Map<String, String> result = new HashMap<>();
        int begin = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;

        if (begin >= 0 && end > 0) {
            result = new Gson().fromJson(response.substring(begin, end),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        } else {
            String[] paras = response.split("&");
            for (String para : paras) {
                result.put(para.split("=")[0], para.split("=")[1]);
            }
        }

        return result;
    }

}
