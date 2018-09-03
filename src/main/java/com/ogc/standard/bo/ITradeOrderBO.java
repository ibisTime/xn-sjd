package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Ads;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.domain.UserStatistics;
import com.ogc.standard.enums.ETradeOrderType;

public interface ITradeOrderBO extends IPaginableBO<TradeOrder> {

    public String contactBuySubmit(Ads ads, String buyUser);

    public String contactSellSubmit(Ads ads, String sellUser);

    public TradeOrder buySubmit(Ads adsSell, String buyUser,
            BigDecimal tradePrice, BigDecimal count, BigDecimal tradeAmount,
            BigDecimal fee);

    public TradeOrder sellSubmit(Ads ads, String sellUser,
            BigDecimal tradePrice, BigDecimal count, BigDecimal tradeAmount,
            BigDecimal fee);

    public void buyRefresh(TradeOrder data, Ads ads, BigDecimal tradePrice,
            BigDecimal count, BigDecimal tradeAmount, BigDecimal fee);

    public void sellRefresh(TradeOrder data, Ads ads, BigDecimal tradePrice,
            BigDecimal count, BigDecimal tradeAmount, BigDecimal fee);

    public List<TradeOrder> queryTradeOrderList(TradeOrder condition);

    public TradeOrder getTradeOrder(String code);

    // 查询待下单的交易订单
    public TradeOrder getToSubmitTradeOrder(String buyUser, String sellUser,
            String adsCode);

    public int cancel(TradeOrder tradeOrder, String updater, String remark);

    public int markPay(TradeOrder tradeOrder, String updater, String remark);

    public int release(TradeOrder tradeOrder, String updater, String remark);

    public int doBsComment(TradeOrder tradeOrder, String userId, String comment,
            String status, String remark);

    public int doSbComment(TradeOrder tradeOrder, String userId, String comment,
            String status, String remark);

    public int applyArbitrate(TradeOrder tradeOrder, String applyUser);

    public int removeTradeOrder(TradeOrder data);

    // 仲裁不通过, 更新状态为待释放
    public int arbitrateUnPass(TradeOrder tradeOrder);

    public int revokePay(TradeOrder tradeOrder, String updater, String string);

    public boolean isExistOningOrder(String adsCode);

    // 检查广告是否有未完成的订单 true 通过，false 不通过
    public boolean checkUserHasUnFinishOrder(String userId,
            ETradeOrderType tradeOrderType, String tradeCoin);

    // 获取 已释放的交易次数
    // 被评价的次数
    // 获取好评数
    // 未获取信任次数
    public UserStatistics obtainUserStatistics(String userId, String currency);

    // 获取用户交易量
    public BigDecimal getUserTotalTradeCount(String userId, String tradeCoin);

    // 获取两个用户之间的交易次数
    public long getTradeTimesBetweenUser(String user1, String user2);

    // 删除和该广告有关的代下单订单
    public void removeDaiXiaDanOrders(String adsCode);
}
