package com.ogc.standard.bo;

import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630063Req;

public interface ISYSUserBO extends IPaginableBO<SYSUser> {

    // 新增系统用户
    public void doSaveSYSuser(SYSUser data);

    public String doSaveSYSuser(XN630063Req req, String loginPwd);

    public String doSaveSYSUser(String kind, String mobile, String loginPwd);

    // 分配角色
    public void refreshRole(String userId, String roleCode, String updater,
            String remark);

    // 重置密码
    public void resetAdminLoginPwd(SYSUser user, String loginPwd);

    // 重置代理密码
    public void resetSelfPwd(SYSUser user, String newLoginPwd);

    // 修改交易密码
    public void refreshTradePwd(String userId, String tradePwd);

    // 验证支付密码
    public void checkTradePwd(String userId, String tradePwd);

    // 修改照片
    public void refreshPhoto(SYSUser user, String photo);

    // 判断手机号是否存在
    public void isMobileExist(String mobile);

    // 判断手机号是否存在
    public void isMobileExist(String kind, String mobile);

    // 修改绑定手机号
    public void resetBindMobile(SYSUser user, String newMobile);

    // 列表查询
    public List<SYSUser> queryUserList(SYSUser condition);

    // 登录判断
    public SYSUser getUserByLoginName(String loginName, String systemCode);

    // 登录判断
    public SYSUser getUserByMobile(String mobile);

    public void checkLoginPwd(String userId, String loginPwd);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

    public void refreshStatus(String userId, String status, String updater,
            String remark);

    // 审核注册用户
    public void approveSYSUser(SYSUser data, String approveResult,
            String updater, String remark);

    public void refreshLoginName(String userId, String loginName);

    // 更新公司
    public void refreshCompany(String userId, String companyCode);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName);

    // 查询详情
    public SYSUser getSYSUser(String userId);

    public SYSUser getSYSUserUnCheck(String userId);

    // 统计创建时间区间的总数
    public long getTotalCount(Date createDatetimeStart, Date createDatetimeEnd);

}
