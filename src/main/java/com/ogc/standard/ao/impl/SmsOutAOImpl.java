package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISmsOutAO;
import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.ECaptchaType;
import com.ogc.standard.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendSmsCaptcha(String mobile, String bizType) {
        if (ECaptchaType.C_REG.getCode().equals(bizType)) {
            userBO.isMobileExist(mobile);
        }
        smsOutBO.sendSmsCaptcha(mobile, bizType);
    }

    @Override
    public void sendEmailCaptcha(String email, String bizType) {
        if ("805043".equals(bizType)) {
            User condition = new User();
            condition.setEmail(email);
            if (userBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "邮箱已经被使用");
            }
        }
        smsOutBO.sendEmailCaptcha(email, bizType);
    }
}
