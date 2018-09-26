package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.exception.BizException;

@Service
public class ApplyBindMaintainAOImpl implements IApplyBindMaintainAO {

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Override
    public String addApplyBindMaintain(ApplyBindMaintain data) {
        return applyBindMaintainBO.saveApplyBindMaintain(data);
    }

    @Override
    public int editApplyBindMaintain(ApplyBindMaintain data) {
        if (!applyBindMaintainBO.isApplyBindMaintainExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return applyBindMaintainBO.refreshApplyBindMaintain(data);
    }

    @Override
    public int dropApplyBindMaintain(String code) {
        if (!applyBindMaintainBO.isApplyBindMaintainExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return applyBindMaintainBO.removeApplyBindMaintain(code);
    }

    @Override
    public Paginable<ApplyBindMaintain> queryApplyBindMaintainPage(int start,
            int limit, ApplyBindMaintain condition) {
        return applyBindMaintainBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition) {
        return applyBindMaintainBO.queryApplyBindMaintainList(condition);
    }

    @Override
    public ApplyBindMaintain getApplyBindMaintain(String code) {
        return applyBindMaintainBO.getApplyBindMaintain(code);
    }
}
