package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;

public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 删除评论
    public void dropComment(String code, String updater);

    // 审核评论
    public void approveComment(String code, String approveResult,
            String approver, String approveNote);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
