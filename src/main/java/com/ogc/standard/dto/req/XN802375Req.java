/**
 * @Title XN802375Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午2:31:23 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午2:31:23 
 * @history:
 */
public class XN802375Req extends APageReq {

    private static final long serialVersionUID = -3869919455355392242L;

    // 订单编号
    private String code;

    // from地址
    private String fromAddress;

    // to地址
    private String toAddress;

    // 交易哈希
    private String txHash;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
