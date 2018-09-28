package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSRoleBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSRole;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630060Req;
import com.ogc.standard.enums.EApplyBindMaintainStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESYSUserKind;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.enums.EUserStatus;
import com.ogc.standard.exception.BizException;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    @Autowired
    ISYSUserBO sysUserBO;

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    ITreeBO treeBO;

    @Autowired
    IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    ICompanyBO companyBO;

    // 新增用户（平台）
    @Override
    public String addSYSUser(String roleCode, String realName, String mobile,
            String loginName, String loginPwd, String photo, String remark) {
        sysUserBO.isMobileExist(mobile);
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setKind(ESYSUserKind.PLATFORM.getCode());
        data.setRealName(realName);
        data.setPhoto(photo);
        data.setMobile(mobile);
        data.setLoginName(loginName);
        data.setRoleCode(roleCode);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.NORMAL.getCode());
        data.setRemark(remark);
        sysUserBO.doSaveSYSuser(data);
        return userId;
    }

    // 注册用户(产权/养护)
    @Override
    public String registerSYSUserOwner(XN630060Req req) {
        sysUserBO.isMobileExist(req.getMobile());
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setKind(req.getKind());
        data.setRealName(req.getCompanyCharger());
        data.setMobile(req.getMobile());
        data.setLoginName(req.getMobile());
        if (ESYSUserKind.OWNER.getCode().equals(req.getKind())) {
            data.setRoleCode("");// TODO
        }
        if (ESYSUserKind.MAINTAIN.getCode().equals(req.getKind())) {
            data.setRoleCode("");// TODO
        }
        data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
            .getLoginPwd()));
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.TO_APPROVE.getCode());// 待审核
        sysUserBO.doSaveSYSuser(data);
        // 公司
        Company company = new Company();
        company.setUserId(userId);
        company.setName(req.getCompanyName());
        company.setCharger(req.getCompanyCharger());
        company.setChargeMobile(req.getChargerMobile());
        company.setAddress(req.getCompanyAddress());
        company.setDescription(req.getDescription());
        company.setBussinessLicense(req.getBussinessLicense());
        company.setOrganizationCode(req.getOrganizationCode());
        company.setCreateDatetime(new Date());
        companyBO.saveCompany(company);
        // 证书模板合同模板 TODO
        return userId;
    }

    // 代申请
    @Override
    public String proxyApplySYSUser(String kind, String loginName,
            String mobile, String realName, String remark) {
        sysUserBO.isMobileExist(mobile);
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setKind(kind);
        data.setRealName(realName);
        data.setMobile(mobile);
        data.setLoginName(loginName);
        if (ESYSUserKind.OWNER.getCode().equals(kind)) {
            data.setRoleCode("");// TODO
        }
        if (ESYSUserKind.MAINTAIN.getCode().equals(kind)) {
            data.setRoleCode("");// TODO
        }
        data.setLoginPwd(MD5Util.md5("888888"));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel("888888"));
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.TO_APPROVE.getCode());// 代注册的也是待审核？
        data.setRemark(remark);
        sysUserBO.doSaveSYSuser(data);
        return userId;
    }

    @Override
    public void approveSYSUser(String userId, String approveResult,
            String updater, String remark) {
        if (!sysUserBO.isUserExist(userId)) {
            throw new BizException("xn805050", "用户不存在");
        }
        SYSUser data = sysUserBO.getSYSUser(userId);
        if (EBoolean.YES.getCode().equals(approveResult)) {
            data.setStatus(ESYSUserStatus.PARTNER.getCode());
        } else {
            data.setStatus(ESYSUserStatus.APPROVE_NO.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        sysUserBO.approveSYSUser(data);
    }

    // 用户登录
    @Override
    public String doLogin(String loginName, String loginPwd) {
        SYSUser condition = new SYSUser();
        condition.setLoginName(loginName);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        SYSUser user = userList2.get(0);
        return user.getUserId();
    }

    // 注销，激活其他管理员
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        sysUserBO.refreshStatus(userId, userStatus, updater, remark);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + smsContent, "805091");
        }

    }

    // 设置角色
    @Override
    public void doRoleSYSUser(String userId, String roleCode, String updater,
            String remark) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        sysUserBO.refreshRole(userId, roleCode, updater, remark);
    }

    // 重置其他管理员密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        sysUserBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    // 重置登录密码
    @Override
    public void resetSelfPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
        // 判断手机号是否存在

        SYSUser user = sysUserBO.getUserByMobile(mobile);
        String oldPwd = user.getLoginPwd();
        if (newLoginPwd.equals(oldPwd)) {
            throw new BizException("xn000000", "新密码与原密码一致");
        }

        // 新密码验证
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "630053");
        sysUserBO.resetSelfPwd(user, newLoginPwd);
        // 发送短信
        smsOutBO.sendSmsOut(
            mobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(mobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1) + "已更改登录密码"
                    + "，请妥善保管您的账户相关信息。", "631072");
    }

    @Override
    public void editPwd(String userId, String oldPwd, String newPwd) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (null == user) {
            throw new BizException("xn000000", "用户不存在！");
        }
        if (!user.getLoginPwd().equals(oldPwd)) {
            throw new BizException("xn000000", "原密码不正确");
        }
        sysUserBO.resetSelfPwd(user, newPwd);

    }

    // 修改照片
    @Override
    public void doModifyPhoto(String userId, String photo) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        sysUserBO.refreshPhoto(user, photo);
    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String remark, String newMobile,
            String smsCaptcha) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 判断手机号是否存在
        sysUserBO.isMobileExist(newMobile);
        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "630052");
        sysUserBO.resetBindMobile(user, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1) + "已将手机号码改为" + newMobile
                    + "，您的登录名更改为" + newMobile + "，请妥善保管您的账户相关信息。", "631072");

    }

    // 分页查询
    @Override
    public Paginable<SYSUser> querySYSUserPage(int start, int limit,
            SYSUser condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart().after(
                    condition.getCreateDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<SYSUser> page = sysUserBO.getPaginable(start, limit,
            condition);
        List<SYSUser> list = page.getList();
        for (SYSUser sysUser : list) {
            init(sysUser);
        }
        return page;
    }

    // 列表查询
    public List<SYSUser> querySYSUserList(SYSUser condition) {
        List<SYSUser> list = sysUserBO.queryUserList(condition);
        for (SYSUser sysUser : list) {
            init(sysUser);
        }
        return list;
    }

    // 详细查询
    public SYSUser getSYSUser(String code) {
        SYSUser sysUser = sysUserBO.getSYSUser(code);
        return sysUser;
    }

    public void init(SYSUser data) {
        if (ESYSUserKind.OWNER.getCode().equals(data.getKind())) { // 产权方
            long count = treeBO.getTotalCountByOwnerId(data.getUserId());
            data.setTreeQuantity(String.valueOf(count));
            data.setTreeValue("");// TODO 古树市值
        }
        if (ESYSUserKind.MAINTAIN.getCode().equals(data.getKind())) {// 养护方
            ApplyBindMaintain abmCondition = new ApplyBindMaintain();
            abmCondition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
            abmCondition.setMaintainId(data.getUserId());
            List<ApplyBindMaintain> abmList = applyBindMaintainBO
                .queryApplyBindMaintainList(abmCondition);
            if (CollectionUtils.isNotEmpty(abmList)) {
                SYSUser sysUser2 = sysUserBO.getSYSUser(abmList.get(0)
                    .getOwnerId());
                data.setOwner(sysUser2.getRealName());
            }
            data.setTotalIncome("");// TODO 总收入
        }
    }

}
