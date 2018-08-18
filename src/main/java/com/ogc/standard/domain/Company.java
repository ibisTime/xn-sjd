/**
 * @Title Company.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午4:06:20 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 公司
 * @author: dl 
 * @since: 2018年8月17日 下午4:06:20 
 * @history:
 */
public class Company extends ABaseDO {

    private static final long serialVersionUID = 3913563734797849223L;

    // ***********db properties***********
    // 编号
    private String code;

    // 公司名称
    private String name;

    // 创建时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db properties***********

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatDatetime() {
        return createDatetime;
    }

    public void setCreatDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
