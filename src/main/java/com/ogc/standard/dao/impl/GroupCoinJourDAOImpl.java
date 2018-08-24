package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupCoinJourDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GroupCoinJour;



@Repository("groupCoinJourDAOImpl")
public class GroupCoinJourDAOImpl extends AMybatisTemplate implements IGroupCoinJourDAO {


	@Override
	public int insert(GroupCoinJour data) {
		return super.insert(NAMESPACE.concat("insert_groupCoinJour"), data);
	}


	@Override
	public int delete(GroupCoinJour data) {
		return super.delete(NAMESPACE.concat("delete_groupCoinJour"), data);
	}


	@Override
	public GroupCoinJour select(GroupCoinJour condition) {
		return super.select(NAMESPACE.concat("select_groupCoinJour"), condition,GroupCoinJour.class);
	}


	@Override
	public long selectTotalCount(GroupCoinJour condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_groupCoinJour_count"),condition);
	}


	@Override
	public List<GroupCoinJour> selectList(GroupCoinJour condition) {
		return super.selectList(NAMESPACE.concat("select_groupCoinJour"), condition,GroupCoinJour.class);
	}


	@Override
	public List<GroupCoinJour> selectList(GroupCoinJour condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_groupCoinJour"), start, count,condition, GroupCoinJour.class);
	}


}