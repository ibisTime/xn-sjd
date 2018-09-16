package com.ogc.standard.dto.req;

/** 
 * 分红
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:50 
 * @history:
 */
public class XN802416Req extends APageReq {

    private static final long serialVersionUID = 8957318364300968462L;

    // 必填，分红id
    private String divideId;

    // 选填，用户编号
    private String userId;

    public String getDivideId() {
        return divideId;
    }

    public void setDivideId(String divideId) {
        this.divideId = divideId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
