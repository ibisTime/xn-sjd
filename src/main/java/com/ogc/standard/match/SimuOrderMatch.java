package com.ogc.standard.match;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ogc.standard.bo.IHandicapBO;
import com.ogc.standard.bo.ISimuKLineBO;
import com.ogc.standard.bo.ISimuMatchResultBO;
import com.ogc.standard.bo.ISimuOrderBO;
import com.ogc.standard.bo.ISimuOrderDetailBO;
import com.ogc.standard.bo.ISimuOrderHistoryBO;
import com.ogc.standard.domain.Handicap;
import com.ogc.standard.domain.SimuKLine;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.domain.SimuOrderDetail;
import com.ogc.standard.enums.ESimuOrderDirection;
import com.ogc.standard.enums.ESimuOrderStatus;
import com.ogc.standard.enums.ESimuOrderType;

/**
 * 币币交易撮合类
 * @author: lei 
 * @since: 2018年8月31日 下午4:50:02 
 * @history:
 */
public class SimuOrderMatch {

    @Autowired
    private ISimuOrderBO simuOrderBO;

    @Autowired
    private ISimuOrderDetailBO simuOrderDetailBO;

    @Autowired
    private ISimuOrderHistoryBO simuOrderHistoryBO;

    @Autowired
    private IHandicapBO handicapBO;

    @Autowired
    private ISimuKLineBO simuKLineBO;

    @Autowired
    private ISimuMatchResultBO simuMatchResultBO;

    /**
     * 按交易对查找委托单进行撮合
     * @param symbol
     * @param toSymbol 
     * @create: 2018年8月31日 下午5:03:56 lei
     * @history:
     */
    public void doMatchTrade(String symbol, String toSymbol) {

        List<SimuOrder> simuOrders = getSimuOrderList(symbol, toSymbol);
        if (CollectionUtils.isNotEmpty(simuOrders)) {

            // 存在市价单时，优先撮合市价单
            doMatchMarket(simuOrders, symbol, toSymbol);

        } else {

            // 不存在市价单时，撮合限价单
            doMatchLimit(symbol, toSymbol);

        }

    }

    /**
     * 从存活委托单池内获取委托单
     * @param symbol
     * @param toSymbol
     * @return 
     * @create: 2018年8月31日 下午5:13:50 lei
     * @history:
     */
    private List<SimuOrder> getSimuOrderList(String symbol, String toSymbol) {
        List<String> statusList = new ArrayList<>();
        statusList.add(ESimuOrderStatus.SUBMIT.getCode());
        statusList.add(ESimuOrderStatus.PART_DEAL.getCode());

        SimuOrder condition = new SimuOrder();
        condition.setSymbol(symbol);
        condition.setToSymbol(toSymbol);
        condition.setStatusList(statusList);
        condition.setType(ESimuOrderType.MARKET.getCode());
        condition.setOrder("create_datetime asc");

        return simuOrderBO.querySimuOrderList(condition);
    }

