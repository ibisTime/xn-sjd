/**
 * @Title XN625246Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午2:35:01 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午2:35:01 
 * @history:
 */
public class XN625246Req {

    // 交易订单编号
    @NotBlank
    private String code;

    // 申请人
    @NotBlank
    private String applyUser;

    // 申请理由
    @NotBlank(message = "申请理由不能为空")
    private String reason;

    // 附件
    private String attach;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
