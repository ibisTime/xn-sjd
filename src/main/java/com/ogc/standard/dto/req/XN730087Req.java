package com.ogc.standard.dto.req;

/**
 * 列表查询代理商
 * @author: silver 
 * @since: 2018年9月28日 下午4:43:59 
 * @history:
 */
public class XN730087Req extends AListReq {

    private static final long serialVersionUID = -1193785447547505011L;

    // 类型（0代理商/1业务员）
    private String type;

    private String userId;

    private String parentUserId;

    // 状态
    private String status;

    // 关键字(名字，手机号模糊查询)
    private String keyword;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
