package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.req.XN629030Req;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.exception.BizException;

@Service
public class AdoptOrderAOImpl implements IAdoptOrderAO {

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Override
    public String addAdoptOrder(XN629030Req req) {
        AdoptOrder data = new AdoptOrder();
        data.setType(req.getType());
        // TODO 产品规格待落地
        data.setProductCode(req.getProductCode());
        data.setQuantity(req.getQuantity());
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
        if (EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            data.setStatus(EAdoptOrderStatus.CANCELED.getCode());
            data.setUpdateDatetime(new Date());
            adoptOrderBO.refreshAdoptOrder(data);
        }
    }

    @Override
    public void payAdoptOrder(String code) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            // TODO 根据订单里的产品编号找到认养开始时间
            if (true) {// 支付时间小于认养开始时间
                data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
            } else {
                data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
            }

        }
    }
}
