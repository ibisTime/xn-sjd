package com.ogc.standard.ao.impl;

import java.util.Date;
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
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629040Req;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;

@Service
public class AdoptOrderAOImpl implements IAdoptOrderAO {

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String addAdoptOrder(XN629040Req req) {
        Product product = productBO.getProduct(req.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        if (StringValidater.toInteger(req.getQuantity()) > treeList.size()) {
            throw new BizException("xn0000", "库存数量不足，不能下单");
        }
        AdoptOrder data = new AdoptOrder();
        data.setType(req.getType());
        data.setProductCode(req.getProductCode());
        ProductSpecs specs = productSpecsBO.getProductSpecs(req.getSpecsCode());
        data.setProductSpecsName(specs.getName());
        data.setPrice(specs.getPrice());
        data.setYear(specs.getYear());
        data.setStartDatetime(specs.getStartDatetime());
        data.setEndDatetime(specs.getEndDatetime());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EAdoptOrderStatus.TO_PAY.getCode());
        String code = adoptOrderBO.saveAdoptOrder(data);
        // 更改树状态
        for (Tree tree : treeList) {
            treeBO.refreshAdoptTree(tree.getCode(), code);
        }
        return code;
    }

    @Override
    public void cancelAdoptOrder(String code) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能取消");
        }
        data.setStatus(EAdoptOrderStatus.CANCELED.getCode());
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        adoptOrderBO.cancelAdoptOrder(data);
        // 更新树状态
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        for (Tree tree : treeList) {
            treeBO.refreshCancelTree(tree.getCode());
        }
    }

    @Override
    @Transactional
    public void payAdoptOrder(String code, String payType, String isJfDeduct) {
        AdoptOrder data = adoptOrderBO.getAdoptOrder(code);
        if (EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能支付");
        }
        if (EBoolean.YES.getCode().equals(isJfDeduct)) {// 使用积分抵扣
            Account account = accountBO.getAccountByUser(data.getApplyUser(),
                ECurrency.JF.getCode());
            // accountBO.changeAmount(account, account.getAmount(),
            // EPayType.BALANCE.getCode(), null, data.getCode(),
            // ECustomerJFAccountBizType.COLLECTIVE.getCode(), null);
            data.setJfDeductAmount(0l);
        }

        if (EPayType.BALANCE.getCode().equals(payType)) {// 余额支付

        }
        // TODO 支付宝微信银行卡
        data.setPayType(payType);
        data.setPayAmount(0l);
        data.setPayDatetime(new Date());
        data.setBackJfAmount(0l);
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        if (data.getPayDatetime().getTime() < data.getStartDatetime()
            .getTime()) {// 支付时间小于认养开始时间
            data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        }
        adoptOrderBO.payAdoptOrder(data);

        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                // 更新树状态
                treeBO.refreshPayTree(tree.getCode());
                // 分配认养权
                adoptOrderTreeBO.saveAdoptOrderTree(data.getCode(),
                    tree.getTreeNumber(), data.getStartDatetime(),
                    data.getEndDatetime(), data.getPrice(),
                    data.getApplyUser());
            }
        }
        // 分配分红 TODO
    }

    @Override
    public int editAdoptOrder(AdoptOrder data) {
        if (!adoptOrderBO.isAdoptOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return adoptOrderBO.refreshAdoptOrder(data);
    }

    @Override
    public int dropAdoptOrder(String code) {
        if (!adoptOrderBO.isAdoptOrderExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return adoptOrderBO.removeAdoptOrder(code);
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
