package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IDayBenefitAO;
import com.ogc.standard.bo.IDayBenefitBO;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.DayBenefit;
import com.ogc.standard.dto.res.XN650090Res;

@Service
public class DayBenefitAOImpl implements IDayBenefitAO {

    @Autowired
    private IDayBenefitBO dayBenefitBO;

    @Override
    public XN650090Res queryDayBenefitList(String groupCode, String days) {

        List<DayBenefit> dayBenefits = dayBenefitBO
            .queryDayBenefitList(groupCode, days);

        XN650090Res res = new XN650090Res();
        res.setHorizontalMax(
            DateUtil.dateToStr(dayBenefits.get(0).getCreateDatetime(),
                DateUtil.DATA_TIME_PATTERN_1));
        res.setHorizontalMin(DateUtil.dateToStr(
            dayBenefits.get(dayBenefits.size() - 1).getCreateDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        res.setList(dayBenefits);

        return res;
    }

}
