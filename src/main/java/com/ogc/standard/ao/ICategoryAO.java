package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Category;

/**
 * 产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午2:22:04 
 * @history:
 */
public interface ICategoryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加产品分类
    public String addCategory(String name, String parentCode, String pic,
            Integer orderNo, String updater, String remark);

    // 修改产品分类
    public void editCategory(String code, String name, String parentCode,
            String pic, Integer orderNo, String updater, String remark);

    // 上架
    public void putOnCategory(String code, String updater);

    // 下架
    public void putOffCategory(String code, String updater);

    public Paginable<Category> queryCategoryPage(int start, int limit,
            Category condition);

    public List<Category> queryCategoryList(Category condition);

    public Category getCategory(String code);

}