    /**
     * 市价单撮合
     * @param symbol
     * @param toSymbol 
     * @create: 2018年8月31日 下午5:13:10 lei
     * @history:
     */
    private void doMatchMarket(List<SimuOrder> simuOrders, String symbol,
            String toSymbol) {

        for (SimuOrder marketOrder : simuOrders) {

            List<Handicap> handicaps = null;
            // 获取对方盘口
            if (ESimuOrderDirection.BUY.getCode()
                .equals(marketOrder.getDirection())) {
                handicaps = handicapBO.queryHandicapList(
                    marketOrder.getSymbol(), marketOrder.getToSymbol(),
                    ESimuOrderDirection.SELL.getCode(), 5);

            } else {
                handicaps = handicapBO.queryHandicapList(
                    marketOrder.getSymbol(), marketOrder.getToSymbol(),
                    ESimuOrderDirection.BUY.getCode(), 5);

            }

            for (Handicap handicap : handicaps) {

                // 根据盘口获取委托单
                SimuOrder limitOrder = simuOrderBO
                    .getSimuOrder(handicap.getOrderCode());

                // 双委托单所有者为同一人
                if (marketOrder.getUserId().equals(limitOrder.getUserId())) {
                    continue;
                }

                // 委托总量减去已成交量：委托剩余可交易量
                BigDecimal avilAmount = marketOrder.getTotalAmount()
                    .subtract(marketOrder.getTradedAmount());

                if (avilAmount.compareTo(BigDecimal.ZERO) > 0) {

                    BigDecimal avilHandicapAmount = handicap.getCount()
                        .multiply(handicap.getPrice());

                    // 当前盘口的交易量是否能满足
                    if (avilHandicapAmount.compareTo(avilAmount) > 0) {
                        // 当前委托单完全成交，新增当前委托单交易明细单
                        doMarketOrderMatch(marketOrder, handicap,
                            avilAmount.divide(handicap.getPrice(), 4,
                                BigDecimal.ROUND_DOWN),
                            avilAmount);

                        // 盘口部分成交，新增盘口单交易明细单
                        doLimitSimuOrderMatch(limitOrder,
                            avilAmount.divide(handicap.getPrice(), 4,
                                BigDecimal.ROUND_DOWN),
                            avilAmount, ESimuOrderStatus.PART_DEAL.getCode());

                    } else if (avilHandicapAmount.compareTo(avilAmount) <= 0) {
                        // 当前委托单完全成交，新增当前委托单交易明细单
                        doMarketOrderMatch(marketOrder, handicap,
                            handicap.getCount(), avilHandicapAmount);

                        // 盘口完全成交，新增盘口单交易明细单
                        doLimitSimuOrderMatch(limitOrder, handicap.getCount(),
                            avilHandicapAmount,
                            ESimuOrderStatus.ENTIRE_DEAL.getCode());

                    } else {
                        // 当前委托单部分成交，新增当前委托单交易明细单
                        doMarketOrderMatch(marketOrder, handicap,
                            handicap.getCount(), avilHandicapAmount);

                        // 盘口完全成交，新增盘口单交易明细单
                        doLimitSimuOrderMatch(limitOrder, handicap.getCount(),
                            avilHandicapAmount,
                            ESimuOrderStatus.ENTIRE_DEAL.getCode());

                    }

                    // 落地撮合结果
                    simuMatchResultBO.doSimuMatchResult(marketOrder,
                        limitOrder);

                } else {
                    break;
                }

            }

            /** 当前市价委托单已 撮合 完所有的对方盘口 **/

            // 获取当前市价委托单的所有成交明细
            SimuOrderDetail condition = new SimuOrderDetail();
            condition.setOrderCode(marketOrder.getCode());
            List<SimuOrderDetail> simuOrderDetails = simuOrderDetailBO
                .querySimuOrderDetailList(condition);

            if (CollectionUtils.isEmpty(simuOrderDetails)) {

                // 此委托单无法成交
                marketOrder.setAvgPrice(BigDecimal.ZERO);
                marketOrder.setTradedCount(BigDecimal.ZERO);
                marketOrder.setTradedAmount(BigDecimal.ZERO);
                marketOrder.setStatus(ESimuOrderStatus.CANCEL.getCode());
                marketOrder.setCancelDatetime(new Date());

                moveToHistory(marketOrder);

            } else {

                // 比较所有明细交易金额相加 和 当前委托单的委托金额，小于则为部分成交，等于则为全部成交
                BigDecimal totalTradeAmount = BigDecimal.ZERO;

                // 成交总数量
                BigDecimal totalTradeCount = BigDecimal.ZERO;

                for (SimuOrderDetail simuOrderDetail : simuOrderDetails) {

                    totalTradeAmount = totalTradeAmount
                        .add(simuOrderDetail.getTradedAmount());

                    totalTradeCount = totalTradeCount
                        .add(simuOrderDetail.getTradedCount());

                }

                // 计算成交均价
                BigDecimal avgPrice = totalTradeAmount.divide(totalTradeCount,
                    4, BigDecimal.ROUND_DOWN);

                // 当前委托单是否完全成交？修改状态，进入历史委托单
                if (totalTradeAmount
                    .compareTo(marketOrder.getTotalAmount()) < 0) {
                    // 已成交集金额 小于 总委托金额，部分成交
                    marketOrder
                        .setStatus(ESimuOrderStatus.PART_DEAL_CANCEL.getCode());
                } else {
                    // 完全成交
                    marketOrder
                        .setStatus(ESimuOrderStatus.ENTIRE_DEAL.getCode());
                }

                // 装填委托单信息并转入历史委托单
                marketOrder.setAvgPrice(avgPrice);
                marketOrder.setTradedCount(totalTradeCount);
                marketOrder.setTradedAmount(totalTradeAmount);

                moveToHistory(marketOrder);

            }

        }

        // 全部市价单成交完毕后撮合 限价单

        // doMatchLimit(symbol, toSymbol);
    }

