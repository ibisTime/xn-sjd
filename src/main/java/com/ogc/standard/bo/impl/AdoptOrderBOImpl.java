package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAdoptOrderDAO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAdoptOrderSettleStatus;
import com.ogc.standard.enums.EAdoptOrderStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class AdoptOrderBOImpl extends PaginableBOImpl<AdoptOrder> implements
        IAdoptOrderBO {

    @Autowired
    private IAdoptOrderDAO adoptOrderDAO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String saveAdoptOrder(User user, Product product,
            ProductSpecs productSpecs, int quantity) {
        String code = null;
        if (user != null) {
            AdoptOrder data = new AdoptOrder();
            code = OrderNoGenerater.generate(EGeneratePrefix.ADOPT_ORDER
                .getCode());
            data.setCode(code);
            data.setType(product.getSellType());
            data.setProductCode(product.getCode());

            data.setProductSpecsName(productSpecs.getName());
            data.setPrice(productSpecs.getPrice());
            data.setStartDatetime(productSpecs.getStartDatetime());
            data.setEndDatetime(productSpecs.getEndDatetime());
            data.setQuantity(quantity);

            data.setAmount(AmountUtil.mul(data.getPrice(), quantity));
            data.setApplyUser(user.getUserId());
            data.setApplyDatetime(new Date());
            data.setStatus(EAdoptOrderStatus.TO_PAY.getCode());
            data.setSettleStatus(EAdoptOrderSettleStatus.NO_SETTLE.getCode());
            adoptOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public void payYueSuccess(AdoptOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount) {
        Date nowDate = new Date();
        if (nowDate.before(data.getStartDatetime())) {// 支付时间小于认养开始时间
            data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        }

        data.setPayType(EPayType.YE.getCode());
        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setPayAmount(data.getAmount());
        data.setPayDatetime(nowDate);
        data.setBackJfAmount(backjfAmount);
        data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
        data.setRemark("余额支付成功");
        adoptOrderDAO.updatePayYueSuccess(data);
    }

    @Override
    public void refreshPayGroup(AdoptOrder data, String payType,
            XN629048Res resultRes) {
        data.setPayType(payType);
        data.setPayGroup(data.getCode());
        data.setCnyDeductAmount(resultRes.getCnyAmount());
        data.setJfDeductAmount(resultRes.getJfAmount());
        data.setRemark("预支付发起中");
        adoptOrderDAO.updatePayGroup(data);
    }

    @Override
    public void paySuccess(AdoptOrder data, BigDecimal payAmount,
            BigDecimal backJfAmount) {
        Date nowDate = new Date();
        if (nowDate.before(data.getStartDatetime())) {// 支付时间小于认养开始时间
            data.setStatus(EAdoptOrderStatus.TO_ADOPT.getCode());
        } else {
            data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        }

        data.setPayAmount(payAmount);
        data.setPayDatetime(nowDate);
        data.setBackJfAmount(backJfAmount);
        data.setSettleStatus(EAdoptOrderSettleStatus.TO_SETTLE.getCode());
        data.setRemark("第三方支付成功");
        adoptOrderDAO.updatePaySuccess(data);
    }

    // 开始订单
    @Override
    public void startAdoptOrder(AdoptOrder data) {
        data.setStatus(EAdoptOrderStatus.ADOPT.getCode());
        data.setRemark("开始认养");
        adoptOrderDAO.updateStartAdopt(data);
    }

    // 结束订单
    @Override
    public void endAdoptOrder(AdoptOrder data) {
        data.setStatus(EAdoptOrderStatus.END.getCode());
        data.setRemark("结束认养");
        adoptOrderDAO.updateStartAdopt(data);

    }

    @Override
    public void refreshSettleStatus(AdoptOrder data, String updater,
            String remark) {
        data.setSettleStatus(EAdoptOrderSettleStatus.SETTLE.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark("已完成结算处理");
        adoptOrderDAO.updateSettleStatus(data);
    }

    @Override
    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition) {
        return adoptOrderDAO.selectList(condition);
    }

    @Override
    public AdoptOrder getAdoptOrder(String code) {
        AdoptOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            AdoptOrder condition = new AdoptOrder();
            condition.setCode(code);
            data = adoptOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "订单编号不存在");
            }
        }
        return data;
    }

    @Override
    public AdoptOrder getAdoptOrderByPayGroup(String payGroup) {
        AdoptOrder data = null;
        if (StringUtils.isNotBlank(payGroup)) {
            AdoptOrder condition = new AdoptOrder();
            condition.setPayGroup(payGroup);
            List<AdoptOrder> list = adoptOrderDAO.selectList(condition);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "根据"
                        + payGroup + "查询订单不存在");
            }
            data = list.get(0);
        }
        return data;
    }

    // 取消订单
    @Override
    public void cancelAdoptOrder(AdoptOrder data, String remark) {
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setStatus(EAdoptOrderStatus.CANCELED.getCode());
            data.setUpdater(data.getApplyUser());
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            adoptOrderDAO.updateCancelAdoptOrder(data);
        }
    }

    @Override
    public XN629048Res getOrderDeductAmount(AdoptOrder data, String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 抵扣积分
        if (data.getAmount().longValue() > 0
                && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.PAY_RULE.getCode());
            Double rate = Double.valueOf(configMap
                .get(SysConstants.JF_DK_MAX_RATE));
            Double cny2jfRate = Double.valueOf(configMap
                .get(SysConstants.CNY2JF_RATE));
            cnyAmount = AmountUtil.mul(data.getAmount(), rate);
            jfAmount = AmountUtil.mul(cnyAmount, cny2jfRate);

            Account jfAccount = accountBO.getAccountByUser(data.getApplyUser(),
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(jfAccount.getAmount()) == 1) {
                jfAmount = jfAccount.getAmount();
                cnyAmount = AmountUtil.mul(jfAccount.getAmount(),
                    1.0 / cny2jfRate);
            }
        }
        return new XN629048Res(cnyAmount, jfAmount);
    }
}
