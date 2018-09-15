package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Divide;

public interface IDivideBO extends IPaginableBO<Divide> {

    public void saveDivide();

    public void refreshDivide(String divideId, BigDecimal divideProfit,
            BigDecimal divideAmount, String divideUser, String remark);

    public List<Divide> queryDivideList(Divide condition);

    // public Divide getDivide(String code);

}
