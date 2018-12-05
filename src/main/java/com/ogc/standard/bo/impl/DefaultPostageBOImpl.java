package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IDefaultPostageBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IDefaultPostageDAO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.DefaultPostage;
import com.ogc.standard.enums.EDefaultPostageTypeStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class DefaultPostageBOImpl extends PaginableBOImpl<DefaultPostage>
        implements IDefaultPostageBO {

    @Autowired
    private IDefaultPostageDAO defaultPostageDAO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String saveDefaultPostage(String shopCode) {
        Company shop = companyBO.getCompany(shopCode);

        // 同省
        DefaultPostage sameProvince = new DefaultPostage();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.DEFAULT_POSTAGE.getCode());
        sameProvince.setCode(code);
        sameProvince.setShopCode(shop.getCode());
        sameProvince.setShopName(shop.getName());
        sameProvince.setType(EDefaultPostageTypeStatus.SAME_PROVINCE.getCode());
        sameProvince
            .setName(EDefaultPostageTypeStatus.SAME_PROVINCE.getValue());
        sameProvince.setPrice(BigDecimal.ZERO);
        sameProvince.setUpdateDatetime(new Date());
        sameProvince.setRemark("同省快递默认价格");

        defaultPostageDAO.insert(sameProvince);

        // 跨省
        DefaultPostage difProvince = new DefaultPostage();
        code = OrderNoGenerater
            .generate(EGeneratePrefix.DEFAULT_POSTAGE.getCode());
        difProvince.setCode(code);
        difProvince.setShopCode(shop.getCode());
        difProvince.setShopName(shop.getName());
        difProvince.setType(EDefaultPostageTypeStatus.DIF_PROVINCE.getCode());
        difProvince.setName(EDefaultPostageTypeStatus.DIF_PROVINCE.getValue());
        difProvince.setPrice(BigDecimal.ZERO);
        difProvince.setUpdateDatetime(new Date());
        difProvince.setRemark("跨省快递默认价格");

        defaultPostageDAO.insert(difProvince);
        return code;
    }

    @Override
    public void refreshPrice(String code, BigDecimal price, String updater) {
        DefaultPostage defaultPostage = new DefaultPostage();
        defaultPostage.setCode(code);
        defaultPostage.setPrice(price);
        defaultPostage.setUpdater(updater);
        defaultPostageDAO.updatePrice(defaultPostage);
    }

    @Override
    public List<DefaultPostage> queryDefaultPostageList(
            DefaultPostage condition) {
        return defaultPostageDAO.selectList(condition);
    }

    @Override
    public DefaultPostage getDefaultPostage(String code) {
        DefaultPostage data = null;
        if (StringUtils.isNotBlank(code)) {
            DefaultPostage condition = new DefaultPostage();
            condition.setCode(code);
            data = defaultPostageDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "默认邮费不存在");
            }
        }
        return data;
    }

    @Override
    public DefaultPostage getDefaultPostage(String shopCode, String type) {
        DefaultPostage data = null;
        if (StringUtils.isNotBlank(shopCode)) {
            DefaultPostage condition = new DefaultPostage();
            condition.setShopCode(shopCode);
            condition.setType(type);
            data = defaultPostageDAO.select(condition);
        }
        return data;
    }

}
