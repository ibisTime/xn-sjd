package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Post;

/**
 * 帖子
 * @author: silver 
 * @since: 2018年8月22日 下午2:46:52 
 * @history:
 */
public interface IPostAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加帖子
    public String addPost(String userId, String content, String plateCode);

    // 审核帖子
    public void approvePost(String code, String approveResult, String approver,
            String approveNote);

    // 评论帖子
    public void commentPost(String code, String content, String userId);

    // 点赞/取消(front)
    public void pointPost(String code, String userId);

    // 阅读帖子(front)
    public void browerPost(String code, String userId);

    // C端删除帖子
    public void dropPostFront(String code, String userId);

    // B端删除帖子
    public void dropPostOss(String code, String updater);

    // 置顶/取消
    public void stickPost(String code, String updater);

    public Paginable<Post> queryPostPage(int start, int limit, Post condition);

    public List<Post> queryPostList(Post condition);

    // 详情查询帖子(oss)
    public Post getPostOss(String code);

    // 详情查询帖子(front)
    public Post getPostFront(String code, String userId);

}
