/**
 * @Title ICommoditySpecs.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午12:25:51 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CommoditySpecs;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午12:25:51 
 * @history:
 */
public interface ICommoditySpecsDAO extends IBaseDAO<CommoditySpecs> {
    String NAMESPACE = ICommoditySpecsDAO.class.getName().concat(".");

    public int updateInventory(CommoditySpecs data);

    public int updateCommoditySpecs(CommoditySpecs data);

}
