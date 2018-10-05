package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IWithdrawBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Withdraw;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EWithdrawStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IJourBO jourBO;

    @Override
    @Transactional
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String tradePwd,
            String applyUser, String applyNote) {
        // 校验资金密码
        userBO.checkTradePwd(applyUser, tradePwd);

        Account dbAccount = accountBO.getAccount(accountNumber);
        // 取现获取手续费
        BigDecimal fee = withdrawBO.doCheckAndGetFee(dbAccount, amount);
        if (amount.compareTo(fee) == 0 || amount.compareTo(fee) == -1) {
            throw new BizException("xn000000", "提现金额需大于手续费");
        }

        // 账户可用余额是否充足
        if (dbAccount.getAmount().subtract(dbAccount.getFrozenAmount())
            .compareTo(amount) == -1) {
            throw new BizException("xn000000", "可用余额不足");
        }
        // 生成取现订单
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote);

        BigDecimal totalAmount = amount.add(fee);
        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, totalAmount,
            EJourBizTypeUser.WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.WITHDRAW_FROZEN.getValue(), withdrawCode);

        return withdrawCode;
    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (null == data) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "取现订单编号不存在");
        }
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "申请记录状态不是待审批状态，无法审批");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES,
            approveUser, approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, BigDecimal transFee) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (data == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不存在编号为"
                    + code + "的订单");
        }
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder, transFee);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode, BigDecimal.ZERO);

        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        BigDecimal totalAmount = data.getAmount().add(data.getFee());
        accountBO.unfrozenAmount(dbAccount, totalAmount,
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode, BigDecimal transFee) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
            payCode, transFee);

        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 先解冻，然后扣减余额
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.WITHDRAW.getCode(),
            EJourBizTypeUser.WITHDRAW.getValue(), data.getCode());

        BigDecimal totalAmount = data.getAmount().add(data.getFee());
        accountBO.unfrozenAmount(dbAccount, totalAmount,
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getCode(), "取现解冻",
            data.getCode());

        // 取现扣钱
        accountBO.changeAmount(dbAccount, totalAmount.negate(),
            EChannelType.Offline, null, data.getCode(),
            EJourBizTypeUser.WITHDRAW.getCode(), "取现成功");
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        return withdrawBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        return withdrawBO.queryWithdrawList(condition);
    }

    @Override
    public Withdraw getWithdraw(String code) {
        return withdrawBO.getWithdraw(code);
    }

    @Override
    public BigDecimal getTotalWithdraw(String currency) {
        return withdrawBO.getTotalWithdraw(currency);
    }
}
