package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPresellOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IPresellOrderDAO;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.PresellSpecs;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EPresellOrderSettleStatus;
import com.ogc.standard.enums.EPresellOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class PresellOrderBOImpl extends PaginableBOImpl<PresellOrder>
        implements IPresellOrderBO {

    @Autowired
    private IPresellOrderDAO presellOrderDAO;

    @Override
    public String savePresellOrder(String userId, PresellProduct presellProduct,
            PresellSpecs presellSpecs, Integer quantity) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PRESELL_ORDER.getCode());
        PresellOrder data = new PresellOrder();

        data.setCode(code);
        data.setProductCode(presellProduct.getCode());
        data.setProductName(presellProduct.getName());
        data.setSpecsCode(presellSpecs.getCode());
        data.setSpecsName(presellSpecs.getName());

        data.setPrice(presellSpecs.getPrice());
        data.setQuantity(quantity);
        data.setAmount(
            presellSpecs.getPrice().multiply(new BigDecimal(quantity)));
        data.setApplyUser(userId);
        data.setApplyDatetime(new Date());

        data.setStatus(EPresellOrderStatus.TO_PAY.getCode());
        data.setSettleStatus(EPresellOrderSettleStatus.NO_SETTLE.getCode());
        presellOrderDAO.insert(data);
        return code;
    }

    @Override
    public void cancelPresellOrder(String code, String remark) {
        if (StringUtils.isNotBlank(code)) {
            PresellOrder data = new PresellOrder();
            data.setCode(code);
            data.setStatus(EPresellOrderStatus.CANCELED.getCode());
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            presellOrderDAO.updateCancelOrder(data);
        }
    }

    @Override
    public void payYueSuccess(String code, XN629048Res resultRes,
            BigDecimal backjfAmount) {
        if (StringUtils.isNotBlank(code)) {
            PresellOrder data = new PresellOrder();
            data.setCode(code);
            data.setPayType(EPayType.YE.getCode());
            data.setCnyDeductAmount(resultRes.getCnyAmount());
            data.setJfDeductAmount(resultRes.getJfAmount());
            data.setPayAmount(
                data.getAmount().subtract(resultRes.getCnyAmount()));

            data.setBackJfAmount(backjfAmount);
            data.setPayDatetime(new Date());
            data.setStatus(EPresellOrderStatus.PAYED.getCode());
            data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
            data.setRemark("余额支付成功");
            presellOrderDAO.updatePayYueSuccess(data);
        }
    }

    @Override
    public void refreshPayGroup(String code, String payType,
            XN629048Res resultRes) {
        if (StringUtils.isNotBlank(code)) {
            PresellOrder data = new PresellOrder();
            data.setCode(code);
            data.setPayType(payType);
            data.setPayGroup(code);

            data.setCnyDeductAmount(resultRes.getCnyAmount());
            data.setJfDeductAmount(resultRes.getJfAmount());
            data.setRemark("预支付发起中");
            presellOrderDAO.updatePayGroup(data);
        }
    }

    @Override
    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount) {
        if (StringUtils.isNotBlank(code)) {
            PresellOrder data = new PresellOrder();
            data.setCode(code);
            data.setPayAmount(payAmount);
            data.setBackJfAmount(backJfAmount);
            data.setStatus(EPresellOrderStatus.PAYED.getCode());
            data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
            data.setRemark("第三方支付成功");
            presellOrderDAO.updatePaySuccess(data);
        }
    }

    @Override
    public List<PresellOrder> queryPresellOrderList(PresellOrder condition) {
        return presellOrderDAO.selectList(condition);
    }

    @Override
    public PresellOrder getPresellOrder(String code) {
        PresellOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            PresellOrder condition = new PresellOrder();
            condition.setCode(code);
            data = presellOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售订单不存在");
            }
        }
        return data;
    }

}
