/**
 * @Title XN808071Res.java 
 * @Package com.xnjr.mall.dto.res 
 * @Description 
 * @author xieyj  
 * @date 2017年12月16日 下午4:23:27 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/** 
 * @author: xieyj 
 * @since: 2017年12月16日 下午4:23:27 
 * @history:
 */
public class XN629728Res {

    // 抵扣人民币
    private BigDecimal cnyAmount;

    // 使用积分数量
    private BigDecimal jfAmount;

    public XN629728Res() {
    }

    public XN629728Res(BigDecimal cnyAmount, BigDecimal jfAmount) {
        this.cnyAmount = cnyAmount;
        this.jfAmount = jfAmount;
    }

    public BigDecimal getCnyAmount() {
        return cnyAmount;
    }

    public void setCnyAmount(BigDecimal cnyAmount) {
        this.cnyAmount = cnyAmount;
    }

    public BigDecimal getJfAmount() {
        return jfAmount;
    }

    public void setJfAmount(BigDecimal jfAmount) {
        this.jfAmount = jfAmount;
    }
}
