package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Post;

/**
 * 帖子
 * @author: silver 
 * @since: 2018年8月22日 下午1:38:17 
 * @history:
 */
public interface IPostBO extends IPaginableBO<Post> {

    public boolean isPostExist(String code);

    // 添加帖子
    public String savePost(String userId, String content, String plateCode,
            String status);

    // 删除帖子
    public void removePost(String code, String updater);

    // 审核帖子
    public void approvePost(String code, String status, String approver,
            String approveNote);

    // 更新评论数量
    public void refreshCommentPost(String code, Integer commentCount);

    // 更新点赞数量
    public void refreshPointPost(String code, Integer pointCount);

    // 更新阅读数量
    public void refreshReadPost(String code, Integer readCount);

    // 置顶/取消
    public void refreshLocatePost(String code, String location, String updater);

    public List<Post> queryPostList(Post condition);

    public Post getPost(String code);

}
