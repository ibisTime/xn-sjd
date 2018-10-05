package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.ITreeDAO;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629010ReqTree;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;

@Component
public class TreeBOImpl extends PaginableBOImpl<Tree> implements ITreeBO {

    @Autowired
    private ITreeDAO treeDAO;

    @Override
    public boolean isTreeNumberExist(String treeNumber) {
        Tree condition = new Tree();
        condition.setTreeNumber(treeNumber);
        if (treeDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveTree(Tree tree) {
        treeDAO.insert(tree);
    }

    @Override
    public String saveTree(Product product, XN629010ReqTree tree) {
        Tree data = new Tree();

        String code = OrderNoGenerater.generate(EGeneratePrefix.Tree.getCode());
        data.setCode(code);
        data.setProductCode(product.getCode());
        data.setOwnerId(product.getOwnerId());
        data.setTreeNumber(tree.getTreeNumber());
        data.setAge(StringValidater.toInteger(tree.getAge()));

        data.setOriginPlace(product.getOriginPlace());
        data.setScientificName(product.getScientificName());
        data.setVariety(product.getVariety());
        data.setRank(product.getRank());
        data.setProvince(product.getProvince());

        data.setCity(product.getCity());
        data.setArea(product.getArea());
        data.setTown(product.getTown());
        data.setLongitude(tree.getLongitude());
        data.setLatitude(tree.getLatitude());

        data.setPic(tree.getPic());
        data.setStatus(ETreeStatus.TO_ADOPT.getCode());
        data.setUpdater(product.getUpdater());
        data.setUpdateDatetime(product.getUpdateDatetime());
        data.setRemark(tree.getRemark());
        treeDAO.insert(data);
        return code;
    }

    @Override
    public void refreshTree(Tree data) {
        treeDAO.updateTree(data);
    }

    @Override
    public void removeTreeByProduct(String productCode) {
        Tree tree = new Tree();
        tree.setProductCode(productCode);
        treeDAO.deleteByProduct(tree);
    }

    @Override
    public void refreshToPayTree(Tree tree, String curOrderCode) {
        tree.setCurOrderCode(curOrderCode);
        tree.setStatus(ETreeStatus.TO_PAY.getCode());
        treeDAO.updateAdoptTree(tree);
    }

    @Override
    public void refreshCancelTree(Tree tree) {
        tree.setCurOrderCode(null);
        tree.setStatus(ETreeStatus.TO_ADOPT.getCode());
        treeDAO.updateCancelTree(tree);
    }

    @Override
    public void refreshPayTree(Tree tree) {
        tree.setStatus(ETreeStatus.ADOPTED.getCode());
        treeDAO.updatePayTree(tree);
    }

    @Override
    public void refreshArticleCount(String treeNumber, Integer articleCount) {
        Tree tree = new Tree();
        tree.setTreeNumber(treeNumber);
        tree.setArticleCount(articleCount);
        treeDAO.updateArticleCount(tree);
    }

    @Override
    public void refreshPointCount(String treeNumber, Integer pointCount) {
        Tree tree = new Tree();
        tree.setTreeNumber(treeNumber);
        tree.setPointCount(pointCount);
        treeDAO.updatePointCount(tree);
    }

    @Override
    public void refreshCollectionCount(String treeNumber,
            Integer collectionCount) {
        Tree tree = new Tree();
        tree.setTreeNumber(treeNumber);
        tree.setCollectionCount(collectionCount);
        treeDAO.updateCollectionCount(tree);
    }

    @Override
    public void refreshAdoptCount(String treeNumber, Integer adoptCount) {
        Tree tree = new Tree();
        tree.setTreeNumber(treeNumber);
        tree.setAdoptCount(adoptCount);
        treeDAO.updateAdoptCount(tree);
    }

    @Override
    public Tree getTreeByTreeNumber(String treeNumber) {
        Tree data = null;
        if (StringUtils.isNotBlank(treeNumber)) {
            Tree condition = new Tree();
            condition.setTreeNumber(treeNumber);
            List<Tree> treeList = treeDAO.selectList(condition);
            if (CollectionUtils.isEmpty(treeList)) {
                throw new BizException("xn0000", "古树不存在！");
            }
            data = treeList.get(0);
        }
        return data;
    }

    @Override
    public long getTotalCountByOwnerId(String ownerId) {
        Tree condition = new Tree();
        condition.setOwnerId(ownerId);
        long count = treeDAO.selectTotalCount(condition);
        return count;
    }

    @Override
    public List<Tree> queryTreeListByProduct(String productCode) {
        Tree condition = new Tree();
        condition.setProductCode(productCode);
        return treeDAO.selectList(condition);
    }

    @Override
    public List<Tree> queryTreeListByProduct(String productCode, String status) {
        Tree condition = new Tree();
        condition.setProductCode(productCode);
        condition.setStatus(status);
        return treeDAO.selectList(condition);
    }

    // 获取树木总量
    public int getTreeCount(String productCode) {
        Tree condition = new Tree();
        condition.setProductCode(productCode);
        return (int) treeDAO.selectTotalCount(condition);
    }

    // 获取树木各个状态总量
    public int getTreeCount(String productCode, String status) {
        Tree condition = new Tree();
        condition.setProductCode(productCode);
        condition.setStatus(status);
        return (int) treeDAO.selectTotalCount(condition);
    }

    @Override
    public List<Tree> queryTreeListByOrderCode(String orderCode) {
        Tree condition = new Tree();
        condition.setCurOrderCode(orderCode);
        return treeDAO.selectList(condition);
    }

    @Override
    public List<Tree> queryTreeList(Tree condition) {
        return treeDAO.selectList(condition);
    }

    @Override
    public Tree getTree(String code) {
        Tree data = null;
        if (StringUtils.isNotBlank(code)) {
            Tree condition = new Tree();
            condition.setCode(code);
            data = treeDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "古树不存在！");
            }
        }
        return data;
    }

}
