package com.ogc.standard.dto.req;

/**
 * 分页查询帖子(front)
 * @author: silver 
 * @since: 2018年8月22日 下午4:26:39 
 * @history:
 */
public class XN628057Req extends APageReq {

    private static final long serialVersionUID = 4971368342303794017L;

    // 发布人
    private String userId;

    // 位置(0普通 1热门)
    private String location;

    // 板块编号
    private String plateCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }
}
