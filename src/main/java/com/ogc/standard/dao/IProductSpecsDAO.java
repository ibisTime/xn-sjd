package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ProductSpecs;

/**
 * 产品规格
 * @author: silver 
 * @since: 2018年9月27日 下午3:09:45 
 * @history:
 */
public interface IProductSpecsDAO extends IBaseDAO<ProductSpecs> {
    String NAMESPACE = IProductSpecsDAO.class.getName().concat(".");

    // 删除产品下的规格
    public int deleteSpecByProduct(ProductSpecs condition);
}
