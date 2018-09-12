package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISimuKLineDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SimuKLine;



@Repository("simuKLineDAOImpl")
public class SimuKLineDAOImpl extends AMybatisTemplate implements ISimuKLineDAO {


	@Override
	public int insert(SimuKLine data) {
		return super.insert(NAMESPACE.concat("insert_simuKLine"), data);
	}


	@Override
	public int delete(SimuKLine data) {
		return super.delete(NAMESPACE.concat("delete_simuKLine"), data);
	}


	@Override
	public SimuKLine select(SimuKLine condition) {
		return super.select(NAMESPACE.concat("select_simuKLine"), condition,SimuKLine.class);
	}


	@Override
	public long selectTotalCount(SimuKLine condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_simuKLine_count"),condition);
	}


	@Override
	public List<SimuKLine> selectList(SimuKLine condition) {
		return super.selectList(NAMESPACE.concat("select_simuKLine"), condition,SimuKLine.class);
	}


	@Override
	public List<SimuKLine> selectList(SimuKLine condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_simuKLine"), start, count,condition, SimuKLine.class);
	}


}