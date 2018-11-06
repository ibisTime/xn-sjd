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
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

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
    public String saveOrder(BigDecimal amount, Long quantity, String applyUser,
            String applyNote, String expressType, String updater, String remark) {
        CommodityOrder data = new CommodityOrder();
        String code = OrderNoGenerater.generate(EGeneratePrefix.CommodityOrder
            .getCode());
        data.setCode(code);
        data.setAmount(amount);
        data.setQuantity(quantity);
        data.setApplyUser(applyUser);
        Date date = new Date();
        data.setApplyDatetime(date);

        data.setApplyNote(applyNote);
        data.setExpressType(expressType);
        data.setStatus(ECommodityOrderStatus.TOPAY.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        commodityOrderDAO.insert(data);
        return code;
    }

    @Override
    public void refreshPay(CommodityOrder data, BigDecimal payAmount,
            String updater, String remark) {
        data.setPayAmount(payAmount);
        Date date = new Date();
        data.setPayDatetime(date);
        data.setStatus(ECommodityOrderStatus.PAIED.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(date);
        data.setRemark(remark);
        commodityOrderDAO.updatePay(data);
    }

    @Override
    public void refreshCancel(CommodityOrder data, String updater, String remark) {
        data.setStatus(ECommodityOrderStatus.CANCEL.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        commodityOrderDAO.updateCancel(data);
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
    public void refreshPayGroup(CommodityOrder data, String payType) {
        data.setPayType(payType);
        data.setPayGroup(data.getCode());
        data.setRemark("预支付发起中");
        commodityOrderDAO.updatePay(data);
    }

    @Override
    public CommodityOrder getCommodityOrderByPayGroup(String payGroup) {
        CommodityOrder condition = new CommodityOrder();
        condition.setPayGroup(payGroup);
        List<CommodityOrder> list = commodityOrderDAO.selectList(condition);
        if (list.isEmpty()) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "根据"
                    + payGroup + "查询订单不存在");
        }
        return list.get(0);
    }

}
