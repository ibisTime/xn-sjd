package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellOrder;

public interface IOriginalGroupBO extends IPaginableBO<OriginalGroup> {

    public String saveOriginalGroup(PresellOrder data);

    public String saveOriginalGroup(String parentCode, String ownerId,
            BigDecimal price, Integer quantity);

    // 更新数量
    public void refreshQuantity(String code, Integer quantity);

    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition);

    public OriginalGroup getOriginalGroup(String code);

}
