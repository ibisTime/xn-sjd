/**
 * @Title XN805095Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 上午10:50:53 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 修改手续费率
 * @author: taojian 
 * @since: 2018年9月13日 上午10:50:53 
 * @history:
 */
public class XN805090Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 手续费率
    @NotBlank
    private String tradeRate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTradRate() {
        return tradeRate;
    }

    public void setTradRate(String tradRate) {
        this.tradeRate = tradRate;
    }

}
