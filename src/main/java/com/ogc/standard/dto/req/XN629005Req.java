package com.ogc.standard.dto.req;

/**
 * 分页查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629005Req extends APageReq {

    private static final long serialVersionUID = 3564125020784062589L;

    // 上级编号
    private String parentCode;

    // 类型
    private String type;

    // 名称
    private String name;

    // 等级
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
