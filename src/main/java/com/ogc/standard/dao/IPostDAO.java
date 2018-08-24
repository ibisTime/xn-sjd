package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Post;

/**
 * 帖子
 * @author: silver 
 * @since: 2018年8月22日 下午1:34:09 
 * @history:
 */
public interface IPostDAO extends IBaseDAO<Post> {
    String NAMESPACE = IPostDAO.class.getName().concat(".");

    // 审核
    public int updateApprovePost(Post data);

    // 更新评论数量
    public int updateCommentPost(Post data);

    // 更新点赞数量
    public int updatePointPost(Post data);

    // 更新阅读数量
    public int updateReadPost(Post data);

    // 置顶/取消
    public int updateLocationPost(Post data);

}
