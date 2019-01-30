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

    public String saveAfterSale(String shopCode, String orderDetailCode,
            String logisticsCompany, String logisticsNumber,
            BigDecimal refundAmount, String deliver, String refundReason,
            String message);

    public String AfterSaleNoGoods(String shopCode, String orderDetailCode,
            BigDecimal refundAmount, String refundReason, String message);

    public void refreshHandle(AfterSale data, String status);

    public void refreshCancled(String code, String status);

    public void refreshReceive(AfterSale data, String receiver);

    public List<AfterSale> queryShList(AfterSale condition);

    public AfterSale getAfterSale(String code);

    public AfterSale getCurrentAfterSaleByOrder(String code);

    public boolean isAftrSaleExist(String orderDetailCode);
}
