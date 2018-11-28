package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGroupAdoptOrderSettleStatus;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

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
        data.setProductSpecsName(productSpecs.getName());
        data.setPrice(productSpecs.getPrice());

        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(quantity);
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(userId);

        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        data.setSettleStatus(EGroupAdoptOrderSettleStatus.NO_SETTLE.getCode());
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
        data.setProductSpecsName(productSpecs.getName());
        data.setPrice(productSpecs.getPrice());

        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(quantity);
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(userId);

        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        data.setSettleStatus(EGroupAdoptOrderSettleStatus.NO_SETTLE.getCode());
        groupAdoptOrderDAO.insertFirst(data);

        return code;
    }

    @Override
    public void refreshCancelOrder(GroupAdoptOrder data, String remark) {
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setStatus(EGroupAdoptOrderStatus.CANCELED.getCode());
            data.setUpdater(data.getApplyUser());
            data.setRemark(remark);
            data.setUpdateDatetime(new Date());
            groupAdoptOrderDAO.updateCancelGroupAdoptOrder(data);
        }
    }

    @Override
    public void refreshPayGroup(GroupAdoptOrder data, String payType,
            XN629048Res resultRes) {
        data.setPayType(payType);
        data.setPayGroup(data.getCode());
        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setRemark("预支付发起中");
        groupAdoptOrderDAO.updatePayGroup(data);
    }

    @Override
    public void payYueSuccess(GroupAdoptOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount) {

        data.setStatus(EGroupAdoptOrderStatus.PAYED.getCode());
        data.setPayType(EPayType.YE.getCode());
        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setPayAmount(data.getAmount().subtract(resultRes.getCnyAmount()));
        data.setPayDatetime(new Date());

        data.setBackJfAmount(backjfAmount);
        data.setSettleStatus(EGroupAdoptOrderSettleStatus.TO_SETTLE.getCode());
        data.setUpdateDatetime(new Date());
        data.setUpdater(data.getApplyUser());
        data.setRemark("余额支付成功");
        groupAdoptOrderDAO.updatePayYueSuccess(data);
    }

    @Override
    public void paySuccess(GroupAdoptOrder data, BigDecimal payAmount,
            BigDecimal backJfAmount) {
        data.setStatus(EGroupAdoptOrderStatus.PAYED.getCode());
        data.setPayAmount(payAmount);
        data.setPayDatetime(new Date());
        data.setBackJfAmount(backJfAmount);
        data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
        data.setRemark("第三方支付成功");
        groupAdoptOrderDAO.updatePaySuccess(data);
    }

    @Override
    public void refreshFullOrder(String code) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setCode(code);
        data.setStatus(EGroupAdoptOrderStatus.FULL.getCode());
        groupAdoptOrderDAO.updateFullOrder(data);
    }

    @Override
    public void refreshUnFullOrder(String code) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setCode(code);
        data.setStatus(EGroupAdoptOrderStatus.UNFULL.getCode());
        groupAdoptOrderDAO.updateUnFullOrder(data);
    }

    @Override
    public void refreshFullOrderById(String id) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setIdentifyCode(id);
        data.setStatus(EGroupAdoptOrderStatus.FULL.getCode());
        groupAdoptOrderDAO.updateFullOrderById(data);
    }

    @Override
    public void refreshUnFullOrderById(String id) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setIdentifyCode(id);
        data.setStatus(EGroupAdoptOrderStatus.UNFULL.getCode());
        groupAdoptOrderDAO.updateUnFullOrderById(data);
    }

    @Override
    public void refreshStartAdopt(String code) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setCode(code);
        data.setStatus(EGroupAdoptOrderStatus.ADOPT.getCode());
        data.setRemark("认养中");
        data.setUpdateDatetime(new Date());
        groupAdoptOrderDAO.updateStartAdopt(data);
    }

    @Override
    public void refreshEndAdopt(String code) {
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setCode(code);
        data.setStatus(EGroupAdoptOrderStatus.END.getCode());
        data.setRemark("已到期");
        data.setUpdateDatetime(new Date());
        groupAdoptOrderDAO.updateEndAdopt(data);
    }

    @Override
    public void refreshSettle(GroupAdoptOrder data, String approveResult,
            String updater, String remark) {
        String status = EGroupAdoptOrderSettleStatus.NO_SETTLE.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EGroupAdoptOrderSettleStatus.SETTLE.getCode();
        }

        data.setSettleStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark("已完成结算处理");
        groupAdoptOrderDAO.updateSettleStatus(data);
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderById(String identifyCode) {
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
    public GroupAdoptOrder getFirstPayedOrderById(String identifyCode) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(identifyCode)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setIdentifyCode(identifyCode);
            condition.setStatus(EGroupAdoptOrderStatus.PAYED.getCode());
            condition.setOrder("apply_datetime", "asc");

            List<GroupAdoptOrder> groupAdoptOrderList = groupAdoptOrderDAO
                .selectList(condition);
            if (CollectionUtils.isNotEmpty(groupAdoptOrderList)) {
                data = groupAdoptOrderList.get(0);
            }
        }
        return data;
    }

    @Override
    public long getPayedTotalQuantity(String id) {
        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setIdentifyCode(id);
        condition.setStatus(EGroupAdoptOrderStatus.PAYED.getCode());
        return groupAdoptOrderDAO.selectPayedTotalQuantity(condition);
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrderByPayGroup(String payGroup) {
        GroupAdoptOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            GroupAdoptOrder condition = new GroupAdoptOrder();
            condition.setPayGroup(payGroup);
            List<GroupAdoptOrder> list = groupAdoptOrderDAO
                .selectList(condition);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "根据" + payGroup + "查询订单不存在");
            }
            data = list.get(0);
        }
        return data;
    }

}
