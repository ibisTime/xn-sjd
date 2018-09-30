package com.ogc.standard.dto.req;

/**
 * 列表查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629317Req extends AListReq {

    private static final long serialVersionUID = 729226596135448795L;

    // 认养权编号
    private String adoptTreeCode;

    // 来访人id
    private String userId;

    // 来访时间起
    private String createDatetimeStart;

    // 来访时间止
    private String createDatetimeEnd;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

}
