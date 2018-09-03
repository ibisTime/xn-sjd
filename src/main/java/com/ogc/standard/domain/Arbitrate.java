package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 仲裁
* @author: haiqingzheng
* @since: 2017年11月14日 14:16:46
* @history:
*/
public class Arbitrate extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 工单编号
    private String code;

    // 交易订单编号
    private String tradeOrderCode;

    // 原告
    private String yuangao;

    // 被告
    private String beigao;

    // 申请理由
    private String reason;

    // 附件
    private String attach;

    // 状态
    private String status;

    // 申请时间
    private Date createDatetime;

    // 处理结果
    private String result;

    // 处理人
    private String updater;

    // 处理时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 原告
    private User yuangaoInfo;

    // 被告
    private User beigaoInfo;

    // 订单信息
    private TradeOrder tradeOrder;

    public TradeOrder getTradeOrder() {
        return tradeOrder;
    }

    public void setTradeOrder(TradeOrder tradeOrder) {
        this.tradeOrder = tradeOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTradeOrderCode() {
        return tradeOrderCode;
    }

    public void setTradeOrderCode(String tradeOrderCode) {
        this.tradeOrderCode = tradeOrderCode;
    }

    public String getYuangao() {
        return yuangao;
    }

    public void setYuangao(String yuangao) {
        this.yuangao = yuangao;
    }

    public String getBeigao() {
        return beigao;
    }

    public void setBeigao(String beigao) {
        this.beigao = beigao;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getYuangaoInfo() {
        return yuangaoInfo;
    }

    public void setYuangaoInfo(User yuangaoInfo) {
        this.yuangaoInfo = yuangaoInfo;
    }

    public User getBeigaoInfo() {
        return beigaoInfo;
    }

    public void setBeigaoInfo(User beigaoInfo) {
        this.beigaoInfo = beigaoInfo;
    }

}
