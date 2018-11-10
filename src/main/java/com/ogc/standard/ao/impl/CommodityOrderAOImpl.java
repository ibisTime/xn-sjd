/**
 * @Title CommodityOrderAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:57:03 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.dto.res.XN629720Res;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:57:03 
 * @history:
 */
@Service
public class CommodityOrderAOImpl implements ICommodityOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(CommodityOrderAOImpl.class);

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Autowired
    private ICommodityOrderDetailAO commodityOrderDetailAO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Autowired
    private IAddressBO addressBO;

    @Override
    @Transactional
    public String addOrder(String applyUser, String applyNote,
            String expressType, Long specsId, Long quantity, String addressCode) {
        // 用户检验
        userBO.getUser(applyUser);

        // 落地订单数据（多店铺）
        String orderCode = commodityOrderBO.saveOrder(applyUser, applyNote,
            expressType, applyUser, applyNote);
        // 规格检验
        CommoditySpecs specs = commoditySpecsBO.getCommoditySpecs(specsId);
        Commodity commodity = commodityBO
            .getCommodity(specs.getCommodityCode());
        // 地址检验
        Address address = addressBO.getAddress(addressCode);
        if (null == address) {
            throw new BizException("xn0000", "该地址不存在");
        }
        if (!address.getUserId().equals(applyUser)) {
            throw new BizException("xn0000", "该地址不属于你");
        }
        // 落地单店铺订单
        XN629720Res res = new XN629720Res();
        res.setCommodityOrderCode(orderCode);
        res.setShopCode(commodity.getShopCode());
        res.setCommodityCode(specs.getCommodityCode());
        res.setCommodityName(commodity.getName());
        res.setSpecsId(specsId.toString());
        res.setSpecsName(specs.getName());
        res.setQuantity(quantity.toString());
        res.setPrice(specs.getPrice().toString());
        String amount = specs.getPrice()
            .multiply(StringValidater.toBigDecimal(res.getQuantity()))
            .toString();
        res.setAmount(amount);
        res.setAddressCode(addressCode);
        commodityOrderDetailBO.saveDetail(orderCode, res);
        // 库存减少
        commoditySpecsBO.inventoryDecrease(
            StringValidater.toLong(res.getSpecsId()),
            -StringValidater.toLong(res.getQuantity()));
        // 加上数量与总价
        commodityOrderBO.refreshAmount(quantity,
            StringValidater.toBigDecimal(amount), orderCode);
        return orderCode;
    }

    @Override
    @Transactional
    public Object pay(XN629721Req req) {
        // 用户检验
        userBO.getUser(req.getUpdater());
        // 订单状态检验
        CommodityOrder order = commodityOrderBO
            .getCommodityOrder(req.getCode());
        if (!ECommodityOrderStatus.TOPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该订单不处于待支付状态");
        }
        // 支付
        Object result = null;
        if (EPayType.ALIPAY.getCode().equals(req.getPayType())) {
            // 状态更新
            commodityOrderBO.refreshPayGroup(order, req.getPayType());

            String signOrder = alipayBO.getSignedOrder(order.getApplyUser(),
                ESysUser.SYS_USER.getCode(), order.getPayGroup(),
                EJourBizTypeUser.BUY.getCode(),
                EJourBizTypeUser.BUY.getValue(), order.getPayAmount());
            result = new PayOrderRes(signOrder);
        } else if (EPayType.YE.getCode().equals(req.getPayType())) {
            result = payByBalance(order);
            commodityOrderBO.refreshPay(order, order.getAmount(),
                req.getUpdater(), req.getRemark());
        } else if (EPayType.WEIXIN_H5.getCode().equals(req.getPayType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持微信支付");
        } else {
            throw new BizException("xn0000", "不支持的支付方式");
        }
        return result;
    }

    // 余额支付
    public Object payByBalance(CommodityOrder data) {
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(data.getCode());
        for (CommodityOrderDetail detail : dataList) {
            // 支付
            commodityOrderDetailAO.payDetail(detail, data.getApplyUser(),
                data.getCode());
            // 分销
            commodityOrderDetailAO.distribution(detail, data.getCode());
        }

        return new BooleanRes(true);
    }

    @Override
    @Transactional
    public void cancel(String code, String updater, String remark) {
        CommodityOrder order = commodityOrderBO.getCommodityOrder(code);
        if (!ECommodityOrderStatus.TOPAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该订单不处于可取消状态");
        }
        // 库存回加
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(code);
        for (CommodityOrderDetail commodityOrderDetail : dataList) {
            commoditySpecsBO.inventoryDecrease(
                commodityOrderDetail.getSpecsId(),
                commodityOrderDetail.getQuantity());
        }
        // 状态更新
        commodityOrderBO.refreshCancel(order, updater, remark);
    }

    @Override
    public Paginable<CommodityOrder> queryOrderPage(int start, int limit,
            CommodityOrder condition) {
        Paginable<CommodityOrder> page = commodityOrderBO.getPaginable(start,
            limit, condition);
        for (CommodityOrder order : page.getList()) {
            List<CommodityOrderDetail> detailList = commodityOrderDetailBO
                .queryOrderDetail(order.getCode());
            order.setDetailList(detailList);
        }
        return page;
    }

    @Override
    public List<CommodityOrder> queryOrderList(CommodityOrder condition) {
        List<CommodityOrder> dataList = commodityOrderBO
            .queryOrderList(condition);
        for (CommodityOrder order : dataList) {
            List<CommodityOrderDetail> detailList = commodityOrderDetailBO
                .queryOrderDetail(order.getCode());
            order.setDetailList(detailList);
        }
        return dataList;
    }

    @Override
    public CommodityOrder getCommodityOrder(String code) {
        CommodityOrder order = commodityOrderBO.getCommodityOrder(code);
        List<CommodityOrderDetail> detailList = commodityOrderDetailBO
            .queryOrderDetail(order.getCode());
        order.setDetailList(detailList);
        return order;
    }

    @Override
    public void paySuccess(String payGroup) {
        CommodityOrder data = commodityOrderBO
            .getCommodityOrderByPayGroup(payGroup);
        // 分销
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(data.getCode());
        for (CommodityOrderDetail detail : dataList) {
            commodityOrderDetailAO.distribution(detail, data.getCode());
        }
        commodityOrderBO
            .refreshPay(data, data.getAmount(), "alipay", "支付宝支付成功");
    }

    public void timeoutCancel() {
        logger.info("***************开始扫描未支付订单***************");
        CommodityOrder condition = new CommodityOrder();
        condition.setStatus(ECommodityOrderStatus.TOPAY.getCode());
        // 一天内未支付订单
        condition.setApplyDatetimeEnd(DateUtil.getRelativeDateOfHour(
            new Date(), (double) -24));
        List<CommodityOrder> orderList = commodityOrderBO
            .queryOrderList(condition);
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (CommodityOrder order : orderList) {
                cancelOrder(order);
            }
        }
        logger.info("***************结束扫描未支付订单***************");
    }

    private void cancelOrder(CommodityOrder order) {
        // 库存回加
        List<CommodityOrderDetail> dataList = commodityOrderDetailBO
            .queryOrderDetail(order.getCode());
        for (CommodityOrderDetail commodityOrderDetail : dataList) {
            commoditySpecsBO.inventoryDecrease(
                commodityOrderDetail.getSpecsId(),
                commodityOrderDetail.getQuantity());
        }
        // 状态更新
        commodityOrderBO.platCancelOrder(order);
    }

}
