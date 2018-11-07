package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 评论
* @author: Silver
* @since: 2018年08月22日 下午05:32:25
* @history:
*/
public class Comment extends ABaseDO {

    private static final long serialVersionUID = -9008867735545331656L;

    // 编号
    private String code;

    // 商品编号
    private String commodityCode;

    // 评论内容
    private String content;

    // 评论人
    private String userId;

    // 评论时间
    private Date commentDatetime;

    // 父级编号（商品/评论编号）
    private String parentCode;

    // 上级评论人
    private String parentUserId;

    // 状态(A=待审核 B=审核通过 C=审核不通过 D=已发布 G=已删除)
    private String status;

    // 审核人
    private String updater;

    // 审核时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /************DB Properties***************/
    // 开始时间
    private Date createDatetimeStart;

    // 结束时间
    private Date createDatetimeEnd;

    // 状态列表
    private List<String> statusList;

    // 昵称
    private String nickname;

    // 头像
    private String photo;

    // 上级评论
    private String parentNickName;

    // 上级评论头像
    private String parentPhoto;

    // 一级评论(1 顶级，0非顶级)
    private String isTop;

    // 是否点赞
    private String isPoint;

    // 关键字过滤状态（0=未过滤，1=关键字被替换，2=待审核）
    private String filterFlag;

    // 父级别评论
    private Comment parentComment;

    // 子集评论
    private List<Comment> nextCommentList;

    // 商品中文名
    private String cName;

    // 非评论人
    private String unCreater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCommentDatetime() {
        return commentDatetime;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
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

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getParentNickName() {
        return parentNickName;
    }

    public void setParentNickName(String parentNickName) {
        this.parentNickName = parentNickName;
    }

    public String getParentPhoto() {
        return parentPhoto;
    }

    public void setParentPhoto(String parentPhoto) {
        this.parentPhoto = parentPhoto;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getNextCommentList() {
        return nextCommentList;
    }

    public void setNextCommentList(List<Comment> nextCommentList) {
        this.nextCommentList = nextCommentList;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getUnCreater() {
        return unCreater;
    }

    public void setUnCreater(String unCreater) {
        this.unCreater = unCreater;
    }

}
