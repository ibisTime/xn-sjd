package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Category;

/**
 * 产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午2:06:37 
 * @history:
 */
public interface ICategoryDAO extends IBaseDAO<Category> {
    String NAMESPACE = ICategoryDAO.class.getName().concat(".");

    // 修改
    public int updateCategory(Category data);

    // 上架
    public int updatePutOn(Category data);

    // 下架
    public int updatePutOff(Category data);
}
