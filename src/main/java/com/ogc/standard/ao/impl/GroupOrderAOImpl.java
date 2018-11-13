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
import com.ogc.standard.bo.IGroupOrderBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAccountType;
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

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IJourBO jourBO;

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

    @Override
    public Object toPayDonateGroupOrder(String code) {
        GroupOrder groupOrder = groupOrderBO.getGroupOrder(code);
        if (!EGroupOrderStatus.TO_PAY.getCode()
            .equals(groupOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能支付");
        }

        // 积分抵扣处理
        XN629048Res deductRes = new XN629048Res(BigDecimal.ZERO,
            BigDecimal.ZERO);

        // 用户升级
        userAO.upgradeUserLevel(groupOrder.getApplyUser());

        // 添加快报
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(groupOrder.getProductCode());
        smsBO.saveBulletin(groupOrder.getApplyUser(),
            groupOrder.getQuantity().toString(), ESellType.PRESELL.getCode(),
            presellProduct.getName());

        // 认领寄售
        claimanDeriveGroup(groupOrder);

        // 业务订单更改
        groupOrderBO.payYueSuccess(groupOrder.getCode(), deductRes,
            BigDecimal.ZERO);

        return new BooleanRes(true);
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
        Account ownerCnyAccount = accountBO.getAccountByUser(data.getOwnerId(),
            ECurrency.CNY.getCode());
        accountBO.transAmount(userCnyAccount, ownerCnyAccount, payAmount,
            EJourBizTypeUser.PRESELL.getCode(),
            EJourBizTypePlat.PRESELL.getCode(),
            EJourBizTypeUser.PRESELL.getValue(),
            EJourBizTypePlat.PRESELL.getValue(), data.getCode());

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(data.getProductCode());
        smsBO.saveBulletin(data.getApplyUser(), data.getQuantity().toString(),
            ESellType.PRESELL.getCode(), presellProduct.getName());

        // 认领寄售
        claimanDeriveGroup(data);

        // 业务订单更改
        groupOrderBO.payYueSuccess(data.getCode(), deductRes, BigDecimal.ZERO);

        return new BooleanRes(true);
    }

    @Override
    public void paySuccess(String payGroup) {
        GroupOrder data = groupOrderBO.getGroupOrder(payGroup);
        if (EGroupOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // 添加快报
            PresellProduct presellProduct = presellProductBO
                .getPresellProduct(data.getProductCode());
            smsBO.saveBulletin(data.getApplyUser(),
                data.getQuantity().toString(), ESellType.PRESELL.getCode(),
                presellProduct.getName());

            // 认领寄售
            claimanDeriveGroup(data);

            // 业务订单更改
            groupOrderBO.paySuccess(data.getCode(), data.getAmount(),
                BigDecimal.ZERO);

        }
    }

    private void claimanDeriveGroup(GroupOrder data) {
        DeriveGroup deriveGroup = deriveGroupBO
            .getDeriveGroup(data.getGroupCode());
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(deriveGroup.getOriginalCode());

        // 定向
        if (EPresellType.DIRECT.getCode().equals(deriveGroup.getType())) {

            // 认领寄售
            deriveGroupBO.refreshClaimDirect(deriveGroup.getCode());

            // 生成新的资产
            originalGroupBO.saveOriginalGroup(data,
                deriveGroup.getProductCode(), data.getApplyUser(),
                data.getPrice(), data.getQuantity());

        }

        // 二维码
        if (EPresellType.QR.getCode().equals(deriveGroup.getType())) {

            // 认领寄售
            deriveGroupBO.refreshClaimQr(deriveGroup.getCode(),
                deriveGroup.getClaimant());

            // 生成新的资产
            String originalGroupCode = originalGroupBO.saveOriginalGroup(data,
                deriveGroup.getProductCode(), data.getApplyUser(),
                data.getPrice(), data.getQuantity());

            // 分配预售权
            int presellInventoryQuantity = 0;
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
            String status = EDeriveGroupStatus.TO_CLAIM.getCode();
            if (deriveGroup.getQuantity() == 0) {
                status = EDeriveGroupStatus.CLAIMED.getCode();
            }
            deriveGroupBO.refreshClaimPublic(deriveGroup.getCode(),
                deriveGroup.getClaimant(), deriveGroup.getQuantity(), status);

            // 生成新的资产
            String originalGroupCode = originalGroupBO.saveOriginalGroup(data,
                deriveGroup.getProductCode(), data.getApplyUser(),
                data.getPrice(), data.getQuantity());

            // 分配预售权
            int presellInventoryQuantity = 0;
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

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(originalGroup.getCode(),
            originalGroup.getPresellQuantity() - deriveGroup.getQuantity());
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
        Paginable<GroupOrder> page = groupOrderBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (GroupOrder groupOrder : page.getList()) {
                init(groupOrder);
            }
        }

        return page;
    }

    @Override
    public List<GroupOrder> queryGroupOrderList(GroupOrder condition) {
        List<GroupOrder> list = groupOrderBO.queryGroupOrderList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (GroupOrder groupOrder : list) {
                init(groupOrder);
            }
        }
        return list;
    }

    @Override
    public GroupOrder getGroupOrder(String code) {
        GroupOrder groupOrder = groupOrderBO.getGroupOrder(code);

        init(groupOrder);

        return groupOrder;
    }

    private void init(GroupOrder groupOrder) {
        // 预售产品
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(groupOrder.getProductCode());
        groupOrder.setPresellProduct(presellProduct);

        // 产权方
        String sellerName = null;
        SYSUser sysUser = sysUserBO
            .getSYSUserUnCheck(presellProduct.getOwnerId());
        if (null != sysUser) {
            sellerName = sysUser.getMobile();
            if (StringUtils.isNotBlank(sysUser.getRealName())) {
                sellerName = sysUser.getRealName() + sellerName;
            }
        }
        groupOrder.setSellerName(sellerName);

        // 支付流水编号
        Account userCnyAccount = accountBO.getAccountByUser(
            groupOrder.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(groupOrder.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            groupOrder.setJourCode(jourList.get(0).getCode());
        }

        // 原生组
        StringBuilder treeNumbers = new StringBuilder();
        if (EGroupOrderStatus.PAYED.getCode().equals(groupOrder.getStatus())) {
            OriginalGroup originalGroup = originalGroupBO
                .getOriginalGroupByOrder(groupOrder.getCode());

            if (null != originalGroup) {
                List<PresellInventory> presellInventorieList = presellInventoryBO
                    .queryTreeNumberListByGroup(originalGroup.getCode());
                if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                    for (PresellInventory presellInventory : presellInventorieList) {
                        treeNumbers.append(presellInventory.getTreeNumber())
                            .append(".");
                    }
                }
            }
        }
        groupOrder.setTreeNumbers(treeNumbers.toString());

        // 下单人
        User applyUser = userBO.getUserUnCheck(groupOrder.getApplyUser());
        String applyUserName = null;
        if (null != applyUser) {
            applyUserName = applyUser.getMobile();
            if (null != applyUser.getNickname()) {
                applyUserName = applyUser.getNickname();
            }
        }
        groupOrder.setApplyUserName(applyUserName);
    }

}
