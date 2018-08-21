package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 每日收益统计
* @author: lei
* @since: 2018年08月21日 下午04:01:36
* @history:
*/
public class DayBenefit extends ABaseDO {

    private static final long serialVersionUID = 3702806657704326980L;

    // 序号（自增长）
    private Long id;

    // 组合编号
    private String groupCode;

    // 昨日总资产
    private BigDecimal yesterdayAssets;

    // 今日总资产
    private BigDecimal todayAssets;

    // 收益
    private BigDecimal benefit;

    // 收益率
    private Double benefitRate;

    // 产生日期
    private Date createDatetime;

    // ******************db properties**************

    // 查询时间起
    private Date qurryDatetimeStart;

    // 查询时间止
    private Date qurryDatetimeEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public BigDecimal getYesterdayAssets() {
        return yesterdayAssets;
    }

    public void setYesterdayAssets(BigDecimal yesterdayAssets) {
        this.yesterdayAssets = yesterdayAssets;
    }

    public BigDecimal getTodayAssets() {
        return todayAssets;
    }

    public void setTodayAssets(BigDecimal todayAssets) {
        this.todayAssets = todayAssets;
    }

    public BigDecimal getBenefit() {
        return benefit;
    }

    public void setBenefit(BigDecimal benefit) {
        this.benefit = benefit;
    }

    public Double getBenefitRate() {
        return benefitRate;
    }

    public void setBenefitRate(Double benefitRate) {
        this.benefitRate = benefitRate;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getQurryDatetimeStart() {
        return qurryDatetimeStart;
    }

    public void setQurryDatetimeStart(Date qurryDatetimeStart) {
        this.qurryDatetimeStart = qurryDatetimeStart;
    }

    public Date getQurryDatetimeEnd() {
        return qurryDatetimeEnd;
    }

    public void setQurryDatetimeEnd(Date qurryDatetimeEnd) {
        this.qurryDatetimeEnd = qurryDatetimeEnd;
    }

}
