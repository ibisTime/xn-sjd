/**
 * @Title XN625100Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月9日 下午7:02:19 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.math.BigInteger;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月9日 下午7:02:19 
 * @history:
 */
public class XN802530Req extends APageReq {

    private static final long serialVersionUID = -2919414958783911395L;

    // 交易哈希
    private String hash;

    // 区块哈希
    private String blockHash;

    // 区块号
    private BigInteger blockNumber;

    // 转出地址
    private String from;

    // 转入地址
    private String to;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
