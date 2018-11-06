package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发货
 * @author: silver 
 * @since: Nov 5, 2018 8:11:29 PM 
 * @history:
 */
public class XN629460Req {

    // 编号
    @NotBlank
    private String code;

    // 物流公司
    @NotBlank
    private String logisticsCompany;

    // 物流单号
    @NotBlank
    private String logisticsNumber;

    // 发货人
    @NotBlank
    private String deliver;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
