/**
 * @Title AwardBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午5:05:07 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAwardBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.dao.IAwardDAO;
import com.ogc.standard.domain.Award;
import com.ogc.standard.enums.EAwardStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECoin;
import com.ogc.standard.enums.ECoinType;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
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
            data.setAmount(
                sysConfigBO.getBigDecimalValue(SysConstants.DUSER_REG));
        } else {
            data.setAmount(
                sysConfigBO.getBigDecimalValue(SysConstants.CUSER_REG));
        }
        awardDAO.insert(data);
    }

    @Override
    public void refreshStatus(Award data, String isSettle, String remark) {
        if (isSettle.equals(EBoolean.YES.getCode())) {
            data.setStatus(EAwardStatus.HANDLE.getCode());
            accountBO.transAmount("SYS_USER", ECoin.X.getCode(),
                data.getUserId(), ECoin.X.getCode(), data.getAmount(),
                EJourBizTypePlat.AJ_INVITE.getCode(),
                EJourBizTypeUser.AJ_INVITE.getCode(), "渠道商分成支出", "推荐注册分佣",
                data.getRefCode());
        } else {
            data.setStatus(EAwardStatus.NOHANDLE.getCode());
        }
        data.setHandleDatetime(new Date());
        data.setRemark(remark);
        awardDAO.updateStauts(data);
    }

    @Override
    public List<Award> queryAwardList(Award condition) {
        return awardDAO.selectList(condition);
    }

}
