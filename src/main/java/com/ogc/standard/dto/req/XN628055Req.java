package com.ogc.standard.dto.req;

/**
 * 分页查询帖子(oss)
 * @author: silver 
 * @since: 2018年8月22日 下午4:26:39 
 * @history:
 */
public class XN628055Req extends APageReq {

    private static final long serialVersionUID = 4971368342303794017L;

    // 发布人
    private String userId;

    // 状态(A=待审核 B=审核通过 C=审核不通过 D=已发布 G=已删除)
    private String status;

    // 位置(0普通 1热门)
    private String location;

    // 板块编号
    private String plateCode;

    // 发布时间起
    private String publishDatetimeStart;

    // 发布时间止
    private String publishDatetimeEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPublishDatetimeStart() {
        return publishDatetimeStart;
    }

    public void setPublishDatetimeStart(String publishDatetimeStart) {
        this.publishDatetimeStart = publishDatetimeStart;
    }

    public String getPublishDatetimeEnd() {
        return publishDatetimeEnd;
    }

    public void setPublishDatetimeEnd(String publishDatetimeEnd) {
        this.publishDatetimeEnd = publishDatetimeEnd;
    }
}
