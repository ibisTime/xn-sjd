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
import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IAgentUserBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.IDistributionOrderBO;
import com.ogc.standard.bo.IProductBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAgentUserLevel;
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
    private IProductBO productBO;

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

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
    public BigDecimal distribution(AdoptOrder data) {
        Map<String, String> mapList = sysConfigBO.getConfigsMap();
        Product product = productBO.getProduct(data.getProductCode());

        // 产权方分销
        BigDecimal ownerDeductAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_OWENER_RATE)));
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
            BigDecimal maintainDeductAmount = data.getAmount().multiply(
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
        distributionAgent(data, mapList);

        // 用户同等金额积分奖励
        BigDecimal cnyAwardAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_USER_BACK_JF_RATE)));// 人民币比例金额

        // 人民币转积分
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.PAY_RULE.getCode());
        BigDecimal rate = new BigDecimal(
            configMap.get(SysConstants.CNY2JF_RATE));
        BigDecimal jfAwardAmount = cnyAwardAmount.multiply(rate);
        Account sysAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        if (sysAccount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {// 系统账户没钱
            jfAwardAmount = BigDecimal.ZERO;
        } else {
            if (sysAccount.getAmount().compareTo(jfAwardAmount) < 0) {// 系统账户钱不够付
                jfAwardAmount = sysAccount.getAmount();
            }
        }

        accountBO.transAmount(ESysUser.SYS_USER.getCode(),
            ECurrency.JF.getCode(), data.getApplyUser(), ECurrency.JF.getCode(),
            jfAwardAmount, EJourBizTypeUser.ADOPT.getCode(),
            EJourBizTypePlat.ADOPT.getCode(), EJourBizTypeUser.ADOPT.getValue(),
            EJourBizTypePlat.ADOPT.getValue(), data.getCode());
        return jfAwardAmount;
    }

    // 代理等级分销
    private void distributionAgent(AdoptOrder data,
            Map<String, String> mapList) {
        // 拿到下单用户的代理商(下单用户一定有代理商)
        User applyUser = userBO.getUser(data.getApplyUser());
        if (StringUtils.isBlank(applyUser.getAgentId())) {
            return;
            // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
            // "代理用户不存在");
        }

        // 分成总金额
        BigDecimal agentTotalAmount = data.getAmount().multiply(
            new BigDecimal(mapList.get(SysConstants.DIST_AGENT_RATE)));
        AgentUser agentUser = agentUserBO.getAgentUser(applyUser.getAgentId());
        if (EAgentUserLevel.FOUR.getCode().equals(agentUser.getLevel())) {
            agent4Back(data, applyUser, agentUser, agentTotalAmount, mapList);
        } else if (EAgentUserLevel.THREE.getCode()
            .equals(agentUser.getLevel())) {
            agent3Back(data, applyUser, agentUser, agentTotalAmount, mapList);
        } else if (EAgentUserLevel.SECOND.getCode()
            .equals(agentUser.getLevel())) {
            agent2Back(data, applyUser, agentUser, agentTotalAmount, mapList);
        } else if (EAgentUserLevel.FIRST.getCode()
            .equals(agentUser.getLevel())) {
            agent1Back(data, applyUser, agentUser, agentTotalAmount, mapList);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "代理等级不存在");
        }
    }

    private void agent4Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList) {
        String preNote = applyUser.getMobile() + "消费"
                + AmountUtil.div(data.getAmount(), 1000L);
        // 四级代理
        BigDecimal level4Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT4_RATE));
        BigDecimal settle4Amount = agentTotalAmount.multiply(level4Rate);

        settleBO.saveSettle(agentUser, settle4Amount, level4Rate,
            data.getCode(), data.getType(), preNote + ",四级代理分成");

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
        settleBO.saveSettle(agentUser, settle3Amount, level3Rate,
            data.getCode(), data.getType(), preNote + ",三级代理分成");

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
        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote + ",二级代理分成");

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
        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote + ",一级代理分成");
    }

    private void agent3Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList) {
        String preNote = applyUser.getMobile() + "消费"
                + AmountUtil.div(data.getAmount(), 1000L);

        // 三级代理
        BigDecimal level3Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT3_RATE));
        BigDecimal settle3Amount = agentTotalAmount.multiply(level3Rate);
        settleBO.saveSettle(agentUser, settle3Amount, level3Rate,
            data.getCode(), data.getType(), preNote + ",三级代理分成");

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
        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote + ",二级代理分成");

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
        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote + ",一级代理分成");
    }

    private void agent2Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList) {
        String preNote = applyUser.getMobile() + "消费"
                + AmountUtil.div(data.getAmount(), 1000L);

        // 二级代理
        BigDecimal level2Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT2_RATE));
        BigDecimal settle2Amount = agentTotalAmount.multiply(level2Rate);
        settleBO.saveSettle(agentUser, settle2Amount, level2Rate,
            data.getCode(), data.getType(), preNote + ",二级代理分成");

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
        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote + ",一级代理分成");
    }

    private void agent1Back(AdoptOrder data, User applyUser,
            AgentUser agentUser, BigDecimal agentTotalAmount,
            Map<String, String> mapList) {
        String preNote = applyUser.getMobile() + "消费"
                + AmountUtil.div(data.getAmount(), 1000L);

        // 一级代理
        BigDecimal level1Rate = new BigDecimal(
            mapList.get(SysConstants.DIST_AGENT1_RATE));
        BigDecimal settle1Amount = agentTotalAmount.multiply(level1Rate);
        settleBO.saveSettle(agentUser, settle1Amount, level1Rate,
            data.getCode(), data.getType(), preNote + ",一级代理分成");
    }
}
