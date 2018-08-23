package com.ogc.standard.dto.req;

/**
 * 分页查询组合（oss）
 * @author: lei 
 * @since: 2018年8月20日 下午8:32:49 
 * @history:
 */
public class XN650005Req extends APageReq {

    private static final long serialVersionUID = 4647527459909018895L;

    // 选填，赛事编号
    private String matchCode;

    // 选填，战队编号
    private String teamCode;

    // 选填，用户编号
    private String userId;

    // 选填，状态(1-进行中，0-已结束)
    private String status;

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

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

}
