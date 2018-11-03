package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OriginalGroup;

@Service
public class OriginalGroupAOImpl implements IOriginalGroupAO {

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Override
    public void directSales(String code, String userId, BigDecimal price,
            Integer quantity) {

    }

    @Override
    public void qrSales(String code, BigDecimal price, Integer quantity) {

    }

    @Override
    public void publicSales(String code, BigDecimal price, Integer quantity) {

    }

    @Override
    public Paginable<OriginalGroup> queryOriginalGroupPage(int start, int limit,
            OriginalGroup condition) {
        return originalGroupBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition) {
        return originalGroupBO.queryOriginalGroupList(condition);
    }

    @Override
    public OriginalGroup getOriginalGroup(String code) {
        return originalGroupBO.getOriginalGroup(code);
    }

}
