package com.ogc.standard.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommentDAO;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/**
 * 评论表
 * @author: silver 
 * @since: 2018年8月22日 下午8:14:28 
 * @history:
 */
@Component
public class CommentBOImpl extends PaginableBOImpl<Comment> implements
        ICommentBO {

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICommodityBO commodityBO;

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
    public String saveComment(String creater, String content,
            String commodityCode, String parentCode, String parentUserId,
            String status) {
        Comment data = new Comment();

        String code = OrderNoGenerater.generate(EGeneratePrefix.Comment
            .getCode());
        data.setCode(code);
        data.setCommodityCode(commodityCode);
        data.setContent(content);
        data.setUserId(creater);
        data.setCommentDatetime(new Date());
        data.setParentCode(commodityCode);
        if (null != parentCode && StringUtils.isNotBlank(parentCode)) {
            data.setParentCode(parentCode);
            data.setParentUserId(parentUserId);
        }

        data.setStatus(status);

        commentDAO.insert(data);
        return code;

    }

    @Override
    public void removeComment(String code, String updater) {
        if (StringUtils.isNotBlank(code)) {
            Comment data = new Comment();
            data.setCode(code);
            data.setStatus(ECommentStatus.DELETED.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark("oss删除评论");
            commentDAO.delete(data);
        }
    }

    @Override
    public void refreshApproveComment(Comment data, String status,
            String approver, String approveNote) {
        data.setStatus(status);
        data.setUpdater(approver);
        data.setUpdateDatetime(new Date());
        data.setRemark(approveNote);
        commentDAO.updateApproveComment(data);
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentDAO.selectList(condition);
    }

    @Override
    public List<Comment> queryCommentListByObjectCode(String objectCode,
            String creater) {
        List<Comment> list = null;
        if (StringUtils.isNotBlank(objectCode)) {
            Comment condition = new Comment();
            List<String> statusList = new ArrayList<String>();
            statusList.add(ECommentStatus.APPROVED_YES.getCode());
            statusList.add(ECommentStatus.RELEASED.getCode());
            condition.setStatusList(statusList);
            condition.setCommodityCode(objectCode);
            condition.setUnCreater(creater);
            condition.setOrder("create_datetime", "asc");
            list = queryCommentList(condition);

            if (CollectionUtils.isNotEmpty(list)) {
                for (Comment comment : list) {
                    initComment(comment);
                }
            }
        }
        return list;
    }

    @Override
    public Comment getComment(String code) {
        Comment data = null;
        if (StringUtils.isNotBlank(code)) {
            Comment condition = new Comment();
            condition.setCode(code);
            data = commentDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该评论不存在");
            }
        }
        return data;
    }

    @Override
    public void searchCycleComment(String parentCode, List<Comment> list) {
        Comment condition = new Comment();
        condition.setParentCode(parentCode);
        List<String> statusList = new ArrayList<String>();
        statusList.add(ECommentStatus.RELEASED.getCode());
        statusList.add(ECommentStatus.APPROVED_YES.getCode());
        condition.setStatusList(statusList);
        condition.setOrder("comment_datetime", "asc");
        List<Comment> nextList = queryCommentList(condition);
        if (CollectionUtils.isNotEmpty(nextList)) {
            list.addAll(nextList);
            for (int i = 0; i < nextList.size(); i++) {
                searchCycleComment(nextList.get(i).getCode(), list);
            }
        }
    }

    @Override
    public void initComment(Comment comment) {
        User user = userBO.getUser(comment.getUserId());
        comment.setNickname(user.getNickname());
        comment.setPhoto(user.getPhoto());
        Commodity commodity = commodityBO.getCommodity(comment
            .getCommodityCode());
        comment.setcName(commodity.getName());
    }

    @Override
    public void orderCommentList(List<Comment> commentList) {
        for (int i = 0; i < commentList.size(); i++) {
            for (int j = i + 1; j < commentList.size(); j++) {
                if (commentList.get(i).getCommentDatetime()
                    .after(commentList.get(j).getCommentDatetime())) {
                    Comment temp = new Comment();
                    temp = commentList.get(i);
                    commentList.set(i, commentList.get(j));
                    commentList.set(j, temp);
                }
            }
        }
        // 初始化参数
        for (Comment comment : commentList) {
            initComment(comment);
        }
    }

}
