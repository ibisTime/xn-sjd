package com.ogc.standard.dto.req;

/**
 * 分页查询评论(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午12:13:11 
 * @history:
 */
public class XN628275Req extends APageReq {

    private static final long serialVersionUID = 526930912942227980L;

    // 类型
    private String type;

    // 内容
    private String content;

    // 开始时间
    private String commentDateStart;

    // 结束时间
    private String commentDateEnd;

    // 状态(A=待审核 B=审核通过 C=审核不通过 D=已发布 G=已删除)
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDateStart() {
        return commentDateStart;
    }

    public void setCommentDateStart(String commentDateStart) {
        this.commentDateStart = commentDateStart;
    }

    public String getCommentDateEnd() {
        return commentDateEnd;
    }

    public void setCommentDateEnd(String commentDateEnd) {
        this.commentDateEnd = commentDateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
