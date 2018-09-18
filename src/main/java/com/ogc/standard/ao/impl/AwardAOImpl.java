/**
 * @Title AwardAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午7:21:20 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAwardAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAwardBO;
import com.ogc.standard.bo.IAwardMonthBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Award;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAwardStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ERefType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午7:21:20 
 * @history:
 */
@Service
public class AwardAOImpl implements IAwardAO {

    @Autowired
    private IAwardBO awardBO;

    @Autowired
    private IAwardMonthBO awardMonthBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public void settle(Long id, String isSettle, String remark) {
        // 检验是否存在
        Award data = awardBO.getAward(id);
        // 更新状态
        awardBO.refreshStatus(data, isSettle, remark);
        if (!EAwardStatus.TOHAND.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该奖励已处理");
        }
        if (EBoolean.YES.getCode().equals(isSettle)) {
            // 奖励结算
            Account userAccount = accountBO.getAccountByUser(data.getUserId(),
                data.getCurrency());
            Account platAccount = accountBO.getAccountByUser(
                ESysUser.SYS_USER.getCode(), data.getCurrency());
            if (ERefType.REGIST.getCode().equals(data.getRefType())) {
                accountBO.transAmount(platAccount, userAccount, data.getCount(),
                    EJourBizTypePlat.AJ_AWARD_REG.getCode(),
                    EJourBizTypeUser.AJ_AWARD_REG.getCode(),
                    EJourBizTypePlat.AJ_AWARD_REG.getValue(),
                    EJourBizTypeUser.AJ_AWARD_REG.getValue(), "注册分佣");
            } else if (ERefType.CCTRADE.getCode().equals(data.getRefType())) {
                accountBO.transAmount(platAccount, userAccount, data.getCount(),
                    EJourBizTypePlat.AJ_AWARD_CCORDER.getCode(),
                    EJourBizTypeUser.AJ_AWARD_CCORDER.getCode(),
                    EJourBizTypePlat.AJ_AWARD_CCORDER.getValue(),
                    EJourBizTypeUser.AJ_AWARD_CCORDER.getValue(), "场外交易分佣");
            } else if (ERefType.BBRRADE.getCode().equals(data.getRefType())) {
                accountBO.transAmount(platAccount, userAccount, data.getCount(),
                    EJourBizTypePlat.AJ_AWARD_BBORDER.getCode(),
                    EJourBizTypeUser.AJ_AWARD_BBORDER.getCode(),
                    EJourBizTypePlat.AJ_AWARD_BBORDER.getValue(),
                    EJourBizTypeUser.AJ_AWARD_BBORDER.getValue(), "币币交易分佣");
            } else if (ERefType.SPECIAL.getCode().equals(data.getRefType())) {
                accountBO.transAmount(platAccount, userAccount, data.getCount(),
                    EJourBizTypePlat.AJ_AWARD_SPECIAL.getCode(),
                    EJourBizTypeUser.AJ_AWARD_SPECIAL.getCode(),
                    EJourBizTypePlat.AJ_AWARD_SPECIAL.getValue(),
                    EJourBizTypeUser.AJ_AWARD_SPECIAL.getValue(), "特殊奖励");
            }
        }
        // 记录
        if (!awardMonthBO.isAwardMonthExist(data.getUserId(),
            data.getCurrency())) {
            awardMonthBO.addNewAwardMonth(data.getUserId());
        }
        awardMonthBO.refreshAwardMonthSettle(data.getUserId(), data.getCount(),
            data.getCurrency(), isSettle);
    }

    @Override
    public Paginable<Award> queryAwardPage(int start, int limit,
            Award condition) {
        Paginable<Award> page = awardBO.getPaginable(start, limit, condition);
        for (Award award : page.getList()) {
            award.setUser(userBO.getUser(award.getUserId()));
            award.setRelUser(userBO.getUser(award.getRefCode()));
        }
        return page;
    }

    @Override
    public List<Award> queryAwardList(Award condition) {
        List<Award> awardList = awardBO.queryAwardList(condition);
        return awardList;
    }

    @Override
    public void addSpecialAward(String userId, BigDecimal count,
            String remark) {
        User user = userBO.getUser(userId);
        awardBO.saveSpecialAward(user, count, remark);
    }

}
