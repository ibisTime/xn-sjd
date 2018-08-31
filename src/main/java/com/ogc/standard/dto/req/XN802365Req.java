/**
 * @Title XN802365Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午1:05:43 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 分页查归集地址
 * @author: dl 
 * @since: 2018年8月31日 下午1:05:43 
 * @history:
 */
public class XN802365Req extends APageReq {
    private static final long serialVersionUID = -6075215182478365802L;

    // 订单编号模糊查询
    private String code;

    // 币种
    private String currency;

    // 被归集地址
    private String fromAddress;

    // 归集地址
    private String toAddress;

    // 交易hash
    private String txHash;

    // 状态
    private String status;

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

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