    /**
     * 限价单撮合
     * @param symbol
     * @param toSymbol 
     * @create: 2018年8月31日 下午5:13:10 lei
     * @history:
     */
    private void doMatchLimit(String symbol, String toSymbol) {

        // 买盘盘口
        List<Handicap> bids = handicapBO.queryHandicapList(symbol, toSymbol,
            ESimuOrderDirection.BUY.getCode(), 5);

        // 卖盘盘口
        List<Handicap> asks = handicapBO.queryHandicapList(symbol, toSymbol,
            ESimuOrderDirection.SELL.getCode(), 5);

        // 用买盘 去扫 卖盘
        for (Handicap bidsHandicap : bids) {

            // 根据盘口获取委托单
            SimuOrder bidsOrder = simuOrderBO
                .getSimuOrder(bidsHandicap.getOrderCode());

            // 委托总量减去已成交量：剩余可交易量
            BigDecimal avilBidsAmount = bidsOrder.getTotalAmount()
                .subtract(bidsOrder.getTradedAmount());

            if (avilBidsAmount.compareTo(BigDecimal.ZERO) > 0) {

                for (Handicap asksHandicap : asks) {

                    // 根据盘口获取委托单
                    SimuOrder asksOrder = simuOrderBO
                        .getSimuOrder(asksHandicap.getOrderCode());

                    // 双委托单所有者为同一人
                    if (bidsOrder.getUserId().equals(asksOrder.getUserId())) {
                        continue;
                    }

                    // 盘口剩余可交易量
                    BigDecimal avilAsksAmount = asksOrder.getTotalAmount()
                        .subtract(asksOrder.getTradedAmount());

                    // 卖盘盘口价格 低于等于 买盘盘口价格 撮合成功
                    if (asksOrder.getPrice()
                        .compareTo(bidsOrder.getPrice()) <= 0) {

                        // 买盘交易量能否被满足
                        if (avilAsksAmount.compareTo(avilBidsAmount) > 0) {

                            // 买盘单完全成交
                            doLimitSimuOrderMatch(bidsOrder,
                                bidsHandicap.getCount(), avilBidsAmount,
                                ESimuOrderStatus.ENTIRE_DEAL.getCode());

                            // 卖盘单部分成交
                            doLimitSimuOrderMatch(asksOrder,
                                bidsHandicap.getCount(), avilBidsAmount,
                                ESimuOrderStatus.PART_DEAL.getCode());

                        } else if (avilAsksAmount
                            .compareTo(avilBidsAmount) == 0) {

                            // 买盘单完全成交
                            doLimitSimuOrderMatch(bidsOrder,
                                bidsHandicap.getCount(), avilBidsAmount,
                                ESimuOrderStatus.ENTIRE_DEAL.getCode());

                            // 卖盘单完全成交
                            doLimitSimuOrderMatch(asksOrder,
                                asksHandicap.getCount(), avilAsksAmount,
                                ESimuOrderStatus.ENTIRE_DEAL.getCode());

                        } else {

                            // 买盘单部分成交
                            doLimitSimuOrderMatch(bidsOrder,
                                asksHandicap.getCount(), avilAsksAmount,
                                ESimuOrderStatus.PART_DEAL.getCode());

                            // 卖盘单完全成交
                            doLimitSimuOrderMatch(asksOrder,
                                asksHandicap.getCount(), avilAsksAmount,
                                ESimuOrderStatus.ENTIRE_DEAL.getCode());

                        }

                        // 落地撮合结果
                        simuMatchResultBO.doSimuMatchResult(bidsOrder,
                            asksOrder);

                    }

                }

            }

        }

    }

    /**
     * 处理市价委托单撮合结果
     * @param simuOrder
     * @param handicap
     * @param tradedCount
     * @param tradedAmount 
     * @create: 2018年8月31日 下午5:12:47 lei
     * @history:
     */
    private void doMarketOrderMatch(SimuOrder simuOrder, Handicap handicap,
            BigDecimal tradedCount, BigDecimal tradedAmount) {

        // 新增盘口成交单并更新委托单
        SimuOrderDetail orderDetail = new SimuOrderDetail();
        orderDetail.setOrderCode(simuOrder.getCode());
        orderDetail.setUserId(simuOrder.getUserId());
        orderDetail.setSymbol(simuOrder.getSymbol());
        orderDetail.setToSymbol(simuOrder.getToSymbol());
        orderDetail.setTradedPrice(handicap.getPrice());

        orderDetail.setTradedCount(tradedCount);
        orderDetail.setTradedAmount(tradedAmount);
        // 交易手续费
        // data.setTradedFee();
        orderDetail.setCreateDatetime(new Date());
        simuOrderDetailBO.saveSimuOrderDetail(orderDetail);

        // 落地行情K线
        saveSimuKLine(orderDetail);

        // 更新当前委托单的已交易信息
        simuOrder.setTradedCount(simuOrder.getTradedCount().add(tradedCount));
        simuOrder
            .setTradedAmount(simuOrder.getTradedAmount().add(tradedAmount));
        // simuOrder.setTradedFee();
        simuOrder.setLastTradedDatetime(new Date());
        simuOrderBO.refreshMarketSimuOrder(simuOrder);
    }

