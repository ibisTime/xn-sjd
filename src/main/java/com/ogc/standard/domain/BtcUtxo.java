package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * @author: haiqingzheng 
 * @since: 2018年2月6日 下午8:53:29 
 * @history:
 */
public class BtcUtxo extends ABaseDO {

    private static final long serialVersionUID = 3556545568582185031L;

    // id
    private Long id;

    // 交易id
    private String txid;

    // vout数
    private Integer vout;

    // 数量
    private BigDecimal count;

    // 对方的公钥
    private String scriptPubKey;

    // 地址
    private String address;

    // 同步时间
    private Date syncTime;

    // 区块高度
    private Long blockHeight;

    // 状态（0-未使用，1-广播中，2-已使用）
    private String status;

    // 地址类型
    private String addressType;

    // 参考类型（1 取现 2 归集）
    private String refType;

    // 参考编号
    private String refNo;

    // 状态列表
    private List<String> statusList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
