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

    // 年龄
    private String age;

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

    // 身份证正面照片
    private String idPic;

    // 身份证反面照片
    private String backIdPic;

    // 企业名称
    private String companyName;

    // 企业地址
    private String companyAddress;

    // 企业法人姓名
    private String companyChargerName;

    // 企业法人联系方式
    private String companyChargerMobile;

    // 企业法人身份证
    private String companyChargerIdNo;

    // 注册统一编码
    private String bussinessLicenseId;

    // 企业开户行
    private String companyBank;

    // 企业开户行账号
    private String companyBankNumber;

    // 企业联系人
    private String companyContactName;

    // 企业联系人电话
    private String companyContactMobile;

    // 企业联系人地址
    private String companyContactAddress;

    // 企业法人身份证正面
    private String companyChargerIdPic;

    // 企业法人身份证反面
    private String companyChargerBackIdPic;

    // 企业简介
    private String companyIntroduce;

    // 营业执照
    private String bussinessLicense;

    // 认证状态
    private String authStatus;

    // 个人认证状态
    private String personAuthStatus;

    // 企业认证状态
    private String companyAuthStatus;

    // ***********db properties***********

    public String getUserId() {
        return userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getIdPic() {
        return idPic;
    }

    public void setIdPic(String idPic) {
        this.idPic = idPic;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBussinessLicenseId() {
        return bussinessLicenseId;
    }

    public void setBussinessLicenseId(String bussinessLicenseId) {
        this.bussinessLicenseId = bussinessLicenseId;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    public String getPersonAuthStatus() {
        return personAuthStatus;
    }

    public void setPersonAuthStatus(String personAuthStatus) {
        this.personAuthStatus = personAuthStatus;
    }

    public String getCompanyAuthStatus() {
        return companyAuthStatus;
    }

    public void setCompanyAuthStatus(String companyAuthStatus) {
        this.companyAuthStatus = companyAuthStatus;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getBackIdPic() {
        return backIdPic;
    }

    public void setBackIdPic(String backIdPic) {
        this.backIdPic = backIdPic;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyChargerName() {
        return companyChargerName;
    }

    public void setCompanyChargerName(String companyChargerName) {
        this.companyChargerName = companyChargerName;
    }

    public String getCompanyChargerMobile() {
        return companyChargerMobile;
    }

    public void setCompanyChargerMobile(String companyChargerMobile) {
        this.companyChargerMobile = companyChargerMobile;
    }

    public String getCompanyChargerIdNo() {
        return companyChargerIdNo;
    }

    public void setCompanyChargerIdNo(String companyChargerIdNo) {
        this.companyChargerIdNo = companyChargerIdNo;
    }

    public String getCompanyBank() {
        return companyBank;
    }

    public void setCompanyBank(String companyBank) {
        this.companyBank = companyBank;
    }

    public String getCompanyBankNumber() {
        return companyBankNumber;
    }

    public void setCompanyBankNumber(String companyBankNumber) {
        this.companyBankNumber = companyBankNumber;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyContactMobile() {
        return companyContactMobile;
    }

    public void setCompanyContactMobile(String companyContactMobile) {
        this.companyContactMobile = companyContactMobile;
    }

    public String getCompanyContactAddress() {
        return companyContactAddress;
    }

    public void setCompanyContactAddress(String companyContactAddress) {
        this.companyContactAddress = companyContactAddress;
    }

    public String getCompanyChargerIdPic() {
        return companyChargerIdPic;
    }

    public void setCompanyChargerIdPic(String companyChargerIdPic) {
        this.companyChargerIdPic = companyChargerIdPic;
    }

    public String getCompanyChargerBackIdPic() {
        return companyChargerBackIdPic;
    }

    public void setCompanyChargerBackIdPic(String companyChargerBackIdPic) {
        this.companyChargerBackIdPic = companyChargerBackIdPic;
    }

}
