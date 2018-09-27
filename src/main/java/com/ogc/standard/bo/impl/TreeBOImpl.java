package com.ogc.standard.bo.impl;

import java.util.List;

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
    public boolean isTreeExist(String code) {
        Tree condition = new Tree();
        condition.setCode(code);
        if (treeDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveTree(Tree tree) {
        return null;
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
    public void removeTreeByProduct(String productCode) {
        Tree tree = new Tree();
        tree.setProductCode(productCode);
        treeDAO.deleteByProduct(tree);
    }

    @Override
    public void refreshTree(Tree data) {
        treeDAO.updateTree(data);
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
