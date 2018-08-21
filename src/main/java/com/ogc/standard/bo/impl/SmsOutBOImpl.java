package com.ogc.standard.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISmsOutBO;
import com.ogc.standard.dto.req.XN804080Req;
import com.ogc.standard.dto.req.XN804081Req;
import com.ogc.standard.dto.req.XN804082Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.enums.ESystemCode;
import com.ogc.standard.http.BizConnecter;
import com.ogc.standard.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendSmsCaptcha(String mobile, String bizType) {
        try {
            XN804081Req req = new XN804081Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            req.setSystemCode(ESystemCode.BZ.getCode());
            BizConnecter.getBizData("804081", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用内陆短信发送服务异常");
        }
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType,
            String systemCode) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setSystemCode(ESystemCode.BZ.getCode());
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType,
            String companyCode, String systemCode) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setCompanyCode(companyCode);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType,
            String companyCode, String systemCode) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }
}
