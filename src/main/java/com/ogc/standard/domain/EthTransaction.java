package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 以太坊交易
* @author: haiqingzheng
* @since: 2017年10月29日 19:13:13
* @history:
*/
public class EthTransaction extends ABaseDO {

    private static final long serialVersionUID = -3990137350298130500L;

    // ID
    private Long id;

    // 交易哈希
    private String hash;

    // 交易次数
    private BigInteger nonce;

    // 区块哈希
    private String blockHash;

    // 区块号
    private BigInteger blockNumber;

    // 区块形成时间
    private Date blockCreateDatetime;

    // 交易index
    private BigInteger transactionIndex;

    // 转出地址
    private String from;

    // 转入地址
    private String to;

    // 交易额
    private BigDecimal value;

    // 同步时间
    private Date syncDatetime;

    // gas价格
    private BigDecimal gasPrice;

    // gas最大使用限制
    private BigInteger gasLimit;

    // gas实际使用量
    private BigInteger gasUsed;

    // 实际使用gas
    private BigDecimal gasFee;

    // input 输入
    private String input;

    // publicKey
    private String publicKey;

    // raw
    private String raw;

    // r
    private String r;

    // s
    private String s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
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

    public Date getBlockCreateDatetime() {
        return blockCreateDatetime;
    }

    public void setBlockCreateDatetime(Date blockCreateDatetime) {
        this.blockCreateDatetime = blockCreateDatetime;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getSyncDatetime() {
        return syncDatetime;
    }

    public void setSyncDatetime(Date syncDatetime) {
        this.syncDatetime = syncDatetime;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    public BigDecimal getGasFee() {
        return gasFee;
    }

    public void setGasFee(BigDecimal gasFee) {
        this.gasFee = gasFee;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

}
