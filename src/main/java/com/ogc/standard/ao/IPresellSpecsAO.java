package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellSpecs;

@Component
public interface IPresellSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 更新上涨价格
    public void doRefreshPrice();

    public Paginable<PresellSpecs> queryPresellSpecsPage(int start, int limit,
            PresellSpecs condition);

    public List<PresellSpecs> queryPresellSpecsList(PresellSpecs condition);

    public PresellSpecs getPresellSpecs(String code);

}
