package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN802399Req;
import com.ogc.standard.dto.req.XN802400Req;
import com.ogc.standard.dto.req.XN805041Req;
import com.ogc.standard.dto.req.XN805042Req;
import com.ogc.standard.dto.req.XN805051Req;
import com.ogc.standard.dto.req.XN805070Req;
import com.ogc.standard.dto.req.XN805081Req;
import com.ogc.standard.dto.res.XN805051Res;

/**
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:46:32 
 * @history:
 */
public interface IUserAO {
    String DEFAULT_ORDER_COLUMN = "user_id";

    // 检查手机号是否存在
    public void doCheckMobile(String mobile);

    // 注册前端用户
    public String doRegisterByMobile(XN805041Req req);

    // 分配注册积分
    public void doAssignRegistJf(String userId, String userReferee,
            String userRefereeType);

    // 代注册
    public String doAddUser(XN805042Req req);

    // 绑定手机号
    public void doBindMobile(String isSendSms, String mobile, String smsCaptcha,
            String userId);

    // 用户登录
    public String doLogin(String loginName, String loginPwd, String client,
            String location);

    // 分类登录积分
    public void doAssignLoginJF(String userId);

    // 修改定位信息
    public void doChangeLocation(XN805081Req req);

    // 更换手机号
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha);

    // 更换手机号_需支付密码
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd);

    // 重置登录密码
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd);

    // 修改登录密码
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd);

    // 管理员重置用户密码
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd);

    // 管理员重置推荐人
    public void doResetReferee(String userId, String userReferee,
            String updater);

    // 设置支付密码
    public void doSetTradePwd(String userId, String tradePwd,
            String smsCaptcha);

    // 重置支付密码
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha);

    // 重置支付密码(需实名认证)
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo);

    // 修改支付密码
    public void doModifyTradePwd(String userId, String oldTradePwd,
            String newTradePwd);

    // 本系统实名认证
    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    // 修改头像
    public void modifyPhoto(String userId, String photo);

    // 注销/激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 修改昵称
    public void doModifyNickname(String userId, String nickname);

    // 两要素实名认证
    public void doTwoIdentify(String userId, String idKind, String idNo,
            String realName);

    // 三四要素实名认证
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile);

    public void doModifyUserInfo(XN805070Req req);

    // 微信注册/登录
    public XN805051Res doLoginWeChat(XN805051Req req);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public User doGetUser(String userId);

    // 绑定邮箱
    public void bindEmail(String captcha, String email, String userId);

    // 个人认证
    public void personAuth(String userId, String realName, String idNo,
            String idPic, String introduce);

    // 企业认证
    public void companyAuth(String userId, String companyName,
            String companyIntroduce, String bussinessLicenseId,
            String bussinessLicense);

    // 更新用户等级
    public void upgradeUserLevel(String userId);

    public User getUserByMobile(String mobile);

    // 直退用户查询
    public Paginable<User> queryFirstRefPage(XN802399Req req, int start,
            int limit);

    public Paginable<User> querySecondRefPage(XN802400Req req, int start,
            int limit);

}
