package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DayBenefit;

public interface IDayBenefitBO extends IPaginableBO<DayBenefit> {

    public void saveDayBenefit(String groupCode, BigDecimal yesterdayAssets,
            BigDecimal todayAssets, BigDecimal benefit, Double benefitRate);

    public List<DayBenefit> queryDayBenefitList(String groupCode, String days);

}
