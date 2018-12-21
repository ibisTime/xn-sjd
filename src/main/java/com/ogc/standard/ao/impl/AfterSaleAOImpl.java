/**
 * @Title AfterSaleAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午9:01:25 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAfterSaleAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAfterSaleBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IWechatBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AfterSale;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.Company;
import com.ogc.standard.enums.EAfterSaleStatus;
import com.ogc.standard.enums.EAfterSaleType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EChargeStatus;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeBusiness;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午9:01:25 
 * @history:
 */
@Service
public class AfterSaleAOImpl implements IAfterSaleAO {

    @Autowired
    private IAfterSaleBO afterSaleBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IWechatBO weChatBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Override
    @Transactional
    public String applyGoods(String orderDetailCode, String logisticsCompany,
            String logisticsNumber, BigDecimal refundAmount, String deliver,
            String refundReason, String message) {

        CommodityOrderDetail orderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(orderDetailCode);

        // 订单状态检验
        if (!ECommodityOrderDetailStatus.TO_COMMENT.getCode()
            .equals(orderDetail.getStatus())) {
            throw new BizException("xn0000", "订单还未完成，无法申请售后");
        }

        // 检验是否存在流程中的售后订单
        if (afterSaleBO.isAftrSaleExist(orderDetailCode)) {
            throw new BizException("xn0000", "该订单正在进行或已完成售后，无法重复申请售后");
        }

        String codeString = afterSaleBO.saveAfterSale(orderDetail.getShopCode(),
            orderDetailCode, logisticsCompany, logisticsNumber, refundAmount,
            deliver, refundReason, message);

        // 更新明细状态
        commodityOrderDetailBO.toAfterSell(orderDetailCode);

        return codeString;
    }

    @Override
    @Transactional
    public String applyMoney(String orderDetailCode, BigDecimal refundAmount,
            String applyUser, String refundReason, String message) {

        CommodityOrderDetail orderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(orderDetailCode);

        // 订单状态检验
        if (!ECommodityOrderDetailStatus.TO_COMMENT.getCode()
            .equals(orderDetail.getStatus())) {
            throw new BizException("xn0000", "订单还未完成，无法申请售后");
        }

        // 检验是否存在流程中的售后订单
        if (afterSaleBO.isAftrSaleExist(orderDetailCode)) {
            throw new BizException("xn0000", "该订单正在进行或已完成售后，无法重复申请售后");
        }

        String codeString = afterSaleBO.AfterSaleNoGoods(
            orderDetail.getShopCode(), orderDetailCode, refundAmount,
            refundReason, message);

        // 更新明细状态
        commodityOrderDetailBO.toAfterSell(orderDetailCode);

        return codeString;
    }

    @Override
    @Transactional
    public void handleAfterSale(String code, String handleResult) {
        AfterSale data = afterSaleBO.getAfterSale(code);
        if (!EAfterSaleStatus.TOHANDLE.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该售后订单不处于可处理的状态");
        }

        if (EBoolean.YES.getCode().equals(handleResult)) {
            if (EAfterSaleType.onlyMoney.getCode().equals(data.getType())) {
                // 退款
                refund(data);
                // 状态更新
                afterSaleBO.refreshHandle(data,
                    EAfterSaleStatus.FINISH.getCode());

                commodityOrderDetailBO.toComment(data.getOrderDetailCode());

                commodityOrderDetailBO.refreshAfterSaleStatus(
                    data.getOrderDetailCode(),
                    ECommodityOrderDetailStatus.AFTER_SALEED_YES.getCode());
            } else {
                // 状态更新
                afterSaleBO.refreshHandle(data,
                    EAfterSaleStatus.PASS.getCode());
            }
        } else {
            afterSaleBO.refreshHandle(data, EAfterSaleStatus.FALSE.getCode());

            commodityOrderDetailBO.toComment(data.getOrderDetailCode());

            commodityOrderDetailBO.refreshAfterSaleStatus(
                data.getOrderDetailCode(),
                ECommodityOrderDetailStatus.AFTER_SALEED_NO.getCode());
        }

    }

    @Override
    @Transactional
    public void doReceive(String code, String receiver) {
        AfterSale data = afterSaleBO.getAfterSale(code);
        if (!EAfterSaleStatus.PASS.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该售后订单不处于可收货的状态");
        }

        refund(data);

        afterSaleBO.refreshReceive(data, receiver);

        commodityOrderDetailBO.toComment(data.getOrderDetailCode());

        commodityOrderDetailBO.refreshAfterSaleStatus(data.getOrderDetailCode(),
            ECommodityOrderDetailStatus.AFTER_SALEED_YES.getCode());
    }

