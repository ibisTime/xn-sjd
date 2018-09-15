/**
 * @Title XN628302Req.java 
 * @Package com.cdkj.info.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年4月24日 下午5:32:01 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年4月24日 下午5:32:01 
 * @history:
 */
public class XN628302Req {

    // ID
    @NotBlank
    private String id;

    // 英文名
    private String ename;

    // 中文名称
    private String cname;

    // 单位
    private String unit;

    // 图片
    private String pic;

    // 介绍
    private String introduce;

    // 序号
    private String orderNo;

    // 上架交易所
    private String putExchange;

    // top上架交易所
    private String topExchange;

    // 钱包类型
    private String walletType;

    // 官网
    private String webUrl;

    // github地址
    private String gitUrl;

    // twitter
    private String twitter;

    // ico时间
    private String icoDatetime;

    // ico成本
    private String icoCost;

    // 募集资金
    private String raiseAmount;

    // 代币分配
    private String tokenDist;

    // 最新提交次数
    private String lastCommitCount;

    // 总提交次数
    private String totalCommitCount;

    // 总贡献值
    private String totalDist;

    // 粉丝数
    private String fansCount;

    // 关注数
    private String keepCount;

    // 复制数
    private String copyCount;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPutExchange() {
        return putExchange;
    }

    public void setPutExchange(String putExchange) {
        this.putExchange = putExchange;
    }

    public String getTopExchange() {
        return topExchange;
    }

    public void setTopExchange(String topExchange) {
        this.topExchange = topExchange;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getIcoDatetime() {
        return icoDatetime;
    }

    public void setIcoDatetime(String icoDatetime) {
        this.icoDatetime = icoDatetime;
    }

    public String getIcoCost() {
        return icoCost;
    }

    public void setIcoCost(String icoCost) {
        this.icoCost = icoCost;
    }

    public String getRaiseAmount() {
        return raiseAmount;
    }

    public void setRaiseAmount(String raiseAmount) {
        this.raiseAmount = raiseAmount;
    }

    public String getTokenDist() {
        return tokenDist;
    }

    public void setTokenDist(String tokenDist) {
        this.tokenDist = tokenDist;
    }

    public String getLastCommitCount() {
        return lastCommitCount;
    }

    public void setLastCommitCount(String lastCommitCount) {
        this.lastCommitCount = lastCommitCount;
    }

    public String getTotalCommitCount() {
        return totalCommitCount;
    }

    public void setTotalCommitCount(String totalCommitCount) {
        this.totalCommitCount = totalCommitCount;
    }

    public String getTotalDist() {
        return totalDist;
    }

    public void setTotalDist(String totalDist) {
        this.totalDist = totalDist;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getKeepCount() {
        return keepCount;
    }

    public void setKeepCount(String keepCount) {
        this.keepCount = keepCount;
    }

    public String getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(String copyCount) {
        this.copyCount = copyCount;
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
