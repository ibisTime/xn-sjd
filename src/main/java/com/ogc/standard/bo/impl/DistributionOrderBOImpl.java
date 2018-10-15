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
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.enums.EAgentUserLevel;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypeMaintain;
import com.ogc.standard.enums.EJourBizTypeOwner;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
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
    private IProductBO productBO;

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

    @Override
    public BigDecimal distribution(AdoptOrder data, XN629048Res resultRes) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        Product product = productBO.getProduct(data.getProductCode());
        BigDecimal payAmount = data.getAmount()
            .subtract(resultRes.getCnyAmount());// 订单支付金额（抵扣积分后）

        // 产权方分销
        BigDecimal ownerDeductAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_OWENER_RATE)));// 产权方分销金额
        accountBO.transAmount(ESysUser.SYS_USER.getCode(), product.getOwnerId(),
            ECurrency.CNY.getCode(), ownerDeductAmount,
            EJourBizTypePlat.ADOPT_DIST.getCode(),
            EJourBizTypeOwner.OWNER_PROFIT.getCode(),
            EJourBizTypePlat.ADOPT_DIST.getValue(),
            EJourBizTypeOwner.OWNER_PROFIT.getValue(), data.getCode());

        // 判断养护方是否存在，没有则平台回收
        String maintainId = applyBindMaintainBO
            .getMaintainId(product.getOwnerId());
        if (StringUtils.isNotBlank(maintainId)) {
            BigDecimal maintainDeductAmount = payAmount.multiply(
                new BigDecimal(mapList.get(SysConstants.DIST_MAINTAIN_RATE)));
            accountBO.transAmount(ESysUser.SYS_USER.getCode(), maintainId,
                ECurrency.CNY.getCode(), maintainDeductAmount,
                EJourBizTypePlat.ADOPT_DIST.getCode(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode(),
                EJourBizTypePlat.ADOPT_DIST.getValue(),
                EJourBizTypeMaintain.MAINTAIN_DEDECT.getValue(),
                data.getCode());
        }

        // 代理方分销
        distributionAgent(data, mapList, payAmount, resultRes);

        // 用户同等金额积分奖励
        BigDecimal jfAwardAmount = payAmount.multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));// 认养返积分比例

        Account sysAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        // 积分池不足时将剩余积分转给用户
        if (jfAwardAmount.compareTo(sysAccount.getAmount()) == 1) {
            jfAwardAmount = sysAccount.getAmount();
        }

        // C端用户返积分
        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), data.getApplyUser(), ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.ADOPT.getCode(),
            EJourBizTypePlat.ADOPT.getCode(), EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypePlat.ADOPT.getValue(), data.getCode());

        return jfAwardAmount;
    }

    // 代理等级分销
    private void distributionAgent(AdoptOrder data, Map<String, String> mapList,
            BigDecimal payAmount, XN629048Res resultRes) {
        // 拿到下单用户的代理商
        User applyUser = userBO.getUser(data.getApplyUser());
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
            agent4Back(data, applyUser, agentUser, agentTotalAmount, mapList,
                dkNote);
        } else if (EAgentUserLevel.THREE.getCode()
            .equals(agentUser.getLevel())) {
            agent3Back(data, applyUser, agentUser, agentTotalAmount, mapList,
                dkNote);
        } else if (EAgentUserLevel.SECOND.getCode()
            .equals(agentUser.getLevel())) {
            agent2Back(data, applyUser, agentUser, agentTotalAmount, mapList,
                dkNote);
        } else if (EAgentUserLevel.FIRST.getCode()
            .equals(agentUser.getLevel())) {
            agent1Back(data, applyUser, agentUser, agentTotalAmount, mapList,
                dkNote);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理等级不存在");
        }
    }

    private void agent4Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 四级代理
        BigDecimal level4Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT4_RATE));
        BigDecimal settle4Amount = agentTotalAmount.multiply(level4Rate);

        String preNote = "四级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle4Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle4Amount, level4Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "三级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle3Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle3Amount, level3Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "二级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "一级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote);
    }

    private void agent3Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 三级代理
        BigDecimal level3Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT3_RATE));
        BigDecimal settle3Amount = agentTotalAmount.multiply(level3Rate);

        String preNote = "三级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle3Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle3Amount, level3Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "二级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "一级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote);
    }

    private void agent2Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        String realName = getRealName(applyUser);

        // 二级代理
        BigDecimal level2Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT2_RATE));
        BigDecimal settle2Amount = agentTotalAmount.multiply(level2Rate);

        String preNote = "二级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle2Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote);

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

        preNote = "一级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote);
    }

    private void agent1Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList, String dkNote) {
        // 一级代理
        BigDecimal level1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);

        String realName = getRealName(applyUser);
        String preNote = "一级代理下级用户" + realName + "消费"
                + AmountUtil.div(data.getAmount(), 1000L) + ",则用于级差是"
                + AmountUtil.div(settle1Amount, 1000L) + "。";
        if (StringUtils.isNotBlank(dkNote)) {
            preNote = preNote + "(" + dkNote + ")";
        }

        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote);
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
