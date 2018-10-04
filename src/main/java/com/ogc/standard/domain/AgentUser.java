package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 代理商用户
* @author: silver 
* @since: 2018-09-28 11:21:17
* @history:
*/
public class AgentUser extends ABaseDO {
    private static final long serialVersionUID = 8087246909505779902L;

    // 用户编号
    private String userId;

    // 类型（0代理商/1业务员）
    private String type;

    // 登陆名
    private String loginName;

    // 手机号
    private String mobile;

    // 登陆密码
    private String loginPwd;

    // 登陆密码强度
    private String loginPwdStrength;

    // 安全密码
    private String tradePwd;

    // 安全密码强度
    private String tradePwdStrength;

    // 真实姓名
    private String realName;

    // 头像
    private String photo;

    // 注册时间
    private Date createDatetime;

    // 状态
    private String status;

    // 推荐人编号
    private String userReferee;

    // 等级
    private String level;

    // 父级用户编号
    private String parentUserId;

    // 角色编号
    private String roleCode;

    // 修改人
    private String updater;

    // 修改时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // **************DB Properties**************
    // 创建开始时间
    private Date createDatetimeStart;

    // 创建结束时间
    private Date createDatetimeEnd;

    // 关键字(名字，手机号模糊查询)
    private String keyword;

    private String realNameForQuery;

    private Company company;

    private AgentUser parentAgentUser;

    public AgentUser getParentAgentUser() {
        return parentAgentUser;
    }

    public void setParentAgentUser(AgentUser parentAgentUser) {
        this.parentAgentUser = parentAgentUser;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getRealNameForQuery() {
        return realNameForQuery;
    }

    public void setRealNameForQuery(String realNameForQuery) {
        this.realNameForQuery = realNameForQuery;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    // 公司信息
    private Company companyInfo;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwdStrength(String tradePwdStrength) {
        this.tradePwdStrength = tradePwdStrength;
    }

    public String getTradePwdStrength() {
        return tradePwdStrength;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Company getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(Company companyInfo) {
        this.companyInfo = companyInfo;
    }

}
