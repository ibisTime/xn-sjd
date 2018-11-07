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

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IGroupOrderBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EDeriveGroupStatus;
import com.ogc.standard.enums.EGroupOrderStatus;
import com.ogc.standard.enums.EGroupType;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EPresellType;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class GroupOrderAOImpl implements IGroupOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(GroupOrderAOImpl.class);

    @Autowired
    private IGroupOrderBO groupOrderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private IDeriveGroupBO deriveGroupBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IPresellInventoryBO presellInventoryBO;

    @Override
    public void cancelGroupOrder(String code, String remark) {
        GroupOrder groupOrder = groupOrderBO.getGroupOrder(code);
        if (!EGroupOrderStatus.TO_PAY.getCode()
            .equals(groupOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能取消");
        }

        // 取消订单
        groupOrderBO.cancelGrouplOrder(code, remark);
    }

    @Override
    public Object toPayGroupOrder(String code, String payType,
            String tradePwd) {
        GroupOrder groupOrder = groupOrderBO.getGroupOrder(code);
        if (!EGroupOrderStatus.TO_PAY.getCode()
            .equals(groupOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能支付");
        }

        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(groupOrder.getApplyUser(), tradePwd);
        }

        // 积分抵扣处理
        XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
            BigDecimal.ZERO);

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付

            result = toPayGroupOrderYue(groupOrder, deductRes);

        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付

            groupOrderBO.refreshPayGroup(groupOrder.getCode(), payType,
                deductRes);
            String signOrder = alipayBO.getSignedOrder(
                groupOrder.getApplyUser(), ESysUser.SYS_USER.getCode(),
                groupOrder.getPayGroup(), EJourBizTypeUser.PRESELL.getCode(),
                EJourBizTypeUser.PRESELL.getValue(), groupOrder.getAmount());
            result = new PayOrderRes(signOrder);

        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付

            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");

        }

        return result;
    }

    // 1、判断余额是否足够，并扣除账户余额
    // 2、进行分销
    // 3、更新订单和树状态
    private Object toPayGroupOrderYue(GroupOrder data, XN629048Res deductRes) {
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
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(data.getProductCode());
        BigDecimal backJfAmount = distributionOrderBO.distribution(
            data.getCode(), presellProduct.getOwnerId(), data.getAmount(),
            data.getApplyUser(), ESellType.PRESELL.getCode(), deductRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveBulletin(data.getApplyUser(), data.getQuantity().toString(),
            ESellType.PRESELL.getCode(), presellProduct.getName());

        // 认领寄售
        claimanDeriveGroup(data);

        // 业务订单更改
        groupOrderBO.payYueSuccess(data.getCode(), deductRes, backJfAmount);

        return new BooleanRes(true);
    }

    @Override
    public void paySuccess(String payGroup) {
        GroupOrder data = groupOrderBO.getGroupOrder(payGroup);
        if (EGroupOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {

            // 进行分销
            XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
                BigDecimal.ZERO);
            PresellProduct presellProduct = presellProductBO
                .getPresellProduct(data.getProductCode());
            BigDecimal backJfAmount = distributionOrderBO.distribution(
                data.getCode(), presellProduct.getOwnerId(), data.getAmount(),
                data.getApplyUser(), ESellType.PRESELL.getCode(), deductRes);

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // 添加快报
            smsBO.saveBulletin(data.getApplyUser(),
                data.getQuantity().toString(), ESellType.PRESELL.getCode(),
                presellProduct.getName());

            // 认领寄售
            claimanDeriveGroup(data);

            // 业务订单更改
            groupOrderBO.paySuccess(data.getCode(), data.getAmount(),
                backJfAmount);

        }
    }

    private void claimanDeriveGroup(GroupOrder data) {
        DeriveGroup deriveGroup = deriveGroupBO
            .getDeriveGroup(data.getGroupCode());

        // 定向
        if (EPresellType.DIRECT.getCode().equals(deriveGroup.getType())) {

            // 认领寄售
            deriveGroupBO.refreshClaimDirect(deriveGroup.getCode());

            // 生成新的资产
            originalGroupBO.saveOriginalGroup(deriveGroup.getOriginalCode(),
                deriveGroup.getClaimant(), data.getPrice(), data.getQuantity());

        }

        // 二维码
        if (EPresellType.QR.getCode().equals(deriveGroup.getType())) {

            // 认领寄售
            deriveGroupBO.refreshClaimQr(deriveGroup.getCode(),
                deriveGroup.getClaimant());

            // 生成新的资产
            String originalGroupCode = originalGroupBO.saveOriginalGroup(
                deriveGroup.getOriginalCode(), deriveGroup.getClaimant(),
                data.getPrice(), data.getQuantity());

            // 分配预售权
            int presellInventoryQuantity = 0;
            OriginalGroup originalGroup = originalGroupBO
                .getOriginalGroup(deriveGroup.getOriginalCode());
            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryPresellInventoryListByGroup(originalGroup.getCode());

            if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                for (PresellInventory presellInventory : presellInventorieList) {
                    if (++presellInventoryQuantity > data.getQuantity()) {
                        break;
                    }

                    presellInventoryBO.refreshGroup(presellInventory.getCode(),
                        EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
                }
            }
        }

        // 挂单
        if (EPresellType.PUBLIC.getCode().equals(deriveGroup.getType())) {

            // 认领寄售
            Integer remainQuantity = deriveGroup.getQuantity()
                    - data.getQuantity();
            String status = EDeriveGroupStatus.TO_CLAIM.getCode();
            if (remainQuantity == 0) {
                status = EDeriveGroupStatus.CLAIMED.getCode();
            }
            deriveGroupBO.refreshClaimPublic(deriveGroup.getCode(),
                deriveGroup.getClaimant(), remainQuantity, status);

            // 生成新的资产
            String originalGroupCode = originalGroupBO.saveOriginalGroup(
                deriveGroup.getOriginalCode(), deriveGroup.getClaimant(),
                deriveGroup.getPrice(), data.getQuantity());

            // 分配预售权
            int presellInventoryQuantity = 0;
            OriginalGroup originalGroup = originalGroupBO
                .getOriginalGroup(deriveGroup.getOriginalCode());
            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryPresellInventoryListByGroup(originalGroup.getCode());

            if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                for (PresellInventory presellInventory : presellInventorieList) {
                    if (++presellInventoryQuantity > data.getQuantity()) {
                        break;
                    }

                    presellInventoryBO.refreshGroup(presellInventory.getCode(),
                        EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
                }
            }
        }

    }

    @Override
    public void doCancelGroupOrder() {
        logger.info("***************开始扫描未支付寄售订单***************");
        GroupOrder condition = new GroupOrder();
        condition.setStatus(EGroupOrderStatus.TO_PAY.getCode());
        condition.setApplyDatetimeEnd(
            DateUtil.getRelativeDateOfMinute(new Date(), -15));// 前15分钟还未支付的订单
        List<GroupOrder> groupOrderList = groupOrderBO
            .queryGroupOrderList(condition);

        if (CollectionUtils.isNotEmpty(groupOrderList)) {
            for (GroupOrder groupOrder : groupOrderList) {
                groupOrderBO.cancelGrouplOrder(groupOrder.getCode(),
                    "超15分钟未支付系统自动取消");
            }
        }
        logger.info("***************结束扫描未支付寄售订单***************");
    }

    @Override
    public Paginable<GroupOrder> queryGroupOrderPage(int start, int limit,
            GroupOrder condition) {
        return groupOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GroupOrder> queryGroupOrderList(GroupOrder condition) {
        return groupOrderBO.queryGroupOrderList(condition);
    }

    @Override
    public GroupOrder getGroupOrder(String code) {
        return groupOrderBO.getGroupOrder(code);
    }

}
