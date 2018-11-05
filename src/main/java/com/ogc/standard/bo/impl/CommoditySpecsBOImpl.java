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
import com.ogc.standard.dao.ICommoditySpecsDAO;
import com.ogc.standard.domain.CommoditySpecs;

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
    public void saveSpecsList(List<CommoditySpecs> dataList) {
        commoditySpecsDAO.insert(dataList);
    }

    @Override
    public void removeSpecs(String commodityCode) {
        CommoditySpecs data = new CommoditySpecs();
        data.setCommodityCode(commodityCode);
        commoditySpecsDAO.delete(data);
    }

    @Override
    public List<CommoditySpecs> querySpecsList(String commodityCode) {
        CommoditySpecs condition = new CommoditySpecs();
        condition.setCommodityCode(commodityCode);
        return commoditySpecsDAO.selectList(condition);
    }

}
