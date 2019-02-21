/**
 * @Title CommodityOrderDetailBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午2:58:22 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommodityOrderDetailDAO;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:58:22 
 * @history:
 */
@Component
public class CommodityOrderDetailBOImpl
        extends PaginableBOImpl<CommodityOrderDetail>
        implements ICommodityOrderDetailBO {

    @Autowired
    private ICommodityOrderDetailDAO commodityOrderDetailDAO;

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private IAddressBO addressBO;

    @Override
    public String saveDetail(String commodityOrderCode, String shopCode,
            String commodityCode, String commodityName, Long specsId,
            String specsName, String applyUser, Long quantity, BigDecimal price,
            String addressCode) {
        CommodityOrderDetail data = new CommodityOrderDetail();
        Commodity commodity = commodityBO.getCommodity(commodityCode);
        Address address = addressBO.getAddress(addressCode);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CommodityOrderDetail.getCode());
        data.setCode(code);
        data.setOrderCode(commodityOrderCode);
        data.setShopCode(shopCode);
        data.setCommodityCode(commodityCode);
        data.setCommodityName(commodityName);

        data.setSpecsId(specsId);
        data.setSpecsName(specsName);
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setQuantity(quantity);
        data.setPrice(price);
        BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

        data.setAmount(amount);
        data.setMaxJfdkRate(commodity.getMaxJfdkRate());
        data.setListPic(commodity.getListPic());
        data.setAddressCode(addressCode);
        data.setReceiver(address.getUserId());
        data.setReceiverMobile(address.getMobile());
        data.setUpdateDatetime(new Date());
        data.setStatus(ECommodityOrderDetailStatus.TO_PAY.getCode());

        commodityOrderDetailDAO.insert(data);
        return code;
    }

    @Override
    public void toCommentByOrder(String orderCode) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setOrderCode(orderCode);
        commodityOrderDetail
            .setStatus(ECommodityOrderDetailStatus.TO_COMMENT.getCode());
        commodityOrderDetailDAO.updateToCommentByOrder(commodityOrderDetail);
    }

    @Override
    public void toComment(String code) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setCode(code);
        commodityOrderDetail
            .setStatus(ECommodityOrderDetailStatus.TO_COMMENT.getCode());
        commodityOrderDetailDAO.updateToComment(commodityOrderDetail);
    }

    @Override
    public void comment(String code) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setCode(code);
        commodityOrderDetail
            .setStatus(ECommodityOrderDetailStatus.COMMENTED.getCode());
        commodityOrderDetailDAO.updateComment(commodityOrderDetail);
    }

    @Override
    public void toAfterSell(String code) {
        CommodityOrderDetail commodityOrderDetail = getCommodityOrderDetail(
            code);

        commodityOrderDetail.setAfterSaleStatus(
            ECommodityOrderDetailStatus.AFTER_SELL_ING.getCode());

        commodityOrderDetailDAO.updateAfterSaleStatus(commodityOrderDetail);
    }

    @Override
    public void refreshStatus(String orderCode, String status) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setOrderCode(orderCode);
        commodityOrderDetail.setStatus(status);
        commodityOrderDetailDAO.updateStatusByOrder(commodityOrderDetail);
    }

    @Override
    public void refreshAfterSaleStatus(String code, String status) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setCode(code);
        commodityOrderDetail.setStatus(status);
        commodityOrderDetail.setAfterSaleStatus(status);
        commodityOrderDetailDAO.updateAfterSaleStatus(commodityOrderDetail);
    }

    @Override
    public void refreshDkAmount(String code, BigDecimal cnyDeductAmount,
            BigDecimal jfDeductAmount, BigDecimal backJfAmount,
            BigDecimal payAmount, String payType) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();
        commodityOrderDetail.setCode(code);
        commodityOrderDetail.setCnyDeductAmount(cnyDeductAmount);
        commodityOrderDetail.setJfDeductAmount(jfDeductAmount);
        commodityOrderDetail.setBackJfAmount(backJfAmount);
        commodityOrderDetail.setPayAmount(payAmount);
        commodityOrderDetail.setPayType(payType);
        commodityOrderDetailDAO.updateDkAmount(commodityOrderDetail);
    }

    @Override
    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition) {
        return commodityOrderDetailDAO.selectList(condition);
    }

    @Override
    public CommodityOrderDetail getCommodityOrderDetail(String code) {
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setCode(code);
        CommodityOrderDetail data = commodityOrderDetailDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "不存在该单店铺订单");
        }
        return data;
    }

    @Override
    public List<CommodityOrderDetail> queryOrderDetail(String orderCode) {
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setOrderCode(orderCode);
        List<CommodityOrderDetail> orderDetail = commodityOrderDetailDAO
            .selectList(condition);
        return orderDetail;
    }

}
