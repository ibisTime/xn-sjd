package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellLogistics;

@Component
public interface IPresellLogisticsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 发货
    public void send(String code, String deliver, String logisticsCompany,
            String logisticsNumber);

    // 确认收货
    public void confirmReceive(String code);

    // 查询物流
    public Object trackQuery(String expCode, String expNo);

    public Paginable<PresellLogistics> queryPresellLogisticsPage(int start,
            int limit, PresellLogistics condition);

    public List<PresellLogistics> queryPresellLogisticsList(
            PresellLogistics condition);

    public PresellLogistics getPresellLogistics(String code);

}
