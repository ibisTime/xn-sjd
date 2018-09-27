package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.exception.BizException;

@Service
public class TreeAOImpl implements ITreeAO {

    @Autowired
    private ITreeBO treeBO;

    @Override
    public String addTree(Tree data) {
        return treeBO.saveTree(data);
    }

    @Override
    public void editTree(Tree data) {
        if (!treeBO.isTreeExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
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
