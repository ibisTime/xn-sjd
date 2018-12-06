/**
 * @Title XN629786Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午2:53:46 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 是否存在未读消息
 * @author: silver 
 * @since: Nov 23, 2018 10:30:26 AM 
 * @history:
 */
public class XN629789Req {

    // 说话人1
    private String user1;

    // 说话人2
    private String user2;

    // 编号
    @NotBlank
    private String code;

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
