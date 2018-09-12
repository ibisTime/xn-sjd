package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISimuOrderHistoryDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SimuOrderHistory;



@Repository("simuOrderHistoryDAOImpl")
public class SimuOrderHistoryDAOImpl extends AMybatisTemplate implements ISimuOrderHistoryDAO {


	@Override
	public int insert(SimuOrderHistory data) {
		return super.insert(NAMESPACE.concat("insert_simuOrderHistory"), data);
	}


	@Override
	public int delete(SimuOrderHistory data) {
		return super.delete(NAMESPACE.concat("delete_simuOrderHistory"), data);
	}


	@Override
	public SimuOrderHistory select(SimuOrderHistory condition) {
		return super.select(NAMESPACE.concat("select_simuOrderHistory"), condition,SimuOrderHistory.class);
	}


	@Override
	public long selectTotalCount(SimuOrderHistory condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_simuOrderHistory_count"),condition);
	}


	@Override
	public List<SimuOrderHistory> selectList(SimuOrderHistory condition) {
		return super.selectList(NAMESPACE.concat("select_simuOrderHistory"), condition,SimuOrderHistory.class);
	}


	@Override
	public List<SimuOrderHistory> selectList(SimuOrderHistory condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_simuOrderHistory"), start, count,condition, SimuOrderHistory.class);
	}


}