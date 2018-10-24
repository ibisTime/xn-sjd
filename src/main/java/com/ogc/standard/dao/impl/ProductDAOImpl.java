package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IProductDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Product;

@Repository("productDAOImpl")
public class ProductDAOImpl extends AMybatisTemplate implements IProductDAO {

    @Override
    public int insert(Product data) {
        return super.insert(NAMESPACE.concat("insert_product"), data);
    }

    @Override
    public int delete(Product data) {
        return 0;
    }

    @Override
    public int updateProduct(Product data) {
        return super.update(NAMESPACE.concat("update_editProduct"), data);
    }

    @Override
    public int updateSubmitProduct(Product data) {
        return super.update(NAMESPACE.concat("update_submitProduct"), data);
    }

    @Override
    public int updateApproveProduct(Product data) {
        return super.update(NAMESPACE.concat("update_approveProduct"), data);
    }

    @Override
    public int updatePutOnProduct(Product data) {
        return super.update(NAMESPACE.concat("update_putOnProduct"), data);
    }

    @Override
    public int updateLockProduct(Product data) {
        return super.update(NAMESPACE.concat("update_lockProduct"), data);
    }

    @Override
    public int updateRaiseCount(Product data) {
        return super.update(NAMESPACE.concat("update_raiseCount"), data);
    }

    @Override
    public int updateNowCount(Product data) {
        return super.update(NAMESPACE.concat("update_nowCount"), data);
    }

    @Override
    public int updateAdoptProduct(Product data) {
        return super.update(NAMESPACE.concat("update_adoptProduct"), data);
    }

    @Override
    public int updatePutOffProduct(Product data) {
        return super.update(NAMESPACE.concat("update_putOffProduct"), data);
    }

    @Override
    public int updateCurrentIdentify(Product data) {
        return super.update(NAMESPACE.concat("update_currentIdentify"), data);
    }

    @Override
    public Product select(Product condition) {
        return super.select(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    @Override
    public long selectTotalCount(Product condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_product_count"),
            condition);
    }

    @Override
    public List<Product> selectList(Product condition) {
        return super.selectList(NAMESPACE.concat("select_product"), condition,
            Product.class);
    }

    @Override
    public List<Product> selectList(Product condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_product"), start,
            count, condition, Product.class);
    }

}
