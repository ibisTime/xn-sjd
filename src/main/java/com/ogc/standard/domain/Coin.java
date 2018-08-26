package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 数字货币币种
* @author: haiqingzheng
* @since: 2018年03月13日 10:25:43
* @history:
*/
public class Coin extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 主键编号
    private Long id;

    // 英文简称
    private String symbol;

    // 英文名称
    private String ename;

    // 中文名称
    private String cname;

    // 分类 0-以太币 1-比特币 2—万维币 0T-以太token币 2T-万维token币
    private String type;

    // 单位
    private Integer unit;

    // 币种图标
    private String icon;

    // 钱包水印图标
    private String pic1;

    // 流水加钱图标
    private String pic2;

    // 流水减钱图标
    private String pic3;

    // UI序号
    private Integer orderNo;

    // 归集阀值
    private BigDecimal collectStart;

    // 取现手续费
    private BigDecimal withdrawFee;

    // 合约地址
    private String contractAddress;

    // 合约ABI
    private String contractAbi;

    // 状态
    private String status;

    // 最后操作人
    private String updater;

    // 最后操作时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 英文简称
    private String symbolForQuery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getCollectStart() {
        return collectStart;
    }

    public void setCollectStart(BigDecimal collectStart) {
        this.collectStart = collectStart;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractAbi() {
        return contractAbi;
    }

    public void setContractAbi(String contractAbi) {
        this.contractAbi = contractAbi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSymbolForQuery() {
        return symbolForQuery;
    }

    public void setSymbolForQuery(String symbolForQuery) {
        this.symbolForQuery = symbolForQuery;
    }

}
