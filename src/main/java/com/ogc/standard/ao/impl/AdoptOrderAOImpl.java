package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EDirectType;
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
public class AdoptOrderAOImpl implements IAdoptOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IDistributionOrderBO distributionOrderBO;

    @Autowired
    private ISettleBO settleBO;

    @Override
    @Transactional
    public String commitAdoptOrder(String userId, String specsCode,
            Integer quantity) {
        // 参数检查
        ProductSpecs productSpecs = productSpecsBO.getProductSpecs(specsCode);
        Product product = productBO.getProduct(productSpecs.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "认养产品不是已上架待认养状态，不能下单");
        }

        int treeRemainCount = treeBO.getTreeCount(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        if (quantity > treeRemainCount) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "库存数量不足，不能下单");
        }

        // 判断用户是否是定向用户
        User user = userBO.getUser(userId);
        if (ESellType.DIRECT.getCode().equals(product.getSellType())) {// 定向产品
            if (EDirectType.LEVEL.getCode().equals(product.getDirectType())) {
                if (!product.getDirectObject().equals(user.getLevel())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "定向产品等级不符，不能下单");
                }
            } else if (EDirectType.ONE_USER.getCode()
                .equals(product.getDirectType())) {
                if (!product.getDirectObject().equals(user.getUserId())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "定向产品针对用户不符，不能下单");
                }
            }
        }

        // 落地订单
        String orderCode = adoptOrderBO.saveAdoptOrder(user, product,
            productSpecs, quantity);

        // 分配树
        int count = 0;
        List<Tree> treeRemainList = treeBO.queryTreeListByProduct(
            product.getCode(), ETreeStatus.TO_ADOPT.getCode());
        for (Tree tree : treeRemainList) {
            if (count >= quantity) {
                break;
            }
            treeBO.refreshToPayTree(tree, orderCode);
            count++;
        }
        return orderCode;
    }

    @Override
    @Transactional
    public void cancelAdoptOrder(String code, String userId, String remark) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能取消");
        }

        if (!data.getApplyUser().equals(userId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前订单不是您本人的，不能取消");
        }
        adoptOrderBO.cancelAdoptOrder(data, remark);

        // 更新树状态
        if (ESellType.PERSON.getCode().equals(data.getType())
                || ESellType.DIRECT.getCode().equals(data.getType())) {
            List<Tree> treeList = treeBO
                .queryTreeListByOrderCode(data.getCode());
            for (Tree tree : treeList) {
                treeBO.refreshCancelTree(tree);
            }
        }
    }

    @Override
    @Transactional
    public Object toPayAdoptOrder(String code, String payType,
            String isJfDeduct) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不是待支付状态，不能支付");
        }

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付
            result = toPayAdoptOrderYue(data, isJfDeduct);
        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "暂不支持支付宝支付");
        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");

        }
        return result;
    }

    // 1、判断余额是否足够，并扣除账户余额
    // 2、进行分销
    // 3、更新订单和树状态
    private Object toPayAdoptOrderYue(AdoptOrder data, String isDk) {
        XN629048Res resultRes = adoptOrderBO.getOrderDkAmount(data, isDk);
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
            EJourBizTypeUser.ADOPT.getCode(), EJourBizTypePlat.ADOPT.getCode(),
            EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypePlat.ADOPT.getValue(), data.getCode());

        // 积分抵扣
        accountBO.transAmount(data.getApplyUser(), ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), resultRes.getJfAmount(),
            EJourBizTypeUser.ADOPT.getCode(), EJourBizTypePlat.ADOPT.getCode(),
            EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypePlat.ADOPT.getValue(), data.getCode());

        // 进行分销
        distributionOrderBO.distribution(data);

        // 业务订单更改
        adoptOrderBO.payYueSuccess(data, resultRes, BigDecimal.ZERO);
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            Product product = productBO.getProduct(data.getProductCode());
            for (Tree tree : treeList) {
                // 更新树状态
                treeBO.refreshPayTree(tree);
                // 分配认养权
                adoptOrderTreeBO.saveAdoptOrderTree(product, data,
                    tree.getTreeNumber());
            }
        }
        return new BooleanRes(true);
    }

    public void doCancelAdoptOrder() {
        logger.info("***************开始扫描未支付订单***************");
        AdoptOrder condition = new AdoptOrder();
        condition.setStatus(EAdoptOrderStatus.TO_PAY.getCode());
        // 前15分钟还未支付的订单
        condition.setApplyDatetimeEnd(
            DateUtil.getRelativeDateOfMinute(new Date(), -15));
        List<AdoptOrder> adoptOrderList = adoptOrderBO
            .queryAdoptOrderList(condition);
        if (CollectionUtils.isNotEmpty(adoptOrderList)) {
            for (AdoptOrder adoptOrder : adoptOrderList) {
                cancelOrder(adoptOrder);
            }
        }
        logger.info("***************结束扫描未支付订单***************");
    }

    public void cancelOrder(AdoptOrder data) {
        // 订单状态变更
        adoptOrderBO.cancelAdoptOrder(data, "超15分钟未支付系统自动取消");
        if (ESellType.PERSON.getCode().equals(data.getType())
                || ESellType.DIRECT.getCode().equals(data.getType())) {
            // 树的状态变更
            List<Tree> treeList = treeBO
                .queryTreeListByOrderCode(data.getCode());
            for (Tree tree : treeList) {
                treeBO.refreshCancelTree(tree);
            }
        }
    }

    public void doDailyAdoptOrder() {
        logger.info("***************开始扫描已支付待认养订单***************");
        AdoptOrder condition = new AdoptOrder();
        condition.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        condition.setStartDatetimeStart(new Date());
        List<AdoptOrder> startAdoptOrderList = adoptOrderBO
            .queryAdoptOrderList(condition);
        if (CollectionUtils.isNotEmpty(startAdoptOrderList)) {
            for (AdoptOrder adoptOrder : startAdoptOrderList) {
                startAdoptOrder(adoptOrder);
            }
        }
        logger.info("***************结束扫描已支付待认养订单***************");

        logger.info("***************开始扫描已认养订单***************");
        AdoptOrder condition2 = new AdoptOrder();
        condition2.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        condition.setEndDatetimeEnd(new Date());
        List<AdoptOrder> endAdoptOrderList = adoptOrderBO
            .queryAdoptOrderList(condition);
        if (CollectionUtils.isNotEmpty(endAdoptOrderList)) {
            for (AdoptOrder adoptOrder : endAdoptOrderList) {
                endAdoptOrder(adoptOrder);
            }
        }
        logger.info("***************结束扫描已认养订单***************");
    }

    // 1、认养订单更改
    // 2、认养权更改
    private void startAdoptOrder(AdoptOrder adoptOrder) {
        adoptOrderBO.startAdoptOrder(adoptOrder);
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(adoptOrder.getCode());
        for (AdoptOrderTree adoptOrderTree : list) {
            adoptOrderTreeBO.refreshAdoptOrderTree(adoptOrderTree,
                EAdoptOrderTreeStatus.ADOPT);
        }
    }

    // 1、订单结束
    // 2、认养权结束
    // 3、个人或定向树解锁
    private void endAdoptOrder(AdoptOrder adoptOrder) {
        adoptOrderBO.endAdoptOrder(adoptOrder);
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(adoptOrder.getCode());
        for (AdoptOrderTree adoptOrderTree : list) {
            adoptOrderTreeBO.refreshAdoptOrderTree(adoptOrderTree,
                EAdoptOrderTreeStatus.END);
        }
        if (ESellType.PERSON.getCode().equals(adoptOrder.getType())
                || ESellType.DIRECT.getCode().equals(adoptOrder.getType())) {
            List<Tree> treeList = treeBO
                .queryTreeListByOrderCode(adoptOrder.getCode());
            for (Tree tree : treeList) {
                treeBO.refreshCancelTree(tree);
            }
        }
    }

    @Override
    public XN629048Res getOrderDkAmount(String code) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前订单不是待支付状态");
        }
        return adoptOrderBO.getOrderDkAmount(data, EBoolean.YES.getCode());
    }

    @Override
    public Paginable<AdoptOrder> queryAdoptOrderPage(int start, int limit,
            AdoptOrder condition) {
        Paginable<AdoptOrder> page = adoptOrderBO.getPaginable(start, limit,
            condition);
        List<AdoptOrder> list = page.getList();
        for (AdoptOrder adoptOrder : list) {
            initAdoptOrder(adoptOrder);
        }
        return page;
    }

    @Override
    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition) {
        List<AdoptOrder> list = adoptOrderBO.queryAdoptOrderList(condition);
        for (AdoptOrder adoptOrder : list) {
            initAdoptOrder(adoptOrder);
        }
        return list;
    }

    @Override
    public AdoptOrder getAdoptOrder(String code, String isSettle) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        initAdoptOrder(data);
        Company company = companyBO
            .getCompanyByUserId(data.getProduct().getOwnerId());
        data.setOwnerContractTemplate(company.getContractTemplate());
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

    private void initAdoptOrder(AdoptOrder data) {
        Product product = productBO.getProduct(data.getProductCode());
        data.setProduct(product);
        List<AdoptOrderTree> adoptOrderTreeList = adoptOrderTreeBO
            .queryAdoptOrderTreeList(data.getCode());
        data.setAdoptOrderTreeList(adoptOrderTreeList);

        List<Tree> treeList = new ArrayList<Tree>();
        for (AdoptOrderTree adoptOrderTree : adoptOrderTreeList) {
            Tree tree = treeBO
                .getTreeByTreeNumber(adoptOrderTree.getTreeNumber());
            treeList.add(tree);
        }
        data.setTreeList(treeList);
    }

}
