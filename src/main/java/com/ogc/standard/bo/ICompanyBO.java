/**
 * @Title ICompanyBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午4:56:01 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Company;

/** 
 * @author: tao 
 * @since: 2018年8月17日 下午4:56:01 
 * @history:
 */
public interface ICompanyBO extends IPaginableBO<Company> {

    public String saveCompany(Company data);

    public int removeCompany(String code);

    public int refreshCompany(Company data);

    public List<Company> queryCompanyList(Company data);

    public Company getCompany(String code);

    public boolean isCompanyExist(String code);

}
