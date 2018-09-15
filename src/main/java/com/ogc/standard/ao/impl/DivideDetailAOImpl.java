package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IDivideDetailAO;
import com.ogc.standard.bo.IDivideDetailBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DivideDetail;

@Service
public class DivideDetailAOImpl implements IDivideDetailAO {

    @Autowired
    private IDivideDetailBO divideDetailBO;

    @Override
    public Paginable<DivideDetail> queryDivideDetailPage(int start, int limit,
            DivideDetail condition) {
        return divideDetailBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<DivideDetail> queryDivideDetailList(DivideDetail condition) {
        return divideDetailBO.queryDivideDetailList(condition);
    }

    @Override
    public DivideDetail getDivideDetail(String code) {
        return divideDetailBO.getDivideDetail(code);
    }
}
