/**
 * @Title ICompanyAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午5:49:58 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN630300Req;
import com.ogc.standard.dto.req.XN630302Req;

/** 
 * @author: tao 
 * @since: 2018年8月17日 下午5:49:58 
 * @history:
 */
public interface ICompanyAO {

    public String addCompany(XN630300Req req);

    public void dropCompany(String code);

    public void editCompany(XN630302Req req);

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);
}
