package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 平台收款处理
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625274Req {

    // 必填，编号
    @NotBlank
    private String code;

    // 必填，收款结果(1=已收款 0=未收款)
    @NotBlank
    private String result;

    // 必填，用户编号
    @NotBlank
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
