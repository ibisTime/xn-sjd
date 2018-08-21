package com.ogc.standard.dto.req;

/**
 * 分页查询战队(front)
 * @author: silver 
 * @since: 2018年8月21日 下午3:07:50 
 * @history:
 */
public class XN628018Req extends APageReq {
    private static final long serialVersionUID = -1397348212917126608L;

    // 赛事编号
    private String matchCode;

    // 位置
    private String location;

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
