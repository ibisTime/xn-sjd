package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellSpecs;

public interface IPresellSpecsBO extends IPaginableBO<PresellSpecs> {

    // 保存规格
    public String savePresellSpecs(String productCode, String name,
            String packCount, String price, String increase);

    // 删除产品规格
    public void removePresellSpecsByProduct(String productCode);

    public List<PresellSpecs> queryPresellSpecsList(PresellSpecs condition);

    public PresellSpecs getPresellSpecs(String code);

}
