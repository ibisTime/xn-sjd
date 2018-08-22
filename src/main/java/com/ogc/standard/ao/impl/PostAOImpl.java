package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IPostAO;
import com.ogc.standard.bo.ICommentBO;
import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.IKeyWordBO;
import com.ogc.standard.bo.IPostBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.KeyWord;
import com.ogc.standard.domain.Post;
import com.ogc.standard.domain.Team;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EKeyWordReaction;
import com.ogc.standard.enums.EPostLocation;
import com.ogc.standard.enums.EPostStatus;
import com.ogc.standard.exception.BizException;

/**
 * 帖子
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
    private IKeyWordBO keyWordBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IInteractBO interactBO;

    @Override
    public String addPost(String userId, String content, String plateCode) {
        // 关键字过滤
        KeyWord keyWord = keyWordBO.checkContent(content);
        String status = EPostStatus.RELEASED.getCode();

        if (null != keyWord) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keyWord.getReaction())) {
                throw new BizException("xn000",
                    "发帖内容存在关键字：【" + keyWord.getWord() + "】,请删除后重新发帖！");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keyWord.getReaction())) {
                content = keyWordBO.replaceKeyword(content, keyWord.getWord());
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keyWord.getReaction())) {
                status = EPostStatus.TO_APPROVE.getCode();
            }
        }

        // 发布后更新战队发帖数
        if (EPostStatus.RELEASED.getCode().equals(status)) {
            Team team = teamBO.getTeam(plateCode);
            teamBO.refreshTeamPostCount(plateCode, team.getPostCount() + 1);
        }

        return postBO.savePost(userId, content, plateCode, status);
    }

    @Override
    public void approvePost(String code, String approveResult, String approver,
            String approveNote) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.TO_APPROVE.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子不处于可审核状态！");
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
    public void comment(String code, String content, String userId) {
        // 关键字过滤
        KeyWord keyWord = keyWordBO.checkContent(content);
        String status = EPostStatus.RELEASED.getCode();

        if (null != keyWord) {

            // 直接拦截
            if (EKeyWordReaction.REFUSE.getCode()
                .equals(keyWord.getReaction())) {
                throw new BizException("xn000",
                    "发帖内容存在关键字：【" + keyWord.getWord() + "】,请删除后重新发帖！");
            }

            // 替换**
            if (EKeyWordReaction.REPLACE.getCode()
                .equals(keyWord.getReaction())) {
                content = keyWordBO.replaceKeyword(content, keyWord.getWord());
            }

            // 审核
            if (EKeyWordReaction.APPROVE.getCode()
                .equals(keyWord.getReaction())) {
                status = EPostStatus.TO_APPROVE.getCode();
            }
        }

        commentBO.saveComment(code, content, status, userId);
    }

    @Override
    public void point(String code, String userId) {

    }

    @Override
    public void dropPost4Front(String code, String userId) {
        Post post = postBO.getPost(code);
        if (!post.getUserId().equals(userId)) {
            throw new BizException("xn0000", "此用户不是发帖人，无法删除该帖子！");
        }

        postBO.removePost(code, userId);
    }

    @Override
    public void dropPost4Oss(String code, String updater) {
        postBO.removePost(code, updater);
    }

    @Override
    public void stickPost(String code, String updater) {
        Post post = postBO.getPost(code);
        if (!EPostStatus.APPROVED_YES.getCode().equals(post.getStatus())
                || !EPostStatus.RELEASED.getCode().equals(post.getStatus())) {
            throw new BizException("xn0000", "帖子不处于可置顶/取消置顶状态！");
        }

        String location = null;
        if (EPostLocation.NORMAL.getCode().equals(post.getLocation())) {
            location = EPostLocation.HOT.getCode();
        } else {
            location = EPostLocation.NORMAL.getCode();
        }

        postBO.stickPost(code, location, updater);
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
    public Post getPost4Oss(String code) {
        return postBO.getPost(code);
    }

    @Override
    public Post getPost4Front(String code, String userId) {
        Post post = postBO.getPost(code);

        if (StringUtils.isNotBlank(userId)) {

        }

        return post;
    }

}
