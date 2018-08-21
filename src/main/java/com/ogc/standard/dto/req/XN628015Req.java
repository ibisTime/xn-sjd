package com.ogc.standard.dto.req;

/**
 * 分页查询战队(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午3:07:50 
 * @history:
 */
public class XN628015Req extends APageReq {
    private static final long serialVersionUID = -1397348212917126608L;

    // 赛事编号
    private String matchCode;

    // 战队名称
    private String name;

    // 队长
    private String captain;

    // 位置
    private String location;

    // 状态
    private String status;

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
