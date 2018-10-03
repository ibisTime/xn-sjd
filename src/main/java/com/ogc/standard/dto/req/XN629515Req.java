package com.ogc.standard.dto.req;

/**
 * 分页查询道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629515Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 道具编号
    private String toolCode;

    // 道具名称
    private String toolName;

    // 购买人
    private String userId;

    // 状态（0未使用/1已使用）
    private String status;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
