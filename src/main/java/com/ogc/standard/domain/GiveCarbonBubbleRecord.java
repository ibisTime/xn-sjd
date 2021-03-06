package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 赠送碳泡泡记录
* @author: silver 
* @since: 2018-09-29 21:27:07
* @history:
*/
public class GiveCarbonBubbleRecord extends ABaseDO {

    private static final long serialVersionUID = -3344086696398069229L;

    // 编号
    private String code;

    // 赠送数量
    private BigDecimal quantity;

    // 赠送人
    private String userId;

    // 被赠送人
    private String toUserId;

    // 赠送时间
    private Date createDatetime;

    /*************DB Properties*****************/

    // 赠送人
    private String userName;

    // 被赠送人
    private String toUserName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

}
