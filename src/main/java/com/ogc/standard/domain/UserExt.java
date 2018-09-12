/**
 * @Title UserExt.java 
 * @Package com.std.user.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年9月18日 上午10:45:44 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 用户扩展表
 * @author: dl 
 * @since: 2018年8月20日 上午11:22:34 
 * @history:
 */
public class UserExt extends ABaseDO {
    private static final long serialVersionUID = 4683217240952437620L;

    // ***********db properties***********

    // 用户编号
    private String userId;

    // 性别(1 男 0 女)
    private String gender;

    // 生日
    private String birthday;

    // 邮箱
    private String email;

    // 驾驶照
    private String driverLi;

    // 护照
    private String passport;

    // 学位
    private String diploma;

    // 职业
    private String occupation;

    // 毕业年限
    private Date gradDatetime;

    // 工作年限
    private String workTime;

    // 用户资料
    private String pdf;

    // 自我介绍
    private String introduce;
    // ***********db properties***********

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Date getGradDatetime() {
        return gradDatetime;
    }

    public void setGradDatetime(Date gradDatetime) {
        this.gradDatetime = gradDatetime;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getDriverLi() {
        return driverLi;
    }

    public void setDriverLi(String driverLi) {
        this.driverLi = driverLi;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

}
