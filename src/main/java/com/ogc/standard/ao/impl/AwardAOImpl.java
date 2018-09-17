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
import com.ogc.standard.domain.Award;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAwardStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ERefType;
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
            // 奖励落地
            if (ERefType.REGIST.getCode().equals(data.getRefType())) {
//                accountBO.transAmount(ESysUser.SYS_USER.getCode(),
//                    ECoin.X.getCode(), data.getUserId(), ECoin.X.getCode(),
//                    data.getCount(), EJourBizTypePlat.AJ_REG.getCode(),
//                    EJourBizTypeUser.AJ_REG.getCode(), "渠道商分成支出", "推荐注册分佣",
//                    data.getRefCode());
            } else if (ERefType.TRADE.getCode().equals(data.getRefType())) {

            } else if (ERefType.SPECIAL.getCode().equals(data.getRefType())) {

            }
        }
        awardMonthBO.refreshAwardMonthSettle(data.getUserId(), data.getCount(),
            isSettle);

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
