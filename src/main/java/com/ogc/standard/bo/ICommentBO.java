package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Comment;

/**
 * @author: taojian 
 * @since: 2018年11月7日 下午4:29:35 
 * @history:
 */
public interface ICommentBO extends IPaginableBO<Comment> {

    public boolean isCommentExist(String code);

    // 添加评论
    public String saveComment(String creater, String content,
            String commodityCode, String parentCode, String parentUserId,
            String status);

    // 删除评论
    public void removeComment(String code, String updater);

    // 审核评论
    public void refreshApproveComment(Comment comment, String status,
            String approver, String approveNote);

    public List<Comment> queryCommentList(Comment condition);

    public List<Comment> queryCommentListByObjectCode(String objectCode,
            String creater);

    public Comment getComment(String code);

    public void searchCycleComment(String parentCode, List<Comment> list);

    public void initComment(Comment comment);

    public void orderCommentList(List<Comment> commentList);

}
