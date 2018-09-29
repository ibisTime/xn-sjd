package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629630Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 古树编号
    @NotBlank
    private String treeNumber;

    // 养护项目编号
    @NotBlank
    private String projectCode;

    // 养护人编号
    @NotBlank
    private String maintainerCode;

    // 照片
    private String pic;

    // 描述
    private String description;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getMaintainerCode() {
        return maintainerCode;
    }

    public void setMaintainerCode(String maintainerCode) {
        this.maintainerCode = maintainerCode;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
