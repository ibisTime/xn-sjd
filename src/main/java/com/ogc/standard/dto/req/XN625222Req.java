/**
 * @Title XN625222Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月4日 下午12:54:17 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 下架广告
 * @author: taojian 
 * @since: 2018年9月4日 下午12:54:17 
 * @history:
 */
public class XN625222Req {

    private String userId;
    
    private String adsCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }
}
