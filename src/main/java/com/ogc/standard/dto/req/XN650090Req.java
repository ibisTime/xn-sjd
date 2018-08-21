package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提醒/取消提醒（front）
 * @author: lei 
 * @since: 2018年8月21日 上午11:23:26 
 * @history:
 */
public class XN650090Req {

    // 必填，组合编号
    @NotBlank
    private String code;

    // 选填，天数（"30"，"90"，""）
    private String days;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
