package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2017年2月13日 下午1:47:40 
 * @history:
 */
public class XN630090Req extends BaseReq {

    private static final long serialVersionUID = 3739327976742271011L;

    // 接受短信的手机号--必填
    @NotBlank
    private String mobile;

    // 业务类型--必填
    @NotBlank
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
