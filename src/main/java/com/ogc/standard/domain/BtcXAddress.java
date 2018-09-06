package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * btcX地址
 * @author: xieyj 
 * @since: 2018年2月7日 下午2:08:49 
 * @history:
 */
public class BtcXAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private Long id;

    // btc地址
    private String address;

    // 私钥
    private transient String privatekey;

    // 用户编号
    private String userId;

    // 创建时间
    private Date createDatetime;

    /* DB properties 结束 */

    // 状态列表
    private List<String> statusList;

    // 类型列表
    private List<String> typeList;

    // 地址模糊查询
    private String addressForQuery;

    private Long useCount;

    private BigDecimal useAmount;

    private String useAmountString;

    private String balanceString;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getAddressForQuery() {
        return addressForQuery;
    }

    public void setAddressForQuery(String addressForQuery) {
        this.addressForQuery = addressForQuery;
    }

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
        this.useAmountString = useAmount.toString();
    }

    public String getUseAmountString() {
        return useAmountString;
    }

    public void setUseAmountString(String useAmountString) {
        this.useAmountString = useAmountString;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public String getBalanceString() {
        return balanceString;
    }

    public void setBalanceString(String balanceString) {
        this.balanceString = balanceString;
    }
}
