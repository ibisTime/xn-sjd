/**
 * @Title DistributionOrderImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2018年10月4日 下午11:10:43 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICommodityOrderBO;
import com.ogc.standard.bo.ICommodityOrderDetailBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IUserExtBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAgentUserLevel;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeBusiness;
import com.ogc.standard.enums.EJourBizTypeMaintain;
import com.ogc.standard.enums.EJourBizTypeOwner;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EUserRefereeType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: xieyj 
 * @since: 2018年10月4日 下午11:10:43 
 * @history:
 */
@Service
public class DistributionOrderBOImpl implements IDistributionOrderBO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private IAgentUserBO agentUserBO;

    @Autowired
    private ISettleBO settleBO;

    @Autowired
    private IUserExtBO userExtBO;

    @Autowired
    private ICommodityOrderBO commodityOrderBO;

    @Autowired
    private ICommodityOrderDetailBO commodityOrderDetailBO;

    @Override
    public XN629048Res getAdoptOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (amount.longValue() > 0 && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());

            Double adoptDkRate = Double
                .valueOf(configMap.get(SysConstants.ADOPT_DK_RATE));// 1人民币兑换多少积分

            cnyAmount = AmountUtil.mul(amount, maxJfdkRate * 0.01);
            jfAmount = AmountUtil.mul(cnyAmount, adoptDkRate);

            Account userJfAccount = accountBO.getAccountByUser(applyUser,
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(userJfAccount.getAmount()) == 1) {
                jfAmount = userJfAccount.getAmount();
                cnyAmount = jfAmount.divide(new BigDecimal(adoptDkRate));
            }
        }

        return new XN629048Res(cnyAmount, jfAmount);

    }

    @Override
    public XN629048Res getPresellOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (amount.longValue() > 0 && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());

            Double adoptDkRate = Double
                .valueOf(configMap.get(SysConstants.PRESELL_DK_RATE));// 1人民币兑换多少积分

            cnyAmount = AmountUtil.mul(amount, maxJfdkRate * 0.01);
            jfAmount = AmountUtil.mul(cnyAmount, adoptDkRate);

            Account userJfAccount = accountBO.getAccountByUser(applyUser,
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(userJfAccount.getAmount()) == 1) {
                jfAmount = userJfAccount.getAmount();
                cnyAmount = jfAmount.divide(new BigDecimal(adoptDkRate));
            }
        }

        return new XN629048Res(cnyAmount, jfAmount);

    }

    @Override
    public XN629048Res getCommodityOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (amount.longValue() > 0 && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.JF_RULE.getCode());

            Double adoptDkRate = Double
                .valueOf(configMap.get(SysConstants.COMMODITY_DK_RATE));// 1人民币兑换多少积分

            cnyAmount = AmountUtil.mul(amount, maxJfdkRate * 0.01);
            jfAmount = AmountUtil.mul(cnyAmount, adoptDkRate);

            Account userJfAccount = accountBO.getAccountByUser(applyUser,
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(userJfAccount.getAmount()) == 1) {
                jfAmount = userJfAccount.getAmount();
                cnyAmount = jfAmount.divide(new BigDecimal(adoptDkRate));
            }
        }

        return new XN629048Res(cnyAmount, jfAmount);
    }

    @Override
    public XN629048Res getCommodityGroupOrderDeductAmount(String payGroup,
            String isDk) {

        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (!EBoolean.YES.getCode().equals(isDk)) {
            return new XN629048Res(cnyAmount, jfAmount);
        }

        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        Double adoptDkRate = Double
            .valueOf(configMap.get(SysConstants.COMMODITY_DK_RATE));// 1人民币兑换多少积分

        List<CommodityOrder> commodityOrderList = commodityOrderBO
            .queryCommodityOrderByPayGroup(payGroup);

        if (CollectionUtils.isNotEmpty(commodityOrderList)) {
            for (CommodityOrder commodityOrder : commodityOrderList) {
                List<CommodityOrderDetail> commodityOrderDetailList = commodityOrderDetailBO
                    .queryOrderDetail(commodityOrder.getCode());

                if (CollectionUtils.isNotEmpty(commodityOrderDetailList)) {
                    BigDecimal detailCnyAmount = BigDecimal.ZERO;
                    BigDecimal detailJfAmount = BigDecimal.ZERO;

                    for (CommodityOrderDetail commodityOrderDetail : commodityOrderDetailList) {
                        detailCnyAmount = AmountUtil.mul(
                            commodityOrderDetail.getAmount(),
                            commodityOrderDetail.getMaxJfdkRate() * 0.01);
                        detailJfAmount = AmountUtil.mul(detailCnyAmount,
                            adoptDkRate);

                        cnyAmount = cnyAmount.add(detailCnyAmount);
                        jfAmount = jfAmount.add(detailJfAmount);

                    }
                }

            }

            Account userJfAccount = accountBO.getAccountByUser(
                commodityOrderList.get(0).getApplyUser(),
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(userJfAccount.getAmount()) == 1) {
                jfAmount = userJfAccount.getAmount();
                cnyAmount = jfAmount.divide(new BigDecimal(adoptDkRate));
            }
        }

        return new XN629048Res(cnyAmount, jfAmount);
    }

    @Override
    public BigDecimal adoptDistribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        BigDecimal payAmount = amount;// 订单支付金额（抵扣积分后）

        // 平台人民币转化为积分池
        BigDecimal sysJfpoolCny = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));// 积分池分销人民币
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.changeAmount(sysCnyAccount, sysJfpoolCny.negate(),
            EChannelType.NBZ, EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getValue());

        BigDecimal sysJfpoolJf = sysJfpoolCny.multiply(
            new BigDecimal(mapList.get(SysConstants.BACK_JFPOOL_RATE)));// 积分池分销积分
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        accountBO.changeAmount(sysJfAccount, sysJfpoolJf, EChannelType.NBZ,
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getValue());

        // 产权方分销
        BigDecimal ownerDeductAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_OWENER_RATE)));// 产权方分销金额
        accountBO.transAmount(ESysUser.SYS_USER.getCode(), ownerId,
            ECurrency.CNY.getCode(), ownerDeductAmount,
            EJourBizTypePlat.ADOPT_DIST.getCode(),
            EJourBizTypeOwner.OWNER_PROFIT.getCode(),
            EJourBizTypePlat.ADOPT_DIST.getValue(),
            EJourBizTypeOwner.OWNER_PROFIT.getValue(), code);

        // 判断养护方是否存在，没有则平台回收
        String maintainId = applyBindMaintainBO.getMaintainId(ownerId);
        if (StringUtils.isNotBlank(maintainId)) {
            BigDecimal maintainDeductAmount = payAmount.multiply(
                new BigDecimal(mapList.get(SysConstants.DIST_MAINTAIN_RATE)));
            accountBO.transAmount(ESysUser.SYS_USER.getCode(), maintainId,
                ECurrency.CNY.getCode(), maintainDeductAmount,
                EJourBizTypePlat.ADOPT_DIST.getCode(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode(),
                EJourBizTypePlat.ADOPT_DIST.getValue(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getValue(), code);
        }

        // 代理方分销
        BigDecimal agentTotalAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_AGENT_RATE)));// 分成总金额
        distributionAgent(code, applyUser, amount, type, mapList,
            agentTotalAmount, resultRes);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.ADOPT_CREATE_RATE)));// 认养返积分比例

        // 生日购买双倍积分
        UserExt userExt = userExtBO.getUserExt(applyUser);
        if (null != userExt.getBirthday()) {
            if (DateUtil.isToday(userExt.getBirthday())) {
                jfAwardAmount = jfAwardAmount.multiply(new BigDecimal(2));
            }
        }

        // C端用户返积分
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), applyUser, ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.ADOPT_PAY_BACK.getCode(),
            EJourBizTypePlat.ADOPT_PAY_BACK.getCode(),
            EJourBizTypeUser.ADOPT_PAY_BACK.getValue(),
            EJourBizTypePlat.ADOPT_PAY_BACK.getValue(), code);

        // 直推用户分销
        User user = userBO.getUser(applyUser);
        if (EUserRefereeType.USER.getCode().equals(user.getUserRefereeType())) {

            BigDecimal adoptDirectRate = new BigDecimal(
                mapList.get(SysConstants.ADOPT_DIRECT));// 用户直推认养送比例
            BigDecimal adoptDirectAmount = payAmount.multiply(adoptDirectRate);// 用户直推认养送金额

            accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                user.getUserReferee(), ECurrency.CNY.getCode(),
                adoptDirectAmount, EJourBizTypePlat.ADOPT_DIRECT.getCode(),
                EJourBizTypeUser.ADOPT_DIRECT.getCode(),
                EJourBizTypePlat.ADOPT_DIRECT.getValue(),
                EJourBizTypeUser.ADOPT_DIRECT.getValue(), code);

            // 间推用户分销
            User directUser = userBO.getUser(user.getUserReferee());
            if (EUserRefereeType.USER.getCode()
                .equals(directUser.getUserRefereeType())) {

                BigDecimal adoptInDirectRate = new BigDecimal(
                    mapList.get(SysConstants.ADOPT_INDIRECT));// 用户间推认养送比例
                BigDecimal adoptInDirectAmount = payAmount
                    .multiply(adoptInDirectRate);// 用户间推认养送金额

                accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                    directUser.getUserReferee(), ECurrency.CNY.getCode(),
                    adoptInDirectAmount,
                    EJourBizTypePlat.ADOPT_INDIRECT.getCode(),
                    EJourBizTypeUser.ADOPT_INDIRECT.getCode(),
                    EJourBizTypePlat.ADOPT_INDIRECT.getValue(),
                    EJourBizTypeUser.ADOPT_INDIRECT.getValue(), code);
            }
        }

        return jfAwardAmount;
    }

    @Override
    public BigDecimal presellDistribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        BigDecimal payAmount = amount;// 订单支付金额（抵扣积分后）

        // 平台人民币转化为积分池
        BigDecimal sysJfpoolCny = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));// 积分池分销人民币
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.changeAmount(sysCnyAccount, sysJfpoolCny.negate(),
            EChannelType.NBZ, EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getValue());

        BigDecimal sysJfpoolJf = sysJfpoolCny.multiply(
            new BigDecimal(mapList.get(SysConstants.BACK_JFPOOL_RATE)));// 积分池分销积分
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        accountBO.changeAmount(sysJfAccount, sysJfpoolJf, EChannelType.NBZ,
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.DIST_USER_BACK_JFPOOL.getValue());

        // 产权方分销
        BigDecimal ownerDeductAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.PRESELL_DIST_OWENER_RATE)));// 产权方分销金额
        accountBO.transAmount(ESysUser.SYS_USER.getCode(), ownerId,
            ECurrency.CNY.getCode(), ownerDeductAmount,
            EJourBizTypePlat.PRESELL_DIST.getCode(),
            EJourBizTypeOwner.OWNER_PROFIT.getCode(),
            EJourBizTypePlat.PRESELL_DIST.getValue(),
            EJourBizTypeOwner.OWNER_PROFIT.getValue(), code);

        // 判断养护方是否存在，没有则平台回收
        String maintainId = applyBindMaintainBO.getMaintainId(ownerId);
        if (StringUtils.isNotBlank(maintainId)) {
            BigDecimal maintainDeductAmount = payAmount.multiply(new BigDecimal(
                mapList.get(SysConstants.PRESELL_DIST_MAINTAIN_RATE)));
            accountBO.transAmount(ESysUser.SYS_USER.getCode(), maintainId,
                ECurrency.CNY.getCode(), maintainDeductAmount,
                EJourBizTypePlat.PRESELL_DIST.getCode(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode(),
                EJourBizTypePlat.PRESELL_DIST.getValue(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getValue(), code);
        }

        // 代理方分销
        BigDecimal agentTotalAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.PRESELL_DIST_AGENT_RATE)));// 分成总金额
        distributionAgent(code, applyUser, amount, type, mapList,
            agentTotalAmount, resultRes);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.PRESELL_CREATE_RATE)));// 预售返积分比例

        // 生日购买双倍积分
        UserExt userExt = userExtBO.getUserExt(applyUser);
        if (null != userExt.getBirthday()) {
            if (DateUtil.isToday(userExt.getBirthday())) {
                jfAwardAmount = jfAwardAmount.multiply(new BigDecimal(2));
            }
        }

        // C端用户返积分
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), applyUser, ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.PRESELL_PAY_BACK.getCode(),
            EJourBizTypePlat.PRESELL_PAY_BACK.getCode(),
            EJourBizTypeUser.PRESELL_PAY_BACK.getValue(),
            EJourBizTypePlat.PRESELL_PAY_BACK.getValue(), code);

        // 直推用户分销
        User user = userBO.getUser(applyUser);
        if (EUserRefereeType.USER.getCode().equals(user.getUserRefereeType())) {

            BigDecimal adoptDirectRate = new BigDecimal(
                mapList.get(SysConstants.PRESELL_DIRECT));// 用户直推预售送比例
            BigDecimal adoptDirectAmount = payAmount.multiply(adoptDirectRate);// 用户直推预售送金额

            accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                user.getUserReferee(), ECurrency.CNY.getCode(),
                adoptDirectAmount, EJourBizTypePlat.PRESELL_DIRECT.getCode(),
                EJourBizTypeUser.PRESELL_DIRECT.getCode(),
                EJourBizTypePlat.PRESELL_DIRECT.getValue(),
                EJourBizTypeUser.PRESELL_DIRECT.getValue(), code);

            // 间推用户分销
            User directUser = userBO.getUser(user.getUserReferee());
            if (EUserRefereeType.USER.getCode()
                .equals(directUser.getUserRefereeType())) {

                BigDecimal adoptInDirectRate = new BigDecimal(
                    mapList.get(SysConstants.PRESELL_INDIRECT));// 用户间推预售送比例
                BigDecimal adoptInDirectAmount = payAmount
                    .multiply(adoptInDirectRate);// 用户间推认养送金额

                accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                    directUser.getUserReferee(), ECurrency.CNY.getCode(),
                    adoptInDirectAmount,
                    EJourBizTypePlat.PRESELL_INDIRECT.getCode(),
                    EJourBizTypeUser.PRESELL_INDIRECT.getCode(),
                    EJourBizTypePlat.PRESELL_INDIRECT.getValue(),
                    EJourBizTypeUser.PRESELL_INDIRECT.getValue(), code);
            }
        }

        return jfAwardAmount;
    }

    @Override
    public BigDecimal commodityDistribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        BigDecimal payAmount = amount.subtract(resultRes.getCnyAmount());// 订单支付金额（抵扣积分后）

        // 平台人民币转化为积分池
        BigDecimal sysJfpoolCny = payAmount.multiply(new BigDecimal(
            mapList.get(SysConstants.COMMODITY_DIST_USER_BACK_JF_RATE)));// 积分池分销人民币
        Account sysCnyAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_CNY.getCode());
        accountBO.changeAmount(sysCnyAccount, sysJfpoolCny.negate(),
            EChannelType.NBZ,
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getValue());

        BigDecimal sysJfpoolJf = sysJfpoolCny.multiply(
            new BigDecimal(mapList.get(SysConstants.BACK_JFPOOL_RATE)));// 积分池分销积分
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        accountBO.changeAmount(sysJfAccount, sysJfpoolJf, EChannelType.NBZ,
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getCode(),
            EJourBizTypePlat.COMMODITY_USER_BACK_JFPOOL.getValue());

        // 商家分销
        BigDecimal bussinessAmount = payAmount.multiply(new BigDecimal(
            mapList.get(SysConstants.COMMODITY_DIST_BUSINESS_RATE)));// 商家金额
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.CNY.getCode(), ownerId, ECurrency.CNY.getCode(),
            bussinessAmount, EJourBizTypeBusiness.BUSINESS_PROFIT.getCode(),
            EJourBizTypePlat.COMMODITY_DIST.getCode(),
            EJourBizTypeBusiness.BUSINESS_PROFIT.getValue(),
            EJourBizTypePlat.COMMODITY_DIST.getValue(), code);

        // 代理方分销
        BigDecimal agentTotalAmount = payAmount.multiply(new BigDecimal(
            mapList.get(SysConstants.COMMODITY_DIST_AGENT_RATE)));// 分成总金额
        distributionAgent(code, applyUser, amount, type, mapList,
            agentTotalAmount, resultRes);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.COMMODITY_CREATE_RATE)));// 预售返积分比例

        // 生日购买双倍积分
        UserExt userExt = userExtBO.getUserExt(applyUser);
        if (null != userExt.getBirthday()) {
            if (DateUtil.isToday(userExt.getBirthday())) {
                jfAwardAmount = jfAwardAmount.multiply(new BigDecimal(2));
            }
        }

        // C端用户返积分
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), applyUser, ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.COMMODITY_PAY_BACK.getCode(),
            EJourBizTypePlat.COMMODITY_PAY_BACK.getCode(),
            EJourBizTypeUser.COMMODITY_PAY_BACK.getValue(),
            EJourBizTypePlat.COMMODITY_PAY_BACK.getValue(), code);

        // 直推用户分销
        User user = userBO.getUser(applyUser);
        if (EUserRefereeType.USER.getCode().equals(user.getUserRefereeType())) {

            BigDecimal adoptDirectRate = new BigDecimal(
                mapList.get(SysConstants.COMMODITY_DIRECT));// 用户直推预售送比例
            BigDecimal adoptDirectAmount = payAmount.multiply(adoptDirectRate);// 用户直推预售送金额

            accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                user.getUserReferee(), ECurrency.CNY.getCode(),
                adoptDirectAmount, EJourBizTypePlat.COMMODITY_DIRECT.getCode(),
                EJourBizTypeUser.COMMODITY_DIRECT.getCode(),
                EJourBizTypePlat.COMMODITY_DIRECT.getValue(),
                EJourBizTypeUser.COMMODITY_DIRECT.getValue(), code);

            // 间推用户分销
            User directUser = userBO.getUser(user.getUserReferee());
            if (EUserRefereeType.USER.getCode()
                .equals(directUser.getUserRefereeType())) {

                BigDecimal adoptInDirectRate = new BigDecimal(
                    mapList.get(SysConstants.COMMODITY_INDIRECT));// 用户间推预售送比例
                BigDecimal adoptInDirectAmount = payAmount
                    .multiply(adoptInDirectRate);// 用户间推认养送金额

                accountBO.transAmount(ESysUser.SYS_USER.getCode(),
                    directUser.getUserReferee(), ECurrency.CNY.getCode(),
                    adoptInDirectAmount,
                    EJourBizTypePlat.COMMODITY_INDIRECT.getCode(),
                    EJourBizTypeUser.COMMODITY_INDIRECT.getCode(),
                    EJourBizTypePlat.COMMODITY_INDIRECT.getValue(),
                    EJourBizTypeUser.COMMODITY_INDIRECT.getValue(), code);
            }
        }

        return jfAwardAmount;
    }

    // 代理等级分销
    private void distributionAgent(String code, String applyUserId,
            BigDecimal amount, String type, Map<String, String> mapList,
            BigDecimal agentTotalAmount, XN629048Res resultRes) {
        // 拿到下单用户的代理商
        User applyUser = userBO.getUser(applyUserId);
        if (StringUtils.isBlank(applyUser.getAgentId())) {
            return;
        }

        String dkNote = getDKNote(resultRes.getJfAmount(),
            resultRes.getCnyAmount());

        AgentUser agentUser = agentUserBO.getAgentUser(applyUser.getAgentId());
        if (EAgentUserLevel.FOUR.getCode().equals(agentUser.getLevel())) {
            agent4Back(code, amount, type, applyUser, agentUser,
                agentTotalAmount, mapList, dkNote);
        } else if (EAgentUserLevel.THREE.getCode()
            .equals(agentUser.getLevel())) {
            agent3Back(code, amount, type, applyUser, agentUser,
                agentTotalAmount, mapList, dkNote);
        } else if (EAgentUserLevel.SECOND.getCode()
            .equals(agentUser.getLevel())) {
            agent2Back(code, amount, type, applyUser, agentUser,
                agentTotalAmount, mapList, dkNote);
        } else if (EAgentUserLevel.FIRST.getCode()
            .equals(agentUser.getLevel())) {
            agent1Back(code, amount, type, applyUser, agentUser,
                agentTotalAmount, mapList, dkNote);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理等级不存在");
        }
    }

    private void agent4Back(String code, BigDecimal amount, String type,
            User applyUser, AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 四级代理
        BigDecimal level4Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT4_RATE));
        BigDecimal settle4Amount = agentTotalAmount.multiply(level4Rate);

        String preNote = "四级代理下级用户" + realName + "消费"
                + AmountUtil.div(amount, 1000L) + ",则用于级差是"
                + AmountUtil.div(settle4Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle4Amount, level4Rate, code, type,
            preNote);

        // 三级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel3Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT3_RATE));
        BigDecimal level3Rate = totalLevel3Rate.subtract(level4Rate);
        BigDecimal settle3Amount = agentTotalAmount.multiply(level3Rate);

        preNote = "四级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle3Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle3Amount, level3Rate, code, type,
            preNote);

        // 二级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel2Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT2_RATE));
        BigDecimal level2Rate = totalLevel2Rate.subtract(totalLevel3Rate);
        BigDecimal settle2Amount = agentTotalAmount.multiply(level2Rate);

        preNote = "四级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate, code, type,
            preNote);

        // 一级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal level1Rate = totalLevel1Rate.subtract(totalLevel2Rate);
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);

        preNote = "四级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate, code, type,
            preNote);
    }

    private void agent3Back(String code, BigDecimal amount, String type,
            User applyUser, AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 三级代理
        BigDecimal level3Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT3_RATE));
        BigDecimal settle3Amount = agentTotalAmount.multiply(level3Rate);

        String preNote = "三级代理下级用户" + realName + "消费"
                + AmountUtil.div(amount, 1000L) + ",则用于级差是"
                + AmountUtil.div(settle3Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle3Amount, level3Rate, code, type,
            preNote);

        // 二级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel2Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT2_RATE));
        BigDecimal level2Rate = totalLevel2Rate.subtract(level3Rate);
        BigDecimal settle2Amount = agentTotalAmount.multiply(level2Rate);

        preNote = "三级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate, code, type,
            preNote);

        // 一级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal level1Rate = totalLevel1Rate.subtract(totalLevel2Rate);
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);

        preNote = "三级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate, code, type,
            preNote);
    }

    private void agent2Back(String code, BigDecimal amount, String type,
            User applyUser, AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 二级代理
        BigDecimal level2Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT2_RATE));
        BigDecimal settle2Amount = agentTotalAmount.multiply(level2Rate);

        String preNote = "二级代理下级用户" + realName + "消费"
                + AmountUtil.div(amount, 1000L) + ",则用于级差是"
                + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate, code, type,
            preNote);

        // 一级代理
        agentUser = agentUserBO
            .getAgentUserUnCheck(agentUser.getParentUserId());
        if (null == agentUser) {
            return;
        }
        BigDecimal totalLevel1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal level1Rate = totalLevel1Rate.subtract(level2Rate);
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);

        preNote = "二级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
                + ",则用于级差是" + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate, code, type,
            preNote);
    }

    private void agent1Back(String code, BigDecimal amount, String type,
            User applyUser, AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        // 一级代理
        BigDecimal level1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);

        String realName = getRealName(applyUser);
        String preNote = "一级代理下级用户" + realName + "消费"
                + AmountUtil.div(amount, 1000L) + ",则用于级差是"
                + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate, code, type,
            preNote);
    }

    private String getRealName(User applyUser) {
        String realName = applyUser.getMobile();

        if (StringUtils.isNotBlank(applyUser.getRealName())) {
            realName = applyUser.getRealName().concat("-").concat(realName);
        }

        return realName;
    }

    private String getDKNote(BigDecimal jfAmount, BigDecimal cnyAmount) {
        String dkNote = null;

        if (jfAmount.compareTo(BigDecimal.ZERO) == 1) {
            dkNote = "使用" + AmountUtil.div(jfAmount, 1000L) + "积分抵扣"
                    + AmountUtil.div(cnyAmount, 1000L) + "人民币";
        }

        return dkNote;
    }

}
