/**
 * @Title UserRelation.java 
 * @Package com.std.user.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午9:37:08 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午9:37:08 
 * @history:
 */
public class UserRelation extends ABaseDO {
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 关系人编号(下家，关注人)
    private String toUser;

    // 状态
    private String status;

    // 关系类型(1信任/2好友)
    private String type;

    // 创建时间
    private Date createDatetime;

    // 备注
    private String remark;

    // **************DB Properties**************

    // 用户
    private User fromUserInfo;

    // 用户
    private User toUserInfo;

    // 证书个数
    private int certificateCount;

    // 序号
    private int rowNo;

    // 碳泡泡余额
    public BigDecimal tppAmount;

    public String mySelf;

    public BigDecimal weightRate1;

    public BigDecimal weightRate2;

    // 可收取的碳泡泡
    public BigDecimal takeableTppAmount;

    public String getMySelf() {
        return mySelf;
    }

    public void setMySelf(String mySelf) {
        this.mySelf = mySelf;
    }

    public BigDecimal getWeightRate1() {
        return weightRate1;
    }

    public void setWeightRate1(BigDecimal weightRate1) {
        this.weightRate1 = weightRate1;
    }

    public BigDecimal getWeightRate2() {
        return weightRate2;
    }

    public void setWeightRate2(BigDecimal weightRate2) {
        this.weightRate2 = weightRate2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getFromUserInfo() {
        return fromUserInfo;
    }

    public void setFromUserInfo(User fromUserInfo) {
        this.fromUserInfo = fromUserInfo;
    }

    public User getToUserInfo() {
        return toUserInfo;
    }

    public void setToUserInfo(User toUserInfo) {
        this.toUserInfo = toUserInfo;
    }

    public String getType() {
        return type;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCertificateCount() {
        return certificateCount;
    }

    public void setCertificateCount(int certificateCount) {
        this.certificateCount = certificateCount;
    }

    public BigDecimal getTppAmount() {
        return tppAmount;
    }

    public void setTppAmount(BigDecimal tppAmount) {
        this.tppAmount = tppAmount;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public BigDecimal getTakeableTppAmount() {
        return takeableTppAmount;
    }

    public void setTakeableTppAmount(BigDecimal takeableTppAmount) {
        this.takeableTppAmount = takeableTppAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
