package com.ogc.standard.pay;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.ao.impl.IAlipayAO;
import com.ogc.standard.dto.res.PaySuccessRes;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConroller {

    private static Logger logger = Logger.getLogger(CallbackConroller.class);

    @Autowired
    IAlipayAO alipayAO;

    @Autowired
    IAdoptOrderAO adoptOrderAO;

    // 支付宝支付回调
    @RequestMapping("/alipay/callback")
    public synchronized void doCallbackAlipayAPP(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            logger.info("******************支付宝回调开始******************");
            System.out.println("******************支付宝回调开始******************");
            // 获取支付宝回调的参数
            PrintWriter out = response.getWriter();
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");
            PaySuccessRes paySuccess = alipayAO.doCallback(result);
            System.out.println("******************isSuccess:"
                    + paySuccess.isSuccess() + ",biz_code:"
                    + paySuccess.getBizCode() + ",biz_type:"
                    + paySuccess.getBizType() + "******************");
            if (paySuccess.isSuccess()
                    && !EJourBizTypeUser.CHARGE.getCode().equals(
                        paySuccess.getBizCode())) {
                doPayOrder(paySuccess.getBizType(), paySuccess.getBizCode());
            }
            // 通知支付宝我已收到请求，不用再继续回调我了
            out.print("success");
        } catch (Exception e) {
            logger.error("支付回调异常,原因：" + e.getMessage());
        }
    }

    public void doPayOrder(String bizType, String bizCode) {
        if (EJourBizTypeUser.ADOPT.getCode().equals(bizType)) {
            adoptOrderAO.paySuccess(bizCode);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "业务类型订单不存在");
        }
    }
}
