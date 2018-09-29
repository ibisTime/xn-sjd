package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.req.XN629040Req;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.exception.BizException;

@Service
public class AdoptOrderAOImpl implements IAdoptOrderAO {

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String addAdoptOrder(XN629040Req req) {
        AdoptOrder data = new AdoptOrder();
        data.setType(req.getType());
        data.setProductCode(req.getProductCode());
        ProductSpecs specs = productSpecsBO.getProductSpecs(req.getSpecsCode());
        data.setProductSpecsName(specs.getName());
        data.setPrice(specs.getPrice());
        data.setYear(specs.getYear());
        data.setStartDatetime(specs.getStartDatetime());
        data.setEndDatetime(specs.getEndDatetime());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EAdoptOrderStatus.TO_PAY.getCode());
        return adoptOrderBO.saveAdoptOrder(data);
    }

    @Override
    public int editAdoptOrder(AdoptOrder data) {
        if (!adoptOrderBO.isAdoptOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return adoptOrderBO.refreshAdoptOrder(data);
    }

    @Override
    public int dropAdoptOrder(String code) {
        if (!adoptOrderBO.isAdoptOrderExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return adoptOrderBO.removeAdoptOrder(code);
    }

    @Override
    public Paginable<AdoptOrder> queryAdoptOrderPage(int start, int limit,
            AdoptOrder condition) {
        return adoptOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition) {
        return adoptOrderBO.queryAdoptOrderList(condition);
    }

    @Override
    public AdoptOrder getAdoptOrder(String code) {
        return adoptOrderBO.getAdoptOrder(code);
    }

    @Override
    public void cancelAdoptOrder(String code) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能取消");
        }
        data.setStatus(EAdoptOrderStatus.CANCELED.getCode());
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        adoptOrderBO.cancelAdoptOrder(data);
    }

    @Override
    public void payAdoptOrder(String code, String payType) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能支付");
        }
        // TODO 掉支付接口返回信息填充
        data.setPayType(payType);
        data.setPayGroup("");
        data.setPayCode("");
        data.setPayAmount(0l);
        // TODO 积分账户查询余额 抵扣 默认抵扣？选择抵扣?
        data.setJfDeductAmount(0l);
        data.setPayDatetime(new Date());
        data.setBackJfAmount(0l);
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        if (data.getPayDatetime().getTime() < data.getStartDatetime().getTime()) {// 支付时间小于认养开始时间
            data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        }
        adoptOrderBO.payAdoptOrder(data);
    }
}
