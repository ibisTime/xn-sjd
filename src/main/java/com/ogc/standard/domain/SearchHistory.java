package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 搜索历史
* @author: silver 
* @since: 2019-01-15 15:42:49
* @history:
*/
public class SearchHistory extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 分类（1树/2商品）
    private String type;

    // 内容
    private String content;

    // 搜索时间
    private Date searchDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSearchDatetime(Date searchDatetime) {
        this.searchDatetime = searchDatetime;
    }

    public Date getSearchDatetime() {
        return searchDatetime;
    }

}
