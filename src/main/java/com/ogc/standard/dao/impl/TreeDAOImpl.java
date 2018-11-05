package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ITreeDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Tree;

@Repository("treeDAOImpl")
public class TreeDAOImpl extends AMybatisTemplate implements ITreeDAO {

    @Override
    public int insert(Tree data) {
        return super.insert(NAMESPACE.concat("insert_tree"), data);
    }

    @Override
    public int updateTree(Tree data) {
        return super.update(NAMESPACE.concat("update_tree"), data);
    }

    @Override
    public int updateToPayTree(Tree data) {
        return super.update(NAMESPACE.concat("update_toPayTree"), data);
    }

    @Override
    public int updateCancelTree(Tree data) {
        return super.update(NAMESPACE.concat("update_cancelTree"), data);
    }

    @Override
    public int updateCancelTreeByProduct(Tree data) {
        return super.update(NAMESPACE.concat("update_cancelTreeByProduct"),
            data);
    }

    @Override
    public int updatePayTree(Tree data) {
        return super.update(NAMESPACE.concat("update_payTree"), data);
    }

    @Override
    public int updateArticleCount(Tree data) {
        return super.update(NAMESPACE.concat("update_articleCount"), data);
    }

    @Override
    public int updatePointCount(Tree data) {
        return super.update(NAMESPACE.concat("update_pointCount"), data);
    }

    @Override
    public int updateCollectionCount(Tree data) {
        return super.update(NAMESPACE.concat("update_collectionCount"), data);
    }

    @Override
    public int updateAdoptCountByProduct(Tree data) {
        return super.update(NAMESPACE.concat("update_adoptCountByProduct"),
            data);
    }

    @Override
    public int updateAdoptCount(Tree data) {
        return super.update(NAMESPACE.concat("update_adoptCount"), data);
    }

    @Override
    public int delete(Tree data) {
        return super.delete(NAMESPACE.concat("delete_tree"), data);
    }

    @Override
    public int deleteByProduct(Tree data) {
        return super.delete(NAMESPACE.concat("delete_treeByProduct"), data);
    }

    @Override
    public Tree select(Tree condition) {
        return super.select(NAMESPACE.concat("select_tree"), condition,
            Tree.class);
    }

    @Override
    public long selectTotalCount(Tree condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_tree_count"),
            condition);
    }

    @Override
    public List<Tree> selectList(Tree condition) {
        return super.selectList(NAMESPACE.concat("select_tree"), condition,
            Tree.class);
    }

    @Override
    public List<Tree> selectList(Tree condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_tree"), start, count,
            condition, Tree.class);
    }

}
