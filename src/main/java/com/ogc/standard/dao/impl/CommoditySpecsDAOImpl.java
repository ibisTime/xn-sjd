/**
 * @Title CommoditySpecsDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午12:27:53 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICommoditySpecsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CommoditySpecs;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午12:27:53 
 * @history:
 */
@Repository("commoditySpecsDAOImpl")
public class CommoditySpecsDAOImpl extends AMybatisTemplate
        implements ICommoditySpecsDAO {

    @Override
    public int insert(CommoditySpecs data) {
        return super.insert(NAMESPACE.concat("insert_commodity_specs"), data);
    }

    @Override
    public int delete(CommoditySpecs data) {
        return super.delete(NAMESPACE.concat("delete_commodity_specs"), data);
    }

    @Override
    public CommoditySpecs select(CommoditySpecs condition) {
        return super.select(NAMESPACE.concat("select_commodity_specs"),
            condition, CommoditySpecs.class);
    }

    @Override
    public long selectTotalCount(CommoditySpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_commodity_specs_count"), condition);
    }

    @Override
    public List<CommoditySpecs> selectList(CommoditySpecs condition) {
        return super.selectList(NAMESPACE.concat("select_commodity_specs"),
            condition, CommoditySpecs.class);
    }

    @Override
    public List<CommoditySpecs> selectList(CommoditySpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_commodity_specs"),
            start, count, condition, CommoditySpecs.class);
    }

    @Override
    public int updateInventory(CommoditySpecs data) {
        return super.update(NAMESPACE.concat("update_inventory"), data);
    }

    @Override
    public int updateCommoditySpecs(CommoditySpecs data) {
        return super.update(NAMESPACE.concat("update_specs"), data);
    }

    @Override
    public int deleteUnUsedCommoditySpecs(CommoditySpecs data) {
        return super.delete(NAMESPACE.concat("delete_unUsedcommodity_specs"),
            data);
    }

}
