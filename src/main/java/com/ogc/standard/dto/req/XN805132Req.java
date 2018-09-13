/**
 * @Title XN805132Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:46:26 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 审核
 * @author: taojian 
 * @since: 2018年9月13日 下午2:46:26 
 * @history:
 */
public class XN805132Req {
    // id
    @NotBlank
    private String id;

    // 审核人
    @NotBlank
    private String approveUser;

    // 审核结果
    @NotBlank
    private String approveResult;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
