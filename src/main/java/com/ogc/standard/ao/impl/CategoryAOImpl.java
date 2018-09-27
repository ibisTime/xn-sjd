package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Category;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.exception.BizException;

/**
 * 产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午2:26:42 
 * @history:
 */
@Service
public class CategoryAOImpl implements ICategoryAO {

    @Autowired
    private ICategoryBO categoryBO;

    @Override
    public String addCategory(String name, String parentCode, String pic,
            Integer orderNo, String updater, String remark) {
        if (categoryBO.isCategoryNameExist(name)) {
            throw new BizException("xn0000", "产品分类名称已存在，请重新输入！");
        }

        if (StringUtils.isNotBlank(parentCode)) {
            Category parentCategory = categoryBO.getCategory(parentCode);

            if (ECategoryStatus.PUT_OFF.getCode()
                .equals(parentCategory.getStatus())) {
                throw new BizException("xn0000", "父类产品分类已下架，请重新选择！");
            }
        }

        return categoryBO.saveCategory(name, parentCode, pic, orderNo, updater,
            remark);
    }

    @Override
    public void editCategory(String code, String name, String parentCode,
            String pic, Integer orderNo, String updater, String remark) {
        Category category = categoryBO.getCategory(code);
        if (ECategoryStatus.PUT_ON.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已上架，无法修改！");
        }

        if (StringUtils.isNotBlank(parentCode)) {
            Category parentCategory = categoryBO.getCategory(parentCode);

            if (ECategoryStatus.PUT_OFF.getCode()
                .equals(parentCategory.getStatus())) {
                throw new BizException("xn0000", "父类产品分类已下架，请重新选择！");
            }
        }

        categoryBO.refreshCategory(code, name, parentCode, pic, orderNo,
            updater, remark);
    }

    @Override
    public void putOnCategory(String code, String updater) {
        Category category = categoryBO.getCategory(code);
        if (ECategoryStatus.PUT_ON.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已上架，无法重复上架！");
        }

        categoryBO.refreshPutOnCategory(code, updater);
    }

    @Override
    public void putOffCategory(String code, String updater) {
        Category category = categoryBO.getCategory(code);
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "产品分类已下架，无法重复下架！");
        }

        categoryBO.refreshPutOffCategory(code, updater);
    }

    @Override
    public Paginable<Category> queryCategoryPage(int start, int limit,
            Category condition) {
        return categoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Category> queryCategoryList(Category condition) {
        return categoryBO.queryCategoryList(condition);
    }

    @Override
    public Category getCategory(String code) {
        return categoryBO.getCategory(code);
    }

}
