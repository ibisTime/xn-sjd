package com.ogc.standard.dto.req;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2017年2月13日 下午1:47:40 
 * @history:
 */
public class XN630090Req {

    // 接受短信的手机号--必填
    private String mobile;

    // 业务类型--必填
    private String bizType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
