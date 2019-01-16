/**
 * @Title CommodityDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午4:30:25 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICommodityDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Commodity;

/** 
 * @author: taojian 
 * @since: 2018年11月2日 下午4:30:25 
 * @history:
 */
@Repository("commodityDAOImpl")
public class CommodityDAOImpl extends AMybatisTemplate
        implements ICommodityDAO {

    @Override
    public int insert(Commodity data) {
        return super.insert(NAMESPACE.concat("insert_commodity"), data);
    }

    @Override
    public int delete(Commodity data) {
        return 0;
    }

    @Override
    public Commodity select(Commodity condition) {
        return super.select(NAMESPACE.concat("select_commodity"), condition,
            Commodity.class);
    }

    @Override
    public long selectTotalCount(Commodity condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_commodity_count"), condition);
    }

    @Override
    public List<Commodity> selectList(Commodity condition) {
        return super.selectList(NAMESPACE.concat("select_commodity"), condition,
            Commodity.class);
    }

    @Override
    public List<Commodity> selectList(Commodity condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_commodity"), start,
            count, condition, Commodity.class);
    }

    @Override
    public List<Commodity> selectDistinctDeliverPlace(Commodity data) {
        return super.selectList(NAMESPACE.concat("select_distinctDeliverPlace"),
            data, Commodity.class);
    }

    @Override
    public List<Commodity> selectDistinctOriginalPlace(Commodity data) {
        return super.selectList(
            NAMESPACE.concat("select_distinctOriginalPlace"), data,
            Commodity.class);
    }

    @Override
    public int updateStatus(Commodity data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateOn(Commodity data) {
        return super.update(NAMESPACE.concat("update_on"), data);
    }

    @Override
    public int updateCommodity(Commodity data) {
        return super.update(NAMESPACE.concat("update_commodity"), data);
    }

    @Override
    public int updateMonthSellCount(Commodity data) {
        return super.update(NAMESPACE.concat("update_month_sell_count"), data);
    }

}
