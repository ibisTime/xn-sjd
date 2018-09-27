package com.ogc.standard.dto.req;

/**
 * 列表查询古树
 * @author: silver 
 * @since: 2018年9月27日 下午8:17:23 
 * @history:
 */
public class XN629037Req extends AListReq {

    private static final long serialVersionUID = 3564125020784062589L;

    // 产品编号
    private String productCode;

    // 产权方编号
    private String ownerId;

    // 树木编号
    private String treeNumber;

    // 学名
    private String scientificName;

    // 状态（0待认养/1已认养）
    private String status;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
