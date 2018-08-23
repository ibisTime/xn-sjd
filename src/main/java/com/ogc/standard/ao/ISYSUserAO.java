package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSUser;

/**
 * 系统用户表
 * @author: clockorange 
 * @since: Jul 31, 2018 4:50:05 PM 
 * @history:
 */
public interface ISYSUserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 新增用户
    public String addSYSUser(String mobile, String loginPwd, String realName,
            String photo);

    // 用户登录
    public String doLogin(String loginName, String loginPwd);

    // 注销 | 激活其他管理员
    public void doCloseOpen(String userId, String updater, String remark);

    // 分配角色
    public void doRoleSYSUser(String userId, String roleCode, String updater,
            String remark);

    // 重置其他管理员密码
    public void resetAdminLoginPwd(String userId, String newLoginPwd);

    // 重置密码
    public void resetSelfPwd(String mobile, String smsCaptcha,
            String newLoginPwd);

    // 根据旧密码改新密码
    public void editPwd(String userId, String oldPwd, String newPwd);

    // 修改照片
    public void doModifyPhoto(String userId, String photo);

    // 修改电话
    public void doResetMoblie(String userId, String remark, String newMobile,
            String smsCaptcha);

    // 分页查询
    public Paginable<SYSUser> querySYSUserPage(int start, int limit,
            SYSUser condition);

    // 列表查询
    public List<SYSUser> querySYSUserList(SYSUser condition);

    // 详细查询
    public SYSUser getSYSUser(String userId);

}
