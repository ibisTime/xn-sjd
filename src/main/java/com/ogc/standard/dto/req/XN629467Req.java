package com.ogc.standard.dto.req;

/**
 * 列表查询物流单
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629467Req extends AListReq {

    private static final long serialVersionUID = 129338806402344599L;

    // 原生组编号
    private String originalGroupCode;

    // 物流公司
    private String logisticsCompany;

    // 物流单号
    private String logisticsNumber;

    // 发货人
    private String deliver;

    // 收货人
    private String receiver;

    // 状态
    private String status;

    public String getOriginalGroupCode() {
        return originalGroupCode;
    }

    public void setOriginalGroupCode(String originalGroupCode) {
        this.originalGroupCode = originalGroupCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
