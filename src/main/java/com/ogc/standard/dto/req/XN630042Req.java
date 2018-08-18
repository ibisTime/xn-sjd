package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:09:34 
 * @history:
 */
public class XN630042Req {
    // 编号
    @NotBlank
    private String id;

    // 值（必填）
    @NotBlank
    private String cvalue;

    // 配置说明（必填）
    @NotBlank
    private String note;

    // 更新人（必填）
    @NotBlank
    private String updater;

    // 备注（选填）
    @NotBlank
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
