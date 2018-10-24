package com.ogc.standard.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGroupAdoptOrderDAO;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class GroupAdoptOrderBOImpl extends PaginableBOImpl<GroupAdoptOrder>
        implements IGroupAdoptOrderBO {

    @Autowired
    private IGroupAdoptOrderDAO groupAdoptOrderDAO;

    @Override
    public String saveGroupAdoptOrderFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.GROUP_ADOPT_ORDER.getCode());

        data.setCode(code);
        data.setIdentifyCode(identifyCode);
        data.setProductCode(product.getCode());
        data.setProductSpecsCode(productSpecs.getCode());
        data.setPrice(productSpecs.getPrice());

        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(quantity);
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(userId);

        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        groupAdoptOrderDAO.insertFirst(data);

        return code;

    }

    @Override
    public String saveGroupAdoptOrderUnFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.GROUP_ADOPT_ORDER.getCode());
        data.setCode(code);
        data.setIdentifyCode(identifyCode);
        data.setProductCode(product.getCode());
        data.setProductSpecsCode(productSpecs.getCode());
        data.setPrice(productSpecs.getPrice());

        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(quantity);
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(userId);

        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        groupAdoptOrderDAO.insertFirst(data);
        return code;
    }

    @Override
    public void refreshCancelOrder(String code, String remark) {
        if (StringUtils.isNotBlank(code)) {
            GroupAdoptOrder data = new GroupAdoptOrder();
            data.setCode(code);
            data.setStatus(EGroupAdoptOrderStatus.CANCELED.getCode());
            data.setRemark(remark);
            data.setUpdateDatetime(new Date());
            groupAdoptOrderDAO.updateCancelGroupAdoptOrder(data);
        }
    }

    @Override
    public void payGroupAdoptOrder(GroupAdoptOrder data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            groupAdoptOrderDAO.updatePayGroupAdoptOrder(data);
        }
    }

    @Override
    public List<GroupAdoptOrder> getGroupAdoptOrderById(String identifyCode) {
        List<GroupAdoptOrder> list = new ArrayList<GroupAdoptOrder>();
        if (StringUtils.isNotBlank(identifyCode)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setIdentifyCode(identifyCode);
            list = groupAdoptOrderDAO.selectList(condition);
        }
        return list;
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition) {
        return groupAdoptOrderDAO.selectList(condition);
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrder(String code) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setCode(code);
            data = groupAdoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "集体订单编号不存在");
            }
        }
        return data;
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrderByIdentifyCode(
            String identifyCode) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(identifyCode)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setIdentifyCode(identifyCode);
            data = groupAdoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "识别码不存在");
            }
        }
        return data;
    }

}
