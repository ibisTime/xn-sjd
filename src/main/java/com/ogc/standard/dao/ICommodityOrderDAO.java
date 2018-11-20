/**
 * @Title ICommodityOrderDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午1:34:42 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CommodityOrder;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午1:34:42 
 * @history:
 */
public interface ICommodityOrderDAO extends IBaseDAO<CommodityOrder> {
    String NAMESPACE = ICommodityOrderDAO.class.getName().concat(".");

    public int updatePay(CommodityOrder data);

    public int updateCancel(CommodityOrder data);

    public int updateAmount(CommodityOrder data);

    public int updateDelive(CommodityOrder data);

    public int updateReceive(CommodityOrder data);
}
