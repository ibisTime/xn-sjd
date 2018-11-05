package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 分页查询寄售
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629455Req extends APageReq {

    private static final long serialVersionUID = 129338806402344599L;

    // 原生组编号
    private String originalCode;

    // 产品编号
    private String productCode;

    // 类型（定向/二维码/挂单）
    private String type;

    // 挂单人
    private String creater;

    // 状态
    private String status;

    // 成交人
    private String claimant;

    // 状态列表
    private List<String> statusList;

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
