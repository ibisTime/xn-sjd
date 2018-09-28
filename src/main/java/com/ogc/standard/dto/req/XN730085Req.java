package com.ogc.standard.dto.req;

/**
 * 分页查询代理商
 * @author: silver 
 * @since: 2018年9月28日 下午4:43:59 
 * @history:
 */
public class XN730085Req extends APageReq {

    private static final long serialVersionUID = -1193785447547505011L;

    // 类型（0代理商/1业务员）
    private String type;

    // 用户等级
    private String level;

    // 状态（0待审核/1合伙中/2已解除合伙/3已注销）
    private String status;

    // 创建开始时间
    private String dateStart;

    // 创建结束时间
    private String dateEnd;

    // 关键字(名字，手机号模糊查询)
    private String keyword;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
