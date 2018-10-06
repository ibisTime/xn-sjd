/**
 * @Title UserExtAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 上午12:37:00 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IUserExtAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.IUserExtBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.UserExt;
import com.ogc.standard.dto.req.XN805085Req;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: dl 
 * @since: 2018年8月22日 上午12:37:00 
 * @history:
 */
@Service
public class UserExtAOImpl implements IUserExtAO {
    @Autowired
    private IUserExtBO userExtBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public String editUserExt(XN805085Req req) {
        UserExt data = new UserExt();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        data.setUserId(req.getUserId());
        data.setBirthday(req.getBirthday());
        data.setDiploma(req.getDiploma());
        data.setEmail(req.getEmail());
        data.setGender(req.getGender());
        try {
            data.setGradDatetime(format.parse(req.getGradDatetime()));
        } catch (ParseException e) {
            throw new BizException("3", "毕业日期格式不对");
        }
        data.setIntroduce(req.getIntroduce());
        data.setOccupation(req.getOccupation());
        data.setPdf(req.getPdf());
        userExtBO.refreshUserExt(data);

        // 添加积分
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.JF_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.COMPLETE_INFO));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userJfAccount = accountBO.getAccountByUser(req.getUserId(),
            ECurrency.JF.getCode());
        Account sysJfAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());

        if (quantity.compareTo(sysJfAccount.getAmount()) != 1) {
            accountBO.transAmount(sysJfAccount, userJfAccount, quantity,
                EJourBizTypeUser.COMPLETE_INFO.getCode(),
                EJourBizTypePlat.COMPLETE_INFO.getCode(),
                EJourBizTypeUser.COMPLETE_INFO.getValue(),
                EJourBizTypePlat.COMPLETE_INFO.getValue(), req.getUserId());
        }

        return req.getUserId();
    }

    @Override
    public void bindEmail(String captcha, String email, String userId) {
        smsOutBO.checkCaptcha(email, captcha, "805086");
        UserExt data = userExtBO.getUserExt(userId);
        if (data == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户不存在");
        }
        if (data.getEmail() != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户已绑定邮箱");
        }
        userExtBO.isEmailExit(email);
        data.setEmail(email);
        userExtBO.refreshEmail(data);
    }

}
