package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代申请
 * @author: jiafr 
 * @since: 2018年9月28日 下午6:08:58 
 * @history:
 */
public class XN630064Req {

    // 类型 O产权M养护
    @NotBlank
    private String kind;

    // 用户名
    @NotBlank
    private String loginName;

    // 手机号
    @NotBlank
    private String mobile;

    // 真实姓名
    @NotBlank
    private String realName;

    // 备注
    private String remark;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
