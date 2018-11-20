/**
 * @Title XN629721Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:42:36 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 组合支付
 * @author: silver 
 * @since: Nov 20, 2018 2:48:46 PM 
 * @history:
 */
public class XN629714Req {

    @NotBlank
    private String payGroup;

    @NotBlank
    private String payType;

    private String remark;

    private String tradePwd;

    private String isJfDeduct;

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsJfDeduct() {
        return isJfDeduct;
    }

    public void setIsJfDeduct(String isJfDeduct) {
        this.isJfDeduct = isJfDeduct;
    }

}
