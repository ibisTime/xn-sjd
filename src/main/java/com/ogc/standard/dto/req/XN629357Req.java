package com.ogc.standard.dto.req;

/**
 * 列表查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629357Req extends AListReq {

    private static final long serialVersionUID = 2745520276151400105L;

    // 认养权编号
    private String adoptTreeCode;

    // 生成时间起
    private String createDatetimeStart;

    // 生成时间止
    private String createDatetimeEnd;

    // 状态（0待收取、1已收完、2已过期）
    private String status;

    // 收取人
    private String taker;

    // 收取时间起
    private String takeDatetimeStart;

    // 收取时间止
    private String takeDatetimeEnd;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaker() {
        return taker;
    }

    public void setTaker(String taker) {
        this.taker = taker;
    }

    public String getTakeDatetimeStart() {
        return takeDatetimeStart;
    }

    public void setTakeDatetimeStart(String takeDatetimeStart) {
        this.takeDatetimeStart = takeDatetimeStart;
    }

    public String getTakeDatetimeEnd() {
        return takeDatetimeEnd;
    }

    public void setTakeDatetimeEnd(String takeDatetimeEnd) {
        this.takeDatetimeEnd = takeDatetimeEnd;
    }

}
