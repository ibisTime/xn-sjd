/**
 * @Title XN802397.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午8:40:29 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 用户佣金明细分页查询
 * @author: taojian 
 * @since: 2018年9月14日 下午8:40:29 
 * @history:
 */
public class XN802397Req extends APageReq {

    private static final long serialVersionUID = -7168766732588823518L;

    // 用户编号
    private String userId;

    // 用户类型
    private String userKind;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }
}
