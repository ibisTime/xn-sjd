package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IChargeAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.dto.res.XN802347Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EChargeStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ChargeAOImpl implements IChargeAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IJourBO jourBO;

    @Override
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "充值金额需大于零");
        }
        Account account = accountBO.getAccount(accountNumber);
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EJourBizTypeUser.CHARGE.getCode(), amount, payCardInfo, payCardNo,
            applyUser, applyNote);
        return code;
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote) {
        Charge data = chargeBO.getCharge(code);
        if (!EChargeStatus.toPay.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote);
        } else {
            payOrderNO(data, payUser, payNote);
        }
    }

    private void payOrderNO(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, false, payUser, payNote);
    }

    private void payOrderYES(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, true, payUser, payNote);

        // 账户加钱
        Account accountUser = accountBO.getAccount(data.getAccountNumber());
        accountBO.changeAmount(accountUser, data.getAmount(),
            EChannelType.Offline, null, data.getCode(),
            EJourBizTypeUser.CHARGE.getCode(), "线下充值");

        Account account = accountBO.getAccount(data.getAccountNumber());
        if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
            // 线下托管账户加钱
            Account sysAccountOffLine = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_OFFLINE.getCode());
            accountBO.changeAmount(sysAccountOffLine, data.getAmount(),
                EChannelType.Offline, null, data.getCode(),
                EJourBizTypePlat.CHARGE.getCode(), "线下充值");
        }
    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        List<Charge> chargeList = page.getList();
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        return list;
    }

    @Override
    public Charge getCharge(String code) {
        return chargeBO.getCharge(code);
    }

    @Override
    public XN802347Res getChargeCheckInfo(String code) {
        XN802347Res res = new XN802347Res();

        return res;
    }
}
