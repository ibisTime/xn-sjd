/**
 * @Title CommodityOrderDetailBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午2:58:22 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.ICommodityOrderDetailDAO;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.dto.res.XN629720Res;
import com.ogc.standard.enums.ECommodityOrderDetailStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:58:22 
 * @history:
 */
@Component
public class CommodityOrderDetailBOImpl extends
        PaginableBOImpl<CommodityOrderDetail> implements
        ICommodityOrderDetailBO {

    @Autowired
    private ICommodityOrderDetailDAO commodityOrderDetailDAO;

    @Autowired
    private ICommodityBO commodityBO;

    @Override
    public String saveDetail(String commodityOrderCode, XN629720Res res) {
        CommodityOrderDetail data = new CommodityOrderDetail();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CommodityOrderDetail.getCode());
        data.setCode(code);
        data.setOrderCode(commodityOrderCode);
        data.setShopCode(res.getShopCode());
        String commodityCode = res.getCommodityCode();
        data.setCommodityCode(commodityCode);
        data.setCommodityName(res.getCommodityName());
        data.setSpecsId(StringValidater.toLong(res.getSpecsId()));
        data.setSpecsName(res.getSpecsName());
        data.setQuantity(StringValidater.toLong(res.getQuantity()));
        data.setPrice(StringValidater.toBigDecimal(res.getPrice()));
        data.setAmount(StringValidater.toBigDecimal(res.getAmount()));
        String listPic = commodityBO.getCommodity(commodityCode).getListPic();
        data.setListPic(listPic);
        data.setStatus(ECommodityOrderDetailStatus.TODELIVE.getCode());
        data.setAddressCode(res.getAddressCode());
        commodityOrderDetailDAO.insert(data);
        return code;
    }

    @Override
    public void refershDelive(CommodityOrderDetail data,
            String logisticsCompany, String logisticsNumber, String deliver,
            String receiver, String receiverMobile) {
        data.setLogisticsCompany(logisticsCompany);
        data.setLogisticsNumber(logisticsNumber);
        data.setDeliver(deliver);
        data.setDeliverDatetime(new Date());
        data.setReceiver(receiver);
        data.setReceiverMobile(receiverMobile);
        data.setStatus(ECommodityOrderDetailStatus.TORECEIVE.getCode());
        commodityOrderDetailDAO.updateDelive(data);
    }

    @Override
    public void refreshReceive(CommodityOrderDetail data) {
        data.setReceiverDatetime(new Date());
        data.setStatus(ECommodityOrderDetailStatus.FINISH.getCode());
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