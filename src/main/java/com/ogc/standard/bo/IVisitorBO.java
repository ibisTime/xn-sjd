package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Visitor;



//CHECK ��鲢��ע�� 
public interface IVisitorBO extends IPaginableBO<Visitor> {


	public boolean isVisitorExist(String code);


	public String saveVisitor(Visitor data);


	public int removeVisitor(String code);


	public int refreshVisitor(Visitor data);


	public List<Visitor> queryVisitorList(Visitor condition);


	public Visitor getVisitor(String code);


}