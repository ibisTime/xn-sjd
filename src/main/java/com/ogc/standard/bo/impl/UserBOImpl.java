/**
 * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IUserDAO;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN805043Req;
import com.ogc.standard.enums.EUserKind;
import com.ogc.standard.enums.EUserLevel;
import com.ogc.standard.enums.EUserStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("000001", "手机号已存在");
            }
        }
    }

    @Override
    public void isEmailExist(String email) {
        if (StringUtils.isNotBlank(email)) {
            User condition = new User();
            condition.setEmail(email);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "邮箱已经存在");
            }
        }
    }

    @Override
    public void isNicknameExist(String nickname) {
        if (StringUtils.isNotBlank(nickname)) {
            // 判断格式
            User condition = new User();
            condition.setNickname(nickname);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "昵称已经被使用");
            }
        }
    }

    @Override
    public String getUserId(String mobile) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                User data = list.get(0);
                userId = data.getUserId();
            } else
                throw new BizException("xn702002", "手机号[" + mobile + "]用户不存在");
        }
        return userId;
    }

    @Override
    public String doAddUser(User data) {
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setKind(EUserKind.Customer.getCode());
        data.setNickname(userId.substring(userId.length() - 8, userId.length()));
        userDAO.insert(data);
        return userId;
    }

    @Override
    public User getUserByMobile(String mobile) {
        User user = null;
        if (StringUtils.isNotBlank(mobile)) {
            User condition = new User();
            condition.setMobile(mobile);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                user = list.get(0);
            }
        }
        return user;
    }

    @Override
    public String doRegister(String mobile, String nickname, String loginPwd,
            User refereeUser, AgentUser agentUser, AgentUser salesmanUser,
            String province, String city, String area) {

        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setKind(EUserKind.Customer.getCode());
        if (StringUtils.isNotBlank(loginPwd)) {
            user.setLoginPwd(MD5Util.md5(loginPwd));
            user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        }
        if (nickname == null) {
            user.setNickname(userId.substring(userId.length() - 8,
                userId.length()));
        }
        user.setNickname(nickname);
        if (refereeUser != null) {
            user.setUserReferee(refereeUser.getMobile());
        }
        if (agentUser != null) {
            user.setAgent(agentUser.getMobile());
        }
        if (salesmanUser != null) {
            user.setSalesman(salesmanUser.getMobile());
        }
        user.setLevel(EUserLevel.ONE.getCode());
        user.setStatus(EUserStatus.NORMAL.getCode());
        user.setProvince(province);
        user.setCity(city);
        user.setArea(area);
        Date date = new Date();
        user.setCreateDatetime(date);
        userDAO.insert(user);
        return userId;
    }

    // 邮箱注册
    @Override
    public String doRegistByEmail(XN805043Req req) {
        User data = new User();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setLoginName(req.getEmail());
        data.setEmail(req.getEmail());
        data.setKind(EUserKind.Customer.getCode());
        data.setNickname(userId.substring(userId.length() - 8, userId.length()));
        if (StringUtils.isNotBlank(req.getLoginPwd())) {
            data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
            data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
                .getLoginPwd()));
        }
        data.setUserReferee(req.getUserReferee());
        data.setLevel(EUserLevel.ONE.getCode());
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());
        Date date = new Date();
        data.setCreateDatetime(date);

        userDAO.insert(data);
        return userId;

    }

    @Override
    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            userId = OrderNoGenerater.generate("U");
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            userDAO.insert(data);
        }
        return userId;
    }

    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;
    }

    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo, String idFace, String idOppo, String idHold) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;

    }

    @Override
    public int refreshRealName(String userId, String realName) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateRealName(data);
        }
        return count;
    }

    @Override
    public int refreshTradePwd(String userId, String tradePwd) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setTradePwd(MD5Util.md5(tradePwd));
            data.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePwd));
            count = userDAO.updateTradePwd(data);
        }
        return count;
    }

    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }
        if (data == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户编号"
                    + userId + "不存在");
        }
        return data;
    }

    @Override
    public User getUserUnCheck(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }

        return data;
    }

    @Override
    public List<User> getUsersByUserReferee(String userReferee) {
        List<User> userList = new ArrayList<User>();
        if (StringUtils.isNotBlank(userReferee)) {
            User condition = new User();
            condition.setUserReferee(userReferee);
            userList = userDAO.selectList(condition);
        }
        return userList;
    }

    @Override
    public User getUserByLoginName(String loginName, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(loginName)) {
            User condition = new User();
            condition.setLoginName(loginName);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    @Override
    public List<User> queryUserList(User data) {
        return userDAO.selectList(data);
    }

    @Override
    public int refreshLoginPwd(String userId, String loginPwd) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginPwd(MD5Util.md5(loginPwd));
            data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
            count = userDAO.updateLoginPwd(data);
        }
        return count;
    }

    @Override
    public int refreshMobile(String userId, String mobile) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(mobile)) {
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            count = userDAO.updateMobile(data);
        }
        return count;
    }

    @Override
    public void isLoginNameExist(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            User condition = new User();
            condition.setLoginName(loginName);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public boolean isUserExist(String userId) {
        User condition = new User();
        condition.setUserId(userId);
        if (userDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void checkUserReferee(String userReferee, String systemCode) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            User condition = new User();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
            User user = this.getUser(userId);
            if (StringUtils.isBlank(user.getTradePwdStrength())) {
                throw new BizException("jd00001", "请您先设置支付密码！");
            }
            User condition = new User();
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
            User condition = new User();
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
            User condition = new User();
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
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        User condition = new User();
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<User> userList = userDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User data = userList.get(0);
            throw new BizException("xn000001", "用户[" + data.getMobile()
                    + "]已使用该身份信息，请重新填写");
        }
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status,
            String updater, String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setNickname(nickname);
            userDAO.updateNickname(data);
        }
    }

    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPhoto(photo);
            userDAO.updatePhoto(data);
        }
    }

    @Override
    public void refreshLevel(User data) {
        userDAO.updateLevel(data);
    }

    @Override
    public List<User> queryUserList(String mobile, String kind,
            String systemCode) {
        User condition = new User();
        condition.setMobile(mobile);
        return userDAO.selectList(condition);
    }

    public Long totalUser(User condition) {
        return userDAO.selectTotalCount(condition);
    }

    @Override
    public void refreshLocation(User data) {
        if (StringUtils.isNotBlank(data.getUserId())) {
            data.setUpdateDatetime(new Date());
            userDAO.updateLocation(data);
        }

    }

    @Override
    public void refreshReferee(String userId, String userReferee, String updater) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setUserReferee(userReferee);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            userDAO.updateReferee(data);
        }
    }

    @Override
    public int refreshEmail(String userId, String email) {
        int count = 0;
        if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setEmail(email);
            count = userDAO.updateEmail(data);
        }
        return count;
    }

    @Override
    public String doAddQDS(String mobile, String idKind, String idNo,
            String realName, String respArea, String loginPwd) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setKind(EUserKind.QDS.getCode());
        user.setUserId(userId);
        user.setCreateDatetime(new Date());
        user.setMobile(mobile);
        user.setLoginName(mobile);
        user.setIdKind(idKind);
        user.setIdNo(idNo);
        user.setRealName(realName);
        user.setStatus(EUserStatus.NORMAL.getCode());
        user.setCreateDatetime(new Date());
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        user.setNickname(userId.substring(userId.length() - 8, userId.length()));
        userDAO.insert(user);
        return userId;
    }

    @Override
    public void refreshRespArea(String userId, String respArea, String updater) {
        User data = new User();
        data.setUserId(userId);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        userDAO.updateRespArea(data);
    }

}
