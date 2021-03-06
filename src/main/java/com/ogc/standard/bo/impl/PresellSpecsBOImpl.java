package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IPresellSpecsDAO;
import com.ogc.standard.domain.PresellSpecs;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class PresellSpecsBOImpl extends PaginableBOImpl<PresellSpecs>
        implements IPresellSpecsBO {

    @Autowired
    private IPresellSpecsDAO presellSpecsDAO;

    @Override
    public String savePresellSpecs(String productCode, String name,
            String packCount, String price, String increase, String interval) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PRESELL_SPECS.getCode());
        PresellSpecs data = new PresellSpecs();

        data.setCode(code);
        data.setProductCode(productCode);
        data.setName(name);
        data.setPackCount(StringValidater.toInteger(packCount));
        data.setPrice(new BigDecimal(price));

        data.setIncrease(StringValidater.toDouble(increase));
        data.setIntervalHours(StringValidater.toInteger(interval));
        presellSpecsDAO.insert(data);
        return code;
    }

    @Override
    public void removePresellSpecsByProduct(String productCode) {
        PresellSpecs condition = new PresellSpecs();
        condition.setProductCode(productCode);
        presellSpecsDAO.delete(condition);
    }

    @Override
    public void refreshPresellSpecsPackCount(String code, Integer packCount) {
        PresellSpecs condition = new PresellSpecs();
        condition.setCode(code);
        condition.setPackCount(packCount);
        presellSpecsDAO.updatePackCount(condition);
    }

    @Override
    public void refreshPresellSpecsPrice(String code, BigDecimal price) {
        PresellSpecs condition = new PresellSpecs();
        condition.setCode(code);
        condition.setPrice(price);
        presellSpecsDAO.updatePrice(condition);
    }

    @Override
    public void refreshNowInterval(String code, Integer nowInterval) {
        PresellSpecs condition = new PresellSpecs();
        condition.setCode(code);
        condition.setNowInterval(nowInterval);
        presellSpecsDAO.updateNowInterval(condition);
    }

    @Override
    public List<PresellSpecs> queryPresellSpecsListByProduct(
            String productCode) {
        PresellSpecs condition = new PresellSpecs();
        condition.setProductCode(productCode);
        return presellSpecsDAO.selectList(condition);
    }

    @Override
    public List<PresellSpecs> queryPresellSpecsList(PresellSpecs condition) {
        return presellSpecsDAO.selectList(condition);
    }

    @Override
    public PresellSpecs getPresellSpecs(String code) {
        PresellSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            PresellSpecs condition = new PresellSpecs();
            condition.setCode(code);
            data = presellSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售规格不存在");
            }
        }
        return data;
    }

}
