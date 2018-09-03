package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * ETH平台归集地址
 * @author: haiqingzheng 
 * @since: 2018年8月26日 下午2:40:29 
 * @history:
 */
public class EthWAddress extends ABaseDO {

    private static final long serialVersionUID = 3965170382137877195L;

    // ID主键
    private Long id;

    // 以太坊地址
    private String address;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 弃用时间
    private Date abandonDatetime;

    // ******** db propertity end *********

    // 地址模糊查询
    private String addressForQuery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getAbandonDatetime() {
        return abandonDatetime;
    }

    public void setAbandonDatetime(Date abandonDatetime) {
        this.abandonDatetime = abandonDatetime;
    }

    public String getAddressForQuery() {
        return addressForQuery;
    }

    public void setAddressForQuery(String addressForQuery) {
        this.addressForQuery = addressForQuery;
    }

}
