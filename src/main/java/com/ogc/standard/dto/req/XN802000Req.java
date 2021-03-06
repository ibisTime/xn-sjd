/**
 * @Title XN802250Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:13:30 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:13:30 
 * @history:
 */
public class XN802000Req {

    // 0T-以太坊token币
    @NotBlank
    private String type;

    // 英文简称
    @NotBlank
    private String symbol;

    // 英文名称
    @NotBlank
    private String ename;

    // 中文名称
    @NotBlank
    private String cname;

    // 单位
    @NotBlank
    private String unit;

    // 币种图标
    @NotBlank
    private String icon;

    // 钱包水印图标
    @NotBlank
    private String pic1;

    // 流水加钱图标
    @NotBlank
    private String pic2;

    // 流水减钱图标
    @NotBlank
    private String pic3;

    // UI序号
    @NotBlank
    private String orderNo;

    // 简介
    private String introduction;

    // 流通量
    private String totalSupply;

    // 流通市值
    private String totalSupplyMarket;

    // 发行总量
    private String maxSupply;

    // 发行市值
    private String maxSupplyMarket;

    // 市值排名
    private String rank;

    // 白皮书
    private String whitePaper;

    // 官网
    private String webUrl;

    // 区块查询
    private String blockUrl;

    // ico时间
    private Date icoDatetime;

    // 归集阀值
    @NotBlank
    private String collectStart;

    // 取现手续费
    @NotBlank
    private String withdrawFee;

    // 合约地址
    @NotBlank
    private String contractAddress;

    // 合约ABI
    @NotBlank
    private String contractABI;

    // 操作人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getTotalSupplyMarket() {
        return totalSupplyMarket;
    }

    public void setTotalSupplyMarket(String totalSupplyMarket) {
        this.totalSupplyMarket = totalSupplyMarket;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getMaxSupplyMarket() {
        return maxSupplyMarket;
    }

    public void setMaxSupplyMarket(String maxSupplyMarket) {
        this.maxSupplyMarket = maxSupplyMarket;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWhitePaper() {
        return whitePaper;
    }

    public void setWhitePaper(String whitePaper) {
        this.whitePaper = whitePaper;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getBlockUrl() {
        return blockUrl;
    }

    public void setBlockUrl(String blockUrl) {
        this.blockUrl = blockUrl;
    }

    public Date getIcoDatetime() {
        return icoDatetime;
    }

    public void setIcoDatetime(Date icoDatetime) {
        this.icoDatetime = icoDatetime;
    }

    public String getCollectStart() {
        return collectStart;
    }

    public void setCollectStart(String collectStart) {
        this.collectStart = collectStart;
    }

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractABI() {
        return contractABI;
    }

    public void setContractABI(String contractABI) {
        this.contractABI = contractABI;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
