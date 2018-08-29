package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.ao.ISimuOrderAO;
import com.ogc.standard.bo.IGroupCoinBO;
import com.ogc.standard.bo.ISimuOrderBO;
import com.ogc.standard.bo.ISimuOrderDetailBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GroupCoin;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.domain.SimuOrderDetail;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN650050Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EJourBizType;
import com.ogc.standard.enums.ESimuOrderDirection;
import com.ogc.standard.enums.ESimuOrderStatus;
import com.ogc.standard.enums.ESimuOrderType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import com.ogc.standard.market.MarketDepth;
import com.ogc.standard.market.MarketDepthItem;
import com.ogc.standard.util.SymbolUtil;

@Service
public class SimuOrderAOImpl implements ISimuOrderAO {

    private static Logger logger = Logger.getLogger(SimuOrderAOImpl.class);

    @Autowired
    private ISimuOrderBO simuOrderBO;

    @Autowired
    private ISimuOrderDetailBO simuOrderDetailBO;

    @Autowired
    private IMarketAO marketAO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IGroupCoinBO groupCoinBO;

    @Override
    @Transactional
    public String submit(XN650050Req req) {

        SimuOrder data = null;

        if (ESimuOrderDirection.BUY.getCode().equals(req.getDirection())) {

            // 买入单
            data = submitBuyOrder(req);

        } else if (ESimuOrderDirection.SELL.getCode()
            .equals(req.getDirection())) {

            // 卖出单
            data = submitSellOrder(req);

        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不支持买卖方向");
        }

        return data.getCode();
    }

    @Override
    public void buySuccessOrder(String orderCode) {
        // 当前挂单的订单是否可立即成交处理
        SimuOrder data = simuOrderBO.getSimuOrder(orderCode);
        MarketDepth marketDepth = marketAO.getMarketDepth(
            SymbolUtil.getSymbolPair(data.getSymbol(), data.getToSymbol()),
            data.getExchange());
        List<MarketDepthItem> asksList = marketDepth.getAsks();
        boolean breakFlag = false;
        for (MarketDepthItem marketDepthItem : asksList) {
            BigDecimal haveCount = data.getTotalCount()
                .subtract(data.getTradedCount());
            if (data.getPrice().compareTo(marketDepthItem.getPrice()) >= 0) {
                BigDecimal tradedCount = BigDecimal.ZERO;
                if (haveCount.compareTo(marketDepthItem.getAmount()) > 0) {
                    tradedCount = marketDepthItem.getAmount();
                } else {
                    tradedCount = haveCount;
                    breakFlag = true;
                }

                SimuOrderDetail simuOrderDetail = new SimuOrderDetail();
                String code = OrderNoGenerater
                    .generate(EGeneratePrefix.SIMU_ORDER_DETAIL.getCode());
                simuOrderDetail.setCode(code);
                simuOrderDetail.setOrderCode(orderCode);
                simuOrderDetail.setTradedPrice(marketDepthItem.getPrice());
                simuOrderDetail.setTradedCount(tradedCount);
                BigDecimal tradedAmount = marketDepthItem.getPrice()
                    .multiply(marketDepthItem.getAmount());
                simuOrderDetail.setTradedAmount(tradedAmount);
                simuOrderDetail.setTradedFee(BigDecimal.ZERO);
                simuOrderDetailBO.saveSimuOrderDetail(simuOrderDetail);
                if (breakFlag) {
                    break;
                }
            }
        }

    }

    @Override
    @Transactional
    public void cancel(String code) {
        SimuOrder data = simuOrderBO.getSimuOrder(code);
        if (!ESimuOrderStatus.SUBMIT.getCode().equals(data.getStatus())
                && !ESimuOrderStatus.PART_DEAL.getCode()
                    .equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前状态下不支持撤单");
        }

        // 买入订单解冻计价币种数量
        if (ESimuOrderDirection.BUY.getCode().equals(data.getDirection())) {

            GroupCoin gcAccount = groupCoinBO.getGroupCoin(data.getGroupCode(),
                data.getUserId(), data.getToSymbol());
            groupCoinBO.unfrozenAmount(gcAccount, data.getTotalCount(),
                EJourBizType.BUY_ORDER_UNFROZEN.getCode(),
                "提交购买[" + SymbolUtil.getSymbolPair(data.getSymbol(),
                    data.getToSymbol()) + "]委托撤单",
                code);
        }

        simuOrderBO.cancel(data);
    }

