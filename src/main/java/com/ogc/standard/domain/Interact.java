package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 点赞收藏表
* @author: Silver
* @since: 2018年08月22日 下午05:43:28
* @history:
*/
public class Interact extends ABaseDO {

    private static final long serialVersionUID = -4594915544956691967L;

    // 编号
    private String code;

    // 类型(0点赞/1收藏)
    private String type;

    // 对象类型(0文章/1古树)
    private String objectType;

    // 对象编号
    private String objectCode;

    // 用户编号
    private String userId;

    // 创建时间
    private Date createDatetime;

    /**************DB Porperties******************/
    // 文章
    private Article article;

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

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
