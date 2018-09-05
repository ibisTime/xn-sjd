package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.domain.UserStatistics;
import com.ogc.standard.dto.res.XN625252Res;

public interface ITradeOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 我要购买开始聊天，创建代下单的订单
    public String contactBuy(String adsCode, String buyUser);

    // 我要出售开始聊天，创建代下单的订单
    public String contactSell(String adsCode, String sellUser);

    // 我要购买
    public TradeOrder buy(String adsCode, String buyUser, BigDecimal tradePrice,
            BigDecimal count, BigDecimal tradeAmount);

    // 我要出售
    public TradeOrder sell(String adsCode, String sellUser,
            BigDecimal tradePrice, BigDecimal count, BigDecimal tradeAmount);

    // 自主取消或超时取消交易订单
    public void userCancel(String code, String updater, String remark);

    // 仲裁取消交易订单
    public void platCancel(String code, String updater, String remark);

    // 买家标记打款
    public void markPay(String code, String updater, String remark);

    // 卖家释放
    public TradeOrder release(String code, String updater, String remark,
            String kind);

    // 评论
    public void comment(String code, String userId, String comment);

    // 申请仲裁
    public void applyArbitrate(String code, String applyUser, String reason,
            String attach);

    // 删除订单，代下单和已取消的交易可删除
    public void dropTradeOrder(String code);

    public Paginable<TradeOrder> queryTradeOrderPage(int start, int limit,
            TradeOrder condition);

    public List<TradeOrder> queryTradeOrderList(TradeOrder condition);

    public TradeOrder getTradeOrder(String code);

    public XN625252Res getTradeOrderCheckInfo(String code);

    // 定时器调用：扫描支付超时订单
    public void doCheckUnpayOrder();

    // 不包含 用户交易量的 统计
    public UserStatistics userStatisticsInfoNotContainTradeCount(String userId,
            String currency);

    // 包含 用户交易量的 统计
    public UserStatistics userStatisticsInfoContainTradeCount(String userId,
            String currency);

    // 获取用户的交易总量
    public BigDecimal getUserTotalTradeCount(String userId, String currency);

    public long getTradeTimesBetweenUser(String user1, String user2);

    public void createGroupIfNotExist();

    public BigDecimal getLastestPrice(String coin);

}
