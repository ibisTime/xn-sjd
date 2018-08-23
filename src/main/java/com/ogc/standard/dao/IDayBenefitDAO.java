package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.DayBenefit;

public interface IDayBenefitDAO extends IBaseDAO<DayBenefit> {
	String NAMESPACE = IDayBenefitDAO.class.getName().concat(".");
}