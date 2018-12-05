package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询邮费
 * @author: silver 
 * @since: Dec 5, 2018 3:01:46 PM 
 * @history:
 */
public class XN629801Req {
    // 编号
    @NotBlank
    private String addressCode;

    // 商品
    @NotEmpty
    private List<String> commodityCodeList;

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public List<String> getCommodityCodeList() {
        return commodityCodeList;
    }

    public void setCommodityCodeList(List<String> commodityCodeList) {
        this.commodityCodeList = commodityCodeList;
    }

}
