package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Article;
import com.ogc.standard.enums.EArticleStatus;

public interface IArticleBO extends IPaginableBO<Article> {

    public boolean isArticleExist(String code);

    public String saveArticle(String adoptTreeCode, String treeNo, String type,
            String openLevel, String title, String content, String photo,
            EArticleStatus status, String publishUserId, String updater);

    public int removeArticle(String code);

    public int refreshArticle(Article data);

    // 更新状态
    public void refreshStatus(String code, EArticleStatus status,
            String updater);

    // 上架文章
    public void putOn(String code, String location, String orderNo,
            String remark, String updater);

    // 点赞
    public void refreshPoint(String code, Integer pointCount);

    // 收藏
    public void refreshCollect(String code, Integer collectCount);

    // 查询好友私密文章列表
    public List<Article> queryPrivateArticleList(String userId);

    public List<Article> queryArticleList(Article condition);

    public Article getArticle(String code);

}
