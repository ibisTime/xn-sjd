package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICurrencyRateDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CurrencyRate;

/**
 * Created by tianlei on 2017/十一月/13.
 */
@Repository("currencyRateDAOImpl")
public class CurrencyRateDAOImpl extends AMybatisTemplate
        implements ICurrencyRateDAO {

    @Override
    public int insert(CurrencyRate data) {

        return super.insert(NAMESPACE.concat("insert"), data);

    }

    @Override
    public int update(CurrencyRate currencyRate) {

        return super.update(NAMESPACE.concat("update"), currencyRate);

    }

    // @Override
    // public BigDecimal selectRateAVG(String currency) {
    //
    // CurrencyRate condition = new CurrencyRate();
    // condition.setCurrency();
    // return super.select(NAMESPACE.concat("selectRateAVG"),condition,);
    // }

    @Override
    public long selectTotalCount(CurrencyRate condition) {

        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),
            condition);

    }

    @Override
    public int delete(CurrencyRate data) {
        return 0;
    }

    @Override
    public CurrencyRate select(CurrencyRate condition) {

        return super.select(NAMESPACE.concat("select"), condition,
            CurrencyRate.class);

    }

    @Override
    public List<CurrencyRate> selectList(CurrencyRate condition) {

        return super.selectList(NAMESPACE.concat("select"), condition,
            CurrencyRate.class);

    }

    @Override
    public List<CurrencyRate> selectList(CurrencyRate condition, int start,
            int count) {

        return super.selectList(NAMESPACE.concat("select"), start, count,
            condition, CurrencyRate.class);

    }

}
