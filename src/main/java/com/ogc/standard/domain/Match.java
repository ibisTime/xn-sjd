package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 赛事
* @author: Silver
* @since: 2018年08月21日 上午10:32:31
* @history:
*/
public class Match extends ABaseDO {
    private static final long serialVersionUID = -8269644146094863132L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 开始时间
    private Date startDatetime;

    // 结束时间
    private Date endDatetime;

    // 序号
    private Integer orderNo;

    // 状态（1待发布，2已发布，3已开始，4已过期）
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /**********DB Properties****************/

    // 开始时间(用于定时器)
    private Date matchStartDatetime;

    // 结束时间(用于定时器)
    private Date matchEndDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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

    public Date getMatchStartDatetime() {
        return matchStartDatetime;
    }

    public void setMatchStartDatetime(Date matchStartDatetime) {
        this.matchStartDatetime = matchStartDatetime;
    }

    public Date getMatchEndDatetime() {
        return matchEndDatetime;
    }

    public void setMatchEndDatetime(Date matchEndDatetime) {
        this.matchEndDatetime = matchEndDatetime;
    }

}
