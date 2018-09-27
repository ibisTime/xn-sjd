package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Tree;

public interface ITreeDAO extends IBaseDAO<Tree> {
    String NAMESPACE = ITreeDAO.class.getName().concat(".");

    // 修改古树
    public int updateTree(Tree data);

    // 删除产品下的树
    public int deleteByProduct(Tree data);

}
