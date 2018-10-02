package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IArticleBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Article;
import com.ogc.standard.dto.req.XN629340Req;
import com.ogc.standard.dto.req.XN629341Req;
import com.ogc.standard.dto.req.XN629342Req;
import com.ogc.standard.dto.req.XN629343Req;
import com.ogc.standard.dto.req.XN629344Req;
import com.ogc.standard.enums.EArticleOpenLevel;
import com.ogc.standard.enums.EArticleStatus;
import com.ogc.standard.enums.EArticleType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.exception.BizException;

@Service
public class ArticleAOImpl implements IArticleAO {

    @Autowired
    private IArticleBO articleBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Override
    public String addArticle(XN629340Req req) {
        EArticleStatus status = null;
        String treeNo = null;
        String openLevel = null;
        if (EBoolean.NO.getCode().equals(req.getDealType())) {// 保存
            status = EArticleStatus.DRAFT;
        } else {// 提交
            if (EArticleType.PLAT.getCode().equals(req.getType())) {// 平台
                status = EArticleStatus.TO_PUT_ON;
                treeNo = req.getTreeNo();
                openLevel = EArticleOpenLevel.OPEN.getCode();
            } else {// 用户
                status = EArticleStatus.TO_APPROVE;
                AdoptOrderTree aot = adoptOrderTreeBO.getAdoptOrderTree(req
                    .getAdoptTreeCode());
                treeNo = aot.getTreeNumber();
                openLevel = req.getOpenLevel();
            }
        }
        return articleBO.saveArticle(req.getAdoptTreeCode(), treeNo,
            req.getType(), openLevel, req.getTitle(), req.getContent(),
            req.getPhoto(), status, req.getUpdater());
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
        String treeNo = null;
        String openLevel = null;
        if (EBoolean.NO.getCode().equals(req.getDealType())) {// 保存
            status = EArticleStatus.DRAFT;
        } else {// 提交
            if (EArticleType.PLAT.getCode().equals(data.getType())) {// 平台
                status = EArticleStatus.TO_PUT_ON;
                treeNo = req.getTreeNo();
                openLevel = EArticleOpenLevel.OPEN.getCode();
            } else {// 用户
                status = EArticleStatus.TO_APPROVE;
                AdoptOrderTree aot = adoptOrderTreeBO.getAdoptOrderTree(req
                    .getAdoptTreeCode());
                treeNo = aot.getTreeNumber();
                openLevel = req.getOpenLevel();
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
    public int dropArticle(String code) {
        if (!articleBO.isArticleExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return articleBO.removeArticle(code);
    }

    @Override
    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition) {
        return articleBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        return articleBO.queryArticleList(condition);
    }

    @Override
    public Article getArticle(String code) {
        return articleBO.getArticle(code);
    }

    public void init(Article data) {
        // if (StringUtils.isNotBlank()) {
        //
        // }
    }

}
