package com.ogc.standard.dto.req;

/**
 * 列表查询申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:23:45 
 * @history:
 */
public class XN629607Req {

    // 产权方
    private String ownerId;

    // 养护方
    private String maintainId;

    // 状态
    private String status;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
