package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICategoryDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Category;

/**
 * 产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午2:06:51 
 * @history:
 */
@Repository("categoryDAOImpl")
public class CategoryDAOImpl extends AMybatisTemplate implements ICategoryDAO {

    @Override
    public int insert(Category data) {
        return super.insert(NAMESPACE.concat("insert_category"), data);
    }

    @Override
    public int delete(Category data) {
        return 0;
    }

    @Override
    public int updateCategory(Category data) {
        return update(NAMESPACE.concat("update_editCategory"), data);
    }

    @Override
    public int updatePutOn(Category data) {
        return update(NAMESPACE.concat("update_putOnCategory"), data);
    }

    @Override
    public int updatePutOff(Category data) {
        return update(NAMESPACE.concat("update_putOffCategory"), data);
    }

    @Override
    public Category select(Category condition) {
        return super.select(NAMESPACE.concat("select_category"), condition,
            Category.class);
    }

    @Override
    public long selectTotalCount(Category condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_category_count"),
            condition);
    }

    @Override
    public List<Category> selectList(Category condition) {
        return super.selectList(NAMESPACE.concat("select_category"), condition,
            Category.class);
    }

    @Override
    public List<Category> selectList(Category condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_category"), start,
            count, condition, Category.class);
    }

}
