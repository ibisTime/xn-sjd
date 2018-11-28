package com.ogc.standard.dto.req;

/**
 * 养护产权收益
 * @author: silver 
 * @since: Oct 22, 2018 5:37:54 PM 
 * @history:
 */
public class XN629905Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    private String userId;

    // 账号类型
    private String accountType;

    // 开始时间
    private String dateStart;

    // 结束时间
    private String dateEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

}
