/**
 * @Title IAfterSaleBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午8:00:26 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AfterSale;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午8:00:26 
 * @history:
 */
public interface IAfterSaleBO extends IPaginableBO<AfterSale> {

    public String saveAfterSale(String orderDetailCode,
            String logisticsCompany, String logisticsNumber,
            BigDecimal refundAmount, String deliver);

    public String AfterSaleNoGoods(String orderDetailCode,
            BigDecimal refundAmount);

    public void refreshHandle(AfterSale data, String status);

    public void refreshReceive(AfterSale data, String receiver);

    public List<AfterSale> queryShList(AfterSale condition);

    public AfterSale getAfterSale(String code);
}