    /**
     * 处理限价委托单撮合结果
     * @param simuOrder
     * @param tradedCount
     * @param tradedAmount
     * @param status 
     * @create: 2018年8月31日 下午5:09:09 lei
     * @history:
     */
    private void doLimitSimuOrderMatch(SimuOrder simuOrder,
            BigDecimal tradedCount, BigDecimal tradedAmount, String status) {

        // 新增盘口成交单并更新委托单
        SimuOrderDetail handicapDetail = new SimuOrderDetail();
        handicapDetail.setOrderCode(simuOrder.getCode());
        handicapDetail.setUserId(simuOrder.getUserId());
        handicapDetail.setSymbol(simuOrder.getSymbol());
        handicapDetail.setToSymbol(simuOrder.getToSymbol());
        handicapDetail.setTradedPrice(simuOrder.getPrice());

        handicapDetail.setTradedCount(tradedCount);
        handicapDetail.setTradedAmount(tradedAmount);
        // 交易手续费
        // data.setTradedFee();
        handicapDetail.setCreateDatetime(new Date());
        simuOrderDetailBO.saveSimuOrderDetail(handicapDetail);

        // 落地行情K线
        saveSimuKLine(handicapDetail);

        // 更新盘口委托单交易信息
        updateSimuOrderTradeInfo(simuOrder, tradedCount, tradedAmount, status);

        // 更新盘口交易信息
        updateHandicapTradeInfo(simuOrder.getCode(),
            simuOrder.getTotalAmount().subtract(tradedCount), status);
    }

    /**
     * 更新委托单交易信息
     * @param simuOrder
     * @param tradedCount
     * @param tradedAmount
     * @param status 
     * @create: 2018年8月31日 下午5:08:32 lei
     * @history:
     */
    private void updateSimuOrderTradeInfo(SimuOrder simuOrder,
            BigDecimal tradedCount, BigDecimal tradedAmount, String status) {

        simuOrder.setAvgPrice(simuOrder.getPrice());
        simuOrder.setLastTradedDatetime(new Date());
        simuOrder.setStatus(status);

        if (ESimuOrderStatus.PART_DEAL.getCode().equals(status)) { // 部分成交

            simuOrder
                .setTradedCount(simuOrder.getTradedCount().add(tradedCount));
            simuOrder
                .setTradedAmount(simuOrder.getTradedAmount().add(tradedAmount));
            // 交易手续费
            // handicapDetail
            // .setTradedFee(handicapDetail.getTradedFee().add(augend));

            simuOrderBO.refreshLimitSimuOrder(simuOrder);

        } else if (ESimuOrderStatus.ENTIRE_DEAL.getCode().equals(status)) { // 完全成交

            simuOrder.setTradedCount(tradedCount);
            simuOrder.setTradedAmount(tradedAmount);
            // 交易手续费
            // handicapDetail.setTradedFee();

            // 放入历史委托单
            moveToHistory(simuOrder);
        }

    }

    /**
     * 根据委托单更新盘口交易信息
     * @param orderCode
     * @param tradedCount
     * @param status 
     * @create: 2018年8月31日 下午5:08:00 lei
     * @history:
     */
    private void updateHandicapTradeInfo(String orderCode,
            BigDecimal tradedCount, String status) {

        Handicap handicap = new Handicap();
        handicap.setOrderCode(orderCode);
        handicap.setCount(tradedCount);

        if (ESimuOrderStatus.PART_DEAL.getCode().equals(status)) { // 部分成交

            // 更新盘口可交易量
            handicapBO.refreshHandicap(handicap);

        } else if (ESimuOrderStatus.ENTIRE_DEAL.getCode().equals(status)) { // 完全成交

            // 删除盘口
            handicapBO.removeHandicap(handicap.getOrderCode());
        }

    }

    /**
     * 落地K线
     * @param orderDetail 
     * @create: 2018年8月31日 下午5:07:46 lei
     * @history:
     */
    private void saveSimuKLine(SimuOrderDetail orderDetail) {
        SimuKLine simuKLine = new SimuKLine();
        simuKLine.setSymbol(orderDetail.getSymbol());
        simuKLine.setToSymbol(orderDetail.getToSymbol());
        simuKLine.setVolume(orderDetail.getTradedCount());
        simuKLine.setQuantity(BigDecimal.ONE);
        simuKLine.setAmount(orderDetail.getTradedAmount());
        simuKLine.setCreateDatetime(new Date());
        simuKLineBO.saveSimuKLine(simuKLine);
    }

    /**
     * 将存活委托单移入历史委托单
     * @param data 
     * @create: 2018年8月31日 下午5:06:44 lei
     * @history:
     */
    private void moveToHistory(SimuOrder data) {
        // 增加历史委托
        simuOrderHistoryBO.saveSimuOrderHistory(data);

        // 撤销，并从 存活委托 中删除；
        simuOrderBO.cancel(data);
    }

}
