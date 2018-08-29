package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IExchangePairBO;
import com.ogc.standard.bo.base.Page;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IExchangePairDAO;
import com.ogc.standard.domain.ExchangePair;

@Component
public class ExchangePairBOImpl extends PaginableBOImpl<ExchangePair>
        implements IExchangePairBO {

    @Autowired
    private IExchangePairDAO exchangePairDAO;

    @Override
    public List<ExchangePair> queryExchangePairList(ExchangePair condition) {
        return exchangePairDAO.selectList(condition);
    }

    @Override
    public Paginable<ExchangePair> queryExchangePairShowPage(int start,
            int pageSize, ExchangePair condition) {
        prepare(condition);
        long totalCount = exchangePairDAO.selectTotalCount(condition);
        Paginable<ExchangePair> page = new Page<ExchangePair>(start, pageSize,
            totalCount);
        List<ExchangePair> dataList = exchangePairDAO.selectListShow(condition,
            page.getStart(), page.getPageSize());
        page.setList(dataList);
        return page;
    }

    @Override
    public void updatePrice(ExchangePair data) {
        if (data != null) {
            exchangePairDAO.updatePrice(data);
        }
    }

}
