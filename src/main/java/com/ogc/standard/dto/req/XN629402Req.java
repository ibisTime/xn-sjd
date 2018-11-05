package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提交预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:58 AM 
 * @history:
 */
public class XN629402Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    @NotBlank
    private String code;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

}
