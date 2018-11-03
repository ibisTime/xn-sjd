package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IOriginalGroupDAO;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EOriginalGroupStatus;
import com.ogc.standard.exception.BizException;

@Component
public class OriginalGroupBOImpl extends PaginableBOImpl<OriginalGroup>
        implements IOriginalGroupBO {

    @Autowired
    private IOriginalGroupDAO originalGroupDAO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Override
    public String saveOriginalGroup(PresellOrder data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ORIGINAL_GROUP.getCode());
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(data.getProductCode());

        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setOrderCode(data.getCode());
        originalGroup.setProductCode(data.getProductCode());
        originalGroup.setProductName(data.getProductName());

        originalGroup.setOwnerId(data.getApplyUser());
        originalGroup.setPrice(data.getPrice());
        originalGroup.setQuantity(data.getQuantity());
        originalGroup.setUnit(presellProduct.getPackUnit());
        originalGroup.setStatus(EOriginalGroupStatus.TO_ADOPT.getCode());

        originalGroup.setUpdateDatetime(new Date());
        originalGroupDAO.insert(originalGroup);
        return code;
    }

    @Override
    public void refreshQuantity(String code, Integer quantity) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setQuantity(quantity);
        originalGroupDAO.updateQuantity(originalGroup);
    }

    @Override
    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition) {
        return originalGroupDAO.selectList(condition);
    }

    @Override
    public OriginalGroup getOriginalGroup(String code) {
        OriginalGroup data = null;
        if (StringUtils.isNotBlank(code)) {
            OriginalGroup condition = new OriginalGroup();
            condition.setCode(code);
            data = originalGroupDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售原生组不存在");
            }
        }
        return data;
    }

}
