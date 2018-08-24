package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 帖子
* @author: Silver
* @since: 2018年08月22日 上午11:26:52
* @history:
*/
public class Post extends ABaseDO {
    private static final long serialVersionUID = -6397158126567368779L;

    // 编号
    private String code;

    // 内容
    private String content;

    // 发布人
    private String userId;

    // 发布时间
    private Date publishDatetime;

    // 状态(A=待审核 B=审核通过 C=审核不通过 D=已发布 G=已删除)
    private String status;

    // 位置(0普通 1热门)
    private String location;

    // 板块编号
    private String plateCode;

    // 评论数
    private Integer commentCount;

    // 点赞数
    private Integer pointCount;

    // 阅读数
    private Integer readCount;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /**************DB Properties**************/

    // 发布时间起
    private Date publishDatetimeStart;

    // 发布时间止
    private Date publishDatetimeEnd;

    // 状态列表
    private List<String> statusList;

    // 前端用户是否点赞(1是/0否)
    private String isPoint;

    // 关键字过滤状态（0=未过滤，1=关键字被替换，2=待审核）
    private String filterFlag;

    // 评论列表
    private List<Comment> commentList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
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

    public Date getPublishDatetimeStart() {
        return publishDatetimeStart;
    }

    public void setPublishDatetimeStart(Date publishDatetimeStart) {
        this.publishDatetimeStart = publishDatetimeStart;
    }

    public Date getPublishDatetimeEnd() {
        return publishDatetimeEnd;
    }

    public void setPublishDatetimeEnd(Date publishDatetimeEnd) {
        this.publishDatetimeEnd = publishDatetimeEnd;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getIsPoint() {
        return isPoint;
    }

    public void setIsPoint(String isPoint) {
        this.isPoint = isPoint;
    }

    public String getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(String filterFlag) {
        this.filterFlag = filterFlag;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

}
