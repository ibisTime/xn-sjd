package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.IInteractBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Interact;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629030Req;
import com.ogc.standard.dto.req.XN629031Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EInteractType;
import com.ogc.standard.enums.EObjectType;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;

@Service
public class TreeAOImpl implements ITreeAO {

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICategoryBO categoryBO;

    @Override
    public String addTree(XN629030Req req) {
        if (treeBO.isTreeNumberExist(req.getTreeNumber())) {
            throw new BizException("xn0000", "树木编号已存在，请重新输入！");
        }

        Tree data = new Tree();

        String code = OrderNoGenerater.generate(EGeneratePrefix.Tree.getCode());
        data.setCode(code);
        data.setProductCode(req.getProductCode());
        data.setOwnerId(req.getOwnerId());
        data.setTreeNumber(req.getTreeNumber());
        data.setAge(StringValidater.toInteger(req.getAge()));

        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());
        data.setRank(req.getRank());
        data.setProvince(req.getProvince());

        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());

        data.setPic(req.getPic());
        data.setStatus(ETreeStatus.TO_ADOPT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        treeBO.saveTree(data);
        return code;
    }

    @Override
    public void editTree(XN629031Req req) {
        Tree condition = new Tree();
        condition.setTreeNumber(req.getTreeNumber());
        if (treeBO.queryTreeList(condition).size() > 1) {
            throw new BizException("xn0000", "树木编号已存在，请重新输入！");
        }

        Tree tree = treeBO.getTree(req.getCode());
        if (!ETreeStatus.ADOPTED.getCode().equals(tree.getStatus())) {
            throw new BizException("xn0000", "树木已被认养，无法修改！");
        }

        Tree data = new Tree();
        data.setCode(req.getCode());
        data.setTreeNumber(req.getTreeNumber());
        data.setAge(StringValidater.toInteger(req.getAge()));

        data.setOriginPlace(req.getOriginPlace());
        data.setScientificName(req.getScientificName());
        data.setVariety(req.getVariety());
        data.setRank(req.getRank());
        data.setProvince(req.getProvince());

        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setTown(req.getTown());
        data.setLongitude(req.getLongitude());
        data.setLatitude(req.getLatitude());

        data.setPic(req.getPic());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        treeBO.refreshTree(data);
    }

    @Override
    @Transactional
    public void pointTree(String userId, String treeNumber) {
        Interact interact = interactBO.getInteract(
            EInteractType.POINT.getCode(), EObjectType.TREE.getCode(),
            treeNumber, userId);
        Tree tree = treeBO.getTreeByTreeNumber(treeNumber);
        Integer pointCount = tree.getPointCount();

        if (null == interact) {
            interactBO.saveInteract(EInteractType.POINT.getCode(),
                EObjectType.TREE.getCode(), treeNumber, userId);

            pointCount = pointCount + 1;
            treeBO.refreshPointCount(treeNumber, pointCount);
        } else {
            interactBO.removeInteract(interact.getCode());

            pointCount = pointCount - 1;
            treeBO.refreshPointCount(treeNumber, pointCount);
        }
    }

    @Override
    public Paginable<Tree> queryTreePage(int start, int limit, Tree condition) {
        if (StringUtils.isNotBlank(condition.getMaintainId())) {
            List<String> ownerList = applyBindMaintainBO
                .queryBindOwnerList(condition.getMaintainId());
            if (CollectionUtils.isNotEmpty(ownerList)) {
                condition.setOwnerList(ownerList);
            } else {
                condition.setOwnerId("not one");
            }
        }

        Paginable<Tree> page = treeBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Tree tree : page.getList()) {
                initTree(tree);
            }
        }

        return page;
    }

    @Override
    public List<Tree> queryTreeList(Tree condition) {
        return treeBO.queryTreeList(condition);
    }

    @Override
    public Tree getTree(String code) {
        Tree tree = treeBO.getTree(code);

        initTree(tree);

        return tree;
    }

    private void initTree(Tree tree) {
        // 产权方
        String ownerName = null;
        SYSUser sysUser = sysUserBO.getSYSUserUnCheck(tree.getOwnerId());
        if (null != sysUser) {
            ownerName = sysUser.getMobile();
            if (StringUtils.isNotBlank(sysUser.getRealName())) {
                ownerName = sysUser.getRealName().concat("-").concat(ownerName);
            }
        }
        tree.setOwnerName(ownerName);

        // 产品信息
        Product product = productBO.getProduct(tree.getProductCode());
        tree.setProductName(product.getName());
        tree.setSellType(product.getSellType());

        // 类型
        Category category = categoryBO.getCategory(product.getCategoryCode());
        tree.setCategory(category.getName());

    }
}
