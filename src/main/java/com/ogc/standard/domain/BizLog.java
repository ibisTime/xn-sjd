package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 日志
* @author: jiafr 
* @since: 2018-09-28 14:42:17
* @history:
*/
public class BizLog extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID
    private Integer id;

    // 认养权编号
    private String adoptTreeCode;

    // 类型（1赠送碳泡泡/2留言/3分享/4收取碳泡泡）
    private String type;

    // 说明
    private String note;

    // 操作人
    private String userId;

    // 产生时间
    private Date createDatetime;

    /*********DB properties*********/

    /*********辅助字段*****************/
    // 产生时间起
    private Date createDatetimeStart;

    // 产生时间止
    private Date createDatetimeEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

}