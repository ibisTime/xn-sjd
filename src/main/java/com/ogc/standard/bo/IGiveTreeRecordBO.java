package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GiveTreeRecord;



//CHECK ��鲢��ע�� 
public interface IGiveTreeRecordBO extends IPaginableBO<GiveTreeRecord> {


	public boolean isGiveTreeRecordExist(String code);


	public String saveGiveTreeRecord(GiveTreeRecord data);


	public int removeGiveTreeRecord(String code);


	public int refreshGiveTreeRecord(GiveTreeRecord data);


	public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition);


	public GiveTreeRecord getGiveTreeRecord(String code);


}