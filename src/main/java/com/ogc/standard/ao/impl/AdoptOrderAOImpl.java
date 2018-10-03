package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EDirectType;
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
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private IAgentUserBO agentUserBO;

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
        List<Tree> treeRemainList = treeBO.queryTreeListByOrderCode(
            product.getCode(), ETreeStatus.TO_ADOPT.getCode());
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
            } else if (EDirectType.ONE_USER.getCode().equals(
                product.getDirectType())) {
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
        for (Tree tree : treeRemainList) {
            if (count >= quantity) {
                break;
            }
            treeBO.refreshAdoptTree(tree.getCode(), orderCode);
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
            List<Tree> treeList = treeBO.queryTreeListByOrderCode(
                data.getCode(), ETreeStatus.TO_PAY.getCode());
            for (Tree tree : treeList) {
                treeBO.refreshCancelTree(tree);
            }
        }
    }

    @Override
    @Transactional
    public Object toPayAdoptOrder(String code, String payType, String isJfDeduct) {
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
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持支付宝支付");
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
        Account userCnyAccount = accountBO.getAccountByUser(
            data.getApplyUser(), ECurrency.CNY.getCode());

        BigDecimal payAmount = data.getAmount().subtract(
            resultRes.getCnyAmount());// 实际付款人民币金额
        if (userCnyAccount.getAmount().compareTo(payAmount) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "人民币账户余额不足");
        }

        // 余额划转
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.transAmount(userCnyAccount, sysCnyAccount, data.getAmount(),
            EJourBizTypeUser.ADOPT.getCode(), EJourBizTypeUser.ADOPT.getCode(),
            EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypeUser.ADOPT.getValue(), data.getCode());

        accountBO.transAmount(data.getApplyUser(), ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), data.getAmount(),
            EJourBizTypeUser.ADOPT.getCode(), EJourBizTypeUser.ADOPT.getCode(),
            EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypeUser.ADOPT.getValue(), data.getCode());

        // 进行分销
        distribution(data);

        // 业务订单更改
        adoptOrderBO.payYueSuccess(data, resultRes, BigDecimal.ZERO);
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
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

    private void distribution(AdoptOrder data) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        Product product = productBO.getProduct(data.getProductCode());

        // 产权方分销
        BigDecimal ownerDeductAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_OWENER_RATE)));
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            product.getOwnerId(), ECurrency.CNY.getCode(), ownerDeductAmount,
            EJourBizTypeUser.OWNER_DEDECT.getCode(),
            EJourBizTypeUser.OWNER_DEDECT.getCode(),
            EJourBizTypeUser.OWNER_DEDECT.getValue(),
            EJourBizTypeUser.OWNER_DEDECT.getValue(), data.getCode());

        // 判断养护方是否存在，没有则平台回收
        String maintainId = applyBindMaintainBO.getMaintainId(product
            .getOwnerId());
        if (StringUtils.isNotBlank(maintainId)) {
            BigDecimal maintainDeductAmount = data.getAmount().multiply(
                new BigDecimal(mapList.get(SysConstants.DIST_MAINTAIN_RATE)));
            accountBO.transAmount(ESysUser.SYS_USER.getCode(), maintainId,
                ECurrency.CNY.getCode(), maintainDeductAmount,
                EJourBizTypeUser.MAINTAIN_DEDUCT.getCode(),
                EJourBizTypeUser.MAINTAIN_DEDUCT.getCode(),
                EJourBizTypeUser.MAINTAIN_DEDUCT.getValue(),
                EJourBizTypeUser.MAINTAIN_DEDUCT.getValue(), data.getCode());
        }

        // 代理方分销
        distributionAgent(data, mapList);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));
        BigDecimal CNY2JF_RATE = sysConfigBO
            .getBigDecimalValue(SysConstants.CNY2JF_RATE);
        BigDecimal jfAmount = jfAwardAmount.multiply(CNY2JF_RATE);// 积分总量
        accountBO.transAmount(ESysUser.SYS_USER.getCode(), data.getApplyUser(),
            ECurrency.JF.getCode(), jfAmount,
            EJourBizTypeUser.ADOPT_PAY_BACK.getCode(),
            EJourBizTypeUser.ADOPT_PAY_BACK.getCode(),
            EJourBizTypeUser.ADOPT_PAY_BACK.getValue(),
            EJourBizTypeUser.ADOPT_PAY_BACK.getValue(), data.getCode());

    }

    // 代理等级分销
    private void distributionAgent(AdoptOrder data, Map<String, String> mapList) {
        // 拿到下单用户的代理商(下单用户一定有代理商)
        User applyUser = userBO.getUser(data.getApplyUser());
        if (StringUtils.isBlank(applyUser.getAgent())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理用户不存在");
        }

        // 分成总金额
        BigDecimal agentTotalAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_AGENT_RATE)));

        BigDecimal currentLevelRate = BigDecimal.ZERO;
        AgentUser agentUser = agentUserBO.getAgentUser(applyUser.getAgent());
        // if (EAgentUserLevel.FOUR.getCode().equals(agentUser.getLevel())) {
        // currentLevelRate = new BigDecimal(
        // mapList.get(SysConstants.DIST_AGENT4_RATE));
        // } else if
        // (EAgentUserLevel.THREE.getCode().equals(agentUser.getLevel())) {
        // currentLevelRate = new BigDecimal(
        // mapList.get(SysConstants.DIST_AGENT3_RATE));
        // } else if (EAgentUserLevel.SECOND.getCode()
        // .equals(agentUser.getLevel())) {
        // currentLevelRate = new BigDecimal(
        // mapList.get(SysConstants.DIST_AGENT2_RATE));
        // } else if
        // (EAgentUserLevel.FIRST.getCode().equals(agentUser.getLevel())) {
        // currentLevelRate = new BigDecimal(
        // mapList.get(SysConstants.DIST_AGENT1_RATE));
        // } else {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理等级不存在");
        // }
        BigDecimal agentCurrentAmount = agentTotalAmount
            .multiply(currentLevelRate);// 当前代理分成金额

        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            agentUser.getUserId(), ECurrency.CNY.getCode(), agentCurrentAmount,
            EJourBizTypeUser.AGENT_DEDUCT.getCode(),
            EJourBizTypeUser.AGENT_DEDUCT.getCode(),
            EJourBizTypeUser.AGENT_DEDUCT.getValue(),
            EJourBizTypeUser.AGENT_DEDUCT.getValue(), data.getCode());

        // 查询三级代理(3<4)
        // if (EAgentUserLevel.THREE.getCode().compareTo(agentUser.getLevel()) <
        // 0) {
        // AgentUser agentUser3 = agentUserBO.getParentAgent(
        // agentUser.getProvince(), agentUser.getCity(),
        // agentUser.getArea(), EAgentUserLevel.THREE.getCode());
        // if (agentUser3 != null) {
        // BigDecimal agent3Amount = agentTotalAmount
        // .multiply(new BigDecimal(mapList
        // .get(SysConstants.DIST_AGENT3_RATE)));
        // accountBO.transAmount(ESysUser.SYS_USER.getCode(),
        // agentUser3.getUserId(), ECurrency.CNY.getCode(),
        // agent3Amount, EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(), data.getCode());
        // }
        // }
        //
        // // 查询二级代理(2<4)
        // if (EAgentUserLevel.SECOND.getCode().compareTo(agentUser.getLevel())
        // < 0) {
        // AgentUser agentUser2 = agentUserBO.getParentAgent(
        // agentUser.getProvince(), agentUser.getCity(),
        // EAgentUserLevel.SECOND.getCode());
        // if (agentUser2 != null) {
        // BigDecimal agent2Amount = agentTotalAmount
        // .multiply(new BigDecimal(mapList
        // .get(SysConstants.DIST_AGENT2_RATE)));
        // accountBO.transAmount(ESysUser.SYS_USER.getCode(),
        // agentUser2.getUserId(), ECurrency.CNY.getCode(),
        // agent2Amount, EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(), data.getCode());
        // }
        // }
        //
        // // 查询一级代理(1<4)
        // if (EAgentUserLevel.FIRST.getCode().compareTo(agentUser.getLevel()) <
        // 0) {
        // AgentUser agentUser1 = agentUserBO.getParentAgent(
        // agentUser.getProvince(), EAgentUserLevel.FIRST.getCode());
        // if (agentUser1 != null) {
        // BigDecimal agent1Amount = agentTotalAmount
        // .multiply(new BigDecimal(mapList
        // .get(SysConstants.DIST_AGENT1_RATE)));
        // accountBO.transAmount(ESysUser.SYS_USER.getCode(),
        // agentUser1.getUserId(), ECurrency.CNY.getCode(),
        // agent1Amount, EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getCode(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(),
        // EJourBizTypeUser.AGENT_DEDUCT.getValue(), data.getCode());
        // }
        // }
    }

    public void doCancelAdoptOrder() {
        logger.info("***************开始扫描未支付订单***************");
        AdoptOrder condition = new AdoptOrder();
        condition.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        // 前15分钟还未支付的订单
        condition.setApplyDatetimeEnd(DateUtil.getRelativeDateOfMinute(
            new Date(), -15));
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
            List<Tree> treeList = treeBO.queryTreeListByOrderCode(data
                .getCode());
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
        return adoptOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition) {
        return adoptOrderBO.queryAdoptOrderList(condition);
    }

    @Override
    public AdoptOrder getAdoptOrder(String code) {
        return adoptOrderBO.getAdoptOrder(code);
    }

}
