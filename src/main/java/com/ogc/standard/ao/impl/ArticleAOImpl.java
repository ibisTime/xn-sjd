package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IArticleBO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Article;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN629340Req;
import com.ogc.standard.dto.req.XN629341Req;
import com.ogc.standard.dto.req.XN629342Req;
import com.ogc.standard.dto.req.XN629343Req;
import com.ogc.standard.dto.req.XN629344Req;
import com.ogc.standard.dto.req.XN629345Req;
import com.ogc.standard.dto.res.XN629348Res;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EArticleOpenLevel;
import com.ogc.standard.enums.EArticleStatus;
import com.ogc.standard.enums.EArticleType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EDealType;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.enums.EInteractType;
import com.ogc.standard.enums.EObjectType;
import com.ogc.standard.enums.EProductType;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.exception.BizException;

@Service
public class ArticleAOImpl implements IArticleAO {

    @Autowired
    private IArticleBO articleBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IGroupAdoptOrderBO groupAdoptOrderBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Override
    @Transactional
    public String addArticle(XN629340Req req) {
        if (StringUtils.isNotBlank(req.getAdoptTreeCode())) {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
                .getAdoptOrderTree(req.getAdoptTreeCode());

            if (!EAdoptOrderTreeStatus.ADOPT.getCode()
                .equals(adoptOrderTree.getStatus())
                    && !EAdoptOrderTreeStatus.TO_ADOPT.getCode()
                        .equals(adoptOrderTree.getStatus())) {
                throw new BizException("xn0000", "认养权不是可发表文章状态！");
            }

            // 集体订单
            if (ESellType.COLLECTIVE.getCode()
                .equals(adoptOrderTree.getOrderType())) {
                GroupAdoptOrder groupAdoptOrder = groupAdoptOrderBO
                    .getGroupAdoptOrder(adoptOrderTree.getOrderCode());

                // 满标和认养中才能发文章
                if (!EGroupAdoptOrderStatus.FULL.getCode()
                    .equals(groupAdoptOrder.getStatus())
                        && !EGroupAdoptOrderStatus.ADOPT.getCode()
                            .equals(groupAdoptOrder.getStatus())) {
                    throw new BizException("xn0000", "集体订单不是可发表文章状态！");
                }
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
                && !EArticleStatus.APPROVE_NO.getCode().equals(data.getStatus())
                && !EArticleStatus.TO_PUT_ON.getCode().equals(data.getStatus())
                && !EArticleStatus.PUT_OFF.getCode().equals(data.getStatus())) {
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
    public void pointArticle(String code, String userId) {
        Interact interact = interactBO.getInteract(
            EInteractType.POINT.getCode(), EObjectType.ARTICLE.getCode(), code,
            userId);
        Article article = articleBO.getArticle(code);
        Integer pointCount = article.getPointCount();

        if (null == interact) {
            interactBO.saveInteract(EInteractType.POINT.getCode(),
                EObjectType.ARTICLE.getCode(), code, userId);

            pointCount = pointCount + 1;
            articleBO.refreshPoint(code, pointCount);
        } else {
            interactBO.removeInteract(interact.getCode());

            pointCount = pointCount - 1;
            articleBO.refreshPoint(code, pointCount);
        }
    }

    @Override
    public void collectArticle(String code, String userId) {
        Interact interact = interactBO.getInteract(
            EInteractType.COLLECT.getCode(), EObjectType.ARTICLE.getCode(),
            code, userId);
        Article article = articleBO.getArticle(code);
        Integer collectCount = article.getCollectCount();

        if (null == interact) {
            interactBO.saveInteract(EInteractType.COLLECT.getCode(),
                EObjectType.ARTICLE.getCode(), code, userId);

            collectCount = collectCount + 1;
            articleBO.refreshCollect(code, collectCount);
        } else {
            interactBO.removeInteract(interact.getCode());

            collectCount = collectCount - 1;
            articleBO.refreshCollect(code, collectCount);
        }
    }

    @Override
    public void readArticle(String code, String userId) {
        Article article = articleBO.getArticle(code);

        articleBO.refreshRead(code, article.getReadCount() + 1);
    }

    @Override
    public XN629348Res isPointCollect(String code, String userId, String type) {
        Interact interact = interactBO.getInteract(type,
            EObjectType.ARTICLE.getCode(), code, userId);

        XN629348Res res = new XN629348Res(EBoolean.YES.getCode());
        if (null == interact) {
            res = new XN629348Res(EBoolean.NO.getCode());
        }

        return res;
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

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Article article : page.getList()) {

                init(article);

            }
        }

        return page;
    }

    @Override
    public List<Interact> queryMyCollectArticleList(String userId) {
        List<Interact> collectArticleList = interactBO.queryInteractList(userId,
            EInteractType.COLLECT.getCode(), EObjectType.ARTICLE.getCode());

        if (CollectionUtils.isNotEmpty(collectArticleList)) {
            for (Interact interact : collectArticleList) {
                Article article = articleBO
                    .getArticle(interact.getObjectCode());
                interact.setArticle(article);
            }
        }

        return collectArticleList;
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        List<Article> list = articleBO.queryArticleList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Article article : list) {

                init(article);

            }
        }

        return list;
    }

    @Override
    public Article getArticle(String code) {
        Article article = articleBO.getArticle(code);

        init(article);

        return article;
    }

    private void init(Article article) {
        // 作者
        String publishUserName = null;
        if (EArticleType.USER.getCode().equals(article.getType())) {
            User user = userBO.getUserUnCheck(article.getPublishUserId());
            if (null != user) {
                publishUserName = PhoneUtil.hideMobile(user.getMobile());
                if (StringUtils.isNotBlank(user.getNickname())) {
                    publishUserName = user.getNickname();
                }
            }
        } else {
            publishUserName = EUser.ADMIN.getValue();
        }

        article.setPublishUserName(publishUserName);

        Tree tree = treeBO.getTreeByTreeNumber(article.getTreeNo());
        article.setTreeName(tree.getScientificName());

        if (EProductType.NORMAL.getCode().equals(tree.getProductType())) {
            Product product = productBO.getProduct(tree.getProductCode());
            article.setProductName(product.getName());
            article.setProductCode(product.getCode());
            article.setProductStatus(product.getStatus());
        } else if (EProductType.YS.getCode().equals(tree.getProductType())) {
            PresellProduct presellProduct = presellProductBO
                .getPresellProduct(tree.getProductCode());
            article.setProductName(presellProduct.getName());
            article.setProductCode(presellProduct.getCode());
            article.setProductStatus(presellProduct.getStatus());
        }

        if (StringUtils.isNotBlank(article.getAdoptTreeCode())) {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
                .getAdoptOrderTree(article.getAdoptTreeCode());
            article.setAdoptOrderTree(adoptOrderTree);
        }
    }

}
