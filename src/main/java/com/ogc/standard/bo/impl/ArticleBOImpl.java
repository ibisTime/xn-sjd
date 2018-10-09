package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IArticleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IArticleDAO;
import com.ogc.standard.domain.Article;
import com.ogc.standard.enums.EArticleStatus;
import com.ogc.standard.enums.EGeneratePrefix;
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
            String right, String title, String content, List<String> photoList,
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
        data.setStatus(status.getCode());
        data.setPublishDatetime(new Date());

        data.setPublishUserId(publishUserId);
        data.setUpdater(updater);
        data.setUpdateDatatime(new Date());
        data.setAdoptTreeCode(adoptTreeCode);

        StringBuffer stringBuffer = new StringBuffer();
        if (CollectionUtils.isNotEmpty(photoList)) {
            for (String contentPic : photoList) {
                stringBuffer.append(",").append(contentPic);
            }
        }
        data.setPhoto(stringBuffer.toString());

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

}
