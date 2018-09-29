package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 养护人
* @author: silver 
* @since: 2018-09-28 19:27:39
* @history:
*/
public class Maintainer extends ABaseDO {
    private static final long serialVersionUID = 9161371946132030089L;

    // 编号
    private String code;

    // 养护方编号
    private String maintainId;

    // 养护人姓名
    private String name;

    // 养护人电话
    private String mobile;

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

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
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

}
