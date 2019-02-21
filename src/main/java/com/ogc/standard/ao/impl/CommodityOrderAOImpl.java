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
import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.ao.IWeChatAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IDefaultPostageBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IPostageTemplateBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.DefaultPostage;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.PostageTemplate;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN629714Req;
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.dto.res.XN629801Res;
import com.ogc.standard.dto.res.XN629802Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EDefaultPostageTypeStatus;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESellType;
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
    private ICommodityOrderDetailAO commodityOrderDetailAO;

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
    private IJourBO jourBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private IWeChatAO weChatAO;

    @Autowired
    private IAddressBO addressBO;

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IPostageTemplateBO postageTemplateBO;

    @Autowired
    private IDefaultPostageBO defaultPostageBO;

    @Override
    @Transactional
    public String commitCommodityOrder(String applyUser, String applyNote,
            String expressType, Long specsId, Long quantity,
            String addressCode) {

        // 规格检验
        CommoditySpecs specs = commoditySpecsBO.getCommoditySpecs(specsId);
        if (quantity > specs.getInventory()) {
            throw new BizException("xn0000", "产品库存不足，无法下单");
        }

        Commodity commodity = commodityBO
            .getCommodity(specs.getCommodityCode());

        // 落地订单数据
        String orderCode = commodityOrderBO.saveOrder(applyUser, applyNote,
            null, commodity.getShopCode(), expressType, applyUser, applyNote,
            addressCode);

        // 落地订单明细
        commodityOrderDetailBO.saveDetail(orderCode, commodity.getShopCode(),
            commodity.getCode(), commodity.getName(), specsId, specs.getName(),
            applyUser, quantity, specs.getPrice(), addressCode);

        // 更新库存
        commoditySpecsBO.refreshInventory(specsId, -quantity);

        // 加上数量与总价
        BigDecimal amount = specs.getPrice()
            .multiply(BigDecimal.valueOf(quantity));

        BigDecimal postalFee = getPostage(commodity.getCode(), addressCode);

        commodityOrderBO.refreshAmount(quantity, amount, orderCode, postalFee);

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

        commodityOrderDetailBO.refreshStatus(code,
            ECommodityOrderDetailStatus.CANCLED.getCode());
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

        // 积分抵扣处理，订单只有一个商品
        Double maxJfdkRate = 0d;
        List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
            .queryOrderDetail(req.getCode());
        if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
            maxJfdkRate = commodityOrderDetailList.get(0).getMaxJfdkRate();
        }
        XN629048Res deductRes = distributionOrderBO
            .getCommodityOrderDeductAmount(maxJfdkRate, order.getAmount(),
                order.getApplyUser(), req.getIsJfDeduct());

        // 支付
        Object result = null;
        if (EPayType.ALIPAY.getCode().equals(req.getPayType())) {

            // 状态更新
            commodityOrderBO.refreshPayGroup(order, req.getPayType(),
                deductRes);

            String signOrder = alipayBO.getSignedOrder(order.getApplyUser(),
                ESysUser.SYS_USER.getCode(), order.getPayGroup(),
                EJourBizTypeUser.COMMODITY.getCode(),
                EJourBizTypeUser.COMMODITY.getValue(),
                order.getAmount().subtract(deductRes.getCnyAmount()));
            result = new PayOrderRes(signOrder);

        } else if (EPayType.YE.getCode().equals(req.getPayType())) {

            result = toPayCommodityOrderYue(order, deductRes);

        } else if (EPayType.WEIXIN_H5.getCode().equals(req.getPayType())) {

            // 状态更新
            commodityOrderBO.refreshPayGroup(order, req.getPayType(),
                deductRes);

            User user = userBO.getUser(order.getApplyUser());
            result = weChatAO.getPrepayIdH5(order.getApplyUser(),
                user.getH5OpenId(), ESysUser.SYS_USER.getCode(),
                order.getPayGroup(), order.getPayGroup(),
                EJourBizTypeUser.COMMODITY.getCode(),
                EJourBizTypeUser.COMMODITY.getValue(),
                order.getAmount().subtract(deductRes.getCnyAmount()));

        } else {
            throw new BizException("xn0000", "不支持的支付方式");
        }

        return result;
    }

    @Override
    @Transactional
    public Object toPayGroupCommodityOrder(XN629714Req req) {

        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryCommodityOrderByPayGroup(req.getPayGroup());

        // 支付密码确认
        if (StringUtils.isNotBlank(req.getTradePwd())) {
            userBO.checkTradePwd(commodityOrderList.get(0).getApplyUser(),
                req.getTradePwd());
        }

        Object result = null;

        // 支付
        if (EPayType.YE.getCode().equals(req.getPayType())) {

            for (CommodityOrder order : commodityOrderList) {

                // 积分抵扣处理，订单只有一个商品
                Double maxJfdkRate = 0d;
                List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                    .queryOrderDetail(order.getCode());
                if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                    maxJfdkRate = commodityOrderDetailList.get(0)
                        .getMaxJfdkRate();
                }

                XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
                    BigDecimal.ZERO);
                // XN629048Res deductRes = distributionOrderBO
                // .getCommodityOrderDeductAmount(maxJfdkRate,
                // order.getAmount(), order.getApplyUser(),
                // req.getIsJfDeduct());

                result = toPayCommodityOrderYue(order, deductRes);

            }

        } else if (EPayType.ALIPAY.getCode().equals(req.getPayType())) {

            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal cnyAmount = BigDecimal.ZERO;
            CommodityOrder commonOrder = commodityOrderList.get(0);

            for (CommodityOrder order : commodityOrderList) {

                // 积分抵扣处理，订单只有一个商品
                Double maxJfdkRate = 0d;
                List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                    .queryOrderDetail(order.getCode());
                if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                    maxJfdkRate = commodityOrderDetailList.get(0)
                        .getMaxJfdkRate();
                }
                XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
                    BigDecimal.ZERO);
                // XN629048Res deductRes = distributionOrderBO
                // .getCommodityOrderDeductAmount(maxJfdkRate,
                // order.getAmount(), order.getApplyUser(),
                // req.getIsJfDeduct());

                // 状态更新
                commodityOrderBO.refreshPayGroup(order, req.getPayType(),
                    deductRes);

                amount = amount.add(order.getAmount());
                cnyAmount = cnyAmount.add(deductRes.getCnyAmount());

            }

            String signOrder = alipayBO.getSignedOrder(
                commonOrder.getApplyUser(), ESysUser.SYS_USER.getCode(),
                commonOrder.getPayGroup(), EJourBizTypeUser.COMMODITY.getCode(),
                EJourBizTypeUser.COMMODITY.getValue(),
                amount.subtract(cnyAmount));
            result = new PayOrderRes(signOrder);

        } else if (EPayType.WEIXIN_H5.getCode().equals(req.getPayType())) {

            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal cnyAmount = BigDecimal.ZERO;
            CommodityOrder commonOrder = commodityOrderList.get(0);

            for (CommodityOrder order : commodityOrderList) {

                // 积分抵扣处理，订单只有一个商品
                Double maxJfdkRate = 0d;
                List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                    .queryOrderDetail(order.getCode());
                if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                    maxJfdkRate = commodityOrderDetailList.get(0)
                        .getMaxJfdkRate();
                }
                XN629048Res deductRes = distributionOrderBO
                    .getCommodityOrderDeductAmount(maxJfdkRate,
                        order.getAmount(), order.getApplyUser(),
                        req.getIsJfDeduct());

                // 状态更新
                commodityOrderBO.refreshPayGroup(order, req.getPayType(),
                    deductRes);

                amount = amount.add(order.getAmount());
                cnyAmount = cnyAmount.add(deductRes.getCnyAmount());

            }

            User user = userBO.getUser(commonOrder.getApplyUser());
            result = weChatAO.getPrepayIdH5(commonOrder.getApplyUser(),
                user.getH5OpenId(), ESysUser.SYS_USER.getCode(),
                commonOrder.getPayGroup(), commonOrder.getPayGroup(),
                EJourBizTypeUser.COMMODITY.getCode(),
                EJourBizTypeUser.COMMODITY.getValue(),
                amount.subtract(cnyAmount));

        } else {
            throw new BizException("xn0000", "不支持的支付方式");
        }

        return result;
    }

    // 余额支付
    public Object toPayCommodityOrderYue(CommodityOrder data,
            XN629048Res resultRes) {

        Account userCnyAccount = accountBO.getAccountByUser(data.getApplyUser(),
            ECurrency.CNY.getCode());
        BigDecimal payAmount = data.getAmount()
            .subtract(resultRes.getCnyAmount().add(data.getPostalFee()));// 实际付款人民币金额
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

        // 进行分销
        BigDecimal backJfAmount = distributionOrderBO.commodityDistribution(
            data.getCode(), data.getShopOwner(), data.getAmount(),
            data.getApplyUser(), ESellType.COMMODITY.getCode(), resultRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveCommodityBulletin(data.getApplyUser(),
            data.getQuantity().toString());

        // 业务订单更改
        commodityOrderBO.payYueSuccess(data, resultRes, backJfAmount);

        // commodityOrderDetailAO.refreshDkAmount(data.getPayGroup());

        commodityOrderDetailBO.refreshStatus(data.getCode(),
            ECommodityOrderDetailStatus.TODELIVE.getCode());

        // 更新商品月销量
        List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
            .queryOrderDetail(data.getCode());
        if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
            for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetailList) {
                Commodity commodity = commodityBO
                    .getCommodity(commodityOrderDetail.getCommodityCode());
                commodityBO.refreshMonthSellCount(commodity,
                    commodity.getMonthSellCount()
                            + commodityOrderDetail.getQuantity());
            }
        }

        return new BooleanRes(true);
    }

    @Override
    public void paySuccess(String payGroup) {
        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryCommodityOrderByPayGroup(payGroup);

        for (CommodityOrder data : commodityOrderList) {

            // 进行分销
            XN629048Res resultRes = new XN629048Res(data.getCnyDeductAmount(),
                data.getJfDeductAmount());
            BigDecimal backJfAmount = distributionOrderBO.commodityDistribution(
                data.getCode(), data.getShopOwner(), data.getAmount(),
                data.getApplyUser(), ESellType.COMMODITY.getCode(), resultRes);

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // 添加快报
            smsBO.saveCommodityBulletin(data.getApplyUser(),
                data.getQuantity().toString());

            // 业务订单更改
            commodityOrderBO.paySuccess(data.getCode(), data.getAmount()
                .subtract(resultRes.getCnyAmount()).add(data.getPostalFee()),
                backJfAmount);

            commodityOrderDetailBO.refreshStatus(data.getCode(),
                ECommodityOrderDetailStatus.TODELIVE.getCode());

            // 更新商品月销量
            List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                .queryOrderDetail(data.getCode());
            if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetailList) {
                    Commodity commodity = commodityBO
                        .getCommodity(commodityOrderDetail.getCommodityCode());
                    commodityBO.refreshMonthSellCount(commodity,
                        commodity.getMonthSellCount()
                                + commodityOrderDetail.getQuantity());
                }
            }
        }

        // commodityOrderDetailAO.refreshDkAmount(payGroup);

    }

    @Override
    public void editAddress(String code, String addressCode) {
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(code);

        // 状态判断
        if (!ECommodityOrderStatus.TO_PAY.getCode()
            .equals(commodityOrder.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可修改收货地址的状态");
        }

        commodityOrderBO.refreshAddress(code, addressCode);
    }

    @Override
    @Transactional
    public void delive(String code, String logisticsCompany,
            String logisticsNumber, String deliver) {
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(code);

        // 状态判断
        if (!ECommodityOrderStatus.TODELIVE.getCode()
            .equals(commodityOrder.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可收货的状态");
        }

        commodityOrderBO.refershDelive(commodityOrder, logisticsCompany,
            logisticsNumber, deliver);

        commodityOrderDetailBO.refreshStatus(code,
            ECommodityOrderDetailStatus.TORECEIVE.getCode());

    }

    @Override
    @Transactional
    public void receive(String code, String receiver) {
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(code);

        // 状态判断
        if (!ECommodityOrderStatus.TORECEIVE.getCode()
            .equals(commodityOrder.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可收货的状态");
        }

        // 状态更新
        commodityOrderBO.refreshReceive(commodityOrder);

        // 更新订单明细状态
        commodityOrderDetailBO.toCommentByOrder(code);

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

        commodityOrderDetailBO.refreshStatus(order.getCode(),
            ECommodityOrderDetailStatus.CANCLED.getCode());

    }

    public void doReceive() {
        logger.info("***************开始扫描待收货订单***************");
        CommodityOrder condition = new CommodityOrder();
        condition.setStatus(ECommodityOrderStatus.TORECEIVE.getCode());
        condition.setDeliverDatetimeEnd(
            DateUtil.getRelativeDateOfDays(new Date(), -15));
        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryOrderList(condition);

        for (CommodityOrder commodityOrder : commodityOrderList) {
            commodityOrderBO.refreshReceive(commodityOrder);

            commodityOrderDetailBO.refreshStatus(commodityOrder.getCode(),
                ECommodityOrderDetailStatus.TO_COMMENT.getCode());
        }
        logger.info("***************结束扫描待收货订单***************");
    }

    @Override
    public XN629048Res getOrderDkAmount(String code) {
        CommodityOrder commodityOrder = commodityOrderBO
            .getCommodityOrder(code);

        // 最大积分抵扣比例
        Double maxJfdkRate = 0d;
        List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
            .queryOrderDetail(code);
        if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
            maxJfdkRate = commodityOrderDetailList.get(0).getMaxJfdkRate();
        }

        return distributionOrderBO.getCommodityOrderDeductAmount(maxJfdkRate,
            commodityOrder.getAmount(), commodityOrder.getApplyUser(),
            EBoolean.YES.getCode());
    }

    @Override
    public XN629048Res getGroupOrderDkAmount(String payGroup) {
        return distributionOrderBO.getCommodityGroupOrderDeductAmount(payGroup,
            EBoolean.YES.getCode());
    }

    private BigDecimal getPostage(String commodityCode, String addressCode) {

        List<String> commodityCodeList = new ArrayList<String>();
        commodityCodeList.add(commodityCode);

        BigDecimal postalFee = getPostalFee(commodityCodeList, addressCode);

        return postalFee;
    }

    @Override
    public XN629801Res getPostage(List<String> commodityCodeList,
            String addressCode) {

        BigDecimal postalFee = getPostalFee(commodityCodeList, addressCode);

        return new XN629801Res(postalFee);

    }

    private BigDecimal getPostalFee(List<String> commodityCodeList,
            String addressCode) {
        BigDecimal postalFee = BigDecimal.ZERO;

        List<Commodity> deliverPlaceList = commodityBO
            .queryDeliverPlaceListByShop(commodityCodeList);
        Address address = addressBO.getAddress(addressCode);

        for (Commodity deliverPlace : deliverPlaceList) {
            PostageTemplate postageTemplate = postageTemplateBO
                .getPostageTemplate(deliverPlace.getShopCode(),
                    deliverPlace.getDeliverPlace(), address.getProvince());

            if (null == postageTemplate) {

                if (deliverPlace.getDeliverPlace()
                    .equals(address.getProvince())) {
                    // 同省
                    DefaultPostage defaultPostage = defaultPostageBO
                        .getDefaultPostage(deliverPlace.getShopCode(),
                            EDefaultPostageTypeStatus.SAME_PROVINCE.getCode());

                    if (null != defaultPostage) {
                        postalFee = postalFee.add(defaultPostage.getPrice());
                    }

                } else {
                    // 跨省
                    DefaultPostage defaultPostage = defaultPostageBO
                        .getDefaultPostage(deliverPlace.getShopCode(),
                            EDefaultPostageTypeStatus.DIF_PROVINCE.getCode());

                    if (null != defaultPostage) {
                        postalFee = postalFee.add(defaultPostage.getPrice());
                    }

                }

            } else {
                postalFee = postalFee.add(postageTemplate.getPrice());
            }
        }
        return postalFee;
    }

    @Override
    public List<XN629802Res> getInfoByPayGroup(String payGroup) {
        List<CommodityOrder> commodityOrders = commodityOrderBO
            .queryCommodityOrderByPayGroup(payGroup);
        List<XN629802Res> resList = new ArrayList<XN629802Res>();

        if (CollectionUtils.isNotEmpty(commodityOrders)) {
            for (CommodityOrder commodityOrder : commodityOrders) {
                Company shop = companyBO
                    .getCompany(commodityOrder.getShopCode());

                List<CommodityOrderDetail> commodityOrderDetails = commodityOrderDetailBO
                    .queryOrderDetail(commodityOrder.getCode());
                if (CollectionUtils.isNotEmpty(commodityOrderDetails)) {
                    for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetails) {
                        commodityOrderDetail
                            .setCommodity(commodityBO.getCommodity(
                                commodityOrderDetail.getCommodityCode()));
                    }
                }

                XN629802Res res = new XN629802Res(shop, commodityOrderDetails);
                resList.add(res);
            }

        }

        return resList;
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
    public CommodityOrder getCommodityOrder(String code, String isSettle) {
        CommodityOrder order = commodityOrderBO.getCommodityOrder(code);

        initOrder(order);

        if (EBoolean.YES.getCode().equals(isSettle)) {
            List<Settle> settleList = settleBO.querySettleList(code);
            if (CollectionUtils.isNotEmpty(settleList)) {
                for (Settle settle : settleList) {
                    AgentUser agentUser = agentUserBO
                        .getAgentUser(settle.getUserId());
                    settle.setAgentUser(agentUser);
                }
            }
            order.setSettleList(settleList);
        }

        return order;
    }

    private void initOrder(CommodityOrder order) {
        // 获得明细列表
        List<CommodityOrderDetail> detailList = commodityOrderDetailBO
            .queryOrderDetail(order.getCode());
        order.setDetailList(detailList);

        // 卖家
        Company shop = companyBO.getCompany(order.getShopCode());
        if (null != shop) {
            order.setSellersName(shop.getName());
        }

        // 支付流水编号
        Account userCnyAccount = accountBO
            .getAccountByUser(order.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(order.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            order.setJourCode(jourList.get(0).getCode());
        }

        // 下单人
        User applyUser = userBO.getUserUnCheck(order.getApplyUser());
        String applyUserName = null;
        if (null != applyUser) {
            applyUserName = applyUser.getMobile();
            if (null != applyUser.getRealName()) {
                applyUserName = applyUser.getRealName().concat(applyUserName);
            }
        }
        order.setApplyUserName(applyUserName);

        // 收货人
        order.setReceiverName(order.getReceiver());
    }

}
