/**
 * @Title AwardBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午5:05:07 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAwardBO;
import com.ogc.standard.bo.IAwardMonthBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.dao.IAwardDAO;
import com.ogc.standard.domain.Award;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAwardStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECoinType;
import com.ogc.standard.enums.ERefType;
import com.ogc.standard.enums.EUserKind;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午5:05:07 
 * @history:
 */
@Component
public class AwardBOImpl extends PaginableBOImpl<Award> implements IAwardBO {

    @Autowired
    private IAwardDAO awardDAO;

    @Autowired
    private IAwardMonthBO awardMonthBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public Award getAward(Long id) {
        Award condition = new Award();
        Award award = awardDAO.select(condition);
        if (null == award) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不存在该奖励");
        }
        return award;
    }

    @Override
    public void saveTradeAward(String refereeUserId, String userKind,
            String refCode, String refNote, BigDecimal tradeCount) {
        Award data = new Award();
        data.setUserId(refereeUserId);
        data.setUserKind(userKind);
        data.setRefCode(refCode);
        data.setRefNote(refNote);
        data.setCurrency(ECoinType.X.getCode());
        data.setRefType(ERefType.TRADE.getCode());
        data.setCreateDatetime(new Date());
        data.setStatus(EAwardStatus.TOHAND.getCode());
        if (userKind.equals(EUserKind.QDS.getCode())) {
            BigDecimal rate = sysConfigBO
                .getBigDecimalValue(SysConstants.REFEREE_DUSER_FEE_RATE);
            data.setRate(rate);
            data.setCount(rate.multiply(tradeCount));
        } else {
            BigDecimal rate = sysConfigBO
                .getBigDecimalValue(SysConstants.REFEREE_CUSER_FEE_RATE);
            data.setRate(rate);
            data.setCount(rate.multiply(tradeCount));
        }
        data.setRemark("推荐用户交易分成");
        awardDAO.insert(data);
        // 记录
        if (awardMonthBO.isAwardMonthExist(new Date())) {
            awardMonthBO.refreshAwardMonthUnsettle(data.getUserId(),
                data.getCount());
        } else {
            awardMonthBO.addAwardMonth(data, refNote);
        }
    }

    @Override
    public void saveRegistAward(String userId, String userKind, String refCode,
            String refNote) {
        Award data = new Award();
        data.setUserId(userId);
        data.setUserKind(userKind);
        data.setRefCode(refCode);
        data.setRefNote(refNote);
        data.setCurrency(ECoinType.X.getCode());
        data.setRefType(ERefType.REGIST.getCode());
        data.setCreateDatetime(new Date());
        data.setStatus(EAwardStatus.TOHAND.getCode());
        if (userKind.equals(EUserKind.QDS.getCode())) {
            data.setCount(
                sysConfigBO.getBigDecimalValue(SysConstants.DUSER_REG));
        } else {
            data.setCount(
                sysConfigBO.getBigDecimalValue(SysConstants.CUSER_REG));
        }
        data.setRemark("推荐注册分成");
        awardDAO.insert(data);
        // 记录
        if (awardMonthBO.isAwardMonthExist(new Date())) {
            awardMonthBO.refreshAwardMonthUnsettle(data.getUserId(),
                data.getCount());
        } else {
            awardMonthBO.addAwardMonth(data, refNote);
        }
    }

    @Override
    public void refreshStatus(Award data, String isSettle, String remark) {
        if (isSettle.equals(EBoolean.YES.getCode())) {
            data.setStatus(EAwardStatus.HANDLE.getCode());
        } else {
            data.setStatus(EAwardStatus.NOHANDLE.getCode());
        }
        data.setHandleDatetime(new Date());
        data.setHandleNote(remark);
        awardDAO.updateStauts(data);
        // 记录
        if (awardMonthBO.isAwardMonthExist(new Date())) {
            awardMonthBO.refreshAwardMonthUnsettle(data.getUserId(),
                data.getCount());
        } else {
            awardMonthBO.addAwardMonth(data, remark);
        }
    }

    @Override
    public List<Award> queryAwardList(Award condition) {
        return awardDAO.selectList(condition);
    }

    @Override
    public void saveSpecialAward(User user, BigDecimal count, String remark) {
        Award data = new Award();
        data.setUserId(user.getUserId());
        data.setUserKind(user.getKind());
//        data.setRefCode(refCode);
//        data.setRefNote(refNote);
        data.setCurrency(ECoinType.X.getCode());
        data.setRefType(ERefType.SPECIAL.getCode());
        data.setCreateDatetime(new Date());
        data.setStatus(EAwardStatus.TOHAND.getCode());
        data.setCount(count);
        data.setRemark(remark);
        awardDAO.insert(data);
        // 记录
        if (awardMonthBO.isAwardMonthExist(new Date())) {
            awardMonthBO.refreshAwardMonthUnsettle(data.getUserId(),
                data.getCount());
        } else {
            awardMonthBO.addAwardMonth(data, remark);
        }
    }

}
