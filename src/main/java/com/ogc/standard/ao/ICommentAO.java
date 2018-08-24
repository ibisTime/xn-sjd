package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.dto.res.XN628271Res;

/**
 * @author: silver 
 * @since: 2018年8月24日 下午2:33:06 
 * @history:
 */
public interface ICommentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 评论评论
    public XN628271Res commentComment(String code, String content,
            String userId);

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
