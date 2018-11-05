package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 填写收货地址
 * @author: silver 
 * @since: Nov 4, 2018 2:57:58 PM 
 * @history:
 */
public class XN629433Req {

    // 编号
    @NotBlank
    private String code;

    // 物流单列表
    @NotEmpty
    private List<XN629433ReqLogistics> logisticsList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<XN629433ReqLogistics> getLogisticsList() {
        return logisticsList;
    }

    public void setLogisticsList(List<XN629433ReqLogistics> logisticsList) {
        this.logisticsList = logisticsList;
    }

}
