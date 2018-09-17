/**
 * @Title XN802396Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午10:01:22 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 统计用户/渠道商月结算记录分页查询
 * @author: taojian 
 * @since: 2018年9月17日 下午10:01:22 
 * @history:
 */
public class XN802396Req extends APageReq {

    private static final long serialVersionUID = 2687853878205494933L;

    // 用户编号
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
