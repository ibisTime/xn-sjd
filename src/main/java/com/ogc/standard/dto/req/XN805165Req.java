/**
 * @Title XN805135Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:46:26 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 分页查
 * @author: taojian 
 * @since: 2018年9月13日 下午2:46:26 
 * @history:
 */
public class XN805165Req extends APageReq {
    private static final long serialVersionUID = -6085715929418687916L;

    // 类型
    private String type;

    // 审核人
    private String approveUser;

    // 申请人
    private String applyUser;

    // 状态
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
