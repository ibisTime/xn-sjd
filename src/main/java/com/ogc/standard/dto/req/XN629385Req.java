package com.ogc.standard.dto.req;

/**
 * 分页查询弹幕
 * @author: silver 
 * @since: Nov 23, 2018 3:43:38 PM 
 * @history:
 */
public class XN629385Req extends APageReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 编号
    private String code;

    // 状态（0上架/1下架）
    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
