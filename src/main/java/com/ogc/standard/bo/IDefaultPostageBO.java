package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DefaultPostage;

public interface IDefaultPostageBO extends IPaginableBO<DefaultPostage> {

    public String saveDefaultPostage(String shopCode);

    public void refreshPrice(String code, BigDecimal price, String updater);

    public List<DefaultPostage> queryDefaultPostageList(
            DefaultPostage condition);

    public DefaultPostage getDefaultPostage(String code);

    public DefaultPostage getDefaultPostage(String shopCode, String type);

}
