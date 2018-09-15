package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DivideDetail;

public interface IDivideDetailBO extends IPaginableBO<DivideDetail> {

    public void saveDivideDetail(String userId, String currency,
            BigDecimal amount, String divideId);

    public int refreshDivideDetail(DivideDetail data);

    public List<DivideDetail> queryDivideDetailList(DivideDetail condition);

    public DivideDetail getDivideDetail(String code);

}
