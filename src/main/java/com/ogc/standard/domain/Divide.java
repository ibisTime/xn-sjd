package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 分红
* @author: lei
* @since: 2018年09月15日 上午01:35:00
* @history:
*/
public class Divide extends ABaseDO {

    private static final long serialVersionUID = 2631997883953799775L;

    // 编号
    private String id;

    // 分红利润
    private BigDecimal divideProfit;

    // 分红总金额
    private BigDecimal divideAmount;

    // 状态(0=待分红 1=已分红)
    private String status;

    // 创建时间
    private Date createDatetime;

    // 分红时间
    private Date divideDatetime;

    // 分红人
    private String divideUser;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getDivideProfit() {
        return divideProfit;
    }

    public void setDivideProfit(BigDecimal divideProfit) {
        this.divideProfit = divideProfit;
    }

    public BigDecimal getDivideAmount() {
        return divideAmount;
    }

    public void setDivideAmount(BigDecimal divideAmount) {
        this.divideAmount = divideAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getDivideDatetime() {
        return divideDatetime;
    }

    public void setDivideDatetime(Date divideDatetime) {
        this.divideDatetime = divideDatetime;
    }

    public String getDivideUser() {
        return divideUser;
    }

    public void setDivideUser(String divideUser) {
        this.divideUser = divideUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
