/**
 * @Title XN629721Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:42:36 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;


/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:42:36 
 * @history:
 */
public class XN629725Req extends APageReq {

    private static final long serialVersionUID = -3471724529126853239L;

    private String applyUser;

    private String expressType;

    private String payType;

    private String logisticsCompany;

    private String status;

    private String applyDatetiemStart;

    private String applyDatetimeEnd;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyDatetiemStart() {
        return applyDatetiemStart;
    }

    public void setApplyDatetiemStart(String applyDatetiemStart) {
        this.applyDatetiemStart = applyDatetiemStart;
    }

    public String getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(String applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

}
