package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OriginalGroup;

@Component
public interface IOriginalGroupAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 定向转让
    public void directSales(String code, String userId, BigDecimal price,
            Integer quantity);

    // 二维码转让
    public void qrSales(String code, BigDecimal price, Integer quantity);

    // 挂单寄售
    public void publicSales(String code, BigDecimal price, Integer quantity);

    public Paginable<OriginalGroup> queryOriginalGroupPage(int start, int limit,
            OriginalGroup condition);

    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition);

    public OriginalGroup getOriginalGroup(String code);

}
