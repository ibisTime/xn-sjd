package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.exception.BizException;

@Service
public class AdoptOrderAOImpl implements IAdoptOrderAO {

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Override
    public String addAdoptOrder(AdoptOrder data) {
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
}
