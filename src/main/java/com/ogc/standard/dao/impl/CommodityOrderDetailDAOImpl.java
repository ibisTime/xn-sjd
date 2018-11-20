/**
 * @Title CommodityOrderDetailDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午1:54:21 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICommodityOrderDetailDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CommodityOrderDetail;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午1:54:21 
 * @history:
 */
@Repository("commodityOrderDetailDAOImpl")
public class CommodityOrderDetailDAOImpl extends AMybatisTemplate
        implements ICommodityOrderDetailDAO {

    @Override
    public int insert(CommodityOrderDetail data) {
        return super.insert(NAMESPACE.concat("insert_commodity_order_detail"),
            data);
    }

    @Override
    public int delete(CommodityOrderDetail data) {
        return 0;
    }

    @Override
    public CommodityOrderDetail select(CommodityOrderDetail condition) {
        return super.select(NAMESPACE.concat("select_commodity_order_detail"),
            condition, CommodityOrderDetail.class);
    }

    @Override
    public long selectTotalCount(CommodityOrderDetail condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_commodity_order_detail_count"), condition);
    }

    @Override
    public List<CommodityOrderDetail> selectList(
            CommodityOrderDetail condition) {
        return super.selectList(
            NAMESPACE.concat("select_commodity_order_detail"), condition,
            CommodityOrderDetail.class);
    }

    @Override
    public List<CommodityOrderDetail> selectList(CommodityOrderDetail condition,
            int start, int count) {
        return super.selectList(
            NAMESPACE.concat("select_commodity_order_detail"), start, count,
            condition, CommodityOrderDetail.class);
    }

    @Override
    public int updateToCommentByOrder(CommodityOrderDetail data) {
        return super.update(NAMESPACE.concat("update_toCommentByOrder"), data);
    }

    @Override
    public int updateComment(CommodityOrderDetail data) {
        return super.update(NAMESPACE.concat("update_comment"), data);
    }

    @Override
    public int updateToAfterSell(CommodityOrderDetail data) {
        return super.update(NAMESPACE.concat("update_toAfterSell"), data);
    }

    @Override
    public int updateHandleAfterSell(CommodityOrderDetail data) {
        return super.update(NAMESPACE.concat("update_afterSelled"), data);
    }

}
