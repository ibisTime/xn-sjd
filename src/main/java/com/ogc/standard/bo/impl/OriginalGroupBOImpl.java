package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IOriginalGroupDAO;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.PresellSpecs;
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

    @Autowired
    private IPresellSpecsBO presellSpecsBO;

    @Override
    public String saveOriginalGroup(PresellOrder data) {
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(data.getProductCode());
        PresellSpecs presellSpecs = presellSpecsBO
            .getPresellSpecs(data.getSpecsCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ORIGINAL_GROUP.getCode());

        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setOrderCode(data.getCode());
        originalGroup.setBelongPartId(presellProduct.getOwnerId());
        originalGroup.setProductCode(data.getProductCode());
        originalGroup.setProductName(data.getProductName());

        originalGroup.setSpecsCode(data.getSpecsCode());
        originalGroup.setSpecsName(data.getSpecsName());
        originalGroup.setOwnerId(data.getApplyUser());
        originalGroup.setPrice(data.getPrice());
        originalGroup
            .setQuantity(data.getQuantity() * presellSpecs.getPackCount());
        originalGroup.setUnit(presellProduct.getPackUnit());

        Date nowDate = new Date();
        if (nowDate.before(presellProduct.getAdoptStartDatetime())) {
            originalGroup.setStatus(EOriginalGroupStatus.TO_ADOPT.getCode());
        } else {
            originalGroup.setStatus(EOriginalGroupStatus.ADOPTING.getCode());
        }

        originalGroup
            .setAdoptStartDatetime(presellProduct.getAdoptStartDatetime());
        originalGroup.setAdoptEndDatetime(presellProduct.getDeliverDatetime());
        originalGroup.setDeliverDatetime(presellProduct.getDeliverDatetime());
        originalGroup.setUpdateDatetime(new Date());

        originalGroupDAO.insert(originalGroup);
        return code;
    }

    @Override
    public String saveOriginalGroup(GroupOrder groupOrder, String productCode,
            String ownerId, BigDecimal price, Integer quantity) {
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(productCode);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ORIGINAL_GROUP.getCode());

        OriginalGroup data = new OriginalGroup();
        data.setCode(code);
        data.setOrderCode(groupOrder.getCode());
        data.setBelongPartId(presellProduct.getOwnerId());
        data.setProductCode(presellProduct.getCode());
        data.setProductName(presellProduct.getName());

        data.setSpecsCode(groupOrder.getSpecsCode());
        data.setSpecsName(groupOrder.getSpecsName());
        data.setOwnerId(ownerId);

        data.setPrice(price);
        data.setQuantity(quantity);
        data.setPresellQuantity(0);
        data.setUnit(presellProduct.getPackUnit());

        Date nowDate = new Date();
        if (nowDate.before(presellProduct.getAdoptStartDatetime())) {
            data.setStatus(EOriginalGroupStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EOriginalGroupStatus.ADOPTING.getCode());
        }

        data.setAdoptStartDatetime(presellProduct.getAdoptStartDatetime());
        data.setAdoptEndDatetime(presellProduct.getDeliverDatetime());
        data.setDeliverDatetime(presellProduct.getDeliverDatetime());
        data.setUpdateDatetime(new Date());

        originalGroupDAO.insert(data);
        return code;
    }

    @Override
    public void refreshQuantityPrice(String code, Integer quantity,
            BigDecimal price) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setQuantity(quantity);
        originalGroup.setPrice(price);
        originalGroupDAO.updateQuantityPrice(originalGroup);
    }

    @Override
    public void refreshQuantity(String code, Integer quantity) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setQuantity(quantity);
        originalGroupDAO.updateQuantity(originalGroup);
    }

    @Override
    public void refreshPresellQuantity(String code, Integer presellQuantity) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setPresellQuantity(presellQuantity);
        originalGroupDAO.updatePresellQuantity(originalGroup);
    }

    @Override
    public void refreshReceivingQuantity(String code,
            Integer receivingQuantity) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setReceivingQuantity(receivingQuantity);
        originalGroupDAO.updateReceivingQuantity(originalGroup);
    }

    @Override
    public void refreshReceivedQuantity(String code, Integer receivedQuantity) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setReceivedQuantity(receivedQuantity);
        originalGroupDAO.updateReceivedQuantity(originalGroup);
    }

    @Override
    public void refreshStartAdopt(String code) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setUpdateDatetime(new Date());
        originalGroup.setStatus(EOriginalGroupStatus.ADOPTING.getCode());
        originalGroupDAO.updateStartAdopt(originalGroup);
    }

    @Override
    public void refreshEndAdopt(String code) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setUpdateDatetime(new Date());
        originalGroup.setStatus(EOriginalGroupStatus.TO_RECEIVE.getCode());
        originalGroupDAO.updateEndAdopt(originalGroup);
    }

    @Override
    public void refreshReceive(String code) {
        OriginalGroup originalGroup = new OriginalGroup();
        originalGroup.setCode(code);
        originalGroup.setUpdateDatetime(new Date());
        originalGroup.setStatus(EOriginalGroupStatus.RECEIVED.getCode());
        originalGroupDAO.updateReceiveOrignalGroup(originalGroup);
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

    @Override
    public OriginalGroup getOriginalGroupByOrder(String orderCode) {
        OriginalGroup data = null;
        if (StringUtils.isNotBlank(orderCode)) {
            OriginalGroup condition = new OriginalGroup();
            condition.setOrderCode(orderCode);
            data = originalGroupDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<OriginalGroup> queryOriginalGroup(String ownerId,
            String productCode) {
        List<OriginalGroup> list = new ArrayList<OriginalGroup>();
        if (StringUtils.isNotBlank(ownerId)) {

            OriginalGroup condition = new OriginalGroup();
            condition.setOwnerId(ownerId);
            condition.setProductCode(productCode);
            list = originalGroupDAO.selectList(condition);
        }
        return list;
    }

}
