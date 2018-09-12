package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 行情K线
* @author: lei
* @since: 2018年08月30日 下午04:33:18
* @history:
*/
public class TsimuKline extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String id;

	// 交易币种
	private String symbol;

	// 计价币种
	private String toSymbol;

	// K线类型
	private String period;

	// 成交量
	private String volume;

	// 成交笔数
	private String quantity;

	// 成交额
	private String amount;

	// 开盘价
	private String open;

	// 收盘价
	private String close;

	// 最高价
	private String high;

	// 最低价
	private String low;

	// 时间
	private String createDatetime;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setToSymbol(String toSymbol) {
		this.toSymbol = toSymbol;
	}

	public String getToSymbol() {
		return toSymbol;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriod() {
		return period;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getVolume() {
		return volume;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getOpen() {
		return open;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getClose() {
		return close;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getHigh() {
		return high;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getLow() {
		return low;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

}