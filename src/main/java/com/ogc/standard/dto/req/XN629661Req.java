package com.ogc.standard.dto.req;

/**
 * 删除审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 3:46:15 PM 
 * @history:
 */
public class XN629661Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 编号
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
