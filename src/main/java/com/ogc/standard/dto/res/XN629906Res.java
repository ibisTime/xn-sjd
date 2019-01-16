package com.ogc.standard.dto.res;

/**
 * 本月签到统计
 * @author: silver 
 * @since: Jan 16, 2019 11:17:10 AM 
 * @history:
 */
public class XN629906Res {
    // 连续签到天数
    private Long continueSignCount;

    // 本月签到天数
    private Long monthSignCount;

    public Long getContinueSignCount() {
        return continueSignCount;
    }

    public void setContinueSignCount(Long continueSignCount) {
        this.continueSignCount = continueSignCount;
    }

    public Long getMonthSignCount() {
        return monthSignCount;
    }

    public void setMonthSignCount(Long monthSignCount) {
        this.monthSignCount = monthSignCount;
    }

    public XN629906Res(Long continueSignCount, Long monthSignCount) {
        super();
        this.continueSignCount = continueSignCount;
        this.monthSignCount = monthSignCount;
    }

}
