package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 使用道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629511Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 必填，道具订单编号
    @NotBlank
    private String toolOrderCode;

    // 必填，使用人
    private String userId;

    // 必填，认养权编号
    @NotBlank
    private String adoptTreeCode;

    public String getToolOrderCode() {
        return toolOrderCode;
    }

    public void setToolOrderCode(String toolOrderCode) {
        this.toolOrderCode = toolOrderCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

}
