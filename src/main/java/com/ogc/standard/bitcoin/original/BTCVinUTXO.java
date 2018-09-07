package com.ogc.standard.bitcoin.original;

import java.math.BigDecimal;

public class BTCVinUTXO {

    private Integer n;

    private Integer vout;

    private String txid;

    private String coinbase;

    private String addr;

    private BigDecimal value;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        // 转化为没有小数
        this.value = value.multiply(BigDecimal.TEN.pow(8)).setScale(0,
            BigDecimal.ROUND_DOWN);
        // this.value = value;
    }

}
