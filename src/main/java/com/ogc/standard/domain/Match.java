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

    // 赛事开始开始时间
    private Date startDatetimeStart;

    // 赛事开始结束时间
    private Date startDatetimeEnd;

    // 赛事结束开始时间
    private Date endDatetimeStart;

    // 赛事结束结束时间
    private Date endDatetimeEnd;

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

    public Date getStartDatetimeStart() {
        return startDatetimeStart;
    }

    public void setStartDatetimeStart(Date startDatetimeStart) {
        this.startDatetimeStart = startDatetimeStart;
    }

    public Date getStartDatetimeEnd() {
        return startDatetimeEnd;
    }

    public void setStartDatetimeEnd(Date startDatetimeEnd) {
        this.startDatetimeEnd = startDatetimeEnd;
    }

    public Date getEndDatetimeStart() {
        return endDatetimeStart;
    }

    public void setEndDatetimeStart(Date endDatetimeStart) {
        this.endDatetimeStart = endDatetimeStart;
    }

    public Date getEndDatetimeEnd() {
        return endDatetimeEnd;
    }

    public void setEndDatetimeEnd(Date endDatetimeEnd) {
        this.endDatetimeEnd = endDatetimeEnd;
    }

}
