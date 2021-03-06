/**
 * @Title SessionMessage.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 上午11:42:18 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 会话消息
 * @author: taojian 
 * @since: 2018年11月8日 上午11:42:18 
 * @history:
 */
public class SessionMessage extends ABaseDO {

    private static final long serialVersionUID = -7053776905448478882L;

    // ***********db properties***********

    // id
    private Long id;

    // 会话编号
    private String sessionCode;

    // 说话人
    private String userId;

    // 消息内容
    private String content;

    // 状态
    private String status;

    // 说话时间
    private Date createDatetime;

    // ***********db properties***********

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
