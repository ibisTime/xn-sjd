package com.ogc.standard.dto.req;

/**
 * 列表查询搜索历史
 * @author: silver 
 * @since: Jan 15, 2019 4:27:51 PM 
 * @history:
 */
public class XN629657Req extends AListReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 用户编号
    private String userId;

    // 分类（1树/2商品）
    private String type;

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

}
