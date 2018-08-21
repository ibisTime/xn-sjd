package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发布赛事
 * @author: silver 
 * @since: 2018年8月21日 上午11:39:50 
 * @history:
 */
public class XN628292Req {

    // 编号
    @NotBlank
    private String code;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
