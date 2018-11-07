/**
 * @Title ICommodityOrderDetailDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午1:36:45 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CommodityOrderDetail;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午1:36:45 
 * @history:
 */
public interface ICommodityOrderDetailDAO extends
        IBaseDAO<CommodityOrderDetail> {
    String NAMESPACE = ICommodityOrderDetailDAO.class.getName().concat(".");

    public int updateDelive(CommodityOrderDetail data);

    public int updateReceive(CommodityOrderDetail data);
}
