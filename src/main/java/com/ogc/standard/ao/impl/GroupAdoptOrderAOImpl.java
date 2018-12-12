package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.ao.IWeChatAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class GroupAdoptOrderAOImpl implements IGroupAdoptOrderAO {
    static final Logger logger = LoggerFactory
        .getLogger(GroupAdoptOrderAOImpl.class);

    @Autowired
    private IGroupAdoptOrderBO groupAdoptOrderBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISmsBO smsBO;

    @Autowired
    private IUserAO userAO;

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IWeChatAO weChatAO;

    @Override
    @Transactional
    public String firstAddGroupAdoptOrder(String userId, String specsCode,
            Integer quantity) {

        // 产品状态检查
        ProductSpecs productSpecs = productSpecsBO.getProductSpecs(specsCode);
        Product product = productBO.getProduct(productSpecs.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }

        // 判断库存数量
        if (quantity > (product.getRaiseCount() - product.getNowCount())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "库存数量不足，不能下单");
        }

        // 落地订单
        String identifyCode = UUID.randomUUID().toString().hashCode() + "";
        identifyCode = identifyCode.replace("-", "");
        String code = groupAdoptOrderBO.saveGroupAdoptOrderFirst(identifyCode,
            userId, quantity, product, productSpecs);

        // 锁定产品
        productBO.refreshLockProduct(product.getCode(), identifyCode,
            specsCode);

        // 更改树状态
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                treeBO.refreshToPayTree(tree, code);
            }
        }

        // 更新认养产品的已募集数量
        productBO.refreshNowCount(product.getCode(),
            product.getNowCount() + quantity);

        return code;
    }

    @Override
    @Transactional
    public String unFirstAddGroupAdoptOrder(String identifyCode, String userId,
            Integer quantity) {

        // 识别码是否存在
        List<GroupAdoptOrder> list = groupAdoptOrderBO
            .queryGroupAdoptOrderById(identifyCode);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn0000", "识别码不存在，请重新输入");
        }

        GroupAdoptOrder groupAdoptOrder = list.get(0);// 所有订单的规格都相同

        // 识别码是否失效
        ProductSpecs productSpecs = productSpecsBO
            .getProductSpecs(groupAdoptOrder.getProductSpecsCode());
        Product product = productBO.getProduct(productSpecs.getProductCode());
        if ((new Date()).after(product.getIdInvalidDatetime())) {
            throw new BizException("xn0000", "识别码已过期，不能下单");
        }

        // 判断库存数量
        if (quantity > (product.getRaiseCount() - product.getNowCount())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "库存数量不足，不能下单");
        }

        // 落地订单
        String code = groupAdoptOrderBO.saveGroupAdoptOrderUnFirst(identifyCode,
            userId, quantity, product, productSpecs);

        // 更新认养产品的已募集数量
        productBO.refreshNowCount(product.getCode(),
            product.getNowCount() + quantity);

        return code;
    }

    @Override
    @Transactional
    public void cancelGroupAdoptOrder(String code, String userId,
            String remark) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);
        if (!EGroupAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能取消");
        }

        if (!data.getApplyUser().equals(userId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前订单不是您本人的，不能取消");
        }

        // 更新认养产品的已募集数量
        Product product = productBO.getProduct(data.getProductCode());
        Integer productCount = product.getNowCount() - data.getQuantity();
        productBO.refreshNowCount(product.getCode(), productCount);

        // 更新未认养的产品状态
        if (productCount == 0) {
            productBO.refreshUnLockProduct(product.getCode());
        }

        // 更新订单状态
        groupAdoptOrderBO.refreshCancelOrder(data, remark);
    }

    @Override
    @Transactional
    public Object toPayAdoptOrder(String code, String payType,
            String isJfDeduct, String tradePwd) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);

        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(data.getApplyUser(), tradePwd);
        }

        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能支付");
        }

        if (EPayType.YE.getCode().equals(payType)) {
            userBO.checkTradePwd(data.getApplyUser(), tradePwd);
        }

        Product product = productBO.getProduct(data.getProductCode());

        // 积分抵扣处理
        XN629048Res deductRes = distributionOrderBO.getAdoptOrderDeductAmount(
            product.getMaxJfdkRate(), data.getAmount(), data.getApplyUser(),
            isJfDeduct);

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付
            result = toPayAdoptOrderYue(data, deductRes);
        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付
            groupAdoptOrderBO.refreshPayGroup(data, payType, deductRes);
            String signOrder = alipayBO.getSignedOrder(data.getApplyUser(),
                ESysUser.SYS_USER.getCode(), data.getPayGroup(),
                EJourBizTypeUser.ADOPT_COLLECT.getCode(),
                EJourBizTypeUser.ADOPT_COLLECT.getValue(),
                data.getAmount().subtract(deductRes.getCnyAmount()));
            result = new PayOrderRes(signOrder);
        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付
            groupAdoptOrderBO.refreshPayGroup(data, payType, deductRes);

            User user = userBO.getUser(data.getApplyUser());
            result = weChatAO.getPrepayIdH5(data.getApplyUser(),
                user.getH5OpenId(), ESysUser.SYS_USER.getCode(), data.getCode(),
                data.getCode(), EJourBizTypeUser.ADOPT_COLLECT.getCode(),
                EJourBizTypeUser.ADOPT_COLLECT.getValue(),
                data.getAmount().subtract(deductRes.getCnyAmount()));
        }
        return result;
    }

    // 1、判断余额是否足够，并扣除账户余额
    // 2、进行分销
    // 3、更新订单和树状态
    private Object toPayAdoptOrderYue(GroupAdoptOrder data,
            XN629048Res resultRes) {
        Account userCnyAccount = accountBO.getAccountByUser(data.getApplyUser(),
            ECurrency.CNY.getCode());
        BigDecimal payAmount = data.getAmount()
            .subtract(resultRes.getCnyAmount());// 实际付款人民币金额
        if (userCnyAccount.getAmount().compareTo(payAmount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "人民币账户余额不足");
        }

        // 人民币余额划转
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.transAmount(userCnyAccount, sysCnyAccount, payAmount,
            EJourBizTypeUser.ADOPT_COLLECT.getCode(),
            EJourBizTypePlat.ADOPT_COLLECT.getCode(),
            EJourBizTypeUser.ADOPT_COLLECT.getValue(),
            EJourBizTypePlat.ADOPT_COLLECT.getValue(), data.getCode());

        // 积分抵扣
        accountBO.transAmount(data.getApplyUser(), ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), resultRes.getJfAmount(),
            EJourBizTypeUser.ADOPT_BUY_DEDUCT.getCode(),
            EJourBizTypePlat.ADOPT_BUY_DEDUCT.getCode(),
            EJourBizTypeUser.ADOPT_BUY_DEDUCT.getValue(),
            EJourBizTypePlat.ADOPT_BUY_DEDUCT.getValue(), data.getCode());

        // 进行分销
        Product productInfo = productBO.getProduct(data.getProductCode());
        BigDecimal backJfAmount = distributionOrderBO.adoptDistribution(
            data.getCode(), productInfo.getOwnerId(), payAmount,
            data.getApplyUser(), ESellType.COLLECTIVE.getCode(), resultRes);

        // 用户升级
        userAO.upgradeUserLevel(data.getApplyUser());

        // 分配认养权、更新树状态
        List<Tree> treeList = treeBO
            .queryTreeListByProduct(data.getProductCode());
        Product product = productBO.getProduct(data.getProductCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                treeBO.refreshPayTree(tree,
                    tree.getAdoptCount() + data.getQuantity());
            }
        }

        List<AdoptOrderTree> adoptOrderTreeList = adoptOrderTreeBO
            .queryUserAdoptedOrder(data.getApplyUser(),
                treeList.get(0).getTreeNumber());
        if (CollectionUtils.isEmpty(adoptOrderTreeList)) {
            adoptOrderTreeBO.saveAdoptOrderTree(product, data,
                treeList.get(0).getTreeNumber());
        } else {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeList.get(0);
            adoptOrderTreeBO.refreshQuantity(adoptOrderTree.getCode(),
                data.getQuantity() + adoptOrderTree.getQuantity());
        }

        // 添加快报
        long totalPayedQuantity = groupAdoptOrderBO
            .getPayedTotalQuantity(product.getIdentifyCode());
        if (totalPayedQuantity == 0) {
            smsBO.saveFirstAdoptBulletin(data.getApplyUser(),
                product.getName());

            productBO.refreshCollectFirstUser(data.getProductCode(),
                data.getApplyUser());
        } else {
            smsBO.saveAdoptBulletin(data.getApplyUser(),
                data.getQuantity().toString(), ESellType.COLLECTIVE.getCode(),
                product.getName());
        }

        // 业务订单更改
        groupAdoptOrderBO.payYueSuccess(data, resultRes, backJfAmount);

        return new BooleanRes(true);
    }

    // 支付宝回调
    @Override
    @Transactional
    public void paySuccess(String payGroup) {
        GroupAdoptOrder data = groupAdoptOrderBO
            .getGroupAdoptOrderByPayGroup(payGroup);
        if (EGroupAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            XN629048Res resultRes = new XN629048Res(data.getCnyDeductAmount(),
                data.getJfDeductAmount());

            // 积分抵扣
            accountBO.transAmount(data.getApplyUser(),
                ESysUser.SYS_USER.getCode(), ECurrency.JF.getCode(),
                resultRes.getJfAmount(),
                EJourBizTypeUser.ADOPT_BUY_DEDUCT.getCode(),
                EJourBizTypePlat.ADOPT_BUY_DEDUCT.getCode(),
                EJourBizTypeUser.ADOPT_BUY_DEDUCT.getValue(),
                EJourBizTypePlat.ADOPT_BUY_DEDUCT.getValue(), data.getCode());

            // 进行分销
            Product productInfo = productBO.getProduct(data.getProductCode());
            BigDecimal backJfAmount = distributionOrderBO.adoptDistribution(
                data.getCode(), productInfo.getOwnerId(),
                data.getAmount().subtract(data.getCnyDeductAmount()),
                data.getApplyUser(), ESellType.COLLECTIVE.getCode(), resultRes);

            // 用户升级
            userAO.upgradeUserLevel(data.getApplyUser());

            // 分配认养权、更新树状态
            List<Tree> treeList = treeBO
                .queryTreeListByProduct(data.getProductCode());
            Product product = productBO.getProduct(data.getProductCode());
            if (CollectionUtils.isNotEmpty(treeList)) {
                for (Tree tree : treeList) {
                    treeBO.refreshPayTree(tree,
                        tree.getAdoptCount() + data.getQuantity());
                }
            }
            List<AdoptOrderTree> adoptOrderTreeList = adoptOrderTreeBO
                .queryUserAdoptedOrder(data.getApplyUser(),
                    treeList.get(0).getTreeNumber());
            if (CollectionUtils.isEmpty(adoptOrderTreeList)) {
                adoptOrderTreeBO.saveAdoptOrderTree(product, data,
                    treeList.get(0).getTreeNumber());
            } else {
                AdoptOrderTree adoptOrderTree = adoptOrderTreeList.get(0);
                adoptOrderTreeBO.refreshQuantity(adoptOrderTree.getCode(),
                    data.getQuantity() + adoptOrderTree.getQuantity());
            }

            // 添加快报
            long totalPayedQuantity = groupAdoptOrderBO
                .getPayedTotalQuantity(product.getIdentifyCode());
            if (totalPayedQuantity == 0) {
                smsBO.saveFirstAdoptBulletin(data.getApplyUser(),
                    product.getName());

                productBO.refreshCollectFirstUser(data.getProductCode(),
                    data.getApplyUser());
            } else {
                smsBO.saveAdoptBulletin(data.getApplyUser(),
                    data.getQuantity().toString(),
                    ESellType.COLLECTIVE.getCode(), product.getName());
            }

            // 业务订单更改
            groupAdoptOrderBO.paySuccess(data,
                data.getAmount().subtract(data.getCnyDeductAmount()),
                backJfAmount);

        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单号[" + data.getCode() + "]支付重复回调");
        }
    }

    @Override
    public XN629048Res getOrderDkAmount(String code) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);
        if (!EGroupAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前订单不是待支付状态");
        }

        Product product = productBO.getProduct(data.getProductCode());

        return distributionOrderBO.getAdoptOrderDeductAmount(
            product.getMaxJfdkRate(), data.getAmount(), data.getApplyUser(),
            EBoolean.YES.getCode());
    }

    // 更新已满标的订单
    @Override
    @Transactional
    public void toFullAdopt() {
        // 已满标的锁定产品
        List<Product> productList = productBO
            .queryLockedProductList(EBoolean.NO.getCode());

        if (CollectionUtils.isNotEmpty(productList)) {
            for (Product product : productList) {

                // 若已全部支付，则更新状态
                long totalPayedQuantity = groupAdoptOrderBO
                    .getPayedTotalQuantity(product.getIdentifyCode());
                if (totalPayedQuantity == product.getRaiseCount()) {
                    productBO.refreshAdoptProduct(product.getCode());

                    groupAdoptOrderBO
                        .refreshFullOrderById(product.getIdentifyCode());
                }

            }
        }
    }

    // 更新订单的认养中、已到期状态
    public void doDailyGroupAdoptOrder() {
        logger.info("***************开始扫描已支付待认养集体订单***************");
        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setStatus(EGroupAdoptOrderStatus.FULL.getCode());
        condition.setStartDatetimeStart(new Date());
        List<GroupAdoptOrder> startGroupAdoptOrderList = groupAdoptOrderBO
            .queryGroupAdoptOrderList(condition);

        if (CollectionUtils.isNotEmpty(startGroupAdoptOrderList)) {
            for (GroupAdoptOrder groupAdoptOrder : startGroupAdoptOrderList) {
                startGroupAdoptOrder(groupAdoptOrder);
            }
        }
        logger.info("***************结束扫描已支付待认养集体订单***************");

        logger.info("***************开始扫描已认养集体订单***************");
        GroupAdoptOrder condition2 = new GroupAdoptOrder();
        condition2.setStatus(EGroupAdoptOrderStatus.ADOPT.getCode());
        condition.setEndDatetimeEnd(new Date());
        List<GroupAdoptOrder> endGroupAdoptOrderList = groupAdoptOrderBO
            .queryGroupAdoptOrderList(condition);

        if (CollectionUtils.isNotEmpty(endGroupAdoptOrderList)) {
            for (GroupAdoptOrder groupAdoptOrder : endGroupAdoptOrderList) {
                endGroupAdoptOrder(groupAdoptOrder);
            }
        }
        logger.info("***************结束扫描已认养集体订单***************");
    }

    private void startGroupAdoptOrder(GroupAdoptOrder groupAdoptOrder) {
        // 订单状态更改
        groupAdoptOrderBO.refreshStartAdopt(groupAdoptOrder.getCode());

        // 认养权状态更改
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(groupAdoptOrder.getCode());
        for (AdoptOrderTree adoptOrderTree : list) {
            adoptOrderTreeBO.refreshAdoptOrderTree(adoptOrderTree,
                EAdoptOrderTreeStatus.ADOPT);
        }
    }

    private void endGroupAdoptOrder(GroupAdoptOrder groupAdoptOrder) {
        // 订单结束
        groupAdoptOrderBO.refreshEndAdopt(groupAdoptOrder.getCode());

        // 认养权结束
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(groupAdoptOrder.getCode());
        for (AdoptOrderTree adoptOrderTree : list) {
            adoptOrderTreeBO.refreshAdoptOrderTree(adoptOrderTree,
                EAdoptOrderTreeStatus.END);
        }

        // 更新产品已募集数量
        Product product = productBO
            .getProduct(groupAdoptOrder.getProductCode());
        productBO.refreshNowCount(groupAdoptOrder.getProductCode(),
            product.getNowCount() - groupAdoptOrder.getQuantity());
    }

    // 更新未支付订单状态
    public void doCancelGroupAdoptOrder() {
        logger.info("***************开始扫描未支付订单***************");

        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setStatus(EAdoptOrderStatus.TO_PAY.getCode());
        condition.setApplyDatetimeEnd(
            DateUtil.getRelativeDateOfMinute(new Date(), -15));// 前15分钟还未支付的订单
        List<GroupAdoptOrder> groupAdoptOrderList = groupAdoptOrderBO
            .queryGroupAdoptOrderList(condition);

        if (CollectionUtils.isNotEmpty(groupAdoptOrderList)) {
            for (GroupAdoptOrder groupAdoptOrder : groupAdoptOrderList) {
                // 订单状态变更
                groupAdoptOrderBO.refreshCancelOrder(groupAdoptOrder,
                    "超15分钟未支付系统自动取消");

                // 更新产品已募集数量
                Product product = productBO
                    .getProduct(groupAdoptOrder.getProductCode());
                Integer productCount = product.getNowCount()
                        - groupAdoptOrder.getQuantity();
                productBO.refreshNowCount(groupAdoptOrder.getProductCode(),
                    productCount);

                // 更新未认养产品状态
                if (productCount == 0) {
                    productBO.refreshUnLockProduct(product.getCode());
                }

            }
        }

        logger.info("***************结束扫描未支付订单***************");
    }

    public void doInvalidIdentifyCode() {
        logger.info("***************开始扫描失效识别码***************");

        // 未满标的锁定产品
        List<Product> productList = productBO
            .queryLockedProductList(EBoolean.YES.getCode());

        if (CollectionUtils.isNotEmpty(productList)) {
            for (Product product : productList) {

                // 还没到失效时间则跳过
                if ((new Date()).before(product.getIdInvalidDatetime())) {
                    continue;
                }

                // 添加快报
                GroupAdoptOrder firstOrder = groupAdoptOrderBO
                    .getFirstPayedOrderById(product.getIdentifyCode());
                if (null != firstOrder) {
                    smsBO.saveExpireGroupAdoptBulletin(
                        firstOrder.getApplyUser(), product.getName());
                }

                // 订单退款
                List<GroupAdoptOrder> groupAdoptOrderList = groupAdoptOrderBO
                    .queryGroupAdoptOrderById(product.getIdentifyCode());
                if (CollectionUtils.isNotEmpty(groupAdoptOrderList)) {
                    for (GroupAdoptOrder groupAdoptOrder : groupAdoptOrderList) {

                        // 已支付的订单退钱
                        if (EGroupAdoptOrderStatus.PAYED.getCode()
                            .equals(groupAdoptOrder.getStatus())) {

                            // 人民币余额划转
                            Account sysCnyAccount = accountBO.getAccount(
                                ESystemAccount.SYS_ACOUNT_CNY.getCode());
                            Account userCnyAccount = accountBO.getAccountByUser(
                                groupAdoptOrder.getApplyUser(),
                                ECurrency.CNY.getCode());
                            accountBO.transAmount(sysCnyAccount, userCnyAccount,
                                groupAdoptOrder.getPayAmount(),
                                EJourBizTypeUser.UN_FULL_CNY.getCode(),
                                EJourBizTypePlat.UN_FULL_CNY.getCode(),
                                EJourBizTypeUser.UN_FULL_CNY.getValue(),
                                EJourBizTypePlat.UN_FULL_CNY.getValue(),
                                groupAdoptOrder.getCode());

                            // 认养抵扣积分划转
                            Account sysJfAccount = accountBO.getAccount(
                                ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
                            Account userJfAccount = accountBO.getAccountByUser(
                                groupAdoptOrder.getApplyUser(),
                                ECurrency.JF.getCode());
                            accountBO.transAmount(sysJfAccount, userJfAccount,
                                groupAdoptOrder.getJfDeductAmount(),
                                EJourBizTypeUser.UN_FULL_DEDUCTJF.getCode(),
                                EJourBizTypePlat.UN_FULL_DEDUCTJF.getCode(),
                                EJourBizTypeUser.UN_FULL_DEDUCTJF.getValue(),
                                EJourBizTypePlat.UN_FULL_DEDUCTJF.getValue(),
                                groupAdoptOrder.getCode());

                            // 认养返利积分划转
                            accountBO.transAmount(userJfAccount, sysJfAccount,
                                groupAdoptOrder.getBackJfAmount(),
                                EJourBizTypeUser.UN_FULL_BACKJF.getCode(),
                                EJourBizTypePlat.UN_FULL_BACKJF.getCode(),
                                EJourBizTypeUser.UN_FULL_BACKJF.getValue(),
                                EJourBizTypePlat.UN_FULL_BACKJF.getValue(),
                                groupAdoptOrder.getCode());

                            // 更新认养权状态
                            adoptOrderTreeBO.refreshInvalidAdoptByOrder(
                                groupAdoptOrder.getCode());
                        }
                    }

                    // 更新订单状态
                    groupAdoptOrderBO
                        .refreshUnFullOrderById(product.getIdentifyCode());

                    // 重置产品下树的认养数量
                    treeBO.refreshAdoptCountByProduct(product.getCode(), 0);

                    // 取消认养
                    treeBO.refreshCancelTreeByProduct(product.getCode());

                    // 解锁产品
                    productBO.refreshUnLockProduct(product.getCode());

                }

            }
        }

        logger.info("***************结束扫描失效识别码***************");
    }

    @Override
    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition) {
        Paginable<GroupAdoptOrder> page = groupAdoptOrderBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (GroupAdoptOrder groupAdoptOrder : page.getList()) {
                initGroupAdoptOrder(groupAdoptOrder);
            }
        }

        return page;
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition) {
        List<GroupAdoptOrder> list = groupAdoptOrderBO
            .queryGroupAdoptOrderList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (GroupAdoptOrder groupAdoptOrder : list) {
                initGroupAdoptOrder(groupAdoptOrder);
            }
        }

        return list;
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrder(String code, String isSettle) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);

        initGroupAdoptOrder(data);

        if (EBoolean.YES.getCode().equals(isSettle)) {
            List<Settle> settleList = settleBO.querySettleList(code);
            if (CollectionUtils.isNotEmpty(settleList)) {
                for (Settle settle : settleList) {
                    AgentUser agentUser = agentUserBO
                        .getAgentUser(settle.getUserId());
                    settle.setAgentUser(agentUser);
                }
            }
            data.setSettleList(settleList);
        }

        return data;
    }

    private void initGroupAdoptOrder(GroupAdoptOrder data) {
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);

        List<AdoptOrderTree> adoptOrderTreeList = adoptOrderTreeBO
            .queryAdoptOrderTreeList(data.getCode());
        data.setAdoptOrderTreeList(adoptOrderTreeList);

        StringBuilder treeNumbers = new StringBuilder();
        List<Tree> treeList = new ArrayList<Tree>();

        for (AdoptOrderTree adoptOrderTree : adoptOrderTreeList) {
            Tree tree = treeBO
                .getTreeByTreeNumber(adoptOrderTree.getTreeNumber());
            treeList.add(tree);

            treeNumbers.append(adoptOrderTree.getTreeNumber()).append(". ");
        }

        data.setTreeList(treeList);

        Integer adoptYear = DateUtil.yearsBetween(data.getStartDatetime(),
            data.getEndDatetime());
        data.setAdoptYear(adoptYear);

        User user = userBO.getUserUnCheck(data.getApplyUser());
        if (null != user) {
            data.setApplyUserName(user.getMobile());
        }

        data.setTreeNumbers(treeNumbers.toString());

        if (null == data.getPayAmount() && null != data.getAmount()
                && null != data.getCnyDeductAmount()) {
            data.setPayAmount(
                data.getAmount().subtract(data.getCnyDeductAmount()));
        }
    }

}
