package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 承兑交易订单
* @author: lei
* @since: 2018年09月10日 下午04:34:54
* @history:
*/
public class CoinAcceptOrder extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 类型(0买入/1卖出)
	private String type;

	// 用户编号
	private String userId;

	// 承兑商
	private String acceptUser;

	// 交易币种
	private String tradeCurrency;

	// 交易数字货币
	private String tradeCoin;

	// 交易单价
	private String tradePrice;

	// 交易数量
	private String count;

	// 交易总额
	private String tradeAmount;

	// 手续费
	private String fee;

	// 支付超时时间
	private String invalidDatetime;

	// 付款方式
	private String payType;

	// 付款信息
	private String payInfo;

	// 付款银行
	private String payBank;

	// 付款卡号
	private String payCardNo;

	// 收款方式
	private String receiveType;

	// 收款信息
	private String receiveInfo;

	// 收款银行
	private String receiveBank;

	// 收款卡号
	private String receiveCardNo;

	// 状态(0=待支付 1=已支付 2=已释放 3=已取消)
	private String status;

	// 打款时间
	private String markDatetime;

	// 打款说明
	private String markNote;

	// 币释放时间
	private String releaseDatetime;

	// 创建时间
	private String createDatetime;

	// 最后更新人
	private String updater;

	// 最后更新时间
	private String updateDatetime;

	// 备注
	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setAcceptUser(String acceptUser) {
		this.acceptUser = acceptUser;
	}

	public String getAcceptUser() {
		return acceptUser;
	}

	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}

	public String getTradeCurrency() {
		return tradeCurrency;
	}

	public void setTradeCoin(String tradeCoin) {
		this.tradeCoin = tradeCoin;
	}

	public String getTradeCoin() {
		return tradeCoin;
	}

	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getTradePrice() {
		return tradePrice;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCount() {
		return count;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFee() {
		return fee;
	}

	public void setInvalidDatetime(String invalidDatetime) {
		this.invalidDatetime = invalidDatetime;
	}

	public String getInvalidDatetime() {
		return invalidDatetime;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveInfo(String receiveInfo) {
		this.receiveInfo = receiveInfo;
	}

	public String getReceiveInfo() {
		return receiveInfo;
	}

	public void setReceiveBank(String receiveBank) {
		this.receiveBank = receiveBank;
	}

	public String getReceiveBank() {
		return receiveBank;
	}

	public void setReceiveCardNo(String receiveCardNo) {
		this.receiveCardNo = receiveCardNo;
	}

	public String getReceiveCardNo() {
		return receiveCardNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setMarkDatetime(String markDatetime) {
		this.markDatetime = markDatetime;
	}

	public String getMarkDatetime() {
		return markDatetime;
	}

	public void setMarkNote(String markNote) {
		this.markNote = markNote;
	}

	public String getMarkNote() {
		return markNote;
	}

	public void setReleaseDatetime(String releaseDatetime) {
		this.releaseDatetime = releaseDatetime;
	}

	public String getReleaseDatetime() {
		return releaseDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}