/**
 * @Title CommoditySpecsBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午12:42:08 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommoditySpecsDAO;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午12:42:08 
 * @history:
 */
@Component
public class CommoditySpecsBOImpl extends PaginableBOImpl<CommoditySpecs>
        implements ICommoditySpecsBO {

    @Autowired
    private ICommoditySpecsDAO commoditySpecsDAO;

    @Override
    public void saveSpecs(CommoditySpecs data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.COMMODITY_SPECS.getCode());
        data.setCode(code);
        commoditySpecsDAO.insert(data);
    }

    @Override
    public void removeSpecsByCommodity(String commodityCode) {
        CommoditySpecs data = new CommoditySpecs();
        data.setCommodityCode(commodityCode);
        commoditySpecsDAO.delete(data);
    }

    @Override
    public void refreshSpecs(CommoditySpecs commoditySpecs) {
        commoditySpecsDAO.updateCommoditySpecs(commoditySpecs);
    }

    @Override
    public void refreshInventory(Long id, Long quantity) {
        CommoditySpecs data = getCommoditySpecs(id);
        data.setInventory(data.getInventory() + quantity);
        commoditySpecsDAO.updateInventory(data);
    }

    @Override
    public List<CommoditySpecs> queryUsedSpecsList(String commodityCode,
            List<Long> notInIdList) {
        CommoditySpecs condition = new CommoditySpecs();
        condition.setCommodityCode(commodityCode);
        condition.setNotInIdList(notInIdList);
        condition.setUsed(EBoolean.YES.getCode());
        return commoditySpecsDAO.selectList(condition);
    }

    @Override
    public List<CommoditySpecs> querySpecsList(String commodityCode) {
        CommoditySpecs condition = new CommoditySpecs();
        condition.setCommodityCode(commodityCode);
        return commoditySpecsDAO.selectList(condition);
    }

    @Override
    public Long getInventory(Long id) {
        CommoditySpecs condition = new CommoditySpecs();
        condition.setId(id);
        CommoditySpecs data = commoditySpecsDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "该商品规格不存在");
        }

        return data.getInventory();
    }

    @Override
    public CommoditySpecs getCommoditySpecs(Long id) {
        CommoditySpecs condition = new CommoditySpecs();
        condition.setId(id);
        CommoditySpecs data = commoditySpecsDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "该商品规格不存在");
        }
        return data;
    }

}
