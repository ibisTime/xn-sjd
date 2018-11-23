package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IArticleDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Article;

@Repository("articleDAOImpl")
public class ArticleDAOImpl extends AMybatisTemplate implements IArticleDAO {

    @Override
    public int insert(Article data) {
        return super.insert(NAMESPACE.concat("insert_article"), data);
    }

    @Override
    public int delete(Article data) {
        return super.delete(NAMESPACE.concat("delete_article"), data);
    }

    @Override
    public Article select(Article condition) {
        return super.select(NAMESPACE.concat("select_article"), condition,
            Article.class);
    }

    @Override
    public long selectTotalCount(Article condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_article_count"),
            condition);
    }

    @Override
    public List<Article> selectList(Article condition) {
        return super.selectList(NAMESPACE.concat("select_article"), condition,
            Article.class);
    }

    @Override
    public List<Article> selectList(Article condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_article"), start,
            count, condition, Article.class);
    }

    @Override
    public int update(Article data) {
        return super.update(NAMESPACE.concat("update_article"), data);
    }

    @Override
    public void updateStatus(Article data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public void updatePutOn(Article data) {
        super.update(NAMESPACE.concat("update_putOn"), data);
    }

    @Override
    public int updatePoint(Article data) {
        return super.update(NAMESPACE.concat("update_pointCount"), data);
    }

    @Override
    public int updateCollect(Article data) {
        return super.update(NAMESPACE.concat("update_collectCount"), data);
    }

}
