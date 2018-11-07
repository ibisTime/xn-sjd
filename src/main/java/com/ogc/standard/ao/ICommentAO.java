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
    public XN628271Res comment(String userId, String playerCode,
            String content, String parentCode, String parentUserId);

    // 删除评论
    public void dropOssComment(String code, String updater);

    // 审核评论
    public void approveComment(String code, String approveResult,
            String approver, String approveNote);

    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition);

    public Paginable<Comment> queryMyCommentPage(int start, int limit,
            Comment condition, String userId);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
