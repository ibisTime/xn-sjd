package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellOrder;

public interface IOriginalGroupBO extends IPaginableBO<OriginalGroup> {

    public String saveOriginalGroup(PresellOrder data);

    public String saveOriginalGroup(GroupOrder data, String productCode,
            String ownerId, BigDecimal price, Integer quantity);

    // 更新数量
    public void refreshQuantity(String code, Integer quantity);

    // 更新寄售数量
    public void refreshPresellQuantity(String code, Integer presellQuantity);

    // 更新提货中数量
    public void refreshReceivingQuantity(String code,
            Integer receivingQuantity);

    // 开始认养
    public void refreshStartAdopt(String code);

    // 结束认养
    public void refreshEndAdopt(String code);

    // 收货
    public void refreshReceive(String code);

    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition);

    public OriginalGroup getOriginalGroup(String code);

    public OriginalGroup getOriginalGroupByOrder(String orderCode);

}
