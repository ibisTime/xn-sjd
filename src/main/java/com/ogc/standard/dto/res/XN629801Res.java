/**
 * @Title XN625000Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午5:02:42 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/**
 * 邮费
 * @author: silver 
 * @since: Dec 5, 2018 3:04:38 PM 
 * @history:
 */
public class XN629801Res {
    private BigDecimal postalFee;

    public XN629801Res(BigDecimal postalFee) {
        super();
        this.postalFee = postalFee;
    }

    public BigDecimal getPostalFee() {
        return postalFee;
    }

    public void setPostalFee(BigDecimal postalFee) {
        this.postalFee = postalFee;
    }

}
