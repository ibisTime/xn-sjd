package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增养护人
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629610Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 养护方编号
    @NotBlank
    private String maintainId;

    // 养护人姓名
    @NotBlank
    private String name;

    // 养护人电话
    @NotBlank
    private String mobile;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
