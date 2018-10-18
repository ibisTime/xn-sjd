package com.ogc.standard.dto.res;

/**
 * 签到
 * @author: silver 
 * @since: Oct 9, 2018 10:56:28 PM 
 * @history:
 */
public class XN805140Res {

    public XN805140Res(Integer tppAmount, long signDays) {
        super();
        this.tppAmount = tppAmount;
        this.signDays = signDays;
    }

    // 碳泡泡数量
    private Integer tppAmount;

    // 连续签到天数
    private long signDays;

    public Integer getTppAmount() {
        return tppAmount;
    }

    public void setTppAmount(Integer tppAmount) {
        this.tppAmount = tppAmount;
    }

    public long getSignDays() {
        return signDays;
    }

    public void setSignDays(long signDays) {
        this.signDays = signDays;
    }

}
