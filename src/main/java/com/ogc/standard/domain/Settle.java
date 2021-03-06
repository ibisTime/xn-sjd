package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 结算订单
* @author: silver 
* @since: 2018-09-29 16:29:58
* @history:
*/
public class Settle extends ABaseDO {

    private static final long serialVersionUID = 4405800466529648936L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 用户种类
    private String userKind;

    // 结算金额
    private BigDecimal amount;

    // 结算比例
    private BigDecimal rate;

    // 参考类型
    private String refType;

    // 参考订单编号
    private String refCode;

    // 参考说明
    private String refNote;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 处理人
    private String handler;

    // 处理时间
    private Date handleDatetime;

    // 处理说明
    private String handleNote;

    // 备注
    private String remark;

    // ****************DB Properties****************//
    // 创建开始时间
    private Date createStartDatetime;

    // 创建结束时间
    private Date createEndDatetime;

    private AgentUser agentUser;

    // 认养订单
    private AdoptOrder adoptOrder;

    // 集体订单
    private GroupAdoptOrder groupAdoptOrder;

    // 产品名称
    private String productName;

    // 认养人
    private String applyUserName;

    // 预售订单
    private PresellOrder presellOrder;

    // 商城订单
    private CommodityOrder commodityOrder;

    // 代理用户
    private String userName;

    public AgentUser getAgentUser() {
        return agentUser;
    }

    public void setAgentUser(AgentUser agentUser) {
        this.agentUser = agentUser;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefNote() {
        return refNote;
    }

    public void setRefNote(String refNote) {
        this.refNote = refNote;
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

    public Date getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(Date handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateStartDatetime() {
        return createStartDatetime;
    }

    public void setCreateStartDatetime(Date createStartDatetime) {
        this.createStartDatetime = createStartDatetime;
    }

    public Date getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(Date createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

    public AdoptOrder getAdoptOrder() {
        return adoptOrder;
    }

    public void setAdoptOrder(AdoptOrder adoptOrder) {
        this.adoptOrder = adoptOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public GroupAdoptOrder getGroupAdoptOrder() {
        return groupAdoptOrder;
    }

    public void setGroupAdoptOrder(GroupAdoptOrder groupAdoptOrder) {
        this.groupAdoptOrder = groupAdoptOrder;
    }

    public PresellOrder getPresellOrder() {
        return presellOrder;
    }

    public void setPresellOrder(PresellOrder presellOrder) {
        this.presellOrder = presellOrder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CommodityOrder getCommodityOrder() {
        return commodityOrder;
    }

    public void setCommodityOrder(CommodityOrder commodityOrder) {
        this.commodityOrder = commodityOrder;
    }

}
