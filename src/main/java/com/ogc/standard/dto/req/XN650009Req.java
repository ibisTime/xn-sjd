package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询组合，包含币种配置和最新成交记录（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:36:20 
 * @history:
 */
public class XN650009Req {

    // 必填，组合编号
    @NotBlank
    private String code;

    // 选填，当前登录用户编号
    private String visitUserId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVisitUserId() {
        return visitUserId;
    }

    public void setVisitUserId(String visitUserId) {
        this.visitUserId = visitUserId;
    }

}
