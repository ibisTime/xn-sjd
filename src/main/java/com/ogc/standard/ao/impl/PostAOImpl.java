package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IPostAO;
import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.IKeywordBO;
import com.ogc.standard.bo.IPostBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.ITeamMemberApplyBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.domain.Post;
import com.ogc.standard.domain.Team;
import com.ogc.standard.dto.res.XN628030Res;
import com.ogc.standard.dto.res.XN628035Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommentStatus;
import com.ogc.standard.enums.ECommentType;
import com.ogc.standard.enums.EFilterFlag;
import com.ogc.standard.enums.EInteractType;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.enums.EObjectType;
import com.ogc.standard.enums.EPostLocation;
import com.ogc.standard.enums.EPostStatus;
import com.ogc.standard.enums.ETeamMemberApplyStatus;
import com.ogc.standard.exception.BizException;

/**
 * @author: silver 
 * @since: 2018年8月22日 下午2:53:31 
 * @history:
 */
@Service
public class PostAOImpl implements IPostAO {

    @Autowired
    private IPostBO postBO;

    @Autowired
    private ITeamBO teamBO;

    @Autowired
    private IKeywordBO keywordBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private ITeamMemberApplyBO teamMemberApplyBO;

    @Override
    @Transactional
    public XN628030Res publishPost(String userId, String content,
            String plateCode) {
        // 用户是否属于此战队
        List<String> statusList = new ArrayList<String>();
        statusList.add(ETeamMemberApplyStatus.APPROVED_YES.getCode());
        if (!teamMemberApplyBO.isTeamMemberApplyExist(plateCode, userId,
            statusList)) {
            throw new BizException("xn000", "用户不属于此战队。无法发帖！");
        }

        // 关键字过滤
        Keyword keyWord = keywordBO.checkContent(content);
        String status = EPostStatus.RELEASED.getCode();
        String filterFlag = null;

        if (null != keyWord) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keyWord.getReaction())) {
                throw new BizException("xn000",
                    "发帖内容存在关键字：【" + keyWord.getWord() + "】,请删除关键字后重新发帖！");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keyWord.getReaction())) {
                content = keywordBO.replaceKeyword(content, keyWord.getWord());
                filterFlag = EFilterFlag.REPLACED.getCode();
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keyWord.getReaction())) {
                status = EPostStatus.TO_APPROVE.getCode();
            }
        }

        // 发布后更新战队发帖数
        if (EPostStatus.RELEASED.getCode().equals(status)
                || EFilterFlag.REPLACED.getCode().equals(filterFlag)) {
            Team team = teamBO.getTeam(plateCode);
            teamBO.refreshTeamPostCount(plateCode, team.getPostCount() + 1);
        }

        String code = postBO.savePost(userId, content, plateCode, status);

        if (EPostStatus.RELEASED.getCode().equals(status)) {
            filterFlag = EFilterFlag.NORMAN.getCode();
        } else if (EPostStatus.TO_APPROVE.getCode().equals(status)) {
            filterFlag = EFilterFlag.TO_APPROVE.getCode();
        }

        XN628030Res res = new XN628030Res(code, filterFlag);

        return res;
    }

    @Override
    @Transactional
    public void approvePost(String code, String approveResult, String approver,
            String approveNote) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.TO_APPROVE.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子未处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EPostStatus.APPROVED_YES.getCode();

            // 审核通过后更新战队帖子数
            Team team = teamBO.getTeam(post.getPlateCode());
            teamBO.refreshTeamPostCount(team.getCode(),
                team.getPostCount() + 1);
        } else {
            status = EPostStatus.APPROVED_NO.getCode();
        }

        postBO.approvePost(code, status, approver, approveNote);
    }

    @Override
    public void modifyLocation(String code, String updater) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.APPROVED_YES.getCode().equals(post.getStatus())
                && EPostStatus.RELEASED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子未处于可置顶/取消置顶状态！");
        }

        String location = null;
        if (EPostLocation.NORMAL.getCode().equals(post.getLocation())) {
            location = EPostLocation.HOT.getCode();
        } else {
            location = EPostLocation.NORMAL.getCode();
        }

        postBO.refreshLocatePost(code, location, updater);
    }

    @Override
    @Transactional
    public XN628035Res commentPost(String code, String content, String userId) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.APPROVED_YES.getCode().equals(post.getStatus())
                && EPostStatus.RELEASED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子未处于可评论状态！");
        }

        // 关键字过滤
        Keyword keyWord = keywordBO.checkContent(content);
        String status = ECommentStatus.RELEASED.getCode();
        String filterFlag = null;

        if (null != keyWord) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keyWord.getReaction())) {
                throw new BizException("xn000",
                    "发帖内容存在关键字：【" + keyWord.getWord() + "】,请删除关键字后重新发帖！");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keyWord.getReaction())) {
                content = keywordBO.replaceKeyword(content, keyWord.getWord());
                filterFlag = EFilterFlag.REPLACED.getCode();
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keyWord.getReaction())) {
                status = ECommentStatus.TO_APPROVE.getCode();
            }
        }

        // 更新帖子评论数量
        if (ECommentStatus.RELEASED.getCode().equals(status)
                || EFilterFlag.REPLACED.getCode().equals(filterFlag)) {
            postBO.refreshCommentPost(code, post.getCommentCount() + 1);
        }

        // 添加评论
        commentBO.saveComment(ECommentType.POST.getCode(), code, userId,
            content, status, userId);

        if (ECommentStatus.RELEASED.getCode().equals(status)) {
            filterFlag = EFilterFlag.NORMAN.getCode();
        } else if (ECommentStatus.TO_APPROVE.getCode().equals(status)) {
            filterFlag = EFilterFlag.TO_APPROVE.getCode();
        }

        XN628035Res res = new XN628035Res(true, filterFlag);

        return res;
    }

    @Override
    @Transactional
    public void pointPost(String code, String userId) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.APPROVED_YES.getCode().equals(post.getStatus())
                && EPostStatus.RELEASED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "当前帖子状态不可操作！");
        }

        Interact interact = interactBO.getInteract(
            EInteractType.POINT.getCode(), EObjectType.POST.getCode(), code,
            userId);

        // 点赞记录不存在时，添加点赞；点赞记录存在时，删除点赞
        if (null == interact) {
            interactBO.saveInteract(EInteractType.POINT.getCode(),
                EObjectType.POST.getCode(), code, userId);

            postBO.refreshPointPost(post.getCode(), post.getPointCount() + 1);
        } else {
            interactBO.removeInteract(interact.getCode());

            postBO.refreshPointPost(post.getCode(), post.getPointCount() - 1);
        }
    }

    @Override
    @Transactional
    public void readPost(String code, String userId) {
        // 添加浏览记录
        interactBO.saveInteract(EInteractType.READ.getCode(),
            EObjectType.POST.getCode(), code, userId);

        // 更新帖子阅读数量
        Post post = postBO.getPost(code);
        postBO.refreshReadPost(code, post.getReadCount() + 1);
    }

    @Override
    public void dropPostFront(String code, String userId) {
        Post post = postBO.getPost(code);
        if (EPostStatus.DELETED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子未处于可删除状态！");
        }

        Team team = teamBO.getTeam(post.getPlateCode());
        if (!userId.equals(post.getUserId())
                && !userId.equals(team.getCaptain())) {
            throw new BizException("xn0000", "此用户不是发帖人或战队队长，无法删除该帖子！");
        }

        postBO.removePost(code, userId);
    }

    @Override
    public void dropPostOss(String code, String updater) {
        Post post = postBO.getPost(code);
        if (EPostStatus.DELETED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子未处于可删除状态！");
        }

        postBO.removePost(code, updater);
    }

    @Override
    public Paginable<Post> queryPostPage(int start, int limit, Post condition) {
        return postBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Post> queryPostList(Post condition) {
        return postBO.queryPostList(condition);
    }

    @Override
    public Post getPostOss(String code) {
        return postBO.getPost(code);
    }

    @Override
    public Post getFrontPost(String code, String userId) {
        Post post = postBO.getPost(code);

        if (StringUtils.isNotBlank(userId)) {
            Interact interact = interactBO.getInteract(
                EInteractType.POINT.getCode(), EObjectType.POST.getCode(), code,
                userId);

            if (null != interact) {
                post.setIsPoint(EBoolean.YES.getCode());
            } else {
                post.setIsPoint(EBoolean.NO.getCode());
            }
        }

        // 评论列表
        List<Comment> commentList = commentBO.queryCommentListByObjectCode(code,
            userId);
        post.setCommentList(commentList);

        return post;
    }

}
