package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISYSRoleBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.MD5Util;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.common.PwdUtil;
import com.ogc.standard.common.RandomUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSRole;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630061Req;
import com.ogc.standard.dto.req.XN630063Req;
import com.ogc.standard.dto.res.XN629901Res;
import com.ogc.standard.dto.res.XN630065PriceRes;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EApplyBindMaintainStatus;
import com.ogc.standard.enums.ECaptchaType;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeMaintain;
import com.ogc.standard.enums.ESYSUserKind;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    // 新增用户（平台）
    @Override
    public String addSYSUser(String roleCode, String realName, String mobile,
            String loginName, String loginPwd, String photo, String remark) {
        sysUserBO.isMobileExist(mobile);
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generate("U");
        data.setUserId(userId);
        data.setKind(ESYSUserKind.PLAT.getCode());
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

    @Override
    @Transactional
    public String regOwnerAndMain(String kind, String mobile, String loginPwd,
            String smsCaptcha) {
        sysUserBO.isMobileExist(kind, mobile);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "630060");

        String userId = sysUserBO.doSaveSYSUser(kind, mobile, loginPwd);
        companyBO.saveCompany(userId);

        // 分配人民币账户
        EAccountType accountType = EAccountType.OWNER;
        if (ESYSUserKind.MAINTAIN.getCode().equals(kind)) {
            accountType = EAccountType.MAINTAIN;
        } else if (ESYSUserKind.BUSINESS.getCode().equals(kind)) {// 新增商家注册
            accountType = EAccountType.BUSINESS;
        }
        accountBO.distributeAccount(userId, accountType,
            ECurrency.CNY.getCode());
        return userId;
    }

    @Override
    @Transactional
    public void commitCompany(XN630061Req req) {
        SYSUser sysUser = sysUserBO.getSYSUser(req.getUserId());
        if (ESYSUserKind.PLAT.getCode().equals(sysUser.getKind())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户类型不支持");
        }
        if (!ESYSUserStatus.TO_FILL.getCode().equals(sysUser.getStatus())
                && !ESYSUserStatus.APPROVE_NO.getCode()
                    .equals(sysUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户状态不是待提交资料或审核不通过，不能提交");
        }

        companyBO.refreshCompany(req);
        sysUserBO.refreshStatus(req.getUserId(), ESYSUserStatus.TO_APPROVE,
            req.getUserId(), null);
    }

    // 代申请
    @Override
    @Transactional
    public String platApplySYSUser(XN630063Req req) {
        sysUserBO.isMobileExist(req.getKind(), req.getMobile());

        // 落地数据
        String loginPwd = RandomUtil.generate6();
        String userId = sysUserBO.doSaveSYSuser(req, loginPwd);
        companyBO.saveCompany(req, userId);

        // 分配人民币账户
        EAccountType accountType = EAccountType.OWNER;
        if (ESYSUserKind.MAINTAIN.getCode().equals(req.getKind())) {
            accountType = EAccountType.MAINTAIN;
        }
        accountBO.distributeAccount(userId, accountType,
            ECurrency.CNY.getCode());

        // 发送短信
        smsOutBO.sendSmsOut(req.getMobile(),
            String.format(SysConstants.DO_ADD_USER_CN,
                PhoneUtil.hideMobile(req.getMobile()), loginPwd),
            ECaptchaType.AG_REG.getCode());
        return userId;
    }

    @Override
    public void approveSYSUser(String userId, String approveResult,
            String updater, String remark) {
        SYSUser data = sysUserBO.getSYSUser(userId);
        if (!ESYSUserStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn805050", "用户不是待审核状态");
        }
        sysUserBO.approveSYSUser(data, approveResult, updater, remark);
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

        if (ESYSUserStatus.Li_Locked.getCode().equals(user.getStatus())
                || ESYSUserStatus.Ren_Locked.getCode()
                    .equals(user.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户已被锁定，请联系管理员");
        }
        return user.getUserId();
    }

    // 注销，激活其他管理员
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getSYSUserUnCheck(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        ESYSUserStatus userStatus = null;
        if (ESYSUserStatus.NORMAL.getCode()
            .equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = ESYSUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = ESYSUserStatus.NORMAL;
        }
        sysUserBO.refreshStatus(userId, userStatus, updater, remark);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile,
                "尊敬的" + PhoneUtil.hideMobile(mobile) + smsContent, "805091");
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
        smsOutBO.sendSmsOut(mobile,
            "尊敬的" + PhoneUtil.hideMobile(mobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已更改登录密码" + "，请妥善保管您的账户相关信息。",
            "631072");
    }

    @Override
    public void setTradePwd(String userId, String tradePwd, String smsCaptcha) {
        SYSUser sysUser = sysUserBO.getSYSUser(userId);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(sysUser.getMobile(), smsCaptcha, "630070");

        // 修改支付密码
        sysUserBO.refreshTradePwd(userId, tradePwd);
    }

    @Override
    @Transactional
    public void doModifyTradePwd(String userId, String newTradePwd,
            String oldTradePwd) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        condition.setTradePwd(MD5Util.md5(oldTradePwd));
        List<SYSUser> list = sysUserBO.queryUserList(condition);

        SYSUser sysUser = null;
        if (CollectionUtils.isNotEmpty(list)) {
            sysUser = list.get(0);
        } else {
            throw new BizException("li01008", "旧支付密码密码不正确");
        }
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新支付密码与原有支付密码重复");
        }

        sysUserBO.refreshTradePwd(userId, newTradePwd);

        // 发短信
        String mobile = sysUser.getMobile();
        smsOutBO.sendSmsOut(mobile,
            String.format(SysConstants.DO_MODIFY_TRADE_PWD_CN,
                PhoneUtil.hideMobile(mobile)),
            ECaptchaType.MODIFY_TRADE_PWD.getCode());
    }

    @Override
    public void resetTradePwd(String userId, String newTradePwd,
            String smsCaptcha) {
        SYSUser sysUser = sysUserBO.getSYSUser(userId);

        // 短信验证码是否正确
        smsOutBO.checkCaptcha(sysUser.getMobile(), smsCaptcha, "630072");

        // 修改支付密码
        sysUserBO.refreshTradePwd(userId, newTradePwd);
    }

    @Override
    public void editPwd(String userId, String oldPwd, String newPwd) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (oldPwd.equals(newPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }

        sysUserBO.checkLoginPwd(userId, oldPwd);

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
        sysUserBO.isMobileExist(user.getKind(), newMobile);

        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "630052");
        sysUserBO.resetBindMobile(user, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(oldMobile,
            "尊敬的" + PhoneUtil.hideMobile(oldMobile) + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "已将手机号码改为" + newMobile + "，您的登录名更改为" + newMobile
                    + "，请妥善保管您的账户相关信息。",
            "631072");

    }

    // 分页查询
    @Override
    public Paginable<SYSUser> querySYSUserPage(int start, int limit,
            SYSUser condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart()
                    .after(condition.getCreateDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<SYSUser> page = sysUserBO.getPaginable(start, limit,
            condition);
        List<SYSUser> list = page.getList();
        for (SYSUser sysUser : list) {
            init(sysUser);

            if (ESYSUserKind.OWNER.getCode().equals(sysUser.getKind())
                    || ESYSUserKind.MAINTAIN.getCode()
                        .equals(sysUser.getKind())) {
                Company company = companyBO
                    .getCompanyByUserId(sysUser.getUserId());
                sysUser.setCompany(company);
            }
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
        init(sysUser);
        return sysUser;
    }

    public void init(SYSUser data) {
        if (ESYSUserKind.OWNER.getCode().equals(data.getKind())) {

            // 产权方
            long count = treeBO.getTotalCountByOwnerId(data.getUserId());
            data.setTreeQuantity(String.valueOf(count));

            // 古树市值
            XN630065PriceRes priceRes = productBO
                .getOwnerProductPrice(data.getUserId());
            data.setMaxPrice(priceRes.getMaxPrice());
            data.setMinPrice(priceRes.getMinPrice());

        } else if (ESYSUserKind.MAINTAIN.getCode().equals(data.getKind())) {

            // 养护方
            ApplyBindMaintain abmCondition = new ApplyBindMaintain();
            abmCondition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
            abmCondition.setMaintainId(data.getUserId());
            List<ApplyBindMaintain> abmList = applyBindMaintainBO
                .queryApplyBindMaintainList(abmCondition);
            if (CollectionUtils.isNotEmpty(abmList)) {
                SYSUser sysUser2 = sysUserBO
                    .getSYSUser(abmList.get(0).getOwnerId());
                data.setOwner(sysUser2.getRealName());
            }

            // 总收入
            Account cnyAccount = accountBO.getAccountByUser(data.getUserId(),
                ECurrency.CNY.getCode());
            BigDecimal totalIncome = jourBO.getTotalAmount(
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode(),
                EChannelType.NBZ.getCode(), cnyAccount.getAccountNumber());
            data.setTotalIncome(totalIncome);

        } else if (ESYSUserKind.BUSINESS.getCode().equals(data.getKind())) {

            // 店铺信息
            Company company = companyBO.getCompany(data.getCompanyCode());
            data.setCompany(company);

        }
    }

    @Override
    public XN629901Res getTotalCreateCount(String userId, String type,
            Date createDatetimeStart, Date createDatetimeEnd) {

        long userTotalCount = 0L;// 用户总数
        XN629901Res res = null;

        // 平台端查询全部用户
        if (EAccountType.PLAT.getCode().equals(type)) {
            long userCount = 0L;// C端用户
            long agentUserCount = 0L;// 代理用户
            long sysUserCount = 0L;// 系统用户

            userCount = userBO.getTotalCount(null, createDatetimeStart,
                createDatetimeEnd);

            agentUserCount = agentUserBO.getTotalCount(null,
                createDatetimeStart, createDatetimeEnd);

            sysUserCount = sysUserBO.getTotalCount(createDatetimeStart,
                createDatetimeEnd);

            userTotalCount = userCount + agentUserCount + sysUserCount;

            res = new XN629901Res(userTotalCount);
        }

        // 产权端查询新增的认养用户
        if (EAccountType.OWNER.getCode().equals(type)) {
            userTotalCount = adoptOrderTreeBO.getCountByOwner(userId,
                createDatetimeStart, createDatetimeEnd);

            res = new XN629901Res(userTotalCount);
        }

        // 养护端查询绑定的产权方
        if (EAccountType.MAINTAIN.getCode().equals(type)) {
            userTotalCount = applyBindMaintainBO.getOwnerCountByMaintain(userId,
                createDatetimeStart, createDatetimeEnd);

            res = new XN629901Res(userTotalCount);
        }

        // 代理商查询新增的业务员和用户
        if (EAccountType.AGENT.getCode().equals(type)) {
            long userCount = 0L;// C端用户
            long agentUserCount = 0L;// 代理用户

            userCount = userBO.getTotalCount(userId, createDatetimeStart,
                createDatetimeEnd);

            agentUserCount = agentUserBO.getTotalCount(userId,
                createDatetimeStart, createDatetimeEnd);

            userTotalCount = userCount + agentUserCount;

            res = new XN629901Res(userTotalCount, userCount, agentUserCount);
        }

        return res;
    }

}
