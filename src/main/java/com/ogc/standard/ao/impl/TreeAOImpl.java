package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629030Req;
import com.ogc.standard.dto.req.XN629031Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;

@Service
public class TreeAOImpl implements ITreeAO {

    @Autowired
    private ITreeBO treeBO;

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
    public Paginable<Tree> queryTreePage(int start, int limit, Tree condition) {
        return treeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Tree> queryTreeList(Tree condition) {
        return treeBO.queryTreeList(condition);
    }

    @Override
    public Tree getTree(String code) {
        return treeBO.getTree(code);
    }
}
