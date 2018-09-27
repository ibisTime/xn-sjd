package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IProductSpecsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ProductSpecs;

@Repository("productSpecsDAOImpl")
public class ProductSpecsDAOImpl extends AMybatisTemplate
        implements IProductSpecsDAO {

    @Override
    public int insert(ProductSpecs data) {
        return super.insert(NAMESPACE.concat("insert_productSpecs"), data);
    }

    @Override
    public int delete(ProductSpecs data) {
        return super.delete(NAMESPACE.concat("delete_productSpecs"), data);
    }

    @Override
    public int deleteSpecByProduct(ProductSpecs condition) {
        return super.delete(NAMESPACE.concat("delete_productSpecsByProduct"),
            condition);
    }

    @Override
    public ProductSpecs select(ProductSpecs condition) {
        return super.select(NAMESPACE.concat("select_productSpecs"), condition,
            ProductSpecs.class);
    }

    @Override
    public long selectTotalCount(ProductSpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_productSpecs_count"), condition);
    }

    @Override
    public List<ProductSpecs> selectList(ProductSpecs condition) {
        return super.selectList(NAMESPACE.concat("select_productSpecs"),
            condition, ProductSpecs.class);
    }

    @Override
    public List<ProductSpecs> selectList(ProductSpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_productSpecs"), start,
            count, condition, ProductSpecs.class);
    }

}
