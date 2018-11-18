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
        String listPic = commodityBO.getCommodity(commodityCode).getListPic();
        data.setListPic(listPic);
        data.setStatus(ECommodityOrderDetailStatus.TO_PAY.getCode());
        data.setAddressCode(addressCode);
        data.setReceiver(addressBO.getAddress(addressCode).getUserId());
        data.setReceiverMobile(addressBO.getAddress(addressCode).getMobile());
        data.setUpdateDatetime(new Date());

        commodityOrderDetailDAO.insert(data);
        return code;
    }

    @Override
    public void refreshCancel(CommodityOrderDetail commodityOrderDetail) {
        if (null != commodityOrderDetail) {
            commodityOrderDetail
                .setStatus(ECommodityOrderDetailStatus.CANCLED.getCode());
            commodityOrderDetail.setUpdateDatetime(new Date());
            commodityOrderDetailDAO.updateCancelOrder(commodityOrderDetail);
        }

    }

    @Override
    public void refreshPay(String code) {
        CommodityOrderDetail commodityOrderDetail = new CommodityOrderDetail();

        commodityOrderDetail.setCode(code);
        commodityOrderDetail
            .setStatus(ECommodityOrderDetailStatus.TODELIVE.getCode());
        commodityOrderDetail.setUpdateDatetime(new Date());

        commodityOrderDetailDAO.updatePay(commodityOrderDetail);
    }

    @Override
    public void refershDelive(CommodityOrderDetail data,
            String logisticsCompany, String logisticsNumber, String deliver) {
        data.setLogisticsCompany(logisticsCompany);
        data.setLogisticsNumber(logisticsNumber);
        data.setDeliver(deliver);
        data.setDeliverDatetime(new Date());
        data.setUpdateDatetime(new Date());
        data.setStatus(ECommodityOrderDetailStatus.TORECEIVE.getCode());
        commodityOrderDetailDAO.updateDelive(data);
    }

    @Override
    public void refreshReceive(CommodityOrderDetail data) {
        data.setReceiverDatetime(new Date());
        data.setUpdateDatetime(new Date());
        data.setStatus(ECommodityOrderDetailStatus.TO_COMMENT.getCode());
        commodityOrderDetailDAO.updateReceive(data);
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
