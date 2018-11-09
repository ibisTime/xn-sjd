package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Category;

/**
 * 产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午2:06:26 
 * @history:
 */
public interface ICategoryBO extends IPaginableBO<Category> {

    public boolean isCategoryExist(String code);

    // 分类名称是否存在
    public boolean isCategoryNameExist(String name);

    // 添加分类
    public String saveCategory(String name, Integer level, String type,
            String parentCode, String pic, Integer orderNo, String updater,
            String remark);

    // 修改分类
    public void refreshCategory(String code, String name, Integer level,
            String type, String parentCode, String pic, Integer orderNo,
            String updater, String remark);

    // 上架
    public void refreshPutOnCategory(String code, String updater);

    // 下架
    public void refreshPutOffCategory(String code, String updater);

    public List<Category> queryCategoryList(Category condition);

    public Category getCategoryByName(String name);

    public Category getCategory(String code);

}
