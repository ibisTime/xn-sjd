package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品已认养名单
 * @author: silver 
 * @since: Nov 2, 2018 2:01:41 PM 
 * @history:
 */
public class XN629209Req extends AListReq {

    private static final long serialVersionUID = 8479425657381483060L;

    // 产品编号
    @NotBlank
    private String productCode;

    // 状态列表
    private List<String> statusList;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
