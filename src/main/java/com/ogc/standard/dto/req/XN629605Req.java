package com.ogc.standard.dto.req;

/**
 * 分页查询申请绑定养护方记录
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:07:58 
 * @history:
 */
public class XN629605Req extends APageReq {

    private static final long serialVersionUID = 6503301934527130331L;

    // 产权方编号
    private String ownerId;

    // 养护方编号
    private String maintainId;

    // 状态（1审核/2审核不通过/3已绑定/4已解除）
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
