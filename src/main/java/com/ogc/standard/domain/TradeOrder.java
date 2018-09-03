package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 交易订单
* @author: haiqingzheng
* @since: 2017年11月14日 12:55:21
* @history:
*/
public class TradeOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 交易订单编号
    private String code;

    // 订单类型
    private String type;

    // 广告编号
    private String adsCode;

    // 买方
    private String buyUser;

    // 卖方
    private String sellUser;

    // 广告留言
    private String leaveMessage;

    // 交易币种
    private String tradeCurrency;

    // 交易数字货币类型
    private String tradeCoin;

    // 交易单价
    private BigDecimal tradePrice;

    // 交易总额
    private BigDecimal tradeAmount;

    // 广告费
    private transient BigDecimal fee;

    private String feeString;

    // 交易数字货币数量
    private transient BigDecimal count;

    private String countString;

    // 付款方式
    private String payType;

    // 支付超时时间
    private Date invalidDatetime;

    // 订单状态
    private String status;

    // 卖方对买方的评价
    private String sbComment;

    // 买方对卖方的评价
    private String bsComment;

    // 标记打款时间
    private Date markDatetime;

    // 币释放时间
    private Date releaseDatetime;

    // 创建时间
    private Date createDatetime;

    // 最后更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ******辅助字段******

    // 订单编号模糊查询
    private String codeForQuery;

    // 买家信息
    private User buyUserInfo;

    // 卖家信息
    private User sellUserInfo;

    // 所属用户
    private String belongUser;

    // 状态列表
    private List<String> statusList;

    private List<String> tradeCoinList;

    // 查询统计信息时使用
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public String getTradeCoin() {
        return tradeCoin;
    }

    public void setTradeCoin(String tradeCoin) {
        this.tradeCoin = tradeCoin;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
        this.feeString = fee.toString();
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
        this.countString = count.toString();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSbComment() {
        return sbComment;
    }

    public void setSbComment(String sbComment) {
        this.sbComment = sbComment;
    }

    public String getBsComment() {
        return bsComment;
    }

    public void setBsComment(String bsComment) {
        this.bsComment = bsComment;
    }

    public Date getMarkDatetime() {
        return markDatetime;
    }

    public void setMarkDatetime(Date markDatetime) {
        this.markDatetime = markDatetime;
    }

    public Date getReleaseDatetime() {
        return releaseDatetime;
    }

    public void setReleaseDatetime(Date releaseDatetime) {
        this.releaseDatetime = releaseDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public User getBuyUserInfo() {
        return buyUserInfo;
    }

    public void setBuyUserInfo(User buyUserInfo) {
        this.buyUserInfo = buyUserInfo;
    }

    public User getSellUserInfo() {
        return sellUserInfo;
    }

    public void setSellUserInfo(User sellUserInfo) {
        this.sellUserInfo = sellUserInfo;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public String getFeeString() {
        return feeString;
    }

    public void setFeeString(String feeString) {
        this.feeString = feeString;
    }

    public String getCountString() {
        return countString;
    }

    public void setCountString(String countString) {
        this.countString = countString;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

    public List<String> getTradeCoinList() {
        return tradeCoinList;
    }

    public void setTradeCoinList(List<String> tradeCoinList) {
        this.tradeCoinList = tradeCoinList;
    }

}
