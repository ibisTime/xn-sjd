package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICategoryDAO;
import com.ogc.standard.domain.Category;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class CategoryBOImpl extends PaginableBOImpl<Category>
        implements ICategoryBO {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    public boolean isCategoryExist(String code) {
        Category condition = new Category();
        condition.setCode(code);
        if (categoryDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCategoryNameExist(String name) {
        Category condition = new Category();
        condition.setName(name);
        if (categoryDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCategory(String name, String parentCode, String pic,
            Integer orderNo, String updater, String remark) {
        Category data = new Category();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Category.getCode());
        data.setCode(code);
        data.setName(name);
        data.setParentCode(parentCode);
        data.setPic(pic);
        data.setOrderNo(orderNo);

        data.setStatus(ECategoryStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        categoryDAO.insert(data);
        return code;
    }

    @Override
    public void refreshCategory(String code, String name, String parentCode,
            String pic, Integer orderNo, String updater, String remark) {
        Category data = new Category();

        data.setCode(code);
        data.setName(name);
        data.setParentCode(parentCode);
        data.setPic(pic);
        data.setOrderNo(orderNo);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        categoryDAO.updateCategory(data);
    }

    @Override
    public void refreshPutOnCategory(String code, String updater) {
        Category data = new Category();

        data.setCode(code);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setStatus(ECategoryStatus.PUT_ON.getCode());
        categoryDAO.updatePutOn(data);
    }

    @Override
    public void refreshPutOffCategory(String code, String updater) {
        Category data = new Category();

        data.setCode(code);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setStatus(ECategoryStatus.PUT_OFF.getCode());
        categoryDAO.updatePutOff(data);
    }

    @Override
    public List<Category> queryCategoryList(Category condition) {
        return categoryDAO.selectList(condition);
    }

    @Override
    public Category getCategory(String code) {
        Category data = null;
        if (StringUtils.isNotBlank(code)) {
            Category condition = new Category();
            condition.setCode(code);
            data = categoryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品分类不存在！");
            }
        }
        return data;
    }

}
