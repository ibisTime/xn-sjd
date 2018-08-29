package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:29:20 
 * @history:
 */
public class TradingViewKLineModel {

    // 收盘价
    List<BigDecimal> c;

    // 最高价
    List<BigDecimal> h;

    // 最低价
    List<BigDecimal> l;

    // 开盘价
    List<BigDecimal> o;

    // 状态码 ok error no_data
    String s;

    // K线时间，unix时间戳
    List<BigInteger> t;

    // 成交量
    List<BigDecimal> v;

    public List<BigDecimal> getC() {
        return c;
    }

    public void setC(List<BigDecimal> c) {
        this.c = c;
    }

    public List<BigDecimal> getH() {
        return h;
    }

    public void setH(List<BigDecimal> h) {
        this.h = h;
    }

    public List<BigDecimal> getL() {
        return l;
    }

    public void setL(List<BigDecimal> l) {
        this.l = l;
    }

    public List<BigDecimal> getO() {
        return o;
    }

    public void setO(List<BigDecimal> o) {
        this.o = o;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<BigInteger> getT() {
        return t;
    }

    public void setT(List<BigInteger> t) {
        this.t = t;
    }

    public List<BigDecimal> getV() {
        return v;
    }

    public void setV(List<BigDecimal> v) {
        this.v = v;
    }

}
