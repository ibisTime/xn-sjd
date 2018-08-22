package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Comment;

/**
 * 评论表
 * @author: silver 
 * @since: 2018年8月22日 下午7:56:51 
 * @history:
 */
public interface ICommentBO extends IPaginableBO<Comment> {

    public boolean isCommentExist(String code);

    // 添加评论
    public String saveComment(String parentCode, String content, String userId);

    // 删除评论
    public void removeComment(String code, String updater);

    // 审核评论
    public void refreshApproveComment(String code, String status,
            String approver, String approveNote);

    public List<Comment> queryCommentList(Comment condition);

    public Comment getComment(String code);

}
