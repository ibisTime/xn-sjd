/**
 * @Title XN802399req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午11:11:25 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 间推用户分页查询
 * @author: taojian 
 * @since: 2018年9月17日 下午11:11:25 
 * @history:
 */
public class XN802400req extends APageReq {

    private static final long serialVersionUID = -3153123375831784323L;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
