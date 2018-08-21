package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 关注
* @author: lei
* @since: 2018年08月21日 上午10:24:44
* @history:
*/
public class Attention extends ABaseDO {

    private static final long serialVersionUID = 1547898749423452688L;

    // 编号
    private String code;

    // 类型(1-关注/0-提醒)
    private String type;

    // 用户编号
    private String userId;

    // 组合编号
    private String groupCode;

    // 组合用户编号
    private String groupUserId;

    // 关注时间
    private Date createDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupUserId(String groupUserId) {
        this.groupUserId = groupUserId;
    }

    public String getGroupUserId() {
        return groupUserId;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

}
