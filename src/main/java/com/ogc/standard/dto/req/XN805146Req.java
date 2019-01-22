/**
 * @Title XN805145.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午5:52:34 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/**
 * 分页查询连续签到
 * @author: silver 
 * @since: Jan 22, 2019 9:56:33 PM 
 * @history:
 */
public class XN805146Req extends AListReq {

    private static final long serialVersionUID = -213328536116448889L;

    // 用户编号
    private String userId;

    // 类型
    private String type;

    // 创建开始时间
    private String createStartDatetime;

    // 创建结束时间
    private String createEndDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateStartDatetime() {
        return createStartDatetime;
    }

    public void setCreateStartDatetime(String createStartDatetime) {
        this.createStartDatetime = createStartDatetime;
    }

    public String getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(String createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

}
