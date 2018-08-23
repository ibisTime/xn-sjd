package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDayBenefitDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.DayBenefit;



@Repository("dayBenefitDAOImpl")
public class DayBenefitDAOImpl extends AMybatisTemplate implements IDayBenefitDAO {


	@Override
	public int insert(DayBenefit data) {
		return super.insert(NAMESPACE.concat("insert_dayBenefit"), data);
	}


	@Override
	public int delete(DayBenefit data) {
		return super.delete(NAMESPACE.concat("delete_dayBenefit"), data);
	}


	@Override
	public DayBenefit select(DayBenefit condition) {
		return super.select(NAMESPACE.concat("select_dayBenefit"), condition,DayBenefit.class);
	}


	@Override
	public long selectTotalCount(DayBenefit condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_dayBenefit_count"),condition);
	}


	@Override
	public List<DayBenefit> selectList(DayBenefit condition) {
		return super.selectList(NAMESPACE.concat("select_dayBenefit"), condition,DayBenefit.class);
	}


	@Override
	public List<DayBenefit> selectList(DayBenefit condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_dayBenefit"), start, count,condition, DayBenefit.class);
	}


}