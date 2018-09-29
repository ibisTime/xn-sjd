package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 养护记录
* @author: silver 
* @since: 2018-09-28 19:31:14
* @history:
*/
public class MaintainRecord extends ABaseDO {

    private static final long serialVersionUID = -625078550459022327L;

    // 编号
    private String code;

    // 古树编号
    private String treeNumber;

    // 养护项目编号
    private String projectCode;

    // 养护项目名称
    private String projectName;

    // 养护人编号
    private String maintainerCode;

    // 养护人姓名
    private String maintainerName;

    // 照片
    private String pic;

    // 描述
    private String description;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setMaintainerCode(String maintainerCode) {
        this.maintainerCode = maintainerCode;
    }

    public String getMaintainerCode() {
        return maintainerCode;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
