/**
 * @Title XN625275Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月15日 下午6:14:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.springframework.beans.factory.annotation.Autowired;

/** 
 * 承兑商付款处理
 * @author: taojian 
 * @since: 2018年9月15日 下午6:14:38 
 * @history:
 */
public class XN625275Req {
    // 订单编号
    @Autowired
    private String code;

    // 用户编号
    @Autowired
    private String userId;

    // 结果 1=已付款 0=不付款
    @Autowired
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
