package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICoinAcceptOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IBankcardBO;
import com.ogc.standard.bo.IBlacklistBO;
import com.ogc.standard.bo.ICoinAcceptOrderBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Bankcard;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECoinAcceptOrderStatus;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.ESystemCode;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class CoinAcceptOrderAOImpl implements ICoinAcceptOrderAO {

    @Autowired
    private ICoinAcceptOrderBO coinAcceptOrderBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IBlacklistBO blacklistBO;

    @Autowired
    private IBankcardBO bankcardBO;

    @Override
    public String buyOrder(XN625270Req req) {

        // 校验用户是否存在
        User user = userBO.getUser(req.getUserId());
        if (user == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户不存在");
        }

        // 检查 平台 黑名单
        blacklistBO.isAddBlacklist(user.getUserId());

        // 校验当时行情，需在容错误差内

        BigDecimal price = StringValidater.toBigDecimal(req.getTradePrice());
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易金额必须大于0");
        }

        BigDecimal count = StringValidater.toBigDecimal(req.getCount());
        if (count.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易数量必须大于0");
        }

        BigDecimal tradeAmount = StringValidater
            .toBigDecimal(req.getTradeAmount());
        if (tradeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易金额必须大于0");
        }

        if (!EBoolean.NO.getCode().equals(req.getPayType())
                && !EBoolean.YES.getCode().equals(req.getPayType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "不被支持的付款方式");
        }

        // 获取所有承兑商收款账号
        List<Bankcard> bankcardList = bankcardBO.queryBankcardList(
            ESysUser.SYS_USER.getCode(), ESystemCode.BZ.getCode());

        // 随机获取一个承兑商账户
        Random rand = new Random();
        Bankcard bankcard = bankcardList.get(rand.nextInt(bankcardList.size()));

        return coinAcceptOrderBO.saveBuyAcceptOrder(req,
            bankcard.getBankcardNumber(), bankcard.getBankName());
    }

    @Override
    public String sellOrder(XN625271Req req) {

        // 校验用户是否存在
        User user = userBO.getUser(req.getUserId());
        if (user == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户不存在");
        }

        // 检查 平台 黑名单
        blacklistBO.isAddBlacklist(user.getUserId());

        BigDecimal price = StringValidater.toBigDecimal(req.getTradePrice());
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易金额必须大于0");
        }

        // 校验当时行情，需在容错误差内

        BigDecimal count = StringValidater.toBigDecimal(req.getCount());
        if (count.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易数量必须大于0");
        }

        BigDecimal tradeAmount = StringValidater
            .toBigDecimal(req.getTradeAmount());
        if (tradeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "交易金额必须大于0");
        }

        if (!EBoolean.NO.getCode().equals(req.getReceiveType())
                && !EBoolean.YES.getCode().equals(req.getReceiveType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "不被支持的付款方式");
        }

        // 校验出售者余额
        Account sellUserAccount = accountBO.getAccountByUser(req.getUserId(),
            req.getTradeCurrency());
        if (sellUserAccount.getAmount()
            .subtract(sellUserAccount.getFrozenAmount())
            .compareTo(StringValidater.toBigDecimal(req.getCount())) < 0) {

            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "您的" + req.getTradeCurrency() + "可用余额不足");
        }

        // 获取所有承兑商收款账号
        List<Bankcard> bankcardList = bankcardBO.queryBankcardList(
            ESysUser.SYS_USER.getCode(), ESystemCode.BZ.getCode());

        // 随机获取一个承兑商打款账户
        Random rand = new Random();
        Bankcard bankcard = bankcardList.get(rand.nextInt(bankcardList.size()));

        return sell(req, bankcard);
    }

    @Override
    public void cancelBuyOrder(String code, String userId, String remark) {
        CoinAcceptOrder order = coinAcceptOrderBO.getCoinAcceptOrder(code);
        if (order == null) {
            throw new BizException("xn000000", "无效的订单编号");
        }

        if (!ECoinAcceptOrderStatus.TO_PAY.getCode()
            .equals(order.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前状态下不能取消订单");
        }

        order.setStatus(ECoinAcceptOrderStatus.CANCELED.getCode());
        order.setUpdater(userId);
        order.setUpdateDatetime(new Date());
        order.setRemark("订单已取消");
        coinAcceptOrderBO.cancel(order, userId, remark);

    }

    public void platCancel(String code, String userId, String remark) {
        CoinAcceptOrder order = coinAcceptOrderBO.getCoinAcceptOrder(code);
        if (order == null) {
            throw new BizException("xn000000", "无效的订单编号");
        }

        if (!ECoinAcceptOrderStatus.PAYED.getCode().equals(order.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前状态下不能取消订单");
        }

        coinAcceptOrderBO.cancel(order, userId, remark);

    }

    @Override
    public void markPay(String code, String note, String pdf, String updater) {
        CoinAcceptOrder order = coinAcceptOrderBO.getCoinAcceptOrder(code);

        // 检查该订单是否超时
        if (order.getInvalidDatetime().compareTo(new Date()) < 0) {
            // 订单已超时,取消订单
            cancelBuyOrder(order.getCode(), "系统", "订单支付超时，系统自动取消");
            return;
        }

        if (!order.getUserId().equals(updater)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "只有此单的买家才可以标记打款");
        }

        if (!ECoinAcceptOrderStatus.TO_PAY.getCode()
            .equals(order.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前状态下不能标记已打款");
        }

        if (order.getType().equals(EBoolean.YES.getCode())) { // 0买入/1卖出

            // 交易解冻
            Account dbAccount = accountBO.getAccountByUser(order.getUserId(),
                order.getTradeCoin());
            accountBO.unfrozenAmount(dbAccount, order.getCount(),
                EJourBizTypeUser.AJ_ADS_UNFROZEN.getCode(),
                EJourBizTypeUser.AJ_ADS_UNFROZEN.getValue(), order.getCode());

            // 划转X币
            Account platAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_ETH.getCode());

            accountBO.transAmount(dbAccount, platAccount, order.getCount(),
                EJourBizTypeUser.AJ_SELL.getCode(),
                EJourBizTypeUser.AJ_BUY.getCode(),
                EJourBizTypeUser.AJ_SELL.getValue(),
                EJourBizTypeUser.AJ_BUY.getValue(), order.getCode());

        } else {
            // 变更交易订单信息
            coinAcceptOrderBO.markPay(order, updater, note);
        }

    }

    @Override
    @Transactional
    public TradeOrder release(String code, String result, String updater) {
        CoinAcceptOrder order = coinAcceptOrderBO.getCoinAcceptOrder(code);

        if (EBoolean.YES.equals(result)) {

            if (!(ECoinAcceptOrderStatus.PAYED.getCode()
                .equals(order.getStatus()))) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "当前状态下不能释放");
            }

            Account dbAccount = accountBO.getAccountByUser(order.getUserId(),
                order.getTradeCoin());

            Account platAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_ETH.getCode());

            accountBO.transAmount(platAccount, dbAccount, order.getCount(),
                EJourBizTypeUser.AJ_SELL.getCode(),
                EJourBizTypeUser.AJ_BUY.getCode(),
                EJourBizTypeUser.AJ_SELL.getValue(),
                EJourBizTypeUser.AJ_BUY.getValue(), order.getCode());

        } else if (EBoolean.NO.getCode().equals(order.getType())) {
            platCancel(order.getCode(), "系统", "未收到钱款，取消订单");
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "未识别的订单类型");
        }

        return null;
    }

    @Override
    public Paginable<CoinAcceptOrder> queryCoinAcceptOrderPage(int start,
            int limit, CoinAcceptOrder condition) {
        return coinAcceptOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CoinAcceptOrder> queryCoinAcceptOrderList(
            CoinAcceptOrder condition) {
        return coinAcceptOrderBO.queryCoinAcceptOrderList(condition);
    }

    @Override
    public CoinAcceptOrder getCoinAcceptOrder(String code) {
        return coinAcceptOrderBO.getCoinAcceptOrder(code);
    }

    /**
     * 直接卖出
     * @param req
     * @param acceptUser
     * @return 
     * @create: 2018年9月10日 下午9:32:59 lei
     * @history:
     */
    @Transactional
    private String sell(XN625271Req req, Bankcard bankcard) {

        CoinAcceptOrder order = coinAcceptOrderBO.saveSellAcceptOrder(req,
            bankcard.getBankcardNumber(), bankcard.getBankName());

        Account dbAccount = accountBO.getAccountByUser(order.getUserId(),
            order.getTradeCoin());

        // 冻结卖家交易金额
        accountBO.frozenAmount(dbAccount, order.getCount(),
            EJourBizTypeUser.AJ_ADS_UNFROZEN.getCode(),
            EJourBizTypeUser.AJ_ADS_UNFROZEN.getValue(), order.getCode());

        return order.getCode();
    }

}
