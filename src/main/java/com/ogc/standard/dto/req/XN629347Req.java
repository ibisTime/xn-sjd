package com.ogc.standard.dto.req;

/**
 * 列表查询文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:52:48 
 * @history:
 */
public class XN629347Req extends AListReq {

    private static final long serialVersionUID = 1574695770499112265L;

    // 认养权编号
    private String adoptTreeCode;

    // 树的编号
    private String treeNo;

    // 类型（1平台2用户）
    private String type;

    // 权限（1公开、2私密、3仅好友可见）
    private String openLevel;

    // 状态（1保存、2待审核、3发布、4下架）
    private String status;

    // 是否置顶
    private String isTop;

    // 发布时间起
    private String publishDatetimeStart;

    // 发布时间止
    private String publishDatetimeEnd;

    // 发布人
    private String publishUserId;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getTreeNo() {
        return treeNo;
    }

    public void setTreeNo(String treeNo) {
        this.treeNo = treeNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenLevel() {
        return openLevel;
    }

    public void setOpenLevel(String openLevel) {
        this.openLevel = openLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublishDatetimeStart() {
        return publishDatetimeStart;
    }

    public void setPublishDatetimeStart(String publishDatetimeStart) {
        this.publishDatetimeStart = publishDatetimeStart;
    }

    public String getPublishDatetimeEnd() {
        return publishDatetimeEnd;
    }

    public void setPublishDatetimeEnd(String publishDatetimeEnd) {
        this.publishDatetimeEnd = publishDatetimeEnd;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

}
