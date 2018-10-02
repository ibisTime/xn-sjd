package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629010Req;
import com.ogc.standard.dto.req.XN629010ReqSpecs;
import com.ogc.standard.dto.req.XN629010ReqTree;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private ITreeBO treeBO;

    @Override
    @Transactional
    public String addProduct(XN629010Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }
        Product product = productBO.saveProduct(req);

        // 添加产品规格
        for (XN629010ReqSpecs productSpecs : req.getProductSpecsList()) {
            productSpecsBO.saveProductSpecs(product.getCode(), productSpecs);
        }

        // 添加古树
        for (XN629010ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000", "树木编号" + tree.getTreeNumber()
                        + "已存在，请重新输入！");
            }

            treeBO.saveTree(product, tree);
        }

        return product.getCode();
    }

    @Override
    @Transactional
    public void editProduct(XN629011Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，请重新选择！");
        }

        Product data = productBO.getProduct(req.getCode());
        // 未提交和已下架后可修改
        if (!EProductStatus.DRAFT.getCode().equals(data.getStatus())
                && !EProductStatus.PUTOFFED.getCode().equals(data.getStatus())
                && !EProductStatus.APPROVE_NO.getCode()
                    .equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "产品不处于可修改状态！");
        }
        productBO.refreshProduct(data, req);

        // 删除旧产品规格
        productSpecsBO.removeProductSpecsByProduct(req.getCode());

        // 添加新产品规格
        for (XN629010ReqSpecs productSpecs : req.getProductSpecsList()) {
            productSpecsBO.saveProductSpecs(req.getCode(), productSpecs);
        }

        // 删除旧古树
        treeBO.removeTreeByProduct(req.getCode());

        // 添加新古树
        for (XN629010ReqTree tree : req.getTreeList()) {
            // 判断重复的树
            if (treeBO.isTreeNumberExist(tree.getTreeNumber())) {
                throw new BizException("xn0000", "树木编号" + tree.getTreeNumber()
                        + "已存在，请重新输入！");
            }

            treeBO.saveTree(data, tree);
        }
    }

    @Override
    public void submitProduct(String code, String updater, String remark) {
        Product product = productBO.getProduct(code);

        if (!EProductStatus.DRAFT.getCode().equals(product.getStatus())
                && !EProductStatus.PUTOFFED.getCode().equals(
                    product.getStatus())
                && !EProductStatus.APPROVE_NO.getCode().equals(
                    product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可提交状态！");
        }

        productBO.refreshSubmitProduct(code, updater, remark);
    }

    @Override
    public void approveProduct(String code, String approveResult,
            String updater, String remark) {
        Product product = productBO.getProduct(code);

        if (!EProductStatus.TO_APPROVE.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EProductStatus.TO_PUTON.getCode();
        } else {
            status = EProductStatus.APPROVE_NO.getCode();
        }

        productBO.refreshApproveProduct(code, status, updater, remark);
    }

    @Override
    public void putOnProduct(String code, String location, Integer orderNo,
            String updater, String remark) {
        Product product = productBO.getProduct(code);

        if (!EProductStatus.TO_PUTON.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可上架状态！");
        }

        productBO.refreshPutOnProduct(code, location, orderNo, updater, remark);
    }

    @Override
    public void putOffProduct(String code, String updater) {
        Product product = productBO.getProduct(code);

        // 个人、定向、捐赠，集体产品
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可下架状态！");
        }

        // 集体产品
        if (ESellType.COLLECTIVE.getCode().equals(product.getSellType())
                && !EProductStatus.ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "产品未处于可下架状态！");
        }

        // 存在认养中的古树时不能下架
        if (CollectionUtils.isNotEmpty(treeBO.queryTreeListByProduct(code,
            ETreeStatus.ADOPTED.getCode()))) {
            throw new BizException("xn0000", "产品中存在认养中的古树，无法下架！");
        }

        productBO.refreshPutOffProduct(code, updater);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition) {
        Paginable<Product> page = productBO.getPaginable(start, limit,
            condition);
        List<Product> list = page.getList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Product product : list) {
                initProduct(product);
            }
        }

        return page;
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productBO.queryProductList(condition);
    }

    @Override
    public Product getProduct(String code) {
        Product product = productBO.getProduct(code);
        initProduct(product);
        return product;
    }

    private void initProduct(Product product) {
        // 产品规格列表
        List<ProductSpecs> specsList = productSpecsBO
            .queryProductSpecsListByProduct(product.getCode());
        product.setProductSpecsList(specsList);

        // 初始化最小价格和最大价格
        BigDecimal minPrice = BigDecimal.ZERO;
        BigDecimal maxPrice = minPrice;
        for (ProductSpecs productSpecs : specsList) {
            if (minPrice.compareTo(BigDecimal.ZERO) == 0) {
                minPrice = productSpecs.getPrice();
            }
            if (productSpecs.getPrice().compareTo(minPrice) < 0) {
                minPrice = productSpecs.getPrice();
            }
            if (productSpecs.getPrice().compareTo(maxPrice) > 0) {
                maxPrice = productSpecs.getPrice();
            }
        }
        product.setMinPrice(minPrice);
        product.setMaxPrice(maxPrice);

        // 古树列表
        List<Tree> treeRemainList = treeBO.queryTreeListByProduct(
            product.getCode(), ETreeStatus.TO_ADOPT.getCode());
        product.setTreeRemainList(treeRemainList);

        // 树木剩余量数量
        product.setTreeRemainCount(treeRemainList.size());

        // 树木总量获取
        int treeTotalCount = treeBO.getTreeCount(product.getCode());
        product.setTreeTotalCount(treeTotalCount);

        // 类型
        Category category = categoryBO.getCategory(product.getCategoryCode());
        product.setCategoryName(category.getName());
    }

}
