package com.ogc.standard.dto.req;

/** 
 * 分红
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:50 
 * @history:
 */
public class XN802415Req extends APageReq {

    private static final long serialVersionUID = 8957318364300968462L;

    // 选填，状态0=待分红 1=已分红
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
