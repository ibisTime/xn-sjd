/**
 * @Title XN625240Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午12:41:06 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 我要出售
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午12:41:06 
 * @history:
 */
public class XN625248Req {

    @NotBlank
    private String adsCode; // 广告编号

    @NotBlank
    private String sellUser; // 卖方

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
    }

}
