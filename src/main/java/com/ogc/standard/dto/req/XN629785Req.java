/**
 * @Title XN629780Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午2:46:19 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午2:46:19 
 * @history:
 */
public class XN629785Req extends APageReq {

    private static final long serialVersionUID = -3635795449795424818L;

    private String type;

    private String user1;

    private String user2;

    private String createDatetimeStart;

    private String createDatetimeEnd;

    // 说话人2最少未读消息数
    private String minUser2UnreadSum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(String createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(String createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getMinUser2UnreadSum() {
        return minUser2UnreadSum;
    }

    public void setMinUser2UnreadSum(String minUser2UnreadSum) {
        this.minUser2UnreadSum = minUser2UnreadSum;
    }

}