    private void refund(AfterSale data) {
        BigDecimal refundAmount = data.getRefundAmount();
        CommodityOrderDetail orderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(data.getOrderDetailCode());
        Company company = companyBO.getCompany(orderDetail.getShopCode());
        String business = company.getUserId();
        String applyUser = orderDetail.getApplyUser();

        // TODO 单订单多次退款
        // 人民币退款
        if (null != orderDetail.getPayType()) {

            if (EPayType.YE.getCode().equals(orderDetail.getPayType())) {

                // 人民币余额划转
                accountBO.transAmount(business, applyUser,
                    ECurrency.CNY.getCode(), refundAmount,
                    EJourBizTypeBusiness.AFTER_SALE.getCode(),
                    EJourBizTypeUser.AFTER_SALE.getCode(),
                    EJourBizTypeBusiness.AFTER_SALE.getValue(),
                    EJourBizTypeUser.AFTER_SALE.getValue(), data.getCode());

            } else if (EPayType.ALIPAY.getCode()
                .equals(orderDetail.getPayType())) {

                // 支付宝退款
                Charge charge = chargeBO.getCharge(orderDetail.getOrderCode(),
                    EChannelType.Alipay.getCode(),
                    EChargeStatus.Pay_YES.getCode());

                String refNo = null;
                if (null != charge) {
                    refNo = charge.getCode();
                }

                Account bussinessAccount = accountBO.getAccountByUser(business,
                    ECurrency.CNY.getCode());

                alipayBO.doRefund(refNo, bussinessAccount,
                    EJourBizTypeUser.UN_FULL_CNY.getCode(),
                    EJourBizTypeUser.UN_FULL_CNY.getValue(),
                    data.getRefundAmount().divide(new BigDecimal(1000)));

            } else if (EPayType.WEIXIN_H5.getCode()
                .equals(orderDetail.getPayType())) {

                // 微信退款
                Charge charge = chargeBO.getCharge(orderDetail.getOrderCode(),
                    EChannelType.WeChat_H5.getCode(),
                    EChargeStatus.Pay_YES.getCode());

                String refNo = null;
                if (null != charge) {
                    refNo = charge.getCode();
                }

                Account bussinessAccount = accountBO.getAccountByUser(business,
                    ECurrency.CNY.getCode());

                weChatBO.doRefund(refNo, bussinessAccount,
                    EJourBizTypeUser.UN_FULL_CNY.getCode(),
                    EJourBizTypeUser.UN_FULL_CNY.getValue(),
                    data.getRefundAmount().divide(new BigDecimal(10))
                        .toString());

            }
        }

        // 积分退款
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), applyUser, ECurrency.JF.getCode(),
            orderDetail.getJfDeductAmount(),
            EJourBizTypeUser.AFTER_SALE_JF.getCode(),
            EJourBizTypePlat.AFTER_SALE_JF.getCode(),
            EJourBizTypeUser.AFTER_SALE_JF.getValue(),
            EJourBizTypePlat.AFTER_SALE_JF.getValue(), data.getCode());

    }

    @Override
    public Paginable<AfterSale> queryOrderPage(int start, int limit,
            AfterSale condition) {
        Paginable<AfterSale> page = afterSaleBO.getPaginable(start, limit,
            condition);
        for (AfterSale afterSale : page.getList()) {
            afterSale.setOrderDetail(commodityOrderDetailBO
                .getCommodityOrderDetail(afterSale.getOrderDetailCode()));
        }
        return page;
    }

    @Override
    public List<AfterSale> queryOrderList(AfterSale condition) {
        List<AfterSale> afterSales = afterSaleBO.queryShList(condition);
        for (AfterSale afterSale : afterSales) {
            afterSale.setOrderDetail(commodityOrderDetailBO
                .getCommodityOrderDetail(afterSale.getOrderDetailCode()));

        }
        return afterSales;
    }

    @Override
    public AfterSale getAfterSale(String code) {
        AfterSale afterSale = afterSaleBO.getAfterSale(code);
        afterSale.setOrderDetail(commodityOrderDetailBO
            .getCommodityOrderDetail(afterSale.getOrderDetailCode()));
        return afterSale;
    }

}
