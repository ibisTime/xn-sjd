package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IWithdrawBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.Withdraw;
import com.ogc.standard.dto.res.XN629903Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.enums.EWithdrawStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Override
    @Transactional
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String tradePwd,
            String applyUser, String applyUserType, String applyNote) {

        // 校验资金密码
        if (EAccountType.CUSTOMER.getCode().equals(applyUserType)) {

            // C端用户
            userBO.checkTradePwd(applyUser, tradePwd);

        } else if (EAccountType.AGENT.getCode().equals(applyUserType)) {

            // 代理用户
            agentUserBO.checkTradePwd(applyUser, tradePwd);

        } else {
            // 其他用户
            sysUserBO.checkTradePwd(applyUser, tradePwd);

        }

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
            payCardInfo, payCardNo, applyUser, applyUserType, applyNote);

        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, amount,
            EJourBizTypeUser.WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.WITHDRAW_FROZEN.getValue(), withdrawCode);

        return withdrawCode;
    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote) {
        Withdraw data = withdrawBO.getWithdraw(code);
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
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES, approveUser,
            approveNote);
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
            String payNote, String channelOrder, BigDecimal payFee) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            if (StringUtils.isBlank(channelOrder) || null == payFee) {
                throw new BizException("xn000000", "请填写渠道单号和转账费");
            }
        }

        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder, payFee);
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
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode, BigDecimal payFee) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
            payCode, payFee);

        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 先解冻，然后扣减余额
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getCode(),
            EJourBizTypeUser.WITHDRAW_UNFROZEN.getValue(), data.getCode());

        // 用户账户取现并扣除手续费
        accountBO.changeAmount(dbAccount, data.getAmount().negate(),
            EChannelType.Offline, payCode, data.getCode(),
            EJourBizTypeUser.WITHDRAW.getCode(), "取现成功");

        accountBO.changeAmount(dbAccount, data.getFee().negate(),
            EChannelType.Offline, payCode, data.getCode(),
            EJourBizTypePlat.WITHDRAW_FEE.getCode(), "取现手续费");

        // 系统账户扣除转账费
        Account sysAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.changeAmount(sysAccount, payFee.negate(),
            EChannelType.Offline, payCode, data.getCode(),
            EJourBizTypePlat.WITHDRAW_TRANS_FEE.getCode(), "取现转账手续费");
    }

    // 取现回录
    @Override
    public void withdrawEnter(String accountNumber, BigDecimal amount,
            String withDate, String channelOrder, String withNote,
            String updater) {
        if (!ESystemAccount.SYS_ACOUNT_OFFLINE.getCode().equals(accountNumber)
                && !ESystemAccount.SYS_ACOUNT_ALIPAY.getCode()
                    .equals(accountNumber)
                && !ESystemAccount.SYS_ACOUNT_WEIXIN.getCode()
                    .equals(accountNumber)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "只支持系统托管账户");
        }

        Account account = accountBO.getAccount(accountNumber);
        if (account.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "账户余额不足");
        }

        String bizNote = "平台于" + withDate + "进行取现"
                + AmountUtil.div(amount, 1000L) + "元";
        if (StringUtils.isNotBlank(withNote)) {
            bizNote = bizNote + withNote;
        }
        accountBO.changeAmount(account, amount.negate(), EChannelType.Offline,
            channelOrder, channelOrder,
            EJourBizTypePlat.WITHDRAW_ENTER.getCode(), bizNote);
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Withdraw withdraw : page.getList()) {
                initWithdraw(withdraw);
            }
        }

        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Withdraw withdraw : list) {
                initWithdraw(withdraw);
            }
        }
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw withdraw = withdrawBO.getWithdraw(code);

        initWithdraw(withdraw);

        return withdraw;
    }

    @Override
    public BigDecimal getTotalWithdraw(String currency) {
        return withdrawBO.getTotalWithdraw(currency);
    }

    @Override
    public XN629903Res getTotalWithdraw(String applyUser,
            List<String> statusList) {
        return new XN629903Res(
            withdrawBO.getTotalWithdraw(applyUser, statusList));
    }

    private void initWithdraw(Withdraw withdraw) {

        // 户名
        String realName = null;

        if (EAccountType.CUSTOMER.getCode()
            .equals(withdraw.getApplyUserType())) {

            // C端用户
            User user = userBO.getUserUnCheck(withdraw.getApplyUser());
            withdraw.setUser(user);

            realName = user.getMobile();
            if (StringUtils.isNotBlank(user.getRealName())) {
                realName = user.getRealName().concat("-").concat(realName);
            }

        } else if (EAccountType.AGENT.getCode()
            .equals(withdraw.getApplyUserType())) {

            // 代理用户
            AgentUser agentUser = agentUserBO
                .getAgentUser(withdraw.getApplyUser());
            User user = new User();
            user.setMobile(agentUser.getMobile());
            withdraw.setUser(user);

            realName = agentUser.getMobile();
            if (StringUtils.isNotBlank(agentUser.getRealName())) {
                realName = agentUser.getRealName().concat("-").concat(realName);
            }

        } else if (EAccountType.PLAT.getCode()
            .equals(withdraw.getApplyUserType())) {

            // 系统用户
            realName = EUser.ADMIN.getValue();
        } else {

            // 其他用户
            SYSUser sysUser = sysUserBO.getSYSUser(withdraw.getApplyUser());
            User user = new User();
            user.setMobile(sysUser.getMobile());
            withdraw.setUser(user);

            realName = sysUser.getMobile();
            if (StringUtils.isNotBlank(sysUser.getRealName())) {
                realName = sysUser.getRealName().concat("-").concat(realName);
            }
        }

        withdraw.setRealName(realName);
    }

}
