package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IChargeDAO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EChargeStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class ChargeBOImpl extends PaginableBOImpl<Charge> implements IChargeBO {
    @Autowired
    private IChargeDAO chargeDAO;

    @Override
    public String applyOrderOffline(Account account, String bizType,
            BigDecimal amount, String payCardInfo, String payCardNo,
            String applyUser, String applyUserType, String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "充值金额不能为0");
        }
        String code = OrderNoGenerater.generate("CZ");

        Charge data = new Charge();

        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setAccountType(account.getType());
        data.setAmount(amount);
        data.setCurrency(account.getCurrency());

        data.setBizType(bizType);
        data.setBizNote(applyNote);
        data.setPayCardInfo(payCardInfo);
        data.setPayCardNo(payCardNo);
        data.setStatus(EChargeStatus.toPay.getCode());

        data.setApplyUser(applyUser);
        data.setApplyUserType(applyUserType);
        data.setApplyNote(applyNote);
        data.setApplyDatetime(new Date());
        data.setChannelType(EChannelType.Offline.getCode());

        chargeDAO.insert(data);
        return code;
    }

    @Override
    public String applyOrderOnline(Account account, String refNo,
            String payGroup, String bizType, String bizNote,
            BigDecimal transAmount, EChannelType channelType, String applyUser) {
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "充值金额不能为0");
        }
        String code = OrderNoGenerater.generate(EGeneratePrefix.Charge
            .getCode());
        Charge data = new Charge();
        data.setCode(code);
        if (EJourBizTypeUser.CHARGE.getCode().equals(bizType)) {
            data.setBizNo(code);
            data.setPayGroup(code);
        } else {
            data.setPayGroup(payGroup);
            data.setBizNo(refNo);
        }
        data.setAccountNumber(account.getAccountNumber());
        data.setAmount(transAmount);

        data.setAccountType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setBizType(bizType);
        data.setBizNote(bizNote);
        data.setPayCardInfo(null);
        data.setPayCardNo(null);

        data.setStatus(EChargeStatus.toPay.getCode());
        data.setApplyUser(applyUser);
        data.setApplyUserType(EAccountType.CUSTOMER.getCode());
        data.setApplyDatetime(new Date());
        data.setChannelType(channelType.getCode());
        chargeDAO.insert(data);
        return code;
    }

    @Override
    public void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote) {
        if (booleanFlag) {
            data.setStatus(EChargeStatus.Pay_YES.getCode());
        } else {
            data.setStatus(EChargeStatus.Pay_NO.getCode());
        }
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayDatetime(new Date());
        chargeDAO.payOrder(data);
    }

    @Override
    public void callBackChange(Charge dbCharge, boolean booleanFlag) {
        if (booleanFlag) {
            dbCharge.setStatus(EChargeStatus.Pay_YES.getCode());
        } else {
            dbCharge.setStatus(EChargeStatus.Pay_NO.getCode());
        }
        dbCharge.setPayUser(null);
        dbCharge.setPayNote("在线充值自动回调");
        dbCharge.setPayDatetime(new Date());
        chargeDAO.payOrder(dbCharge);

    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        return chargeDAO.selectList(condition);
    }

    @Override
    public Charge getCharge(String code) {
        Charge order = null;
        if (StringUtils.isNotBlank(code)) {
            Charge condition = new Charge();
            condition.setCode(code);
            order = chargeDAO.select(condition);
            if (null == order) {
                throw new BizException("xn000000", "订单号[" + code + "]不存在");
            }
        }
        return order;
    }
}
