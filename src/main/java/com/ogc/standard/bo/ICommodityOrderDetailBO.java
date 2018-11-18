/**
 * @Title ICommodityOrderDetailBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午2:14:15 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CommodityOrderDetail;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:14:15 
 * @history:
 */
public interface ICommodityOrderDetailBO
        extends IPaginableBO<CommodityOrderDetail> {

    public String saveDetail(String commodityOrderCode, String shopCode,
            String commodityCode, String commodityName, Long specsId,
            String specsName, String applyUser, Long quantity, BigDecimal price,
            String addressCode);

    // 取消订单
    public void refreshCancel(CommodityOrderDetail commodityOrderDetail);

    // 支付成功
    public void refreshPay(String code);

    public void refershDelive(CommodityOrderDetail data,
            String logisticsCompany, String logisticsNumber, String deliver);

    public void refreshReceive(CommodityOrderDetail data);

    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition);

    public CommodityOrderDetail getCommodityOrderDetail(String code);

    public List<CommodityOrderDetail> queryOrderDetail(String orderCode);
}
