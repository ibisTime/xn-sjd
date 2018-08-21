package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:47:38 
 * @history:
 */
public class XN628300Req {

    // 赛事编号
    @NotBlank
    private String matchCode;

    // 战队名称
    @NotBlank
    private String teamName;

    // logo
    @NotBlank
    private String teamLogo;

    // 描述
    @NotBlank
    private String teamDesc;

    // 申请人编号
    @NotBlank
    private String userId;

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
