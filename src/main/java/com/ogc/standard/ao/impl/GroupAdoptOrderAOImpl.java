package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.IProductSpecsBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629050Req;
import com.ogc.standard.dto.req.XN629051Req;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;

@Service
public class GroupAdoptOrderAOImpl implements IGroupAdoptOrderAO {

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

    @Override
    @Transactional
    public String firstAddGroupAdoptOrder(XN629050Req req) {
        Product product = productBO.getProduct(req.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }
        if (new Date().getTime() > product.getRaiseEndDatetime().getTime()) {
            throw new BizException("xn0000", "认养产品募集时间已结束，不能下单");
        }
        if (StringValidater.toInteger(req.getQuantity()) > product
            .getRaiseCount()) {
            throw new BizException("xn0000", "下单数量大于产品募集总量，不能下单");
        }
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setProductCode(req.getProductCode());
        ProductSpecs productSpecs = productSpecsBO.getProductSpecs(req
            .getSpecsCode());
        data.setProductSpecsName(productSpecs.getName());
        data.setPrice(productSpecs.getPrice());
        data.setStartDatetime(productSpecs.getStartDatetime());
        data.setEndDatetime(productSpecs.getEndDatetime());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        data.setIdentifyCode(UUID.randomUUID().toString().hashCode() + "");
        String code = groupAdoptOrderBO.saveGroupAdoptOrderFirst(data);
        // 更新认养产品的已募集数量
        product.setNowCount(product.getNowCount() + data.getQuantity());
        productBO.refreshNowCount(product.getCode(), product.getNowCount());
        // 更改树状态
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        for (Tree tree : treeList) {
            treeBO.refreshAdoptTree(tree.getCode(), code);
        }
        return code;
    }

    @Override
    @Transactional
    public String unFirstAddGroupAdoptOrder(XN629051Req req) {
        GroupAdoptOrder order = groupAdoptOrderBO
            .getGroupAdoptOrderByIdentifyCode(req.getIdentifyCode());
        Product product = productBO.getProduct(order.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }
        if (new Date().getTime() > product.getRaiseEndDatetime().getTime()) {
            throw new BizException("xn0000", "认养产品募集时间已结束，不能下单");
        }
        if (StringValidater.toInteger(req.getQuantity()) > (product
            .getRaiseCount() - product.getNowCount())) {
            throw new BizException("xn0000", "库存数量不足，不能下单");
        }
        GroupAdoptOrder data = new GroupAdoptOrder();
        data.setProductCode(order.getProductCode());
        data.setProductSpecsName(order.getProductSpecsName());
        data.setPrice(order.getPrice());
        data.setStartDatetime(order.getStartDatetime());
        data.setEndDatetime(order.getEndDatetime());
        data.setQuantity(StringValidater.toInteger(req.getQuantity()));
        data.setAmount(AmountUtil.mul(data.getPrice(), data.getQuantity()));
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        data.setStatus(EGroupAdoptOrderStatus.TO_PAY.getCode());
        String code = groupAdoptOrderBO.saveGroupAdoptOrderUnFirst(data);
        // 更新认养产品的已募集数量
        product.setNowCount(product.getNowCount() + data.getQuantity());
        productBO.refreshNowCount(product.getCode(), product.getNowCount());
        // 更改树状态
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        for (Tree tree : treeList) {
            treeBO.refreshAdoptTree(tree.getCode(), code);
        }
        return code;
    }

    @Override
    @Transactional
    public void cancelGroupAdoptOrder(String code) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);
        if (!EGroupAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能取消");
        }
        data.setStatus(EGroupAdoptOrderStatus.CANCELED.getCode());
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        // 更新认养产品的已募集数量
        Product product = productBO.getProduct(data.getProductCode());
        product.setNowCount(product.getNowCount() - data.getQuantity());
        productBO.refreshNowCount(product.getCode(), product.getNowCount());
        // 更新树状态
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        for (Tree tree : treeList) {
            treeBO.refreshCancelTree(tree.getCode());
        }
        groupAdoptOrderBO.refreshCancelGroupAdoptOrder(data);
    }

    @Override
    @Transactional
    public String payGroupAdoptOrder(String code, String type) {
        GroupAdoptOrder data = groupAdoptOrderBO.getGroupAdoptOrder(code);
        if (!EAdoptOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单不是待支付状态，不能支付");
        }
        String identifyCode = null;
        if (StringUtils.isNotBlank(data.getIdentifyCode())) {
            identifyCode = data.getIdentifyCode();
        }
        // TODO 调支付接口返回信息填充
        data.setPayType(type);
        data.setPayGroup("");
        data.setPayCode("");
        // data.setPayAmount(0l);
        // TODO 积分抵扣 积分账户查询余额 默认抵扣还是选择抵扣?
        // data.setJfDeductAmount(0l);
        data.setPayDatetime(new Date());
        // data.setBackJfAmount(0l);
        data.setUpdater(data.getApplyUser());
        data.setUpdateDatetime(new Date());
        if (data.getPayDatetime().getTime() < data.getStartDatetime().getTime()) {// 支付时间小于认养开始时间
            data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        }
        groupAdoptOrderBO.payGroupAdoptOrder(data);
        List<Tree> treeList = treeBO.queryTreeListByOrderCode(data.getCode(),
            ETreeStatus.TO_PAY.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                // 更新树状态
                treeBO.refreshPayTree(tree.getCode());
                // // 分配认养权
                // adoptOrderTreeBO
                // .saveAdoptOrderTree(data.getCode(), tree.getTreeNumber(),
                // data.getStartDatetime(), data.getEndDatetime(),
                // data.getPrice(), data.getApplyUser());
            }
        }
        // 分配分红 TODO
        return identifyCode;
    }

    @Override
    public int editGroupAdoptOrder(GroupAdoptOrder data) {
        if (!groupAdoptOrderBO.isGroupAdoptOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return groupAdoptOrderBO.refreshGroupAdoptOrder(data);
    }

    @Override
    public int dropGroupAdoptOrder(String code) {
        if (!groupAdoptOrderBO.isGroupAdoptOrderExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return groupAdoptOrderBO.removeGroupAdoptOrder(code);
    }

    @Override
    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition) {
        return groupAdoptOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition) {
        return groupAdoptOrderBO.queryGroupAdoptOrderList(condition);
    }

    @Override
    public GroupAdoptOrder getGroupAdoptOrder(String code) {
        return groupAdoptOrderBO.getGroupAdoptOrder(code);
    }

}
