package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.exception.BizException;



//CHECK ��鲢��ע�� 
@Service
public class GiveTreeRecordAOImpl implements IGiveTreeRecordAO {

	@Autowired
	private IGiveTreeRecordBO giveTreeRecordBO;

	@Override
	public String addGiveTreeRecord(GiveTreeRecord data) {
		return giveTreeRecordBO.saveGiveTreeRecord(data);
	}

	@Override
	public int editGiveTreeRecord(GiveTreeRecord data) {
		if (!giveTreeRecordBO.isGiveTreeRecordExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return giveTreeRecordBO.refreshGiveTreeRecord(data);
	}

	@Override
	public int dropGiveTreeRecord(String code) {
		if (!giveTreeRecordBO.isGiveTreeRecordExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return giveTreeRecordBO.removeGiveTreeRecord(code);
	}

	@Override
	public Paginable<GiveTreeRecord> queryGiveTreeRecordPage(int start, int limit,
			GiveTreeRecord condition) {
		return giveTreeRecordBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition) {
		return giveTreeRecordBO.queryGiveTreeRecordList(condition);
	}

	@Override
	public GiveTreeRecord getGiveTreeRecord(String code) {
		return giveTreeRecordBO.getGiveTreeRecord(code);
	}
}