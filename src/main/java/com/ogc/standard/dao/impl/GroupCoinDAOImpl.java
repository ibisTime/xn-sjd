package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupCoinDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GroupCoin;



@Repository("groupCoinDAOImpl")
public class GroupCoinDAOImpl extends AMybatisTemplate implements IGroupCoinDAO {


	@Override
	public int insert(GroupCoin data) {
		return super.insert(NAMESPACE.concat("insert_groupCoin"), data);
	}


	@Override
	public int delete(GroupCoin data) {
		return super.delete(NAMESPACE.concat("delete_groupCoin"), data);
	}


	@Override
	public GroupCoin select(GroupCoin condition) {
		return super.select(NAMESPACE.concat("select_groupCoin"), condition,GroupCoin.class);
	}


	@Override
	public long selectTotalCount(GroupCoin condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_groupCoin_count"),condition);
	}


	@Override
	public List<GroupCoin> selectList(GroupCoin condition) {
		return super.selectList(NAMESPACE.concat("select_groupCoin"), condition,GroupCoin.class);
	}


	@Override
	public List<GroupCoin> selectList(GroupCoin condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_groupCoin"), start, count,condition, GroupCoin.class);
	}


}