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
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
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
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAgentUserLevel;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeMaintain;
import com.ogc.standard.enums.EJourBizTypeOwner;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.enums.ESystemAccount;
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

    @Override
    public XN629048Res getOrderDeductAmount(BigDecimal amount, String applyUser,
            String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (amount.longValue() > 0 && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.PAY_RULE.getCode());

            Double rate = Double
                .valueOf(configMap.get(SysConstants.JF_DK_MAX_RATE));// 订单可用积分抵扣比例

            Double cny2jfRate = Double
                .valueOf(configMap.get(SysConstants.CNY2JF_RATE));// 1人民币兑换多少积分

            cnyAmount = AmountUtil.mul(amount, rate);
            jfAmount = AmountUtil.mul(cnyAmount, cny2jfRate);

            // 积分余额不够时用剩余积分抵扣
            Account jfAccount = accountBO.getAccountByUser(applyUser,
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(jfAccount.getAmount()) == 1) {
                jfAmount = jfAccount.getAmount();
                cnyAmount = AmountUtil.mul(jfAccount.getAmount(),
                    1.0 / cny2jfRate);
            }

        }
        return new XN629048Res(cnyAmount, jfAmount);
    }

    @Override
    public XN629048Res getOrderDeductAmount(Double maxJfdkRate,
            BigDecimal amount, String applyUser, String isDk) {
        BigDecimal cnyAmount = BigDecimal.ZERO;// 可抵扣多少人民币
        BigDecimal jfAmount = BigDecimal.ZERO;// 需要用多少积分来抵扣

        if (amount.longValue() > 0 && EBoolean.YES.getCode().equals(isDk)) {
            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.PAY_RULE.getCode());

            Double rate = Double
                .valueOf(configMap.get(SysConstants.JF_DK_MAX_RATE));// 订单可用积分抵扣比例

            Double cny2jfRate = Double
                .valueOf(configMap.get(SysConstants.CNY2JF_RATE));// 1人民币兑换多少积分

            cnyAmount = AmountUtil.mul(amount, rate);
            jfAmount = AmountUtil.mul(cnyAmount, cny2jfRate);

            // 积分余额不够时用剩余积分抵扣
            Account jfAccount = accountBO.getAccountByUser(applyUser,
                ECurrency.JF.getCode());
            if (jfAmount.compareTo(jfAccount.getAmount()) == 1) {
                jfAmount = jfAccount.getAmount();
                cnyAmount = AmountUtil.mul(jfAccount.getAmount(),
                    1.0 / cny2jfRate);
            }

            // 最多只能抵扣最大抵扣比例
            BigDecimal maxCnyAmount = amount
                .multiply(new BigDecimal(maxJfdkRate));
            if (cnyAmount.compareTo(maxCnyAmount) == 1) {
                cnyAmount = maxCnyAmount;
                jfAmount = AmountUtil.mul(cnyAmount, cny2jfRate);
            }
        }

        return new XN629048Res(cnyAmount, jfAmount);

    }

    @Override
    public BigDecimal distribution(String code, String ownerId,
            BigDecimal amount, String applyUser, String type,
            XN629048Res resultRes) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        BigDecimal payAmount = amount.subtract(resultRes.getCnyAmount());// 订单支付金额（抵扣积分后）

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
        distributionAgent(code, applyUser, amount, type, mapList, payAmount,
            resultRes);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));// 认养返积分比例

        // 生日购买双倍积分
        UserExt userExt = userExtBO.getUserExt(applyUser);
        if (null != userExt.getBirthday()) {
            if (DateUtil.isToday(userExt.getBirthday())) {
                jfAwardAmount = jfAwardAmount.multiply(new BigDecimal(2));
            }
        }

        Account sysAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        // 积分池不足时将剩余积分转给用户
        if (jfAwardAmount.compareTo(sysAccount.getAmount()) == 1) {
            jfAwardAmount = sysAccount.getAmount();
        }

        // C端用户返积分
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), applyUser, ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.ADOPT_PAY_BACK.getCode(),
            EJourBizTypePlat.ADOPT_PAY_BACK.getCode(),
            EJourBizTypeUser.ADOPT_PAY_BACK.getValue(),
            EJourBizTypePlat.ADOPT_PAY_BACK.getValue(), code);

        return jfAwardAmount;
    }

    // 代理等级分销
    private void distributionAgent(String code, String applyUserId,
            BigDecimal amount, String type, Map<String, String> mapList,
            BigDecimal payAmount, XN629048Res resultRes) {
        // 拿到下单用户的代理商
        User applyUser = userBO.getUser(applyUserId);
        if (StringUtils.isBlank(applyUser.getAgentId())) {
            return;
        }

        String dkNote = getDKNote(resultRes.getJfAmount(),
            resultRes.getCnyAmount());

        // 分成总金额
        BigDecimal agentTotalAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_AGENT_RATE)));
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

        preNote = "三级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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

        preNote = "二级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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

        preNote = "一级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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

        preNote = "二级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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

        preNote = "一级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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

        preNote = "一级代理下级用户" + realName + "消费" + AmountUtil.div(amount, 1000L)
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
