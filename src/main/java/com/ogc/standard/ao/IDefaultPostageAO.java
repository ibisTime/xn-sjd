package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DefaultPostage;

@Component
public interface IDefaultPostageAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void editDefaultPostage(String code, BigDecimal price,
            String updater);

    public Paginable<DefaultPostage> queryDefaultPostagePage(int start,
            int limit, DefaultPostage condition);

    public List<DefaultPostage> queryDefaultPostageList(
            DefaultPostage condition);

    public DefaultPostage getDefaultPostage(String code);

}
