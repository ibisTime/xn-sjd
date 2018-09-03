package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * ETH用户分发地址
 * @author: haiqingzheng 
 * @since: 2018年8月26日 下午2:37:44 
 * @history:
 */
public class EthXAddress extends ABaseDO {

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

    // 用户编号
    private String userId;

    // 创建时间
    private Date createDatetime;

    // ******** db propertity end *********

    // 所属用户信息
    private User userInfo;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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
