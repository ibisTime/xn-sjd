package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 战队表
* @author: Silver
* @since: 2018年08月21日 下午04:05:00
* @history:
*/
public class Team extends ABaseDO {

    private static final long serialVersionUID = 8344517217173179346L;

    // 编号
    private String code;

    // 赛事编号
    private String matchCode;

    // 战队名称
    private String name;

    // logo
    private String logo;

    // 描述
    private String description;

    // 队长编号
    private String captain;

    // 权重
    private Double weight;

    // 位置(0:普通1：置顶)
    private String location;

    // 序号
    private Integer orderNo;

    // 状态(1待开始，2参赛中，3已结束)
    private String status;

    // 创建时间
    private Date createDatetime;

    // 帖子数
    private Integer postCount;

    // 队员人数
    private Integer memberCount;

    // 总收益率
    private Double benefitRate;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /*************DB Properties****************/
    // 前端用户是否属于此战队
    private String isUserBelongTeam;

    // 赛事名称
    private String matchName;

    // 队长信息
    private User captainInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Double getBenefitRate() {
        return benefitRate;
    }

    public void setBenefitRate(Double benefitRate) {
        this.benefitRate = benefitRate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsUserBelongTeam() {
        return isUserBelongTeam;
    }

    public void setIsUserBelongTeam(String isUserBelongTeam) {
        this.isUserBelongTeam = isUserBelongTeam;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public User getCaptainInfo() {
        return captainInfo;
    }

    public void setCaptainInfo(User captainInfo) {
        this.captainInfo = captainInfo;
    }

}
