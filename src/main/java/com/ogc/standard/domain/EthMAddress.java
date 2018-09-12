package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * ETH平台散取地址
 * @author: haiqingzheng 
 * @since: 2018年8月26日 下午2:40:29 
 * @history:
 */
public class EthMAddress extends ABaseDO {

    private static final long serialVersionUID = 3965170382137877195L;

    // ID主键
    private Long id;

    // 以太坊地址
    private String address;

    // keystore密码
    private String keystorePwd;

    // keystore文件名
    private String keystoreName;

    // keystore文件内容
    private String keystoreContent;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 弃用时间
    private Date abandonDatetime;

    // ******** db propertity end *********

    // 所属用户信息
    private User userInfo;

    private List<String> statusList;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

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

    public String getKeystorePwd() {
        return keystorePwd;
    }

    public void setKeystorePwd(String keystorePwd) {
        this.keystorePwd = keystorePwd;
    }

    public String getKeystoreName() {
        return keystoreName;
    }

    public void setKeystoreName(String keystoreName) {
        this.keystoreName = keystoreName;
    }

    public String getKeystoreContent() {
        return keystoreContent;
    }

    public void setKeystoreContent(String keystoreContent) {
        this.keystoreContent = keystoreContent;
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

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public String getAddressForQuery() {
        return addressForQuery;
    }

    public void setAddressForQuery(String addressForQuery) {
        this.addressForQuery = addressForQuery;
    }

}
