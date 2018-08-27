package com.ogc.standard.dto.req;

/**
 * Created by tianlei on 2017/十一月/22.
 */
public class XN802035Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7328540406053213582L;

    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
