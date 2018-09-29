package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629620Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 养护方编号
    @NotBlank
    private String maintainId;

    // 项目名称
    @NotBlank
    private String projectName;

    // 描述
    private String description;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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