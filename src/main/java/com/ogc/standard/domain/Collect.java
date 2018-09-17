package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 归集订单
* @author: haiqingzheng
* @since: 2017年11月09日 14:26:36
* @history:
*/
public class Collect extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 币种
    private String currency;

    // 被归集地址
    private String fromAddress;

    // 归集地址
    private String toAddress;

    // 归集数量
    private BigDecimal amount;

    // 交易hash
    private String txHash;

    // 矿工费
    private BigDecimal txFee;

    // 状态
    private String status;

    // 发起时间
    private Date createDatetime;

    // 网络记账时间
    private Date confirmDatetime;

    // 完成时间
    private Date finishDatetime;

    // 关联充值单号
    private String refNo;

    // 币种类型
    private String coinType;

    // 矿工费补给地址
    private String preFrom;

    // 矿工费收款地址
    private String preTo;

    // 矿工费补给数量
    private BigDecimal preAmount;

    // 矿工费补给交易hash
    private String preTxHash;

    // 矿工费补给交易矿工费
    private BigDecimal preTxFee;

    // 矿工费补给发起时间
    private Date preCreateDatetime;

    // 矿工费补给网络记账时间
    private Date preConfirmDatetime;

    // 备注
    private String remark;

    // 订单编号模糊查询
    private String codeForQuery;

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getPreFrom() {
        return preFrom;
    }

    public void setPreFrom(String preFrom) {
        this.preFrom = preFrom;
    }

    public String getPreTo() {
        return preTo;
    }

    public void setPreTo(String preTo) {
        this.preTo = preTo;
    }

    public BigDecimal getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(BigDecimal preAmount) {
        this.preAmount = preAmount;
    }

    public String getPreTxHash() {
        return preTxHash;
    }

    public void setPreTxHash(String preTxHash) {
        this.preTxHash = preTxHash;
    }

    public BigDecimal getPreTxFee() {
        return preTxFee;
    }

    public void setPreTxFee(BigDecimal preTxFee) {
        this.preTxFee = preTxFee;
    }

    public Date getPreCreateDatetime() {
        return preCreateDatetime;
    }

    public void setPreCreateDatetime(Date preCreateDatetime) {
        this.preCreateDatetime = preCreateDatetime;
    }

    public Date getPreConfirmDatetime() {
        return preConfirmDatetime;
    }

    public void setPreConfirmDatetime(Date preConfirmDatetime) {
        this.preConfirmDatetime = preConfirmDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public BigDecimal getTxFee() {
        return txFee;
    }

    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
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

    public Date getConfirmDatetime() {
        return confirmDatetime;
    }

    public void setConfirmDatetime(Date confirmDatetime) {
        this.confirmDatetime = confirmDatetime;
    }

    public Date getFinishDatetime() {
        return finishDatetime;
    }

    public void setFinishDatetime(Date finishDatetime) {
        this.finishDatetime = finishDatetime;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

}
