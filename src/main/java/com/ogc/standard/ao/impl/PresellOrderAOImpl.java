package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellOrderBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.PresellSpecs;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EPresellOrderStatus;
import com.ogc.standard.enums.EPresellProductStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class PresellOrderAOImpl implements IPresellOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(PresellOrderAOImpl.class);

    @Autowired
    private IPresellOrderBO presellOrderBO;

    @Autowired
    private IPresellSpecsBO presellSpecsBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private IAlipayBO alipayBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IPresellInventoryBO presellInventoryBO;

    @Override
    @Transactional
    public String commitPresellOrder(String userId, String specsCode,
            Integer quantity) {
        PresellSpecs presellSpecs = presellSpecsBO.getPresellSpecs(specsCode);
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellSpecs.getProductCode());
        if (!EPresellProductStatus.TO_ADOPT.getCode()
            .equals(presellProduct.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "预售产品不是已上架待购买状态，不能下单");
        }

        if ((new Date()).after(presellProduct.getAdoptEndDatetime())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "产品认养时间已到期，不能下单");
        }

        if (quantity > presellSpecs.getPackCount()) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "库存数量不足，不能下单");
        }

        // 落地订单
        String code = presellOrderBO.savePresellOrder(userId, presellProduct,
            presellSpecs, quantity);

        // TODO 分配树

        // 更新产品已预售数量
        Integer nowCount = presellProduct.getNowCount() + quantity;
        presellProductBO.refreshNowCount(presellProduct.getCode(), nowCount);

        return code;
    }

    @Override
    @Transactional
    public void cancelPresellOrder(String code, String remark) {
        PresellOrder presellOrder = presellOrderBO.getPresellOrder(code);
        if (!EPresellOrderStatus.TO_PAY.getCode()
            .equals(presellOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能取消");
        }

        // TODO 取消树

        // 更新产品已预售数量
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellOrder.getProductCode());
        Integer nowCount = presellProduct.getNowCount()
                - presellOrder.getQuantity();
        presellProductBO.refreshNowCount(presellProduct.getCode(), nowCount);

        // 取消订单
        presellOrderBO.cancelPresellOrder(code, remark);
    }

    @Override
    @Transactional
    public Object toPayPresellOrder(String code, String payType,
            String tradePwd) {
        PresellOrder presellOrder = presellOrderBO.getPresellOrder(code);
        if (!EPresellOrderStatus.TO_PAY.getCode()
            .equals(presellOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能支付");
        }

        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(presellOrder.getApplyUser(), tradePwd);
        }

        // 积分抵扣处理
        XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
            BigDecimal.ZERO);

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付

            result = toPayPresellOrderYue(presellOrder, deductRes);

        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付

            presellOrderBO.refreshPayGroup(presellOrder.getCode(), payType,
                deductRes);
            String signOrder = alipayBO.getSignedOrder(
                presellOrder.getApplyUser(), ESysUser.SYS_USER.getCode(),
                presellOrder.getPayGroup(), EJourBizTypeUser.PRESELL.getCode(),
                EJourBizTypeUser.PRESELL.getValue(), presellOrder.getAmount());
            result = new PayOrderRes(signOrder);

        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付

            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");

        }

        return result;
    }

    // 1、判断余额是否足够，并扣除账户余额
    // 2、进行分销
    // 3、更新订单和树状态
    private Object toPayPresellOrderYue(PresellOrder data,
            XN629048Res deductRes) {
        Account userCnyAccount = accountBO.getAccountByUser(data.getApplyUser(),
            ECurrency.CNY.getCode());
        BigDecimal payAmount = data.getAmount();// 实际付款人民币金额
        if (userCnyAccount.getAmount().compareTo(payAmount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "人民币账户余额不足");
        }

        // 人民币余额划转
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.transAmount(userCnyAccount, sysCnyAccount, payAmount,
            EJourBizTypeUser.PRESELL.getCode(),
            EJourBizTypePlat.PRESELL.getCode(),
            EJourBizTypeUser.PRESELL.getValue(),
            EJourBizTypePlat.PRESELL.getValue(), data.getCode());

        // 进行分销
        BigDecimal backJfAmount = distributionOrderBO.distribution(
            data.getCode(), data.getProductCode(), data.getAmount(),
            data.getApplyUser(), ESellType.PRESELL.getCode(), deductRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveBulletin(data.getApplyUser(), data.getQuantity().toString(),
            data.getProductCode());

        // 添加原生组
        String originalGroupCode = originalGroupBO.saveOriginalGroup(data);

        // TODO 分配树和预售权，更新树状态
        for (int i = 0; i > data.getQuantity(); i++) {
            presellInventoryBO.savePresellInventory(originalGroupCode, null);
        }

        // 业务订单更改
        presellOrderBO.payYueSuccess(data.getCode(), deductRes, backJfAmount);

        return new BooleanRes(true);
    }

    @Override
    public void paySuccess(String payGroup) {
        PresellOrder data = presellOrderBO.getPresellOrder(payGroup);
        if (EPresellOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {

            XN629048Res resultRes = new XN629048Res(BigDecimal.ZERO,
                BigDecimal.ZERO);

            // 进行分销
            BigDecimal backJfAmount = distributionOrderBO.distribution(
                data.getCode(), data.getProductCode(), data.getAmount(),
                data.getApplyUser(), ESellType.PRESELL.getCode(), resultRes);

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // TODO 分配预售权，更新树状态

            // 添加原生组
            originalGroupBO.saveOriginalGroup(data);

            // 添加快报
            smsBO.saveBulletin(data.getApplyUser(),
                data.getQuantity().toString(), data.getProductCode());

            // 业务订单更改
            presellOrderBO.payYueSuccess(data.getCode(), resultRes,
                backJfAmount);
        }
    }

    @Override
    public void doCancelPresellOrder() {
        logger.info("***************开始扫描未支付预售订单***************");
        PresellOrder condition = new PresellOrder();
        condition.setStatus(EPresellOrderStatus.TO_PAY.getCode());
        condition.setApplyDatetimeEnd(
            DateUtil.getRelativeDateOfMinute(new Date(), -15));// 前15分钟还未支付的订单
        List<PresellOrder> presellOrderList = presellOrderBO
            .queryPresellOrderList(condition);

        if (CollectionUtils.isNotEmpty(presellOrderList)) {
            for (PresellOrder presellOrder : presellOrderList) {
                cancelOrder(presellOrder);
            }
        }
        logger.info("***************结束扫描未支付预售订单***************");
    }

    public void cancelOrder(PresellOrder presellOrder) {

        // 更新产品已预售数量
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellOrder.getProductCode());
        Integer nowCount = presellProduct.getNowCount()
                - presellOrder.getQuantity();
        presellProductBO.refreshNowCount(presellProduct.getCode(), nowCount);

        // TODO 取消树

        // 订单状态变更
        presellOrderBO.cancelPresellOrder(presellOrder.getCode(),
            "超15分钟未支付系统自动取消");
    }

    @Override
    public Paginable<PresellOrder> queryPresellOrderPage(int start, int limit,
            PresellOrder condition) {
        return presellOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PresellOrder> queryPresellOrderList(PresellOrder condition) {
        return presellOrderBO.queryPresellOrderList(condition);
    }

    @Override
    public PresellOrder getPresellOrder(String code) {
        return presellOrderBO.getPresellOrder(code);
    }

}
