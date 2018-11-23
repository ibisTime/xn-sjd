package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGroupOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGroupOrderDAO;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGroupOrderStatus;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EPresellOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class GroupOrderBOImpl extends PaginableBOImpl<GroupOrder>
        implements IGroupOrderBO {

    @Autowired
    private IGroupOrderDAO groupOrderDAO;

    @Override
    public String saveGroupOrder(DeriveGroup deriveGroup, String applyUser) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.GROUP_ORDER.getCode());
        GroupOrder data = new GroupOrder();

        data.setCode(code);
        data.setGroupCode(deriveGroup.getCode());
        data.setPresellType(deriveGroup.getType());
        data.setOwnerId(deriveGroup.getCreater());
        data.setProductCode(deriveGroup.getProductCode());
        data.setProductName(deriveGroup.getProductName());

        data.setSpecsCode(deriveGroup.getSpecsCode());
        data.setSpecsName(deriveGroup.getSpecsName());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupOrderStatus.TO_PAY.getCode());
        data.setUpdateDatetime(new Date());

        BigDecimal price = deriveGroup.getPrice();
        Integer quantity = deriveGroup.getQuantity();
        data.setPrice(price);
        data.setQuantity(quantity);
        data.setAmount(price.multiply(new BigDecimal(quantity)));

        groupOrderDAO.insert(data);

        return code;
    }

    @Override
    public String saveGroupOrder(DeriveGroup deriveGroup, Integer quantity,
            String applyUser) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.GROUP_ORDER.getCode());
        GroupOrder data = new GroupOrder();

        data.setCode(code);
        data.setGroupCode(deriveGroup.getCode());
        data.setPresellType(deriveGroup.getType());
        data.setOwnerId(deriveGroup.getCreater());
        data.setProductCode(deriveGroup.getProductCode());
        data.setProductName(deriveGroup.getProductName());

        data.setSpecsCode(deriveGroup.getSpecsCode());
        data.setSpecsName(deriveGroup.getSpecsName());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupOrderStatus.TO_PAY.getCode());
        data.setUpdateDatetime(new Date());

        BigDecimal price = deriveGroup.getPrice();
        data.setPrice(price);
        data.setQuantity(quantity);
        data.setAmount(price.multiply(new BigDecimal(quantity)));

        groupOrderDAO.insert(data);

        return code;
    }

    @Override
    public void cancelGrouplOrder(String code, String remark) {
        GroupOrder data = new GroupOrder();

        data.setCode(code);
        data.setStatus(EGroupOrderStatus.CANCELED.getCode());
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        groupOrderDAO.updateCancelOrder(data);
    }

    @Override
    public void payYueSuccess(String code, XN629048Res resultRes,
            BigDecimal backjfAmount) {
        if (StringUtils.isNotBlank(code)) {
            GroupOrder data = new GroupOrder();
            data.setCode(code);
            data.setPayType(EPayType.YE.getCode());
            // data.setCnyDeductAmount(resultRes.getCnyAmount());
            // data.setJfDeductAmount(resultRes.getJfAmount());
            data.setPayAmount(data.getAmount());

            // data.setBackJfAmount(backjfAmount);
            data.setPayDatetime(new Date());
            data.setStatus(EGroupOrderStatus.PAYED.getCode());
            data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
            data.setRemark("余额支付成功");
            groupOrderDAO.updatePayYueSuccess(data);
        }
    }

    @Override
    public void refreshPayGroup(String code, String payType,
            XN629048Res resultRes) {
        if (StringUtils.isNotBlank(code)) {
            GroupOrder data = new GroupOrder();
            data.setCode(code);
            data.setPayType(payType);
            data.setPayGroup(code);

            data.setCnyDeductAmount(resultRes.getCnyAmount());
            data.setJfDeductAmount(resultRes.getJfAmount());
            data.setRemark("预支付发起中");
            groupOrderDAO.updatePayGroup(data);
        }
    }

    @Override
    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount) {
        if (StringUtils.isNotBlank(code)) {
            GroupOrder data = new GroupOrder();
            data.setCode(code);
            data.setPayAmount(payAmount);
            data.setBackJfAmount(backJfAmount);
            data.setStatus(EPresellOrderStatus.PAYED.getCode());
            data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
            data.setRemark("第三方支付成功");
            groupOrderDAO.updatePaySuccess(data);
        }
    }

    @Override
    public List<GroupOrder> queryGroupOrderList(GroupOrder condition) {
        return groupOrderDAO.selectList(condition);
    }

    @Override
    public GroupOrder getUnPayedGroupOrder(String groupCode) {
        GroupOrder data = null;
        if (StringUtils.isNotBlank(groupCode)) {
            GroupOrder condition = new GroupOrder();
            condition.setGroupCode(groupCode);
            condition.setStatus(EGroupOrderStatus.TO_PAY.getCode());
            data = groupOrderDAO.select(condition);
        }
        return data;
    }

    @Override
    public GroupOrder getGroupOrder(String code) {
        GroupOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            GroupOrder condition = new GroupOrder();
            condition.setCode(code);
            data = groupOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "寄售订单不存在");
            }
        }
        return data;
    }

}
