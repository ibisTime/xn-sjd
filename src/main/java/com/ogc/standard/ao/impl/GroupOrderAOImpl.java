package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.ao.IWeChatAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IGroupOrderBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.PhoneUtil;
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

    @Autowired
    private IWeChatAO weChatAO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IPresellSpecsBO presellSpecsBO;

    @Override
    @Transactional
    public void cancelGroupOrder(String code, String remark) {
        GroupOrder groupOrder = groupOrderBO.getGroupOrder(code);
        if (!EGroupOrderStatus.TO_PAY.getCode()
            .equals(groupOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能取消");
        }

        // 还原寄售数量
        DeriveGroup deriveGroup = deriveGroupBO
            .getDeriveGroup(groupOrder.getGroupCode());
        deriveGroupBO.refreshQuantity(deriveGroup.getCode(),
            deriveGroup.getQuantity() + groupOrder.getQuantity());

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
                groupOrder.getCode(), EJourBizTypeUser.CONSIGN_SELL.getCode(),
                EJourBizTypeUser.CONSIGN_SELL.getValue(),
                groupOrder.getAmount());
            result = new PayOrderRes(signOrder);

        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付

            groupOrderBO.refreshPayGroup(groupOrder.getCode(), payType,
                deductRes);

            User user = userBO.getUser(groupOrder.getApplyUser());
            result = weChatAO.getPrepayIdH5(groupOrder.getApplyUser(),
                user.getH5OpenId(), ESysUser.SYS_USER.getCode(),
                groupOrder.getCode(), groupOrder.getCode(),
                EJourBizTypeUser.CONSIGN_SELL.getCode(),
                EJourBizTypeUser.CONSIGN_SELL.getValue(),
                groupOrder.getAmount());

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
        smsBO.saveAdoptBulletin(groupOrder.getApplyUser(),
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
            EJourBizTypeUser.CONSIGN_SELL.getCode(),
            EJourBizTypePlat.CONSIGN_SELL.getCode(),
            EJourBizTypeUser.CONSIGN_SELL.getValue(),
            EJourBizTypePlat.CONSIGN_SELL.getValue(), data.getCode());

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(data.getProductCode());
        smsBO.saveAdoptBulletin(data.getApplyUser(),
            data.getQuantity().toString(), ESellType.PRESELL.getCode(),
            presellProduct.getName());

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
            smsBO.saveAdoptBulletin(data.getApplyUser(),
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
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());

        // 定向
        if (EPresellType.DIRECT.getCode().equals(deriveGroup.getType())) {

            // 分配资产
            String originalGroupCode = null;
            List<OriginalGroup> existsOriginalList = originalGroupBO
                .queryOriginalGroup(data.getApplyUser(), data.getProductCode());

            if (CollectionUtils.isEmpty(existsOriginalList)) {
                originalGroupCode = originalGroupBO.saveOriginalGroup(data,
                    deriveGroup.getProductCode(), data.getApplyUser(),
                    data.getAmount(), data.getQuantity());
            } else {
                OriginalGroup existsOriginal = existsOriginalList.get(0);

                Integer quantity = existsOriginal.getQuantity()
                        + data.getQuantity();
                BigDecimal price = existsOriginal.getPrice()
                    .add(data.getAmount());

                originalGroupBO.refreshQuantityPrice(existsOriginal.getCode(),
                    quantity, price);

                originalGroupCode = existsOriginal.getCode();
            }

            // 转移预售权
            int presellInventoryQuantity = 1;
            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryPresellInventoryListByGroup(originalGroup.getCode());
            if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                for (PresellInventory presellInventory : presellInventorieList) {
                    if (presellInventoryQuantity++ > data.getQuantity()) {
                        break;
                    }

                    presellInventoryBO.refreshGroup(presellInventory.getCode(),
                        EGroupType.DERIVE_GROUP.getCode(), originalGroupCode);
                }
            }

            // 认领寄售
            String status = EDeriveGroupStatus.TO_CLAIM.getCode();
            if (deriveGroup.getQuantity() == 0) {
                status = EDeriveGroupStatus.CLAIMED.getCode();
            }
            deriveGroupBO.refreshClaimPublic(deriveGroup.getCode(),
                deriveGroup.getClaimant(), deriveGroup.getQuantity(), status);

            // 生成认养权
            PresellInventory presellInventory = presellInventoryBO
                .getTreeNumberByGroup(originalGroupCode);
            String[] treeNumbers = presellInventory.getTreeNumber().split("&");// 认养权的树

            Set<String> treeNumberSet = new HashSet<>(
                Arrays.asList(treeNumbers));

            for (String treeNumber : treeNumberSet) {
                adoptOrderTreeBO.saveAdoptOrderTree(presellProduct, data,
                    treeNumber);
            }
        }

        // 二维码
        if (EPresellType.QR.getCode().equals(deriveGroup.getType())) {

            // 分配资产
            String originalGroupCode = null;
            List<OriginalGroup> existsOriginalList = originalGroupBO
                .queryOriginalGroup(data.getApplyUser(), data.getProductCode());

            if (CollectionUtils.isEmpty(existsOriginalList)) {
                originalGroupCode = originalGroupBO.saveOriginalGroup(data,
                    deriveGroup.getProductCode(), data.getApplyUser(),
                    data.getAmount(), data.getQuantity());
            } else {
                OriginalGroup existsOriginal = existsOriginalList.get(0);

                Integer quantity = existsOriginal.getQuantity()
                        + data.getQuantity();
                BigDecimal price = existsOriginal.getPrice()
                    .add(data.getAmount());

                originalGroupBO.refreshQuantityPrice(existsOriginal.getCode(),
                    quantity, price);

                originalGroupCode = existsOriginal.getCode();
            }

            // 分配预售权
            int presellInventoryQuantity = 1;
            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryPresellInventoryListByGroup(originalGroup.getCode());

            if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                for (PresellInventory presellInventory : presellInventorieList) {
                    if (presellInventoryQuantity++ > data.getQuantity()) {
                        break;
                    }

                    presellInventoryBO.refreshGroup(presellInventory.getCode(),
                        EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
                }
            }

            // 认领寄售
            String status = EDeriveGroupStatus.TO_CLAIM.getCode();
            if (deriveGroup.getQuantity() == 0) {
                status = EDeriveGroupStatus.CLAIMED.getCode();
            }
            deriveGroupBO.refreshClaimPublic(deriveGroup.getCode(),
                deriveGroup.getClaimant(), deriveGroup.getQuantity(), status);

            // 生成认养权
            PresellInventory presellInventory = presellInventoryBO
                .getTreeNumberByGroup(originalGroupCode);
            String[] treeNumbers = presellInventory.getTreeNumber().split("&");// 认养权的树

            Set<String> treeNumberSet = new HashSet<>(
                Arrays.asList(treeNumbers));

            for (String treeNumber : treeNumberSet) {
                adoptOrderTreeBO.saveAdoptOrderTree(presellProduct, data,
                    treeNumber.replace(",", ""));
            }
        }

        // 挂单
        if (EPresellType.PUBLIC.getCode().equals(deriveGroup.getType())) {

            // 分配资产
            String originalGroupCode = null;
            List<OriginalGroup> existsOriginalList = originalGroupBO
                .queryOriginalGroup(data.getApplyUser(), data.getProductCode());

            if (CollectionUtils.isEmpty(existsOriginalList)) {
                originalGroupCode = originalGroupBO.saveOriginalGroup(data,
                    deriveGroup.getProductCode(), data.getApplyUser(),
                    data.getAmount(), data.getQuantity());
            } else {
                OriginalGroup existsOriginal = existsOriginalList.get(0);

                Integer quantity = existsOriginal.getQuantity()
                        + data.getQuantity();
                BigDecimal price = existsOriginal.getPrice()
                    .add(data.getAmount());

                originalGroupBO.refreshQuantityPrice(existsOriginal.getCode(),
                    quantity, price);

                originalGroupCode = existsOriginal.getCode();
            }

            // 分配预售权
            int presellInventoryQuantity = 1;
            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryPresellInventoryListByGroup(originalGroup.getCode());

            if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                for (PresellInventory presellInventory : presellInventorieList) {
                    if (presellInventoryQuantity++ > data.getQuantity()) {
                        break;
                    }

                    presellInventoryBO.refreshGroup(presellInventory.getCode(),
                        EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
                }
            }

            // 更新波动
            DeriveGroup newest = deriveGroupBO
                .getNewestByVariety(deriveGroup.getVariety());
            Double wave = 0d;
            if (null != newest) {
                Double newestPrice = newest.getPrice().doubleValue();
                if (newestPrice > 0) {
                    wave = (data.getPrice().doubleValue() - newestPrice)
                            / newestPrice;
                    wave = new BigDecimal(wave)
                        .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
            deriveGroupBO.refreshWave(deriveGroup.getCode(), wave);

            // 认领寄售
            String status = EDeriveGroupStatus.TO_CLAIM.getCode();
            if (deriveGroup.getQuantity() == 0) {
                status = EDeriveGroupStatus.CLAIMED.getCode();
            }
            deriveGroupBO.refreshClaimPublic(deriveGroup.getCode(),
                deriveGroup.getClaimant(), deriveGroup.getQuantity(), status);

            // 生成认养权
            PresellInventory presellInventory = presellInventoryBO
                .getTreeNumberByGroup(originalGroupCode);
            String[] treeNumbers = presellInventory.getTreeNumber().split("&");// 认养权的树

            Set<String> treeNumberSet = new HashSet<>(
                Arrays.asList(treeNumbers));

            for (String treeNumber : treeNumberSet) {
                adoptOrderTreeBO.saveAdoptOrderTree(presellProduct, data,
                    treeNumber);
            }
        }

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(originalGroup.getCode(),
            originalGroup.getPresellQuantity() - data.getQuantity());
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

                // 还原寄售数量
                DeriveGroup deriveGroup = deriveGroupBO
                    .getDeriveGroup(groupOrder.getGroupCode());
                deriveGroupBO.refreshQuantity(deriveGroup.getCode(),
                    deriveGroup.getQuantity() + groupOrder.getQuantity());
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
            applyUserName = PhoneUtil.hideMobile(applyUser.getMobile());
            if (null != applyUser.getNickname()) {
                applyUserName = applyUser.getNickname() + applyUserName;
            }
        }
        groupOrder.setApplyUserName(applyUserName);

        if (null == groupOrder.getPayAmount() && null != groupOrder.getAmount()
                && null != groupOrder.getCnyDeductAmount()) {
            groupOrder.setPayAmount(groupOrder.getAmount()
                .subtract(groupOrder.getCnyDeductAmount()));
        }
    }

}
