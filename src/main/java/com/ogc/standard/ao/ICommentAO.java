package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;

public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 评论评论
    public String saveComment(String code, String content, String userId);

    // 删除评论
    public void dropComment(String code, String updater);

    // 审核评论
    public void approveComment(String code, String approveResult,
            String approver, String approveNote);

    // 点赞/取消评论(front)
    public void pointComment(String code, String userId);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
