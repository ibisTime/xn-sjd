package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 申请绑定养护方
* @author: jiafr 
* @since: 2018-09-26 17:53:01
* @history:
*/
public class ApplyBindMaintain extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 产权方编号
    private String ownerId;

    // 养护方编号
    private String maintainId;

    // 状态（1审核/2审核不通过/3已绑定/4已解除）
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /****************DB properties********/

    private SYSUser ownerUser; // 产权方用户

    private SYSUser maintainUser;// 养护方用户

    // 更新人
    private String updaterName;

    // 产权方古树数量
    private long ownerTreeCount;

    // 养护方人民币账户
    private Account maintainCnyAccount;

    // 更新开始时间
    private Date updateDatetimeStart;

    // 更新结束时间
    private Date updateDatetimeEnd;

    private String ownerUserName; // 产权方用户

    private String maintainUserName;// 养护方用户

    private String ownerCompanyName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setMaintainId(String maintainId) {
        this.maintainId = maintainId;
    }

    public String getMaintainId() {
        return maintainId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public SYSUser getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(SYSUser ownerUser) {
        this.ownerUser = ownerUser;
    }

    public SYSUser getMaintainUser() {
        return maintainUser;
    }

    public void setMaintainUser(SYSUser maintainUser) {
        this.maintainUser = maintainUser;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public long getOwnerTreeCount() {
        return ownerTreeCount;
    }

    public void setOwnerTreeCount(long ownerTreeCount) {
        this.ownerTreeCount = ownerTreeCount;
    }

    public Account getMaintainCnyAccount() {
        return maintainCnyAccount;
    }

    public void setMaintainCnyAccount(Account maintainCnyAccount) {
        this.maintainCnyAccount = maintainCnyAccount;
    }

    public Date getUpdateDatetimeStart() {
        return updateDatetimeStart;
    }

    public void setUpdateDatetimeStart(Date updateDatetimeStart) {
        this.updateDatetimeStart = updateDatetimeStart;
    }

    public Date getUpdateDatetimeEnd() {
        return updateDatetimeEnd;
    }

    public void setUpdateDatetimeEnd(Date updateDatetimeEnd) {
        this.updateDatetimeEnd = updateDatetimeEnd;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public String getMaintainUserName() {
        return maintainUserName;
    }

    public void setMaintainUserName(String maintainUserName) {
        this.maintainUserName = maintainUserName;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

}
