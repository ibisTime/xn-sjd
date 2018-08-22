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

    // 置顶/取消
    public void stickPost(String code, String location, String updater);

    public List<Post> queryPostList(Post condition);

    public Post getPost(String code);

}
