package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IDayBenefitBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IDayBenefitDAO;
import com.ogc.standard.domain.DayBenefit;

@Component
public class DayBenefitBOImpl extends PaginableBOImpl<DayBenefit>
        implements IDayBenefitBO {

    @Autowired
    private IDayBenefitDAO dayBenefitDAO;

    @Override
    public void saveDayBenefit(String groupCode, BigDecimal yesterdayAssets,
            BigDecimal todayAssets, BigDecimal benefit, Double benefitRate) {

        if (StringUtils.isNotBlank(groupCode) && null != todayAssets
                && null != benefit && null != benefitRate) {

            DayBenefit data = new DayBenefit();
            data.setGroupCode(groupCode);
            data.setYesterdayAssets(yesterdayAssets);
            data.setTodayAssets(todayAssets);
            data.setBenefit(benefit);
            data.setBenefitRate(benefitRate);

            data.setCreateDatetime(new Date());
            dayBenefitDAO.insert(data);
        }

    }

    @Override
    public List<DayBenefit> queryDayBenefitList(String groupCode, String days) {

        DayBenefit condition = new DayBenefit();
        condition.setGroupCode(groupCode);
        condition.setOrder("create_datetime desc");

        if (StringUtils.isNotBlank(days)) {

            // 获取查询天数前的日期
            Date now = new Date();
            Date startDate = DateUtils.addDays(now,
                -StringValidater.toInteger(days));

            condition.setQurryDatetimeStart(startDate);
            condition.setQurryDatetimeEnd(new Date());

        }

        return dayBenefitDAO.selectList(condition);
    }

}
