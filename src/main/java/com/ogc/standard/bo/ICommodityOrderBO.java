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
import com.ogc.standard.dto.res.XN629048Res;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:02:06 
 * @history:
 */
public interface ICommodityOrderBO extends IPaginableBO<CommodityOrder> {

    public String saveOrder(String applyUser, String applyNote, String payGroup,
            String shopCode, String expressType, String updater, String remark,
            String addressCode);

    public void refreshCancel(CommodityOrder data, String updater,
            String remark);

    public void payYueSuccess(CommodityOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount);

    public void refreshPayGroup(CommodityOrder data, String payType,
            XN629048Res resultRes);

    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount);

    public void platCancelOrder(CommodityOrder data);

    public void refreshAmount(Long quantity, BigDecimal amount, String code,
            BigDecimal postalFee);

    public void refreshAddress(String code, String addressCode);

    public void refershDelive(CommodityOrder data, String logisticsCompany,
            String logisticsNumber, String deliver);

    public void refreshReceive(CommodityOrder data);

    public void refreshFinish(String code);

    public void refreshSettleStatus(CommodityOrder data, String approveResult,
            String updater, String remark);

    public List<CommodityOrder> queryOrderList(CommodityOrder condition);

    public CommodityOrder getCommodityOrder(String code);

    public List<CommodityOrder> queryCommodityOrderByPayGroup(String payGroup);

}
