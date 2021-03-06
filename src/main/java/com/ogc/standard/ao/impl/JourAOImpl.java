package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IJourAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629905Res;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeBusiness;
import com.ogc.standard.enums.EJourBizTypeMaintain;
import com.ogc.standard.enums.EJourBizTypeOwner;
import com.ogc.standard.enums.EJourStatus;
import com.ogc.standard.enums.EUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private IAccountBO accountBO;

    /*
     * 人工调账： 1、判断流水账是否平，平则更改订单状态，不平则更改产生红冲蓝补订单，而后更改订单状态
     */
    @Override
    @Transactional
    public void checkJour(String code, BigDecimal checkAmount, String checkUser,
            String checkNote, String systemCode) {
        Jour jour = jourBO.getJourNotException(code);
        if (null != jour) {
            doCheckJourNow(code, checkAmount, checkUser, checkNote, jour);// 现在流水对账
        }
    }

    private void doCheckJourNow(String code, BigDecimal checkAmount,
            String checkUser, String checkNote, Jour jour) {
        if (!EJourStatus.todoCheck.getCode().equals(jour.getStatus())) {
            throw new BizException("xn000000", "该流水<" + code + ">不处于待对账状态");
        }
        if (checkAmount.compareTo(BigDecimal.ZERO) != 0) {
            jourBO.doCheckJour(jour, EBoolean.NO, checkAmount, checkUser,
                checkNote);
        } else {
            jourBO.doCheckJour(jour, EBoolean.YES, checkAmount, checkUser,
                checkNote);
        }
    }

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }

        Paginable<Jour> page = jourBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Jour jour : page.getList()) {

                initJour(jour);

            }
        }

        return page;
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        List<Jour> jourList = jourBO.queryJourList(condition);
        List<Jour> result = new ArrayList<Jour>();
        result.addAll(jourList);
        return result;
    }

    @Override
    public Jour getJour(String code) {
        Jour jour = jourBO.getJour(code);

        initJour(jour);

        return jour;
    }

    private void initJour(Jour jour) {
        // 户名
        String realName = null;
        if (EAccountType.CUSTOMER.getCode().equals(jour.getAccountType())) {

            // C端用户
            User user = userBO.getUserUnCheck(jour.getUserId());
            if (null != user) {
                realName = user.getMobile();
                if (StringUtils.isNotBlank(user.getRealName())) {
                    realName = user.getRealName().concat("-").concat(realName);
                }
            }

        } else if (EAccountType.AGENT.getCode().equals(jour.getAccountType())) {

            // 代理用户
            AgentUser agentUser = agentUserBO
                .getAgentUserUnCheck(jour.getUserId());
            if (null != agentUser) {
                realName = agentUser.getMobile();
                if (StringUtils.isNotBlank(agentUser.getRealName())) {
                    realName = agentUser.getRealName().concat("-")
                        .concat(realName);
                }
            }

        } else if (EAccountType.PLAT.getCode().equals(jour.getAccountType())) {

            // 系统用户
            realName = EUser.ADMIN.getValue();
        } else {

            // 其他用户
            SYSUser sysUser = sysUserBO.getSYSUserUnCheck(jour.getUserId());
            if (null != sysUser) {
                realName = sysUser.getMobile();
                if (StringUtils.isNotBlank(sysUser.getRealName())) {
                    realName = sysUser.getRealName().concat("-")
                        .concat(realName);
                }
            }

        }

        jour.setRealName(realName);
    }

    @Override
    public XN629905Res getTotalBenefitAmount(String userId, String accountType,
            String dateStart, String dateEnd) {
        Account account = accountBO.getAccountByUser(userId,
            ECurrency.CNY.getCode());

        List<String> bizTypeList = new ArrayList<String>();

        if (EAccountType.OWNER.getCode().equals(accountType)) {
            bizTypeList.add(EJourBizTypeOwner.OWNER_PROFIT.getCode());
        }

        if (EAccountType.MAINTAIN.getCode().equals(accountType)) {
            bizTypeList.add(EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode());
        }

        if (EAccountType.BUSINESS.getCode().equals(accountType)) {
            bizTypeList.add(EJourBizTypeBusiness.BUSINESS_PROFIT.getCode());
        }

        BigDecimal totalAmount = jourBO.getTotalAmount(
            account.getAccountNumber(), bizTypeList, dateStart, dateEnd);

        return new XN629905Res(totalAmount);
    }
}
