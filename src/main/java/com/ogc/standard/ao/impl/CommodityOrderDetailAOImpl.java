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

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeBusiness;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午8:00:23 
 * @history:
 */
@Service
public class CommodityOrderDetailAOImpl implements ICommodityOrderDetailAO {

    static final Logger logger = LoggerFactory
        .getLogger(CommodityOrderDetailAOImpl.class);

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

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IJourBO jourBO;

    @Override
    public void payDetail(CommodityOrderDetail data, String applyUser,
            String orderCode) {
        accountBO.transAmount(applyUser, ESysUser.SYS_USER.getCode(),
            ECurrency.CNY.getCode(), data.getAmount(),
            EJourBizTypeUser.COMMODITY.getCode(),
            EJourBizTypePlat.COMMODITY.getCode(),
            EJourBizTypeUser.COMMODITY.getValue(),
            EJourBizTypePlat.COMMODITY.getValue(), orderCode);
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
            EJourBizTypePlat.COMMODITY_DIST.getCode(),
            EJourBizTypeBusiness.BUSINESS_PROFIT.getCode(),
            EJourBizTypePlat.COMMODITY_DIST.getValue(),
            EJourBizTypeBusiness.BUSINESS_PROFIT.getValue(), orderCode);
    }

    @Override
    public Paginable<CommodityOrderDetail> queryDetailPage(int start, int limit,
            CommodityOrderDetail condition) {
        Paginable<CommodityOrderDetail> page = commodityOrderDetailBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (CommodityOrderDetail commodityOrderDetail : page.getList()) {
                init(commodityOrderDetail);
            }
        }

        return page;
    }

    @Override
    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition) {
        List<CommodityOrderDetail> list = commodityOrderDetailBO
            .queryDetailList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (CommodityOrderDetail commodityOrderDetail : list) {
                init(commodityOrderDetail);
            }
        }

        return list;
    }

    @Override
    public CommodityOrderDetail getCommodityOrderDetail(String code) {
        CommodityOrderDetail commodityOrderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(code);

        init(commodityOrderDetail);

        return commodityOrderDetail;
    }

    private void init(CommodityOrderDetail commodityOrderDetail) {
        // 收货地址
        Address address = addressBO
            .getAddress(commodityOrderDetail.getAddressCode());
        commodityOrderDetail.setAddress(address);

        // 店铺名称
        Company shop = companyBO.getCompany(commodityOrderDetail.getShopCode());
        commodityOrderDetail.setShopName(shop.getName());

        // 卖家
        commodityOrderDetail.setSellerName(shop.getName());

        // 支付流水编号
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(commodityOrderDetail.getOrderCode());
        Account userCnyAccount = accountBO.getAccountByUser(
            commodityOrder.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(commodityOrder.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            commodityOrderDetail.setJourCode(jourList.get(0).getCode());
        }

        // 物流方式
        Commodity commodity = commodityBO
            .getCommodity(commodityOrderDetail.getCommodityCode());
        commodityOrderDetail.setLogistics(commodity.getLogistics());

    }

}
