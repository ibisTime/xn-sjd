/**
 * @Title XN629710Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:08:41 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午11:08:41 
 * @history:
 */
public class XN629710Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 规格编号
    @NotBlank
    private String specsId;

    // 数量
    @NotBlank
    private String quantity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpecsId() {
        return specsId;
    }

    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
