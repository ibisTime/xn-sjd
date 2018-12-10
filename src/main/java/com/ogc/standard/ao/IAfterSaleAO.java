/**
 * @Title IAfterSaleAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午8:54:17 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AfterSale;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午8:54:17 
 * @history:
 */
public interface IAfterSaleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 售后退货退款
    public String applyGoods(String orderDetailCode, String logisticsCompany,
            String logisticsNumber, BigDecimal refundAmount, String deliver);

    // 售后退货
    public String applyMoney(String orderDetailCode, BigDecimal refundAmount,
            String applyUser);

    public void handleAfterSale(String code, String handleResult);

    public void doReceive(String code, String receiver);

    public Paginable<AfterSale> queryOrderPage(int start, int limit,
            AfterSale condition);

    public List<AfterSale> queryOrderList(AfterSale condition);

    public AfterSale getAfterSale(String code);
}
