/**
 * @Title CommodityOrderAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:57:03 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.CommodityShopOrder;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.dto.req.XN629714Req;
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:57:03 
 * @history:
 */
@Service
public class CommodityOrderAOImpl implements ICommodityOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(CommodityOrderAOImpl.class);

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private ISmsBO smsBO;

    @Override
    @Transactional
    public String commitCommodityOrder(String applyUser, String applyNote,
            String expressType, Long specsId, Long quantity,
            String addressCode) {

        // 规格检验
        CommoditySpecs specs = commoditySpecsBO.getCommoditySpecs(specsId);
        Commodity commodity = commodityBO
            .getCommodity(specs.getCommodityCode());

        // 落地订单数据
        String orderCode = commodityOrderBO.saveOrder(applyUser, applyNote,
            null, expressType, applyUser, applyNote, addressCode);

        // 落地单店铺订单
        commodityOrderDetailBO.saveDetail(orderCode, commodity.getShopCode(),
            commodity.getCode(), commodity.getName(), specsId, specs.getName(),
            applyUser, quantity, specs.getPrice(), addressCode);

        // 更新库存
        commoditySpecsBO.refreshInventory(specsId, -quantity);

        // 加上数量与总价
        BigDecimal amount = specs.getPrice()
            .multiply(BigDecimal.valueOf(quantity));

        commodityOrderBO.refreshAmount(quantity, amount, orderCode);

        return orderCode;
    }

    @Override
    @Transactional
    public void cancelCommodityOrder(String code, String updater,
            String remark) {
        CommodityOrder order = commodityOrderBO.getCommodityOrder(code);
        if (!ECommodityOrderStatus.TO_PAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可取消状态");
        }

        // 库存回加
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(code);
        for (CommodityOrderDetail commodityOrderDetail : dataList) {
            commoditySpecsBO.refreshInventory(commodityOrderDetail.getSpecsId(),
                commodityOrderDetail.getQuantity());
        }

        // 状态更新
        commodityOrderBO.refreshCancel(order, updater, remark);
    }

    @Override
    @Transactional
    public Object toPayCommodityOrder(XN629721Req req) {

        // 支付密码确认
        if (StringUtils.isNotBlank(req.getTradePwd())) {
            userBO.checkTradePwd(req.getUpdater(), req.getTradePwd());
        }

        // 订单状态检验
        CommodityOrder order = commodityOrderBO
            .getCommodityOrder(req.getCode());
        if (!ECommodityOrderStatus.TO_PAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该订单不处于待支付状态");
        }

        // 积分抵扣处理
        XN629048Res deductRes = distributionOrderBO.getOrderDeductAmount(
            order.getAmount(), order.getApplyUser(), req.getIsJfDeduct());

        // 支付
        Object result = null;
        if (EPayType.ALIPAY.getCode().equals(req.getPayType())) {

            // 状态更新
            commodityOrderBO.refreshPayGroup(order, req.getPayType());

            String signOrder = alipayBO.getSignedOrder(order.getApplyUser(),
                ESysUser.SYS_USER.getCode(), order.getPayGroup(),
                EJourBizTypeUser.COMMODITY.getCode(),
                EJourBizTypeUser.COMMODITY.getValue(), order.getPayAmount());
            result = new PayOrderRes(signOrder);

        } else if (EPayType.YE.getCode().equals(req.getPayType())) {

            result = toPayCommodityOrderYue(order, deductRes);

        } else if (EPayType.WEIXIN_H5.getCode().equals(req.getPayType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");
        } else {
            throw new BizException("xn0000", "不支持的支付方式");
        }
        return result;
    }

    @Override
    @Transactional
    public Object toPayGroupCommodityOrder(XN629714Req req) {

        // 支付密码确认
        if (StringUtils.isNotBlank(req.getTradePwd())) {
            userBO.checkTradePwd(req.getUpdater(), req.getTradePwd());
        }

        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryCommodityOrderByPayGroup(req.getPayGroup());
        Object result = null;

        for (CommodityOrder order : commodityOrderList) {

            if (!ECommodityOrderStatus.TO_PAY.getCode()
                .equals(order.getStatus())) {
                throw new BizException("xn0000", "该订单不处于待支付状态");
            }

            // 积分抵扣处理
            XN629048Res deductRes = distributionOrderBO.getOrderDeductAmount(
                order.getAmount(), order.getApplyUser(), req.getIsJfDeduct());

            // 支付
            if (EPayType.ALIPAY.getCode().equals(req.getPayType())) {

                // 状态更新
                commodityOrderBO.refreshPayGroup(order, req.getPayType());

                String signOrder = alipayBO.getSignedOrder(order.getApplyUser(),
                    ESysUser.SYS_USER.getCode(), order.getPayGroup(),
                    EJourBizTypeUser.COMMODITY.getCode(),
                    EJourBizTypeUser.COMMODITY.getValue(),
                    order.getPayAmount());
                result = new PayOrderRes(signOrder);

            } else if (EPayType.YE.getCode().equals(req.getPayType())) {

                result = toPayCommodityOrderYue(order, deductRes);

            } else if (EPayType.WEIXIN_H5.getCode().equals(req.getPayType())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "暂不支持微信支付");
            } else {
                throw new BizException("xn0000", "不支持的支付方式");
            }
        }

        return result;
    }

    // 余额支付
    public Object toPayCommodityOrderYue(CommodityOrder data,
            XN629048Res resultRes) {

        Account userCnyAccount = accountBO.getAccountByUser(data.getApplyUser(),
            ECurrency.CNY.getCode());
        BigDecimal payAmount = data.getAmount()
            .subtract(resultRes.getCnyAmount());// 实际付款人民币金额
        if (userCnyAccount.getAmount().compareTo(payAmount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "人民币账户余额不足");
        }

        // 人民币余额划转
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.transAmount(userCnyAccount, sysCnyAccount, payAmount,
            EJourBizTypeUser.COMMODITY.getCode(),
            EJourBizTypePlat.COMMODITY.getCode(),
            EJourBizTypeUser.COMMODITY.getValue(),
            EJourBizTypePlat.COMMODITY.getValue(), data.getCode());

        // 积分抵扣
        accountBO.transAmount(data.getApplyUser(), ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), resultRes.getJfAmount(),
            EJourBizTypeUser.COMMODITY_BUY_DEDUCT.getCode(),
            EJourBizTypePlat.COMMODITY_BUY_DEDUCT.getCode(),
            EJourBizTypeUser.COMMODITY_BUY_DEDUCT.getValue(),
            EJourBizTypePlat.COMMODITY_BUY_DEDUCT.getValue(), data.getCode());

        // TODO 进行分销
        // backJfAmount = distributionOrderBO.distribution(data.getCode(),
        // commodityInfo.getShopCode(), data.getAmount(), data.getApplyUser(),
        // ESellType.COMMODITY.getCode(), resultRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveCommodityBulletin(data.getApplyUser(),
            data.getQuantity().toString());

        // TODO backjfamount
        commodityOrderBO.refreshPay(data, data.getAmount());

        return new BooleanRes(true);
    }

    @Override
    public void paySuccess(String payGroup) {
        CommodityOrder data = commodityOrderBO.getCommodityOrder(payGroup);

        // TODO 进行分销
        // backJfAmount = distributionOrderBO.distribution(data.getCode(),
        // commodityInfo.getShopCode(), data.getAmount(), data.getApplyUser(),
        // ESellType.COMMODITY.getCode(), resultRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveCommodityBulletin(data.getApplyUser(),
            data.getQuantity().toString());

        // TODO backjfamount
        commodityOrderBO.refreshPay(data, data.getAmount());

    }

    @Override
    @Transactional
    public void delive(String code, String logisticsCompany,
            String logisticsNumber, String deliver) {
        CommodityOrderDetail commodityOrderDetail = commodityOrderDetailBO
            .getCommodityOrderDetail(code);
        if (!ECommodityOrderDetailStatus.TODELIVE.getCode()
            .equals(commodityOrderDetail.getStatus())) {
            throw new BizException("xn0000", "订单未处于可发货状态");
        }

        commodityOrderDetailBO.refershDelive(commodityOrderDetail,
            logisticsCompany, logisticsNumber, deliver);

    }

    @Override
    @Transactional
    public void receive(String code, String receiver) {
        CommodityOrderDetail detail = commodityOrderDetailBO
            .getCommodityOrderDetail(code);

        // 状态判断
        if (!ECommodityOrderDetailStatus.TORECEIVE.getCode()
            .equals(detail.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可收货的状态");
        }

        // 状态更新
        commodityOrderDetailBO.refreshReceive(detail);

        // 月销量更新
        Commodity data = commodityBO.getCommodity(detail.getCommodityCode());
        commodityBO.refreshMonthSellCount(data,
            detail.getQuantity() + data.getMonthSellCount());
    }

    public void timeoutCancel() {
        logger.info("***************开始扫描未支付订单***************");
        CommodityOrder condition = new CommodityOrder();
        condition.setStatus(ECommodityOrderStatus.TO_PAY.getCode());
        condition.setApplyDatetimeEnd(
            DateUtil.getRelativeDateOfMinute(new Date(), -15));// 前15分钟还未支付的订单
        List<CommodityOrder> orderList = commodityOrderBO
            .queryOrderList(condition);

        if (CollectionUtils.isNotEmpty(orderList)) {
            for (CommodityOrder order : orderList) {
                cancelOrder(order);
            }
        }
        logger.info("***************结束扫描未支付订单***************");
    }

    private void cancelOrder(CommodityOrder order) {
        // 库存回加
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(order.getCode());
        for (CommodityOrderDetail commodityOrderDetail : dataList) {
            commoditySpecsBO.refreshInventory(commodityOrderDetail.getSpecsId(),
                commodityOrderDetail.getQuantity());
        }

        // 状态更新
        commodityOrderBO.platCancelOrder(order);
    }

    public void doReceive() {
        logger.info("***************开始扫描待收货订单***************");
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setStatus(ECommodityOrderDetailStatus.TORECEIVE.getCode());
        condition.setDeliverDatetimeEnd(
            DateUtil.getRelativeDateOfDays(new Date(), -15));
        List<CommodityOrderDetail> detailList = commodityOrderDetailBO
            .queryDetailList(condition);
        for (CommodityOrderDetail detail : detailList) {
            commodityOrderDetailBO.refreshReceive(detail);
        }
        logger.info("***************结束扫描待收货订单***************");
    }

    @Override
    public XN629048Res getOrderDkAmount(String code) {
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(code);

        return distributionOrderBO.getOrderDeductAmount(
            commodityOrder.getAmount(), commodityOrder.getApplyUser(),
            EBoolean.YES.getCode());
    }

    @Override
    public Paginable<CommodityOrder> queryOrderPage(int start, int limit,
            CommodityOrder condition) {
        Paginable<CommodityOrder> page = commodityOrderBO.getPaginable(start,
            limit, condition);
        for (CommodityOrder order : page.getList()) {
            initOrder(order);
        }
        return page;
    }

    @Override
    public List<CommodityOrder> queryOrderList(CommodityOrder condition) {
        List<CommodityOrder> dataList = commodityOrderBO
            .queryOrderList(condition);
        for (CommodityOrder order : dataList) {
            initOrder(order);
        }
        return dataList;
    }

    @Override
    public CommodityOrder getCommodityOrder(String code) {
        CommodityOrder order = commodityOrderBO.getCommodityOrder(code);
        initOrder(order);
        return order;
    }

    private void initOrder(CommodityOrder order) {
        // 获得明细列表
        List<CommodityOrderDetail> detailList = commodityOrderDetailBO
            .queryOrderDetail(order.getCode());

        // 获得所包含的店铺编号
        List<String> shopCodes = new ArrayList<String>();
        for (CommodityOrderDetail detail : detailList) {
            if (!shopCodes.contains(detail.getShopCode())) {
                shopCodes.add(detail.getShopCode());
            }
        }
        List<CommodityShopOrder> shopOrders = new ArrayList<CommodityShopOrder>();

        // 分配店铺订单
        for (String shopCode : shopCodes) {
            CommodityOrderDetail condition = new CommodityOrderDetail();
            condition.setShopCode(shopCode);
            condition.setOrderCode(order.getCode());
            List<CommodityOrderDetail> shopDetail = commodityOrderDetailBO
                .queryDetailList(condition);
            CommodityShopOrder shopOrder = new CommodityShopOrder();
            shopOrder.setOrderCode(order.getCode());
            shopOrder.setShopCode(shopCode);
            shopOrder.setShopName(companyBO.getCompany(shopCode).getName());
            shopOrder.setDetailList(shopDetail);
            shopOrders.add(shopOrder);
        }
        order.setShopOrderList(shopOrders);

        // 卖家
        StringBuilder sellersName = new StringBuilder();
        for (String shopCode : shopCodes) {
            Company shop = companyBO.getCompany(shopCode);
            sellersName.append(shop.getName()).append(".");
        }
        order.setSellersName(sellersName.toString());

        // 支付流水编号
        Account userCnyAccount = accountBO
            .getAccountByUser(order.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(order.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            order.setJourCode(jourList.get(0).getCode());
        }

        // 地址
        Address address = addressBO.getAddress(order.getAddressCode());
        order.setAddress(address);
    }

}
