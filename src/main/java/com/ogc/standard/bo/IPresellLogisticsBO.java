package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellLogistics;
import com.ogc.standard.dto.req.XN629433ReqLogistics;

public interface IPresellLogisticsBO extends IPaginableBO<PresellLogistics> {

    public String savePresellLogistics(String originalGroupCode,
            XN629433ReqLogistics logistics);

    public List<PresellLogistics> queryPresellLogisticsList(
            PresellLogistics condition);

    public PresellLogistics getPresellLogistics(String code);

}
