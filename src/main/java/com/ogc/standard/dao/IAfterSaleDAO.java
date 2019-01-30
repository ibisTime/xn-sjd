/**
 * @Title IAfterSaleDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午7:49:35 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AfterSale;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午7:49:35 
 * @history:
 */
public interface IAfterSaleDAO extends IBaseDAO<AfterSale> {

    String NAMESPACE = IAfterSaleDAO.class.getName().concat(".");

    public int updateHandle(AfterSale data);

    public int updateReceive(AfterSale data);

    int updateCancled(AfterSale data);
}
