package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.IPostBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.domain.Post;
import com.ogc.standard.dto.res.XN628271Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.ECommentType;
import com.ogc.standard.enums.EFilterFlag;
import com.ogc.standard.enums.EInteractType;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.enums.EObjectType;
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
    private IInteractBO interactBO;

    @Autowired
    private IPostBO postBO;

    @Override
    public XN628271Res commentComment(String commentCode, String content,
            String userId) {
        // 关键字过滤
        List<Keyword> keywordList = keywordBO.checkContent(content);
        String status = ECommentStatus.RELEASED.getCode();
        String filterFlag = null;

        if (CollectionUtils.isNotEmpty(keywordList)) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                throw new BizException("xn000", "发帖内容存在关键字：【"
                        + keywordList.get(0).getWord() + "】,请删除后重新发帖！");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                for (Keyword keyword : keywordList) {
                    content = keywordBO.replaceKeyword(content,
                        keyword.getWord());
                }

                filterFlag = EFilterFlag.REPLACED.getCode();
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keywordList.get(0).getReaction())) {
                status = ECommentStatus.TO_APPROVE.getCode();
            }
        }

        if (ECommentStatus.RELEASED.getCode().equals(status)
                && null == filterFlag) {
            filterFlag = EFilterFlag.NORMAN.getCode();
        } else if (ECommentStatus.TO_APPROVE.getCode().equals(status)) {
            filterFlag = EFilterFlag.TO_APPROVE.getCode();
        }

        String code = commentBO.saveComment(ECommentType.COMMENT.getCode(),
            commentCode, userId, content, status, userId);

        return new XN628271Res(code, filterFlag);
    }

    @Override
    public void approveComment(String code, String approveResult,
            String approver, String approveNote) {
        Comment comment = commentBO.getComment(code);
        if (!ECommentStatus.TO_APPROVE.getCode().equals(comment.getStatus())) {
            throw new BizException("xn000", "帖子不处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ECommentStatus.APPROVED_YES.getCode();

            // 帖子评论审核通过后更新评论数量
            if (ECommentType.POST.getCode().equals(comment.getType())) {
                Post post = postBO.getPost(comment.getParentCode());
                postBO.refreshCommentPost(post.getCode(),
                    post.getCommentCount() + 1);
            }

        } else {
            status = ECommentStatus.APPROVED_NO.getCode();
        }

        commentBO.refreshApproveComment(code, status, approver, approveNote);
    }

    @Override
    @Transactional
    public void pointComment(String code, String userId) {
        Interact interact = interactBO.getInteract(
            EInteractType.POINT.getCode(), EObjectType.COMMENT.getCode(), code,
            userId);
        Comment comment = commentBO.getComment(code);
        Integer pointCount = comment.getPointCount();

        // 点赞记录不存在时，添加点赞；点赞记录存在时，删除点赞
        if (null == interact) {
            interactBO.saveInteract(EInteractType.POINT.getCode(),
                EObjectType.COMMENT.getCode(), code, userId);

            pointCount += 1;
        } else {
            interactBO.removeInteract(interact.getCode());

            pointCount -= 1;
        }

        // 更新帖子点赞量
        commentBO.refreshPoingComment(code, pointCount);
    }

    @Override
    public void dropComment(String code, String updater) {
        Comment comment = commentBO.getComment(code);
        if (ECommentStatus.DELETED.getCode().equals(comment.getStatus())) {
            throw new BizException("xn000", "帖子不处于可删除状态！");
        }

        commentBO.removeComment(code, updater);
    }

    @Override
    public Paginable<Comment> queryCommentPage(int start, int limit,
            Comment condition) {
        return commentBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Comment> queryCommentList(Comment condition) {
        return commentBO.queryCommentList(condition);
    }

    @Override
    public Comment getComment(String code) {
        return commentBO.getComment(code);
    }

}
