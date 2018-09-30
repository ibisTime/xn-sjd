package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IProductDAO;
import com.ogc.standard.domain.Product;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public boolean isProductExist(String code) {
        Product condition = new Product();
        condition.setCode(code);
        if (productDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveProduct(Product data) {
        productDAO.insert(data);
    }

    @Override
    public void refreshProduct(Product data) {
        productDAO.updateProduct(data);
    }

    @Override
    public void refreshSubmitProduct(String code, String updater, String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.TO_APPROVE.getCode());
        product.setUpdater(updater);
        product.setUpdateDatetime(new Date());
        product.setRemark(remark);

        productDAO.updateSubmitProduct(product);
    }

    @Override
    public void refreshApproveProduct(String code, String status,
            String updater, String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(status);
        product.setUpdater(updater);
        product.setRemark(remark);
        product.setUpdateDatetime(new Date());

        productDAO.updateApproveProduct(product);
    }

    @Override
    public void refreshPutOnProduct(String code, String location,
            Integer orderNo, String updater, String remark) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.TO_ADOPT.getCode());
        product.setLocation(location);
        product.setOrderNo(orderNo);
        product.setUpdater(updater);

        product.setRemark(remark);
        product.setUpdateDatetime(new Date());
        productDAO.updatePutOnProduct(product);
    }

    @Override
    public void refreshLoakProduct(String code) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.LOCKED.getCode());

        productDAO.updateLockProduct(product);
    }

    @Override
    public void refreshNowCount(String code, Integer nowCount) {
        Product product = new Product();

        product.setCode(code);
        product.setNowCount(nowCount);

        productDAO.updateNowCount(product);
    }

    @Override
    public void refreshAdoptProduct(String code) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.TO_PUTOFF.getCode());

        productDAO.updateAdoptProduct(product);
    }

    @Override
    public void refreshPutOffProduct(String code, String updater) {
        Product product = new Product();

        product.setCode(code);
        product.setStatus(EProductStatus.PUTOFFED.getCode());
        product.setUpdater(updater);
        product.setUpdateDatetime(new Date());

        productDAO.updatePutOffProduct(product);
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productDAO.selectList(condition);
    }

    @Override
    public Product getProduct(String code) {
        Product data = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            data = productDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "认养产品不存在！");
            }
        }
        return data;
    }

}
