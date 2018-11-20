/**
 * @Title CommodityOrderBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午2:33:49 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommodityOrderDAO;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午2:33:49 
 * @history:
 */
@Component
public class CommodityOrderBOImpl extends PaginableBOImpl<CommodityOrder>
        implements ICommodityOrderBO {

    @Autowired
    private ICommodityOrderDAO commodityOrderDAO;

    @Override
    public String saveOrder(String applyUser, String applyNote, String payGroup,
            String shopCode, String expressType, String updater, String remark,
            String addressCode) {
        CommodityOrder data = new CommodityOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CommodityOrder.getCode());
        data.setCode(code);
        data.setShopCode(shopCode);
        data.setPayGroup(payGroup);
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());

        data.setApplyNote(applyNote);
        data.setExpressType(expressType);
        data.setAddressCode(addressCode);
        data.setStatus(ECommodityOrderStatus.TO_PAY.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        commodityOrderDAO.insert(data);
        return code;
    }

    @Override
    public void refreshPay(CommodityOrder data, BigDecimal payAmount) {
        data.setPayAmount(payAmount);
        Date date = new Date();
        data.setPayDatetime(date);
        data.setStatus(ECommodityOrderStatus.TODELIVE.getCode());
        data.setUpdateDatetime(date);
        commodityOrderDAO.updatePay(data);
    }

    @Override
    public void refreshCancel(CommodityOrder data, String updater,
            String remark) {
        data.setStatus(ECommodityOrderStatus.CANCLED.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        commodityOrderDAO.updateCancel(data);
    }

    @Override
    public void refreshPayGroup(CommodityOrder data, String payType) {
        data.setPayType(payType);
        data.setPayGroup(data.getCode());
        data.setRemark("预支付发起中");
        commodityOrderDAO.updatePay(data);
    }

    @Override
    public void platCancelOrder(CommodityOrder data) {
        data.setStatus(ECommodityOrderStatus.CANCLED.getCode());
        data.setUpdater(ESysUser.SYS_USER.getCode());
        data.setUpdateDatetime(new Date());
        data.setRemark("超过支付时间平台自动取消");
        commodityOrderDAO.updateCancel(data);
    }

    @Override
    public void refreshAmount(Long quantity, BigDecimal amount, String code) {
        CommodityOrder data = new CommodityOrder();
        data.setCode(code);
        data.setQuantity(quantity);
        data.setAmount(amount);
        data.setPayAmount(amount);
        commodityOrderDAO.updateAmount(data);
    }

    @Override
    public void refershDelive(CommodityOrder data, String logisticsCompany,
            String logisticsNumber, String deliver) {
        data.setLogisticsCompany(logisticsCompany);
        data.setLogisticsNumber(logisticsNumber);
        data.setDeliver(deliver);
        data.setDeliverDatetime(new Date());
        data.setUpdateDatetime(new Date());
        data.setStatus(ECommodityOrderStatus.TORECEIVE.getCode());
        commodityOrderDAO.updateDelive(data);
    }

    @Override
    public void refreshReceive(CommodityOrder data) {
        data.setReceiverDatetime(new Date());
        data.setUpdateDatetime(new Date());
        data.setStatus(ECommodityOrderStatus.TO_COMMENT.getCode());
        commodityOrderDAO.updateReceive(data);
    }

    @Override
    public List<CommodityOrder> queryOrderList(CommodityOrder condition) {
        return commodityOrderDAO.selectList(condition);
    }

    @Override
    public CommodityOrder getCommodityOrder(String code) {
        CommodityOrder condition = new CommodityOrder();
        condition.setCode(code);
        CommodityOrder data = commodityOrderDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "不存在该订单");
        }
        return data;
    }

    @Override
    public List<CommodityOrder> queryCommodityOrderByPayGroup(String payGroup) {
        CommodityOrder condition = new CommodityOrder();
        condition.setPayGroup(payGroup);
        List<CommodityOrder> list = commodityOrderDAO.selectList(condition);
        return list;
    }

}
