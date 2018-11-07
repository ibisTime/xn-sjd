package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.dto.res.XN628271Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.ECommodityStatus;
import com.ogc.standard.enums.EFilterFlag;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.exception.BizException;

/**
 * @author: silver 
 * @since: 2018年8月22日 下午8:29:21 
 * @history:
 */
@Service
public class CommentAOImpl implements ICommentAO {

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public XN628271Res comment(String userId, String commodityCode,
            String content, String parentCode, String parentUserId) {
        // 检验商品是否存在
        Commodity commodity = commodityBO.getCommodity(commodityCode);
        if (!ECommodityStatus.ON.getCode().equals(commodity.getStatus())) {
            throw new BizException("xn0000", "当前商品未上架，暂不能评论");
        }
        userBO.getUser(userId);

        // 关键字过滤
        List<Keyword> keywordList = keywordBO.checkContent(content);
        String status = ECommentStatus.RELEASED.getCode();
        String filterFlag = null;

        if (CollectionUtils.isNotEmpty(keywordList)) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode().equals(
                keywordList.get(0).getReaction())) {
                throw new BizException("xn0000", "你的评论含有拦截词:"
                        + keywordList.get(0).getWord() + " 无法评论，请重新编辑再评论");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode().equals(
                keywordList.get(0).getReaction())) {
                for (Keyword keyword : keywordList) {
                    content = keywordBO.replaceKeyword(content,
                        keyword.getWord());
                }

                filterFlag = EFilterFlag.REPLACED.getCode();
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode().equals(
                keywordList.get(0).getReaction())) {
                status = ECommentStatus.TO_APPROVE.getCode();
            }
        }

        if (ECommentStatus.RELEASED.getCode().equals(status)
                && null == filterFlag) {
            filterFlag = EFilterFlag.NORMAN.getCode();
        } else if (ECommentStatus.TO_APPROVE.getCode().equals(status)) {
            filterFlag = EFilterFlag.TO_APPROVE.getCode();
        }

        String code = commentBO.saveComment(userId, content, commodityCode,
            parentCode, parentUserId, status);

        return new XN628271Res(code, filterFlag);
    }

    @Override
    @Transactional
    public void approveComment(String code, String approveResult,
            String approver, String approveNote) {
        Comment comment = commentBO.getComment(code);
        if (!ECommentStatus.TO_APPROVE.getCode().equals(comment.getStatus())) {
            throw new BizException("xn0000", "评论不处于可评论状态");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ECommentStatus.APPROVED_YES.getCode();

        } else {
            status = ECommentStatus.APPROVED_NO.getCode();
        }

        commentBO.refreshApproveComment(comment, status, approver, approveNote);
    }

    @Override
    public void dropOssComment(String code, String updater) {
        Comment comment = commentBO.getComment(code);
        if (!ECommentStatus.RELEASED.getCode().equals(comment.getStatus())
                && !ECommentStatus.APPROVED_YES.getCode().equals(
                    comment.getStatus())) {
            throw new BizException("xn0000", "评论状态不可删除");
        }

        commentBO.removeComment(code, updater);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        Paginable<Comment> page = commentBO.getPaginable(start, limit,
            condition);
        List<Comment> resultList = page.getList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Comment comment : resultList) {
                commentBO.initComment(comment);
            }
        }
        return page;
    }

    @Override
    public Paginable<Comment> queryMyCommentPage(int start, int limit,
            Comment condition, String userId) {
        Paginable<Comment> page = commentBO.getPaginable(start, limit,
            condition);
        List<Comment> resultList = page.getList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Comment comment : resultList) {

                List<Comment> commentList = new ArrayList<Comment>();
                commentBO.searchCycleComment(comment.getCode(), commentList);
                commentBO.orderCommentList(commentList);
                comment.setNextCommentList(commentList);
            }
        }
        return page;
    }

    @Override
    public Comment getComment(String code) {
        Comment comment = commentBO.getComment(code);
        commentBO.initComment(comment);
        return comment;
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        List<Comment> resultList = commentBO.queryCommentList(condition);
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Comment comment : resultList) {

                List<Comment> commentList = new ArrayList<Comment>();
                commentBO.searchCycleComment(comment.getCode(), commentList);
                commentBO.orderCommentList(commentList);
                comment.setNextCommentList(commentList);
            }
        }
        return resultList;
    }

}
