/**
 * @Title ICommodityDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午4:27:59 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Commodity;

/** 
 * @author: taojian 
 * @since: 2018年11月2日 下午4:27:59 
 * @history:
 */
public interface ICommodityDAO extends IBaseDAO<Commodity> {
    String NAMESPACE = ICommodityDAO.class.getName().concat(".");

    public int updateStatus(Commodity data);

    public int updateOn(Commodity data);

    public int updateCommodity(Commodity data);

    public int updateMonthSellCount(Commodity data);

    public List<Commodity> selectDistinctDeliverPlaceByShop(Commodity data);

    public List<Commodity> selectDistinctDeliverPlace(Commodity data);

    public List<Commodity> selectDistinctOriginalPlace(Commodity data);

}