    @Override
    public Paginable<SimuOrder> querySimuOrderPage(int start, int limit,
            SimuOrder condition) {
        return simuOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SimuOrder> querySimuOrderList(SimuOrder condition) {
        return simuOrderBO.querySimuOrderList(condition);
    }

    @Override
    public SimuOrder getSimuOrder(String code) {
        return simuOrderBO.getSimuOrder(code);
    }

    private SimuOrder submitBuyOrder(XN650050Req req) {

        // 买入币总数量
        BigDecimal totalCount = StringValidater
            .toBigDecimal(req.getTotalCount());

        // 单个币买入价格（所需计价币种的数量）
        BigDecimal price = getBuyPrice(req);

        // 所有币买入，总共需要的计价币种的数量
        BigDecimal totalAmount = price.multiply(totalCount);

        User user = userBO.getUser(req.getUserId());
        if (user == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "用户编号" + req.getUserId() + "不存在");
        }

        // 检查计价币种是否存在 和 账户余额
        GroupCoin groupCoin = groupCoinBO.getGroupCoin(req.getGroupCode(),
            req.getUserId(), req.getToSymbol());
        if (groupCoin.getCount().subtract(groupCoin.getFrozenCount())
            .compareTo(totalAmount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前组合" + groupCoin.getSymbol() + "资产可用余额不足");
        }

        // 落地委托单
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SIMU_ORDER.getCode());
        SimuOrder data = new SimuOrder();
        data.setCode(code);
        data.setGroupCode(req.getGroupCode());
        data.setUserId(req.getUserId());
        data.setExchange(req.getExchange());
        data.setSymbol(req.getSymbol());

        data.setToSymbol(req.getToSymbol());
        data.setType(req.getType());
        data.setDirection(req.getDirection());
        data.setPrice(price);
        data.setTotalCount(totalCount);

        data.setTotalAmount(totalAmount);
        data.setTradedCount(BigDecimal.ZERO);
        data.setTradedAmount(BigDecimal.ZERO);
        data.setTradedFee(BigDecimal.ZERO);
        data.setLastTradedDatetime(new Date());

        data.setCreateDatetime(new Date());
        data.setStatus(ESimuOrderStatus.SUBMIT.getCode());
        simuOrderBO.saveSimuOrder(data);

        // 冻结计价币种
        groupCoinBO.frozenCount(groupCoin, totalAmount,
            EJourBizType.BUY_ORDER_FROZEN.getCode(), "提交购买[" + SymbolUtil
                .getSymbolPair(req.getSymbol(), req.getToSymbol()) + "]委托单",
            code);

        return data;
    }

    private SimuOrder submitSellOrder(XN650050Req req) {

        // 卖出币总数量
        BigDecimal totalCount = StringValidater
            .toBigDecimal(req.getTotalCount());

        // 单个币卖出价格（所需计价币种的数量）
        BigDecimal price = getSellPrice(req);

        // 所有币卖出，总共需要的计价币种的数量
        BigDecimal totalAmount = price.multiply(totalCount);

        User user = userBO.getUser(req.getUserId());
        if (user == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "用户编号" + req.getUserId() + "不存在");
        }

        // 检查卖出币种账户 和 账户余额
        GroupCoin groupCoin = groupCoinBO.getGroupCoin(req.getGroupCode(),
            req.getUserId(), req.getSymbol());
        if (groupCoin.getCount().subtract(groupCoin.getFrozenCount())
            .compareTo(totalCount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前组合" + req.getSymbol() + "资产可用余额不足");
        }

        // 落地委托单
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SIMU_ORDER.getCode());
        SimuOrder data = new SimuOrder();
        data.setCode(code);
        data.setGroupCode(req.getGroupCode());
        data.setUserId(req.getUserId());
        data.setExchange(req.getExchange());
        data.setSymbol(req.getSymbol());

        data.setToSymbol(req.getToSymbol());
        data.setType(req.getType());
        data.setDirection(req.getDirection());
        data.setPrice(price);
        data.setTotalCount(totalCount);

        data.setTotalAmount(totalAmount);
        data.setTradedCount(BigDecimal.ZERO);
        data.setTradedAmount(BigDecimal.ZERO);
        data.setTradedFee(BigDecimal.ZERO);
        data.setLastTradedDatetime(new Date());

        data.setCreateDatetime(new Date());
        data.setStatus(ESimuOrderStatus.SUBMIT.getCode());
        simuOrderBO.saveSimuOrder(data);

