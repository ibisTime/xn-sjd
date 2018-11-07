/**
 * @Title CommodityOrderDetailAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午8:00:23 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.Company;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeBusiness;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午8:00:23 
 * @history:
 */
@Service
public class CommodityOrderDetailAOImpl implements ICommodityOrderDetailAO {

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public void delive(String orderCode, String shopCode,
            String logisticsCompany, String logisticsNumber, String deliver,
            String receiver, String receiverMobile) {
        // 订单状态判断
        CommodityOrder order = commodityOrderBO.getCommodityOrder(orderCode);
        if (!ECommodityOrderStatus.PAIED.getCode().equals(order.getCode())) {
            throw new BizException("xn0000", "订单还未付款不能发货");
        }
        // 取一个订单中同一家店铺的商品，一起发货
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setOrderCode(orderCode);
        condition.setShopCode(shopCode);
        List<CommodityOrderDetail> shopList = commodityOrderDetailBO
            .queryDetailList(condition);
        for (CommodityOrderDetail detail : shopList) {
            // 落地数据
            commodityOrderDetailBO.refershDelive(detail, logisticsCompany,
                logisticsNumber, deliver, receiver, receiverMobile);

        }
    }

    @Override
    @Transactional
    public void receive(String code, String receiver, String shopCode) {
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setOrderCode(code);
        condition.setShopCode(shopCode);
        List<CommodityOrderDetail> shopList = commodityOrderDetailBO
            .queryDetailList(condition);
        for (CommodityOrderDetail detail : shopList) {
            // 状态判断
            if (!ECommodityOrderDetailStatus.TORECEIVE.getCode().equals(
                detail.getStatus())) {
                throw new BizException("xn0000", "该订单不处于可收货的状态");
            }
            // 状态更新
            commodityOrderDetailBO.refreshReceive(detail);
        }
    }

    @Override
    public Paginable<CommodityOrderDetail> queryDetailPage(int start,
            int limit, CommodityOrderDetail condition) {
        return commodityOrderDetailBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition) {
        return commodityOrderDetailBO.queryDetailList(condition);
    }

    @Override
    public CommodityOrderDetail getCommodityOrderDetail(String code) {
        return commodityOrderDetailBO.getCommodityOrderDetail(code);
    }

    @Override
    public void payDetail(CommodityOrderDetail data, String applyUser,
            String orderCode) {
        accountBO.transAmount(applyUser, ESysUser.SYS_USER.getCode(),
            ECurrency.CNY.getCode(), data.getAmount(),
            EJourBizTypeUser.BUY.getCode(), EJourBizTypePlat.BUY.getCode(),
            EJourBizTypeUser.BUY.getValue(), EJourBizTypePlat.BUY.getValue(),
            orderCode);

    }

    @Override
    public void distribution(CommodityOrderDetail data, String orderCode) {
        Company company = companyBO.getCompany(data.getShopCode());
        String businessman = company.getUserId();
        // 商家分销
        BigDecimal rate = sysConfigBO
            .getBigDecimalValue(SysConstants.DIST_BUSINESS_RATE);
        BigDecimal businessAmount = data.getAmount().multiply(rate);
        accountBO.transAmount(ESysUser.SYS_USER.getCode(), businessman,
            ECurrency.CNY.getCode(), businessAmount,
            EJourBizTypePlat.BUY_DIST.getCode(),
            EJourBizTypeBusiness.BUSINESS_PROFIT.getCode(),
            EJourBizTypePlat.BUY_DIST.getValue(),
            EJourBizTypeBusiness.BUSINESS_PROFIT.getValue(), orderCode);
    }

}
