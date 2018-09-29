package com.ogc.standard.dto.req;

/**
 * 列表查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629027Req extends AListReq {

    private static final long serialVersionUID = 3564125020784062589L;

    // 名称
    private String name;

    // 销售分类（0个人/1定向/2捐赠/3集体）
    private String sellType;

    // 产权方编号
    private String ownerId;

    // UI位置
    private String location;

    // 产品分类
    private String categoryCode;

    // 状态（0草稿/1已提交待审核/2审核不通过/3审核通过待上架/4已上架待认养/5已锁定/6已认养可下架/7已下架）
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
