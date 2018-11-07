/**
 * @Title ICommodityOrderBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午2:02:06 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CommodityOrder;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:02:06 
 * @history:
 */
public interface ICommodityOrderBO extends IPaginableBO<CommodityOrder> {

    public String saveOrder(BigDecimal amount, Long quantity, String applyUser,
            String applyNote, String expressType, String updater, String remark);

    public void refreshPay(CommodityOrder data, BigDecimal payAmount,
            String updater, String remark);

    public void refreshPayGroup(CommodityOrder data, String payType);

    public void refreshCancel(CommodityOrder data, String updater, String remark);

    public List<CommodityOrder> queryOrderList(CommodityOrder condition);

    public CommodityOrder getCommodityOrder(String code);

    public CommodityOrder getCommodityOrderByPayGroup(String payGroup);

    public void platCancelOrder(CommodityOrder data);
}
