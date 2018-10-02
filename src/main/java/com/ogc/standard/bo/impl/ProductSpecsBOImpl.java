package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IProductSpecsDAO;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.req.XN629010ReqSpecs;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs> implements
        IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Override
    public String saveProductSpecs(String productCode, XN629010ReqSpecs specs) {
        ProductSpecs data = new ProductSpecs();

        String code = OrderNoGenerater.generate(EGeneratePrefix.ProductSpec
            .getCode());
        data.setCode(code);
        data.setProductCode(productCode);
        data.setName(specs.getName());
        data.setPrice(StringValidater.toBigDecimal(specs.getPrice()));

        data.setStartDatetime(DateUtil.strToDate(specs.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setEndDatetime(DateUtil.strToDate(specs.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        productSpecsDAO.insert(data);
        return code;
    }

    @Override
    public void removeProductSpecsByProduct(String productCode) {
        ProductSpecs productSpecs = new ProductSpecs();
        productSpecs.setProductCode(productCode);
        productSpecsDAO.deleteSpecByProduct(productSpecs);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsListByProduct(String productCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setProductCode(productCode);
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        ProductSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecs condition = new ProductSpecs();
            condition.setCode(code);
            data = productSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品规格不存在！");
            }
        }
        return data;
    }

}
