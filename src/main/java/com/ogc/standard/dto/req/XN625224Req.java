package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/15.
 */
public class XN625224Req {

    @NotBlank
    private String adsCode;

    @NotBlank
    private String userId;


    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
