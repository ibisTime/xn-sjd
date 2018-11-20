/**
 * @Title CommodityOrderDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午1:39:25 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICommodityOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CommodityOrder;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午1:39:25 
 * @history:
 */
@Repository("commodityOrderDAOImpl")
public class CommodityOrderDAOImpl extends AMybatisTemplate
        implements ICommodityOrderDAO {

    @Override
    public int insert(CommodityOrder data) {
        return super.insert(NAMESPACE.concat("insert_commodity_order"), data);
    }

    @Override
    public int delete(CommodityOrder data) {
        return 0;
    }

    @Override
    public CommodityOrder select(CommodityOrder condition) {
        return super.select(NAMESPACE.concat("select_commodity_order"),
            condition, CommodityOrder.class);
    }

    @Override
    public long selectTotalCount(CommodityOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_commodity_order_count"), condition);
    }

    @Override
    public List<CommodityOrder> selectList(CommodityOrder condition) {
        return super.selectList(NAMESPACE.concat("select_commodity_order"),
            condition, CommodityOrder.class);
    }

    @Override
    public List<CommodityOrder> selectList(CommodityOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_commodity_order"),
            start, count, condition, CommodityOrder.class);
    }

    @Override
    public int updatePay(CommodityOrder data) {
        return super.update(NAMESPACE.concat("update_pay"), data);
    }

    @Override
    public int updateCancel(CommodityOrder data) {
        return super.update(NAMESPACE.concat("update_cancel"), data);
    }

    @Override
    public int updateAmount(CommodityOrder data) {
        return super.update(NAMESPACE.concat("update_amount"), data);
    }

    @Override
    public int updateDelive(CommodityOrder data) {
        return super.update(NAMESPACE.concat("update_delive"), data);
    }

    @Override
    public int updateReceive(CommodityOrder data) {
        return super.update(NAMESPACE.concat("update_receive"), data);
    }

}
