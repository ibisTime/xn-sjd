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

    // 类型（代理商/业务员）
    private String type;

    // 用户等级
    private String level;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 真实姓名
    private String realName;

    // 头像
    private String photo;

    // 手机号
    private String mobile;

    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    // 登陆密码强度
    private String loginPwdStrength;

    // 安全密码
    private String tradePwd;

    // 安全密码强度
    private String tradePwdStrength;

    // 注册时间
    private Date createDatetime;

    // 状态（0待审核/1审核不通过/2合伙中/3已解除合伙/4已注销）
    private String status;

    // 修改人
    private String updater;

    // 修改时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // **************DB Properties**************
    // 创建开始时间
    private Date dateStart;

    // 创建结束时间
    private Date dateEnd;

    // 关键字(名字，手机号模糊查询)
    private String keyword;

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

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
