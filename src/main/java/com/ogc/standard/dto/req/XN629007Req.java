package com.ogc.standard.dto.req;

/**
 * 列表查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:54:37 
 * @history:
 */
public class XN629007Req extends AListReq {

    private static final long serialVersionUID = 7971742946065410825L;

    // 上级编号
    private String parentCode;

    // 名称
    private String name;

    // 级别
    private String level;

    // 顺序
    private String orderNo;

    // 状态(0下架/1上架)
    private String status;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
