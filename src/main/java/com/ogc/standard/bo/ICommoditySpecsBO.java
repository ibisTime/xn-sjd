/**
 * @Title ICommoditySpecsBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午12:37:35 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CommoditySpecs;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午12:37:35 
 * @history:
 */
public interface ICommoditySpecsBO extends IPaginableBO<CommoditySpecs> {
    public void saveSpecsList(List<CommoditySpecs> dataList);

    public void removeSpecs(String commodityCode);

    public List<CommoditySpecs> querySpecsList(String commodityCode);

    public void inventoryDecrease(Long id, Long quantity);

    public Long getIeventory(Long id);
}