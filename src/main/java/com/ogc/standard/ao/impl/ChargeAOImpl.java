package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IChargeAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EChargeStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.EPayType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ChargeAOImpl implements IChargeAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IAlipayBO alipayBO;

    @Override
    public String applyOrderOnline(String userId, String payType,
            BigDecimal transAmount) {
        User user = userBO.getUser(userId);
        String result = null;
        if (EPayType.ALIPAY.getCode().equals(payType)) {
            result = alipayBO.getSignedOrder(user.getUserId(), user.getUserId(),
                EJourBizTypeUser.CHARGE.getCode(),
                EJourBizTypeUser.CHARGE.getCode(),
                EJourBizTypeUser.CHARGE.getValue(), transAmount);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持支付方式");
        }
        return result;
    }

    @Override
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "充值金额需大于零");
        }
        Account account = accountBO.getAccount(accountNumber);
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EJourBizTypeUser.CHARGE.getCode(), amount, payCardInfo, payCardNo,
            applyUser, applyUserType, applyNote);
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

    // 手动增发
    @Override
    @Transactional
    public void addSysAccount(BigDecimal amount, String currency,
            String bizNote, String updater) {
        if (ECurrency.JF.getCode().equals(currency)) {
            Account account = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
            accountBO.changeAmount(account, amount, EChannelType.Offline,
                EJourBizTypePlat.HAND_CHARGE.getCode(),
                EJourBizTypePlat.HAND_CHARGE.getCode(),
                EJourBizTypePlat.HAND_CHARGE.getCode(), bizNote);
        } else if (ECurrency.TPP.getCode().equals(currency)) {
            Account account = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());
            accountBO.changeAmount(account, amount, EChannelType.Offline,
                EJourBizTypePlat.HAND_CHARGE.getCode(),
                EJourBizTypePlat.HAND_CHARGE.getCode(),
                EJourBizTypePlat.HAND_CHARGE.getCode(), bizNote);
        }

    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        List<Charge> chargeList = page.getList();
        for (Charge charge : chargeList) {
            initCharge(charge);
        }
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        for (Charge charge : list) {
            initCharge(charge);
        }
        return list;
    }

    @Override
    public Charge getCharge(String code) {
        Charge charge = chargeBO.getCharge(code);
        initCharge(charge);
        return charge;
    }

    private void initCharge(Charge charge) {
        // 户名
        String realName = null;

        if (EAccountType.CUSTOMER.getCode().equals(charge.getApplyUserType())) {

            // C端用户
            User user = userBO.getUser(charge.getApplyUser());
            charge.setPayer(user);

            realName = user.getMobile();
            if (StringUtils.isNotBlank(user.getRealName())) {
                realName = user.getRealName().concat("-").concat(realName);
            }
        } else if (EAccountType.AGENT.getCode()
            .equals(charge.getApplyUserType())) {

            // 代理用户
            AgentUser agentUser = agentUserBO
                .getAgentUser(charge.getApplyUser());
            User user = new User();
            user.setMobile(agentUser.getMobile());
            charge.setPayer(user);

            realName = agentUser.getMobile();
            if (StringUtils.isNotBlank(agentUser.getRealName())) {
                realName = agentUser.getRealName().concat("-").concat(realName);
            }

        } else if (EAccountType.PLAT.getCode()
            .equals(charge.getApplyUserType())) {

            // 系统用户
            realName = EUser.ADMIN.getValue();
        } else {

            // 其他用户
            SYSUser sysUser = sysUserBO.getSYSUser(charge.getApplyUser());
            User user = new User();
            user.setMobile(sysUser.getMobile());
            charge.setPayer(user);

        }

        charge.setRealName(realName);
    }
}
