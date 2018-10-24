package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IArticleBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Article;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629340Req;
import com.ogc.standard.dto.req.XN629341Req;
import com.ogc.standard.dto.req.XN629342Req;
import com.ogc.standard.dto.req.XN629343Req;
import com.ogc.standard.dto.req.XN629344Req;
import com.ogc.standard.dto.req.XN629345Req;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EArticleOpenLevel;
import com.ogc.standard.enums.EArticleStatus;
import com.ogc.standard.enums.EArticleType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EDealType;
import com.ogc.standard.exception.BizException;

@Service
public class ArticleAOImpl implements IArticleAO {

    @Autowired
    private IArticleBO articleBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ITreeBO treeBO;

    @Override
    @Transactional
    public String addArticle(XN629340Req req) {
        if (StringUtils.isNotBlank(req.getAdoptTreeCode())) {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
                .getAdoptOrderTree(req.getAdoptTreeCode());
            if (!EAdoptOrderTreeStatus.ADOPT.getCode()
                .equals(adoptOrderTree.getStatus())) {
                throw new BizException("xn0000", "认养权不是可发表文章状态！");
            }
        }

        EArticleStatus status = null;
        String openLevel = req.getOpenLevel();

        // 保存
        if (EDealType.SAVE.getCode().equals(req.getDealType())) {
            status = EArticleStatus.DRAFT;
        }

        // 提交
        if (EDealType.SUBMIT.getCode().equals(req.getDealType())) {

            // 平台
            if (EArticleType.PLAT.getCode().equals(req.getType())) {
                status = EArticleStatus.TO_PUT_ON;
                openLevel = EArticleOpenLevel.OPEN.getCode();
            } else {// 用户

                status = EArticleStatus.TO_APPROVE;

            }

        }

        // 更新树木文章数量
        String treeNumber = null;
        if (StringUtils.isNotBlank(req.getTreeNo())) {
            treeNumber = req.getTreeNo();
        }
        if (StringUtils.isNotBlank(req.getAdoptTreeCode())) {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
                .getAdoptOrderTree(req.getAdoptTreeCode());
            treeNumber = adoptOrderTree.getTreeNumber();
        }
        Tree tree = treeBO.getTreeByTreeNumber(treeNumber);
        treeBO.refreshArticleCount(req.getTreeNo(), tree.getArticleCount() + 1);

        return articleBO.saveArticle(req.getAdoptTreeCode(), treeNumber,
            req.getType(), openLevel, req.getTitle(), req.getContent(),
            req.getPhoto(), status, req.getPublishUserId(), req.getUpdater());
    }

    @Override
    public int editArticle(XN629341Req req) {
        Article data = articleBO.getArticle(req.getCode());
        if (!EArticleStatus.DRAFT.getCode().equals(data.getStatus())
                && !EArticleStatus.APPROVE_NO.getCode()
                    .equals(data.getStatus())) {
            throw new BizException("xn0000", "文章不是可以修改的状态");
        }

        EArticleStatus status = null;
        String treeNo = req.getTreeNo();
        String openLevel = req.getOpenLevel();

        // 保存
        if (EDealType.SAVE.getCode().equals(req.getDealType())) {
            status = EArticleStatus.DRAFT;
        }

        // 提交
        if (EDealType.SUBMIT.getCode().equals(req.getDealType())) {
            if (EArticleType.PLAT.getCode().equals(data.getType())) {// 平台
                status = EArticleStatus.TO_PUT_ON;
                openLevel = EArticleOpenLevel.OPEN.getCode();
            } else {// 用户
                status = EArticleStatus.TO_APPROVE;
            }
        }
        data.setAdoptTreeCode(req.getAdoptTreeCode());
        data.setTreeNo(treeNo);
        data.setOpenLevel(openLevel);
        data.setTitle(req.getTitle());
        data.setContent(req.getContent());

        data.setPhoto(req.getPhoto());
        data.setStatus(status.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatatime(new Date());
        return articleBO.refreshArticle(data);
    }

    @Override
    public void approve(XN629342Req req) {
        Article data = articleBO.getArticle(req.getCode());
        if (!EArticleStatus.TO_APPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "文章不是待审核状态");
        }
        EArticleStatus status = null;
        if (EBoolean.YES.getCode().equals(req.getApproveResult())) {
            status = EArticleStatus.TO_PUT_ON;
        } else {
            status = EArticleStatus.APPROVE_NO;
        }
        articleBO.refreshStatus(data.getCode(), status, req.getUpdater());
    }

    @Override
    public void putOn(XN629343Req req) {
        Article data = articleBO.getArticle(req.getCode());
        if (!EArticleStatus.TO_PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "文章不是待上架状态");
        }
        articleBO.putOn(req.getCode(), req.getLocation(), req.getOrderNo(),
            req.getRemark(), req.getUpdater());
    }

    @Override
    public void putOff(XN629344Req req) {
        // 随时可以下架 无关状态 相当于软删
        articleBO.refreshStatus(req.getCode(), EArticleStatus.PUT_OFF,
            req.getUpdater());
    }

    @Override
    @Transactional
    public int dropArticle(String code) {
        Article article = articleBO.getArticle(code);

        // 更新树木文章数量
        if (null != article.getTreeNo()) {
            Tree tree = treeBO.getTreeByTreeNumber(article.getTreeNo());
            treeBO.refreshArticleCount(article.getTreeNo(),
                tree.getArticleCount() - 1);
        }

        return articleBO.removeArticle(code);
    }

    @Override
    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition, XN629345Req req) {
        Paginable<Article> page = articleBO.getPaginable(start, limit,
            condition);

        // 查询好友的好友可见文章
        if (StringUtils.isNotBlank(req.getQueryUserId())
                && StringUtils.isBlank(req.getTreeNo())) {
            List<Article> privateArticle = articleBO
                .queryPrivateArticleList(req.getQueryUserId());
            List<Article> list = page.getList();
            list.addAll(privateArticle);
            page.setList(list);
        }

        return page;
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        return articleBO.queryArticleList(condition);
    }

    @Override
    public Article getArticle(String code) {
        return articleBO.getArticle(code);
    }

}
