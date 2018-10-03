package com.ogc.standard.dto.req;

/**
 * 分页查询道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629525Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 选填，道具订单编号
    private String toolOrderCode;

    // 选填，认养权编号
    private String adoptTreeCode;

    // 选填，状态(1生效中 0已失效)
    private String status;

    // 选填，使用人
    private String userId;

    public String getToolOrderCode() {
        return toolOrderCode;
    }

    public void setToolOrderCode(String toolOrderCode) {
        this.toolOrderCode = toolOrderCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
