package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AdoptOrderAOImpl implements IAdoptOrderAO {

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
    private IAccountBO accountBO;

    @Override
    @Transactional
    public String commitAdoptOrder(String userId, String specsCode,
            Integer quantity) {
        // 参数检查
        User user = userBO.getUser(userId);

        ProductSpecs productSpecs = productSpecsBO.getProductSpecs(specsCode);
        Product product = productBO.getProduct(productSpecs.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }

        int treeRemainCount = treeBO.getTreeCount(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        List<Tree> treeRemainList = treeBO.queryTreeListByOrderCode(
            product.getCode(), ETreeStatus.TO_ADOPT.getCode());
        if (quantity > treeRemainCount) {
            throw new BizException("xn0000", "库存数量不足，不能下单");
        }

        // 落地订单
        String orderCode = adoptOrderBO.saveAdoptOrder(user, product,
            productSpecs, quantity);

        // 更改树状态
        for (Tree tree : treeRemainList) {
            treeBO.refreshAdoptTree(tree.getCode(), orderCode);
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
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        for (Tree tree : treeList) {
            treeBO.refreshCancelTree(tree.getCode());
        }
    }

    @Override
    @Transactional
    public Object toPayAdoptOrder(String code, String payType, String isJfDeduct) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能支付");
        }

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付
            result = toPayAdoptOrderYue(data, isJfDeduct);
        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付

        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付
        }
        return result;
    }

    // 1、判断余额是否足够，并扣除账户余额
    // 2、进行分销
    // 3、更新订单和树状态
    private Object toPayAdoptOrderYue(AdoptOrder data, String isDk) {
        XN629048Res resultRes = adoptOrderBO.getOrderDkAmount(data, isDk);
        Account cnyAccount = accountBO.getAccountByUser(data.getApplyUser(),
            ECurrency.CNY.getCode());
        BigDecimal payAmount = data.getAmount().subtract(
            resultRes.getCnyAmount());
        if (cnyAccount.getAmount().compareTo(payAmount) == -1) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "人民币账户余额不足");
        }

        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_CNY
            .getCode());
        // accountBO.transAmount(cnyAccount, sysAccount, data.getAmount(),
        // EJourBizTypePlat.AJ_SUBSIDY.getCode(),
        // EJourBizTypeUser.AJ_CHARGE.getCode(),
        // EJourBizTypePlat.AJ_SUBSIDY.getValue(),
        // EJourBizTypeUser.AJ_CHARGE.getValue(), "用户充值");

        adoptOrderBO.payYueSuccess(data, resultRes, BigDecimal.ZERO);
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                // 更新树状态
                treeBO.refreshPayTree(tree.getCode());
                // 分配认养权
                adoptOrderTreeBO.saveAdoptOrderTree(data, tree.getTreeNumber());
            }
        }
        return new BooleanRes(true);
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
