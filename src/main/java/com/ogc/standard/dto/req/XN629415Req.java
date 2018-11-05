package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 分页查询预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:45:37 AM 
 * @history:
 */
public class XN629415Req extends APageReq {

    private static final long serialVersionUID = 3564125020784062589L;

    // 名称
    private String name;

    // 产权方编号
    private String ownerId;

    // 产品分类
    private String categoryCode;

    // UI位置
    private String location;

    // 状态
    private String status;

    // 状态列表
    private List<String> statusList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
