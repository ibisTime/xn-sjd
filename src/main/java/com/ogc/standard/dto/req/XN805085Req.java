/**
 * @Title XN805085Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 上午12:21:52 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月22日 上午12:21:52 
 * @history:
 */
public class XN805085Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 性别(1 男 0 女)
    @NotBlank
    private String gender;

    // 生日
    @NotBlank
    private String birthday;

    // 邮箱
    @NotBlank
    private String email;

    // 学位
    @NotBlank
    private String diploma;

    // 职业
    @NotBlank
    private String occupation;

    // 毕业年限
    @NotBlank
    private String gradTime;

    // 工作年限
    @NotBlank
    private String workTime;

    // 用户资料
    @NotBlank
    private String pdf;

    // 自我介绍
    @NotBlank
    private String introduce;

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

    public String getGradTime() {
        return gradTime;
    }

    public void setGradTime(String gradTime) {
        this.gradTime = gradTime;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
