package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Article;

//dao层 
public interface IArticleDAO extends IBaseDAO<Article> {
    String NAMESPACE = IArticleDAO.class.getName().concat(".");

    int update(Article data);

    void updateStatus(Article data);

    void updatePutOn(Article data);

    public int updatePoint(Article data);

    public int updateCollect(Article data);

}
