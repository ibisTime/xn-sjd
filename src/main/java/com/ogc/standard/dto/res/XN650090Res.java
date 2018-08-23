package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.DayBenefit;

public class XN650090Res {

    // 横轴最大峰值
    private String horizontalMax;

    // 横轴最小峰值
    private String horizontalMin;

    // 竖轴最大峰值
    private String verticalMax;

    // 竖轴最小峰值
    private String verticalMin;

    // 收益率走势
    private List<DayBenefit> list;

    public XN650090Res() {

    }

    public XN650090Res(String horizontalMax, String horizontalMin,
            String verticalMax, String verticalMin, List<DayBenefit> list) {
        this.horizontalMax = horizontalMax;
        this.horizontalMin = horizontalMin;
        this.verticalMax = verticalMax;
        this.verticalMin = verticalMin;
        this.list = list;
    }

    public String getHorizontalMax() {
        return horizontalMax;
    }

    public void setHorizontalMax(String horizontalMax) {
        this.horizontalMax = horizontalMax;
    }

    public String getHorizontalMin() {
        return horizontalMin;
    }

    public void setHorizontalMin(String horizontalMin) {
        this.horizontalMin = horizontalMin;
    }

    public String getVerticalMax() {
        return verticalMax;
    }

    public void setVerticalMax(String verticalMax) {
        this.verticalMax = verticalMax;
    }

    public String getVerticalMin() {
        return verticalMin;
    }

    public void setVerticalMin(String verticalMin) {
        this.verticalMin = verticalMin;
    }

    public List<DayBenefit> getList() {
        return list;
    }

    public void setList(List<DayBenefit> list) {
        this.list = list;
    }

}
