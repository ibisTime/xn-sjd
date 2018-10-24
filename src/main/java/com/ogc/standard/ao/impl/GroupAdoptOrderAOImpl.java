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
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EGroupAdoptOrderStatus;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.EProductStatus;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ETreeStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

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

    @Autowired
    private IUserBO userBO;

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
        String code = groupAdoptOrderBO.saveGroupAdoptOrderFirst(identifyCode,
            userId, quantity, product, productSpecs);

        // 更新认养产品的已募集数量
        productBO.refreshNowCount(product.getCode(),
            product.getNowCount() + quantity);

        // 更新认养产品的当前识别码
        productBO.refreshCurrentIdentify(product.getCode(), identifyCode);

        // 更改树状态
        List<Tree> treeList = treeBO.queryTreeListByProduct(product.getCode(),
            ETreeStatus.TO_ADOPT.getCode());
        if (CollectionUtils.isNotEmpty(treeList)) {
            for (Tree tree : treeList) {
                treeBO.refreshToPayTree(tree, code);
            }
        }

        return code;
    }

    @Override
    @Transactional
    public String unFirstAddGroupAdoptOrder(String identifyCode, String userId,
            Integer quantity) {

        // 识别码是否存在
        List<GroupAdoptOrder> list = groupAdoptOrderBO
            .getGroupAdoptOrderById(identifyCode);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn0000", "识别码不存在，请重新输入");
        }

        GroupAdoptOrder groupAdoptOrder = list.get(0);// 所有订单的规格都相同

        // 产品状态检查
        ProductSpecs productSpecs = productSpecsBO
            .getProductSpecs(groupAdoptOrder.getProductSpecsCode());
        Product product = productBO.getProduct(productSpecs.getProductCode());
        if (!EProductStatus.TO_ADOPT.getCode().equals(product.getStatus())) {
            throw new BizException("xn0000", "认养产品不是已上架待认养状态，不能下单");
        }

        // 识别码是否失效
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
        productBO.refreshNowCount(product.getCode(),
            product.getNowCount() - data.getQuantity());

        // 更新订单状态
        groupAdoptOrderBO.refreshCancelOrder(code, remark);
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

        // 积分抵扣处理
        XN629048Res deductRes = adoptOrderBO.getOrderDeductAmount(data,
            isJfDeduct);

        // 支付订单
        Object result = null;
        if (EPayType.YE.getCode().equals(payType)) {// 余额支付
            result = toPayAdoptOrderYue(data, deductRes);
        } else if (EPayType.ALIPAY.getCode().equals(payType)) {// 支付宝支付
            adoptOrderBO.refreshPayGroup(data, payType, deductRes);
            String signOrder = alipayBO.getSignedOrder(data.getApplyUser(),
                ESysUser.SYS_USER.getCode(), data.getPayGroup(),
                EJourBizTypeUser.ADOPT.getCode(),
                EJourBizTypeUser.ADOPT.getValue(), data.getAmount());
            result = new PayOrderRes(signOrder);
        } else if (EPayType.WEIXIN_H5.getCode().equals(payType)) {// 微信支付
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");
        }
        return result;
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
