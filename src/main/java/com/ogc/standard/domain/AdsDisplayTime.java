package com.ogc.standard.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ogc.standard.dao.base.ABaseDO;

public class AdsDisplayTime extends ABaseDO implements Serializable {
    private Integer id;

    @NotBlank
    private String adsCode;

    @NotBlank
    private String week;

    @NotNull
    private Integer startTime;

    @NotNull
    private Integer endTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
