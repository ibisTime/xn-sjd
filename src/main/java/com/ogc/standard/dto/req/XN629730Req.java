/**
 * @Title XN629750Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午4:43:37 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 订单评论
 * @author: silver 
 * @since: Nov 20, 2018 4:00:04 PM 
 * @history:
 */
public class XN629730Req {

    @NotBlank
    private String code;

    @NotBlank
    private String userId;

    @NotBlank
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
