package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISimuMatchResultHistoryDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SimuMatchResultHistory;



@Repository("simuMatchResultHistoryDAOImpl")
public class SimuMatchResultHistoryDAOImpl extends AMybatisTemplate implements ISimuMatchResultHistoryDAO {


	@Override
	public int insert(SimuMatchResultHistory data) {
		return super.insert(NAMESPACE.concat("insert_simuMatchResultHistory"), data);
	}


	@Override
	public int delete(SimuMatchResultHistory data) {
		return super.delete(NAMESPACE.concat("delete_simuMatchResultHistory"), data);
	}


	@Override
	public SimuMatchResultHistory select(SimuMatchResultHistory condition) {
		return super.select(NAMESPACE.concat("select_simuMatchResultHistory"), condition,SimuMatchResultHistory.class);
	}


	@Override
	public long selectTotalCount(SimuMatchResultHistory condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_simuMatchResultHistory_count"),condition);
	}


	@Override
	public List<SimuMatchResultHistory> selectList(SimuMatchResultHistory condition) {
		return super.selectList(NAMESPACE.concat("select_simuMatchResultHistory"), condition,SimuMatchResultHistory.class);
	}


	@Override
	public List<SimuMatchResultHistory> selectList(SimuMatchResultHistory condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_simuMatchResultHistory"), start, count,condition, SimuMatchResultHistory.class);
	}


}