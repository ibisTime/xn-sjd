package com.ogc.standard.dto.req;

/**
 * 查默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:51:28 PM 
 * @history:
 */
public class XN629796Req extends APageReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 编号
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
