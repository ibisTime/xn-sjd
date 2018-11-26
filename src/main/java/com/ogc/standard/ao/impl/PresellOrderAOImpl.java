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
import com.ogc.standard.ao.IWeChatAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellOrderBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.PresellSpecs;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
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

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IWeChatAO weChatAO;

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

        Integer orderWeight = quantity * presellSpecs.getPackCount()
                * presellProduct.getPackWeight();// 下单重量
        Integer remainWeight = presellProduct.getTotalOutput()
                - presellProduct.getNowCount();// 剩余重量
        if (orderWeight > remainWeight) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "库存数量不足，不能下单");
        }

        // 落地订单
        String code = presellOrderBO.savePresellOrder(userId, presellProduct,
            presellSpecs, quantity);

        // TODO 分配树

        // 更新产品已预售数量
        Integer nowCount = presellProduct.getNowCount() + orderWeight;
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

        Integer orderWeight = presellOrder.getQuantity()
                * presellOrder.getPackCount() * presellProduct.getPackWeight();// 下单重量
        Integer nowCount = presellProduct.getNowCount() - orderWeight;

        presellProductBO.refreshNowCount(presellProduct.getCode(), nowCount);

        // 取消订单
        presellOrderBO.cancelPresellOrder(code, remark);
    }

    @Override
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
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellOrder.getProductCode());
        XN629048Res deductRes = distributionOrderBO.getAdoptOrderDeductAmount(
            presellProduct.getMaxJfdkRate(), presellOrder.getAmount(),
            presellOrder.getApplyUser(), EBoolean.YES.getCode());

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付

            result = toPayPresellOrderYue(presellOrder, deductRes);

        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付

            presellOrderBO.refreshPayGroup(presellOrder, payType, deductRes);

            String signOrder = alipayBO.getSignedOrder(
                presellOrder.getApplyUser(), ESysUser.SYS_USER.getCode(),
                presellOrder.getPayGroup(), EJourBizTypeUser.PRESELL.getCode(),
                EJourBizTypeUser.PRESELL.getValue(),
                presellOrder.getAmount().subtract(deductRes.getCnyAmount()));
            result = new PayOrderRes(signOrder);

        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付

            presellOrderBO.refreshPayGroup(presellOrder, payType, deductRes);

            User user = userBO.getUser(presellOrder.getApplyUser());
            result = weChatAO.getPrepayIdH5(presellOrder.getApplyUser(),
                user.getH5OpenId(), ESysUser.SYS_USER.getCode(),
                presellOrder.getCode(), presellOrder.getCode(),
                EJourBizTypeUser.PRESELL.getCode(),
                EJourBizTypeUser.PRESELL.getValue(),
                presellOrder.getAmount().subtract(deductRes.getCnyAmount()));

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
        BigDecimal payAmount = data.getAmount()
            .subtract(deductRes.getCnyAmount());// 实际付款人民币金额
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
        BigDecimal backJfAmount = distributionOrderBO.presellDistribution(
            data.getCode(), presellProduct.getOwnerId(), data.getAmount(),
            data.getApplyUser(), ESellType.PRESELL.getCode(), deductRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 添加快报
        smsBO.saveAdoptBulletin(data.getApplyUser(),
            data.getQuantity().toString(), ESellType.PRESELL.getCode(),
            presellProduct.getName());

        // 添加原生组
        String originalGroupCode = originalGroupBO.saveOriginalGroup(data);

        // 分配树和预售权
        PresellSpecs presellSpecs = presellSpecsBO
            .getPresellSpecs(data.getSpecsCode());
        assignPresellInventory(data, presellProduct, presellSpecs,
            presellProduct.getSingleOutput(), originalGroupCode);

        // 业务订单更改
        presellOrderBO.payYueSuccess(data, deductRes, backJfAmount);

        return new BooleanRes(true);
    }

    @Override
    @Transactional
    public void paySuccess(String payGroup) {
        PresellOrder data = presellOrderBO.getPresellOrder(payGroup);
        if (EPresellOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {

            XN629048Res resultRes = new XN629048Res(BigDecimal.ZERO,
                BigDecimal.ZERO);

            // 进行分销
            PresellProduct presellProduct = presellProductBO
                .getPresellProduct(data.getProductCode());
            BigDecimal backJfAmount = distributionOrderBO.presellDistribution(
                data.getCode(), presellProduct.getOwnerId(), data.getAmount(),
                data.getApplyUser(), ESellType.PRESELL.getCode(), resultRes);

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // 添加原生组
            String originalGroupCode = originalGroupBO.saveOriginalGroup(data);

            // 分配树和预售权
            PresellSpecs presellSpecs = presellSpecsBO
                .getPresellSpecs(data.getSpecsCode());
            assignPresellInventory(data, presellProduct, presellSpecs,
                presellProduct.getSingleOutput(), originalGroupCode);

            // 添加快报
            smsBO.saveAdoptBulletin(data.getApplyUser(),
                data.getQuantity().toString(), ESellType.PRESELL.getCode(),
                presellProduct.getName());

            // 业务订单更改
            presellOrderBO.paySuccess(data.getCode(), data.getAmount(),
                backJfAmount);
        }
    }

    /**
     * 分配树和预售权
         1、获取还有库存的树列表，树按照剩余库存数量，从小到大排序
         2、为树的剩余库存分配预售权
             2.1、预售权数量为下单【包装单位】数
             2.2、一个预售权可能有多棵树
         3、更新树的库存数量
     */
    private void assignPresellInventory(PresellOrder presellOrder,
            PresellProduct presellProduct, PresellSpecs presellSpecs,
            Integer singleOutput, String originalGroupCode) {
        int presellInventoryQuantity = 0;// 预售权数量
        int tmpPackWeight;// 预售权已经分配的重量
        int tmpTreeWeight;// 树已分配的总数量
        int packWeight = presellProduct.getPackWeight();// 包装重量:$斤/箱
        int quantity = presellOrder.getQuantity() * presellSpecs.getPackCount();// 下单【包装单位】数:$箱

        StringBuffer treeNumbers = new StringBuffer();// 树木编号

        _assignPI: while (presellInventoryQuantity < quantity) {
            List<Tree> treeList = treeBO.queryTreeListRemainInventory(
                presellProduct.getCode(), singleOutput);

            if (CollectionUtils.isNotEmpty(treeList)) {

                tmpPackWeight = 0;
                tmpTreeWeight = 0;

                for (Tree tree : treeList) {

                    tmpPackWeight = tmpPackWeight
                            + (singleOutput - tree.getAdoptCount());
                    treeNumbers.append(tree.getTreeNumber()).append("&");

                    // 添加认养权
                    adoptOrderTreeBO.saveAdoptOrderTree(presellProduct,
                        presellOrder, tree.getTreeNumber());

                    /**
                     * 三种情况：
                     * 1、这棵树不够分：更新树的销量，再分一颗树
                     * 2、这棵树正好被分完：更新树的销量，分配预售权，再次循环
                     * 3、这棵树有剩余：更新树的销量，分配预售权，再次循环
                     */
                    if (tmpPackWeight < packWeight) {
                        treeBO.refreshAdoptCount(tree.getTreeNumber(),
                            singleOutput);
                        tmpTreeWeight = tmpTreeWeight
                                + (singleOutput - tree.getAdoptCount());

                        continue;
                    } else if (tmpPackWeight == packWeight) {
                        treeBO.refreshAdoptCount(tree.getTreeNumber(),
                            singleOutput);

                        presellInventoryBO.savePresellInventory(
                            originalGroupCode, treeNumbers.toString());
                        treeNumbers.setLength(0);

                        presellInventoryQuantity = presellInventoryQuantity + 1;

                        continue _assignPI;
                    } else if (tmpPackWeight > packWeight) {
                        int adoptCount = tree.getAdoptCount() + packWeight
                                - tmpTreeWeight;
                        treeBO.refreshAdoptCount(tree.getTreeNumber(),
                            adoptCount);

                        presellInventoryBO.savePresellInventory(
                            originalGroupCode, treeNumbers.toString());
                        treeNumbers.setLength(0);

                        presellInventoryQuantity = presellInventoryQuantity + 1;
                        continue _assignPI;
                    }

                }
            } else {
                break;
            }
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

        Integer orderWeight = presellOrder.getQuantity()
                * presellOrder.getPackCount() * presellProduct.getPackWeight();// 下单重量
        Integer nowCount = presellProduct.getNowCount() - orderWeight;

        presellProductBO.refreshNowCount(presellProduct.getCode(), nowCount);

        // TODO 取消树

        // 订单状态变更
        presellOrderBO.cancelPresellOrder(presellOrder.getCode(),
            "超15分钟未支付系统自动取消");
    }

    @Override
    public XN629048Res getOrderDkAmount(String code) {

        PresellOrder presellOrder = presellOrderBO.getPresellOrder(code);
        if (!EPresellOrderStatus.TO_PAY.getCode()
            .equals(presellOrder.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前订单不是待支付状态");
        }

        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellOrder.getProductCode());

        return distributionOrderBO.getAdoptOrderDeductAmount(
            presellProduct.getMaxJfdkRate(), presellOrder.getAmount(),
            presellOrder.getApplyUser(), EBoolean.YES.getCode());
    }

    @Override
    public Paginable<PresellOrder> queryPresellOrderPage(int start, int limit,
            PresellOrder condition) {
        Paginable<PresellOrder> page = presellOrderBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (PresellOrder presellOrder : page.getList()) {
                init(presellOrder);
            }
        }

        return page;
    }

    @Override
    public List<PresellOrder> queryPresellOrderList(PresellOrder condition) {
        List<PresellOrder> list = presellOrderBO
            .queryPresellOrderList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (PresellOrder presellOrder : list) {
                init(presellOrder);
            }
        }

        return list;

    }

    @Override
    public List<PresellOrder> queryPresellOrderListByProduct(
            String productCode) {
        List<PresellOrder> list = presellOrderBO
            .queryPresellOrderListByProduct(productCode);

        if (CollectionUtils.isNotEmpty(list)) {
            for (PresellOrder presellOrder : list) {
                // 下单人
                User applyUser = userBO
                    .getUserUnCheck(presellOrder.getApplyUser());
                String applyUserName = null;
                if (null != applyUser) {
                    applyUserName = PhoneUtil.hideMobile(applyUser.getMobile());
                    if (null != applyUser.getNickname()) {
                        applyUserName = applyUser.getNickname();
                    }
                }
                presellOrder.setApplyUserName(applyUserName);
                presellOrder.setApplyUserInfo(applyUser);
            }
        }

        return list;
    }

    @Override
    public PresellOrder getPresellOrder(String code, String isSettle) {
        PresellOrder presellOrder = presellOrderBO.getPresellOrder(code);

        init(presellOrder);

        if (EBoolean.YES.getCode().equals(isSettle)) {
            List<Settle> settleList = settleBO.querySettleList(code);
            if (CollectionUtils.isNotEmpty(settleList)) {
                for (Settle settle : settleList) {
                    AgentUser agentUser = agentUserBO
                        .getAgentUser(settle.getUserId());
                    settle.setAgentUser(agentUser);
                }
            }
            presellOrder.setSettleList(settleList);
        }

        return presellOrder;
    }

    private void init(PresellOrder presellOrder) {
        // 预售产品
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(presellOrder.getProductCode());
        presellOrder.setPresellProduct(presellProduct);

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
        presellOrder.setSellerName(sellerName);

        // 支付流水编号
        Account userCnyAccount = accountBO.getAccountByUser(
            presellOrder.getApplyUser(), ECurrency.CNY.getCode());
        List<Jour> jourList = jourBO.queryJour(presellOrder.getCode(),
            userCnyAccount.getAccountNumber(), EAccountType.CUSTOMER.getCode());
        if (CollectionUtils.isNotEmpty(jourList)) {
            presellOrder.setJourCode(jourList.get(0).getCode());
        }

        // 原生组
        StringBuilder treeNumbers = new StringBuilder();
        if (EPresellOrderStatus.PAYED.getCode()
            .equals(presellOrder.getStatus())) {
            OriginalGroup originalGroup = originalGroupBO
                .getOriginalGroupByOrder(presellOrder.getCode());

            List<PresellInventory> presellInventorieList = presellInventoryBO
                .queryTreeNumberListByGroup(originalGroup.getCode());
            if (CollectionUtils.isNotEmpty(presellInventorieList)) {

                int presellInventoryCount = 1;
                for (PresellInventory presellInventory : presellInventorieList) {
                    treeNumbers.append(
                        presellInventory.getTreeNumber().replace("&", ""));

                    if (presellInventoryCount++ < presellInventorieList
                        .size()) {
                        treeNumbers.append(".");
                    }
                }
            }
        }
        presellOrder.setTreeNumbers(treeNumbers.toString());

        // 下单人
        User applyUser = userBO.getUserUnCheck(presellOrder.getApplyUser());
        String applyUserName = null;
        if (null != applyUser) {
            applyUserName = PhoneUtil.hideMobile(applyUser.getMobile());
            if (null != applyUser.getNickname()) {
                applyUserName = applyUser.getNickname() + applyUserName;
            }
        }
        presellOrder.setApplyUserName(applyUserName);
    }

}
