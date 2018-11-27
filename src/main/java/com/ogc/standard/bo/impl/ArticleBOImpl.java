package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IArticleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IArticleDAO;
import com.ogc.standard.domain.Article;
import com.ogc.standard.enums.EArticleOpenLevel;
import com.ogc.standard.enums.EArticleStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EUserReleationStatus;
import com.ogc.standard.enums.EUserReleationType;
import com.ogc.standard.exception.BizException;

@Component
public class ArticleBOImpl extends PaginableBOImpl<Article>
        implements IArticleBO {

    @Autowired
    private IArticleDAO articleDAO;

    @Override
    public boolean isArticleExist(String code) {
        Article condition = new Article();
        condition.setCode(code);
        if (articleDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveArticle(String adoptTreeCode, String treeNo, String type,
            String right, String title, String content, String photo,
            EArticleStatus status, String publishUserId, String updater) {
        Article data = new Article();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ARTICLE.getCode());
        data.setCode(code);
        data.setAdoptTreeCode(adoptTreeCode);
        data.setTreeNo(treeNo);
        data.setType(type);

        data.setOpenLevel(right);
        data.setTitle(title);
        data.setContent(content);
        data.setPhoto(photo);
        data.setStatus(status.getCode());
        data.setPublishDatetime(new Date());

        data.setPublishUserId(publishUserId);
        data.setUpdater(updater);
        data.setUpdateDatatime(new Date());
        data.setAdoptTreeCode(adoptTreeCode);

        data.setCollectCount(0);
        data.setReadCount(0);
        data.setPointCount(0);

        articleDAO.insert(data);
        return code;
    }

    @Override
    public int removeArticle(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Article data = new Article();
            data.setCode(code);
            count = articleDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshArticle(Article data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = articleDAO.update(data);
        }
        return count;
    }

    @Override
    public void refreshStatus(String code, EArticleStatus status,
            String updater) {
        Article data = getArticle(code);
        data.setStatus(status.getCode());
        data.setUpdater(updater);
        data.setUpdateDatatime(new Date());
        articleDAO.updateStatus(data);
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String remark, String updater) {
        Article data = getArticle(code);
        data.setLocation(location);
        data.setOrderNo(orderNo);
        data.setStatus(EArticleStatus.PUT_ON.getCode());
        data.setUpdater(updater);
        data.setUpdateDatatime(new Date());
        data.setRemark(remark);
        articleDAO.updatePutOn(data);
    }

    @Override
    public void refreshPoint(String code, Integer pointCount) {
        Article data = new Article();
        data.setCode(code);
        data.setPointCount(pointCount);
        articleDAO.updatePoint(data);
    }

    @Override
    public void refreshCollect(String code, Integer collectCount) {
        Article data = new Article();
        data.setCode(code);
        data.setCollectCount(collectCount);
        articleDAO.updateCollect(data);
    }

    @Override
    public void refreshRead(String code, Integer readCount) {
        Article data = new Article();
        data.setCode(code);
        data.setReadCount(readCount);
        articleDAO.updateRead(data);
    }

    @Override
    public List<Article> queryPrivateArticleList(String userId) {
        Article condition = new Article();
        condition.setQueryUserId(userId);
        condition.setRelationType(EUserReleationType.FRIEND.getCode());
        condition.setOpenLevel(EArticleOpenLevel.ONLY_FRIEND.getCode());
        condition.setRelationStatus(EUserReleationStatus.NORMAL.getCode());
        condition.setStatus(EArticleStatus.PUT_ON.getCode());
        return articleDAO.selectList(condition);
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        return articleDAO.selectList(condition);
    }

    @Override
    public Article getArticle(String code) {
        Article data = null;
        if (StringUtils.isNotBlank(code)) {
            Article condition = new Article();
            condition.setCode(code);
            data = articleDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "文章不存在");
            }
        }
        return data;
    }

}
