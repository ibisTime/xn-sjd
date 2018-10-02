package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午10:05:15 
 * @history:
 */
public class XN629510Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 必填，道具编号
    @NotBlank
    private String toolCode;

    // 必填，购买人
    @NotBlank
    private String userId;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
