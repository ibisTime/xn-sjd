package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPostDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Post;

/**
 * 帖子
 * @author: silver 
 * @since: 2018年8月22日 下午1:36:23 
 * @history:
 */
@Repository("postDAOImpl")
public class PostDAOImpl extends AMybatisTemplate implements IPostDAO {

    @Override
    public int insert(Post data) {
        return super.insert(NAMESPACE.concat("insert_post"), data);
    }

    @Override
    public int delete(Post data) {
        return super.update(NAMESPACE.concat("delete_post"), data);
    }

    @Override
    public int updateApprovePost(Post data) {
        return super.update(NAMESPACE.concat("update_approvePost"), data);
    }

    @Override
    public int updateCommentPost(Post data) {
        return super.update(NAMESPACE.concat("update_commentCount"), data);
    }

    @Override
    public int updatePointPost(Post data) {
        return super.update(NAMESPACE.concat("update_pointCount"), data);
    }

    @Override
    public int updateReadPost(Post data) {
        return super.update(NAMESPACE.concat("update_readCount"), data);
    }

    @Override
    public int updateLocationPost(Post data) {
        return super.update(NAMESPACE.concat("update_postLocation"), data);
    }

    @Override
    public Post select(Post condition) {
        return super.select(NAMESPACE.concat("select_post"), condition,
            Post.class);
    }

    @Override
    public long selectTotalCount(Post condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_post_count"),
            condition);
    }

    @Override
    public List<Post> selectList(Post condition) {
        return super.selectList(NAMESPACE.concat("select_post"), condition,
            Post.class);
    }

    @Override
    public List<Post> selectList(Post condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_post"), start, count,
            condition, Post.class);
    }

}
