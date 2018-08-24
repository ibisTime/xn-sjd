package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPostBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IPostDAO;
import com.ogc.standard.domain.Post;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPostLocation;
import com.ogc.standard.enums.EPostStatus;
import com.ogc.standard.exception.BizException;

/**
 * 帖子
 * @author: silver 
 * @since: 2018年8月22日 下午2:38:50 
 * @history:
 */
@Component
public class PostBOImpl extends PaginableBOImpl<Post> implements IPostBO {

    @Autowired
    private IPostDAO postDAO;

    @Override
    public boolean isPostExist(String code) {
        Post condition = new Post();
        condition.setCode(code);
        if (postDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String savePost(String userId, String content, String plateCode,
            String status) {
        Post data = new Post();
        String code = OrderNoGenerater.generate(EGeneratePrefix.Post.getCode());

        data.setCode(code);
        data.setUserId(userId);
        data.setContent(content);
        data.setPlateCode(plateCode);
        data.setStatus(status);

        data.setLocation(EPostLocation.NORMAL.getCode());
        data.setUpdater(userId);
        data.setUpdateDatetime(new Date());
        data.setCommentCount(0);
        data.setPointCount(0);
        data.setReadCount(0);

        if (EPostStatus.RELEASED.getCode().equals(status)) {
            data.setPublishDatetime(new Date());
        }

        postDAO.insert(data);
        return code;

    }

    @Override
    public void removePost(String code, String updater) {
        if (StringUtils.isNotBlank(code)) {
            Post data = new Post();
            data.setCode(code);
            data.setStatus(EPostStatus.DELETED.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            postDAO.delete(data);
        }
    }

    @Override
    public void approvePost(String code, String status, String approver,
            String approveNote) {
        Post data = new Post();
        data.setCode(code);
        data.setStatus(status);
        data.setUpdater(approver);
        data.setUpdateDatetime(new Date());
        data.setRemark(approveNote);
        postDAO.updateApprovePost(data);
    }

    @Override
    public void refreshCommentPost(String code, Integer commentCount) {
        Post data = new Post();
        data.setCode(code);
        data.setCommentCount(commentCount);
        postDAO.updateCommentPost(data);
    }

    @Override
    public void refreshPointPost(String code, Integer pointCount) {
        Post data = new Post();
        data.setCode(code);
        data.setPointCount(pointCount);
        postDAO.updatePointPost(data);
    }

    @Override
    public void refreshReadPost(String code, Integer readCount) {
        Post data = new Post();
        data.setCode(code);
        data.setReadCount(readCount);
        postDAO.updateReadPost(data);
    }

    @Override
    public void refreshLocatePost(String code, String location,
            String updater) {
        Post data = new Post();
        data.setCode(code);
        data.setLocation(location);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        postDAO.updateLocationPost(data);
    }

    @Override
    public List<Post> queryPostList(Post condition) {
        return postDAO.selectList(condition);
    }

    @Override
    public Post getPost(String code) {
        Post data = null;
        if (StringUtils.isNotBlank(code)) {
            Post condition = new Post();
            condition.setCode(code);
            data = postDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "帖子记录不存在");
            }
        }
        return data;
    }

}
