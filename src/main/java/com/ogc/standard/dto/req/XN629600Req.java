package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请绑定养护方（产权）
 * @author: jiafr 
 * @since: 2018年9月27日 上午9:46:46 
 * @history:
 */
public class XN629600Req {

    // 产权方编号
    @NotBlank
    private String ownerId;

    // 养护方编号
    @NotBlank
    private String maintainId;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
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
