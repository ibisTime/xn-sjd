package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.req.XN629050Req;
import com.ogc.standard.dto.req.XN629051Req;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.exception.BizException;

@Service
public class GroupAdoptOrderAOImpl implements IGroupAdoptOrderAO {

    @Autowired
    private IGroupAdoptOrderBO groupAdoptOrderBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String addGroupAdoptOrder(XN629050Req req) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setProductCode(req.getProductCode());
        ProductSpecs productSpecs = productSpecsBO.getProductSpecs(req
            .getSpecsCode());
        data.setProductSpecsName(productSpecs.getName());
        data.setPrice(productSpecs.getPrice());
        data.setYear(productSpecs.getYear());
        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(req.getQuantity());
        data.setAmount(data.getPrice()
                * StringValidater.toInteger(data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        data.setUpdater(req.getUserId());
        data.setUpdateDatetime(new Date());
        data.setIdentifyCode(UUID.randomUUID().toString().hashCode() + "");// 暂定
        groupAdoptOrderBO.saveGroupAdoptOrder(data);
        return data.getIdentifyCode();
    }

    @Override
    public String unFirstAddGroupAdoptOrder(XN629051Req req) {
        GroupAdoptOrder order = groupAdoptOrderBO
            .getGroupAdoptOrderByIdentifyCode(req.getIdentifyCode());
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setIdentifyCode(order.getIdentifyCode());
        data.setProductCode(order.getProductCode());
        data.setProductSpecsName(order.getProductSpecsName());
        data.setPrice(order.getPrice());
        data.setYear(order.getYear());
        data.setStartDatetime(order.getStartDatetime());
        data.setEndDatetime(order.getEndDatetime());
        data.setQuantity(req.getQuantity());
        data.setAmount(data.getPrice()
                * StringValidater.toInteger(data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        data.setUpdater(req.getUserId());
        data.setUpdateDatetime(new Date());
        return groupAdoptOrderBO.saveGroupAdoptOrder(data);
    }

    @Override
    public void cancelGroupAdoptOrder(String code) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);
        if (EGroupAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            data.setStatus(EGroupAdoptOrderStatus.CANCELED.getCode());
            data.setUpdateDatetime(new Date());
            groupAdoptOrderBO.refreshGroupAdoptOrder(data);
        }

    }

    @Override
    public int editGroupAdoptOrder(GroupAdoptOrder data) {
        if (!groupAdoptOrderBO.isGroupAdoptOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return groupAdoptOrderBO.refreshGroupAdoptOrder(data);
    }

    @Override
    public int dropGroupAdoptOrder(String code) {
        if (!groupAdoptOrderBO.isGroupAdoptOrderExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return groupAdoptOrderBO.removeGroupAdoptOrder(code);
    }

    @Override
    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition) {
        return groupAdoptOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition) {
        return groupAdoptOrderBO.queryGroupAdoptOrderList(condition);
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrder(String code) {
        return groupAdoptOrderBO.getGroupAdoptOrder(code);
    }

}