        // 冻结出售币种资产
        groupCoinBO.frozenCount(groupCoin, totalCount,
            EJourBizType.SELL_ORDER_FROZEN.getCode(), "提交卖出[" + SymbolUtil
                .getSymbolPair(req.getSymbol(), req.getToSymbol()) + "]委托单",
            code);

        return data;

    }

    private BigDecimal getBuyPrice(XN650050Req req) {
        BigDecimal price = null;
        if (ESimuOrderType.LIMIT.getCode().equals(req.getType())) {
            price = StringValidater.toBigDecimal(req.getPrice());
        } else {
            MarketDepth marketDepth = marketAO.getMarketDepth(
                SymbolUtil.getSymbolPair(req.getSymbol(), req.getToSymbol()),
                req.getExchange());
            price = marketDepth.getAsks().get(0).getPrice();
        }
        return price;
    }

    private BigDecimal getSellPrice(XN650050Req req) {
        BigDecimal price = null;
        if (ESimuOrderType.LIMIT.getCode().equals(req.getType())) {
            price = StringValidater.toBigDecimal(req.getPrice());
        } else {
            MarketDepth marketDepth = marketAO.getMarketDepth(
                SymbolUtil.getSymbolPair(req.getSymbol(), req.getToSymbol()),
                req.getExchange());
            price = marketDepth.getBids().get(0).getPrice();
        }
        return price;
    }

    @Override
    public void doCheckDeal() {

        boolean isDebug = false;

        if (isDebug) {
            logger.info("**** 扫描委托单开始 ******");
        }

        List<String> statusList = new ArrayList<>();
        statusList.add(ESimuOrderStatus.SUBMIT.getCode());
        statusList.add(ESimuOrderStatus.PART_DEAL.getCode());

        SimuOrder condition = new SimuOrder();
        condition.setStatusList(statusList);

        List<SimuOrder> simuOrders = simuOrderBO.querySimuOrderList(condition);
        if (CollectionUtils.isNotEmpty(simuOrders)) {
            for (SimuOrder simuOrder : simuOrders) {
                if (isDebug) {
                    logger
                        .info("**** 共扫描到" + simuOrders.size() + "个委托单 ******");
                }
                doCheck(simuOrder);

            }
        }
        if (isDebug) {
            logger.info("**** 扫描委托单结束 ******");
        }

    }

    private void doCheck(SimuOrder simuOrder) {
        try {

            MarketDepth marketDepth = marketAO.getMarketDepth(SymbolUtil
                .getSymbolPair(simuOrder.getSymbol(), simuOrder.getToSymbol()),
                simuOrder.getExchange());

            if (ESimuOrderDirection.BUY.getCode()
                .equals(simuOrder.getDirection())) {

                MarketDepthItem sellOne = marketDepth.getAsks().get(0);
                if (sellOne.getPrice().compareTo(simuOrder.getPrice()) <= 0) {
                    buySuccess(simuOrder, sellOne.getPrice());
                }

            } else if (ESimuOrderDirection.SELL.getCode()
                .equals(simuOrder.getDirection())) {

                MarketDepthItem buyOne = marketDepth.getBids().get(0);
                if (buyOne.getPrice().compareTo(simuOrder.getPrice()) >= 0) {
                    sellSuccess(simuOrder, buyOne.getPrice());
                }

            }

        } catch (Exception e) {
            logger.error(
                "扫描委托单" + simuOrder.getCode() + "发送异常，原因：" + e.getMessage());
        }

    }

    @Override
    @Transactional
    public void buySuccess(SimuOrder simuOrder, BigDecimal tradePrice) {

        // 第一版本 实现逻辑：价格匹配，全部成交
        BigDecimal tradeCount = simuOrder.getTotalCount();

        SimuOrderDetail simuOrderDetail = new SimuOrderDetail();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SIMU_ORDER_DETAIL.getCode());
        simuOrderDetail.setCode(code);
        simuOrderDetail.setOrderCode(simuOrder.getCode());
        simuOrderDetail.setTradedPrice(tradePrice);
        simuOrderDetail.setTradedCount(tradeCount);
        simuOrderDetail.setTradedAmount(tradeCount.multiply(tradePrice));

        simuOrderDetail.setTradedFee(BigDecimal.ZERO);
        simuOrderDetail.setCreateDatetime(new Date());
        simuOrderDetailBO.saveSimuOrderDetail(simuOrderDetail);

        // 更新委托单装填
        simuOrder.setTradedCount(tradeCount);
        simuOrder.setTradedAmount(simuOrderDetail.getTradedAmount());
        simuOrder.setTradedFee(simuOrderDetail.getTradedFee());
        simuOrder.setLastTradedDatetime(simuOrderDetail.getCreateDatetime());
        simuOrder.setStatus(ESimuOrderStatus.ENTIRE_DEAL.getCode());

        GroupCoin gcAccount = groupCoinBO.getGroupCoin(simuOrder.getGroupCode(),
            simuOrder.getUserId(), simuOrder.getToSymbol());

        // 解冻金额
        gcAccount = groupCoinBO.unfrozenAmount(gcAccount,
            simuOrder.getTotalCount(),
            EJourBizType.BUY_ORDER_UNFROZEN.getCode(),
            EJourBizType.BUY_ORDER_UNFROZEN.getValue(), code);

        // 扣减
        groupCoinBO.changeAmount(gcAccount, simuOrder.getTotalCount().negate(),
            simuOrder.getCode(), EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            EJourBizType.BUY_ORDER_SUCCESS.getValue());

        // 添加购买的交易币种金额
        GroupCoin symbolAccount = groupCoinBO.checkAccountAndDistribute(
            simuOrder.getUserId(), gcAccount.getGroupCode(),
            simuOrder.getSymbol());
        symbolAccount = groupCoinBO.changeAmount(symbolAccount,
            simuOrder.getTotalCount(), simuOrder.getCode(),
            EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            EJourBizType.BUY_ORDER_SUCCESS.getValue());

        // 修改组合币种配置占比

        // 更新委托单状态
        simuOrderBO.tradeSuccess(simuOrder);

    }

    @Override
    @Transactional
    public void sellSuccess(SimuOrder simuOrder, BigDecimal tradePrice) {

        // 第一版本 实现逻辑：价格匹配，全部成交
        BigDecimal tradeCount = simuOrder.getTotalCount();

        SimuOrderDetail simuOrderDetail = new SimuOrderDetail();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.SIMU_ORDER_DETAIL.getCode());
        simuOrderDetail.setCode(code);
        simuOrderDetail.setOrderCode(simuOrder.getCode());
        simuOrderDetail.setTradedPrice(tradePrice);
        simuOrderDetail.setTradedCount(tradeCount);
        simuOrderDetail.setTradedAmount(tradeCount.multiply(tradePrice));

        simuOrderDetail.setTradedFee(BigDecimal.ZERO);
        simuOrderDetail.setCreateDatetime(new Date());
        simuOrderDetailBO.saveSimuOrderDetail(simuOrderDetail);

        // 更新委托单装填
        simuOrder.setTradedCount(tradeCount);
        simuOrder.setTradedAmount(simuOrderDetail.getTradedAmount());
        simuOrder.setTradedFee(simuOrderDetail.getTradedFee());
        simuOrder.setLastTradedDatetime(simuOrderDetail.getCreateDatetime());
        simuOrder.setStatus(ESimuOrderStatus.ENTIRE_DEAL.getCode());

        GroupCoin gcAccount = groupCoinBO.getGroupCoin(simuOrder.getGroupCode(),
            simuOrder.getUserId(), simuOrder.getToSymbol());

        // 解冻金额
        gcAccount = groupCoinBO.unfrozenAmount(gcAccount,
            simuOrder.getTotalCount(),
            EJourBizType.SELL_ORDER_UNFROZEN.getCode(),
            EJourBizType.SELL_ORDER_UNFROZEN.getValue(), code);

        // 扣减币种金额
        groupCoinBO.changeAmount(gcAccount, simuOrder.getTotalCount().negate(),
            simuOrder.getCode(), EJourBizType.SELL_ORDER_SUCCESS.getCode(),
            EJourBizType.SELL_ORDER_SUCCESS.getValue());

        // 增加计价币种金额
        GroupCoin symbolAccount = groupCoinBO.checkAccountAndDistribute(
            simuOrder.getUserId(), simuOrder.getExchange(),
            simuOrder.getToSymbol());
        symbolAccount = groupCoinBO.changeAmount(symbolAccount,
            simuOrder.getTotalCount(), simuOrder.getCode(),
            EJourBizType.BUY_ORDER_SUCCESS.getCode(),
            EJourBizType.BUY_ORDER_SUCCESS.getValue());

        // 修改组合币种配置占比

        // 更新委托单状态
        simuOrderBO.tradeSuccess(simuOrder);

    }

}
