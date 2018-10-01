package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 文章
* @author: jiafr 
* @since: 2018-10-01 17:04:21
* @history:
*/
public class Article extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养权编号
    private String adoptTreeCode;

    // 树的编号
    private String treeNo;

    // 类型（1平台2用户）
    private String type;

    // 权限（1公开、2私密、3仅好友可见）
    private String right;

    // 标题
    private String title;

    // 内容
    private String content;

    // 照片
    private String photo;

    // 收藏总数
    private String collectCount;

    // 点赞总数
    private String pointCount;

    // 阅读总数
    private String readCount;

    // 状态（1保存、2待审核、3发布、4下架）
    private String status;

    // UI位置
    private String location;

    // UI次序
    private String orderNo;

    // 发布时间
    private Date publishDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatatime;

    // 备注
    private String remark;

    /*************DB properties**********/

    /**************辅助字段************/

    // 发布时间起
    private Date publishDatetimeStart;

    // 发布时间止
    private Date publishDatetimeEnd;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setTreeNo(String treeNo) {
        this.treeNo = treeNo;
    }

    public String getTreeNo() {
        return treeNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getRight() {
        return right;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setCollectCount(String collectCount) {
        this.collectCount = collectCount;
    }

    public String getCollectCount() {
        return collectCount;
    }

    public void setPointCount(String pointCount) {
        this.pointCount = pointCount;
    }

    public String getPointCount() {
        return pointCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Date getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(Date publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatatime() {
        return updateDatatime;
    }

    public void setUpdateDatatime(Date updateDatatime) {
        this.updateDatatime = updateDatatime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
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

}
