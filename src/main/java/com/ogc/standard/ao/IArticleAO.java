package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Article;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.dto.req.XN629340Req;
import com.ogc.standard.dto.req.XN629341Req;
import com.ogc.standard.dto.req.XN629342Req;
import com.ogc.standard.dto.req.XN629343Req;
import com.ogc.standard.dto.req.XN629344Req;
import com.ogc.standard.dto.req.XN629345Req;
import com.ogc.standard.dto.res.XN629348Res;

public interface IArticleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 新增
    public String addArticle(XN629340Req req);

    public int dropArticle(String code);

    // 修改
    public int editArticle(XN629341Req req);

    // 审核
    public void approve(XN629342Req req);

    // 上架文章
    public void putOn(XN629343Req req);

    // 下架文章
    public void putOff(XN629344Req req);

    // 点赞
    public void pointArticle(String code, String userId);

    // 收藏
    public void collectArticle(String code, String userId);

    // 用户是否点赞收藏
    public XN629348Res isPointCollect(String code, String userId, String type);

    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition, XN629345Req req);

    // 我收藏的文章
    public List<Interact> queryMyCollectArticleList(String userId);

    public List<Article> queryArticleList(Article condition);

    public Article getArticle(String code);

}
