package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 加入战队申请(front)
 * @author: silver 
 * @since: 2018年8月21日 下午7:24:54 
 * @history:
 */
public class XN628020Req {
    // 战队编号
    @NotBlank
    private String teamCode;

    // 申请人
    @NotBlank
    private String applyUser;

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
}
