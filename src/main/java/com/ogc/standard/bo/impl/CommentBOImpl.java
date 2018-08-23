package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommentDAO;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.ECommentType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/**
 * 评论表
 * @author: silver 
 * @since: 2018年8月22日 下午8:14:28 
 * @history:
 */
@Component
public class CommentBOImpl extends PaginableBOImpl<Comment>
        implements ICommentBO {

    @Autowired
    private ICommentDAO commentDAO;

    @Override
    public boolean isCommentExist(String code) {
        Comment condition = new Comment();
        condition.setCode(code);
        if (commentDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveComment(String type, String parentCode,
            String parentUserId, String content, String status, String userId) {
        Comment data = new Comment();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Comment.getCode());
        data.setCode(code);
        data.setType(type);
        data.setParentCode(parentCode);
        data.setParentUserId(parentUserId);

        data.setContent(content);
        data.setPointCount(0);
        data.setUserId(userId);
        data.setStatus(status);
        data.setCommentDatetime(new Date());

        if (ECommentType.POST.getCode().equals(type)) {
            data.setObjectCode(parentCode);
        }

        commentDAO.insert(data);
        return code;

    }

    @Override
    public void removeComment(String code, String updater) {
        if (StringUtils.isNotBlank(code)) {
            Comment data = new Comment();
            data.setCode(code);
            data.setStatus(ECommentStatus.DELETED.getCode());
            commentDAO.delete(data);
        }
    }

    @Override
    public void refreshApproveComment(String code, String status,
            String approver, String approveNote) {
        Comment data = new Comment();
        data.setCode(code);
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setRemark(approveNote);
        commentDAO.updateApproveComment(data);
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentDAO.selectList(condition);
    }

    @Override
    public Comment getComment(String code) {
        Comment data = null;
        if (StringUtils.isNotBlank(code)) {
            Comment condition = new Comment();
            condition.setCode(code);
            data = commentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "评论记录不存在");
            }
        }
        return data;
    }

}
