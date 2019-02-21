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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommodityOrderDAO;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECommodityOrderSettleStatus;
import com.ogc.standard.enums.ECommodityOrderStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPayType;
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

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAddressBO addressBO;

    @Override
    public String saveOrder(String applyUser, String applyNote, String payGroup,
            String shopCode, String expressType, String updater, String remark,
            String addressCode) {
        Company shop = companyBO.getCompany(shopCode);
        Address address = addressBO.getAddress(addressCode);

        CommodityOrder data = new CommodityOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CommodityOrder.getCode());
        data.setCode(code);
        data.setShopCode(shopCode);
        data.setShopOwner(shop.getUserId());
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());

        data.setApplyNote(applyNote);
        data.setExpressType(expressType);
        data.setAddressCode(addressCode);
        data.setProvince(address.getProvince());
        data.setCity(address.getCity());

        data.setDistrict(address.getDistrict());
        data.setDetailAddress(address.getDetailAddress());
        data.setReceiver(address.getAddressee());
        data.setReceiverMobile(address.getMobile());

        data.setStatus(ECommodityOrderStatus.TO_PAY.getCode());
        data.setSettleStatus(ECommodityOrderSettleStatus.NO_SETTLE.getCode());
        data.setUpdater(updater);

        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        if (null == payGroup) {
            data.setPayGroup(code);
        } else {
            data.setPayGroup(payGroup);
        }

        commodityOrderDAO.insert(data);
        return code;
    }

    @Override
    public void refreshAmount(Long quantity, BigDecimal amount, String code,
            BigDecimal postalFee) {
        CommodityOrder data = new CommodityOrder();
        data.setCode(code);
        data.setQuantity(quantity);
        data.setAmount(amount);
        data.setPayAmount(amount.add(postalFee));
        data.setPostalFee(postalFee);
        commodityOrderDAO.updateAmount(data);
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
    public void payYueSuccess(CommodityOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount) {
        data.setPayType(EPayType.YE.getCode());
        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setPayAmount(data.getAmount().subtract(resultRes.getCnyAmount())
            .add(data.getPostalFee()));

        data.setBackJfAmount(backjfAmount);
        data.setPayDatetime(new Date());
        data.setStatus(ECommodityOrderStatus.TODELIVE.getCode());
        data.setSettleStatus(ECommodityOrderSettleStatus.TO_SETTLE.getCode());
        data.setRemark("余额支付成功");
        commodityOrderDAO.updatePayYueSuccess(data);
    }

    @Override
    public void refreshPayGroup(CommodityOrder data, String payType,
            XN629048Res resultRes) {
        data.setPayType(payType);

        if (null == data.getPayGroup()) {
            data.setPayGroup(data.getCode());
        } else {
            data.setPayGroup(data.getPayGroup());
        }

        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setRemark("预支付发起中");
        commodityOrderDAO.updatePayGroup(data);
    }

    @Override
    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount) {
        if (StringUtils.isNotBlank(code)) {
            CommodityOrder data = new CommodityOrder();
            data.setCode(code);
            data.setPayAmount(payAmount);
            data.setBackJfAmount(backJfAmount);
            data.setStatus(ECommodityOrderStatus.TODELIVE.getCode());
            data.setSettleStatus(
                ECommodityOrderSettleStatus.TO_SETTLE.getCode());
            data.setRemark("第三方支付成功");
            commodityOrderDAO.updatePaySuccess(data);
        }
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
    public void refreshAddress(String code, String addressCode) {
        CommodityOrder data = new CommodityOrder();
        Address address = addressBO.getAddress(addressCode);

        data.setCode(code);
        data.setAddressCode(addressCode);
        data.setProvince(address.getProvince());
        data.setCity(address.getCity());

        data.setDistrict(address.getDistrict());
        data.setDetailAddress(address.getDetailAddress());
        data.setReceiver(address.getAddressee());
        data.setReceiverMobile(address.getMobile());
        commodityOrderDAO.updateAddress(data);
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
    public void refreshFinish(String code) {
        CommodityOrder data = new CommodityOrder();
        data.setCode(code);
        data.setStatus(ECommodityOrderStatus.FINISH.getCode());
        commodityOrderDAO.updateComment(data);
    }

    @Override
    public void refreshAftersale(String code) {
        CommodityOrder data = new CommodityOrder();
        data.setCode(code);
        data.setStatus(ECommodityOrderStatus.ATFER_SALES.getCode());
        data.setUpdateDatetime(new Date());
        commodityOrderDAO.updateAftersale(data);
    }

    @Override
    public void refreshSettleStatus(CommodityOrder data, String approveResult,
            String updater, String remark) {
        String status = ECommodityOrderSettleStatus.NO_SETTLE.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ECommodityOrderSettleStatus.SETTLE.getCode();
        }

        data.setSettleStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark("已完成结算处理");
        commodityOrderDAO.updateSettle(data);

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
