package com.ogc.standard.pay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.ao.IWeChatAO;
import com.ogc.standard.ao.impl.IAlipayAO;
import com.ogc.standard.dto.res.ChargeRes;
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

    @Autowired
    IGroupAdoptOrderAO groupAdoptOrderAO;

    @Autowired
    ICommodityOrderAO commodityOrderAO;

    @Autowired
    IWeChatAO weChatAO;

    @Autowired
    private IPresellOrderAO presellOrderAO;

    @Autowired
    private IGroupOrderAO groupOrderAO;

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
            System.out
                .println("******************isSuccess:" + paySuccess.isSuccess()
                        + ",biz_code:" + paySuccess.getBizCode() + ",biz_type:"
                        + paySuccess.getBizType() + "******************");
            if (paySuccess.isSuccess() && !EJourBizTypeUser.CHARGE.getCode()
                .equals(paySuccess.getBizCode())) {
                doPayOrder(paySuccess.getBizType(), paySuccess.getBizCode());
            }
            // 通知支付宝我已收到请求，不用再继续回调我了
            out.print("success");
        } catch (Exception e) {
            logger.error("支付回调异常,原因：" + e.getMessage());
        }
    }

    // 微信H5支付回调
    @RequestMapping("/wechat/H5/callback")
    public synchronized void doCallbackWechatH5(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            logger.info("******************微信公众号回调开始******************");

            // 获取回调参数
            PrintWriter out = response.getWriter();
            InputStream inStream = request.getInputStream();
            String result = getReqResult(out, inStream);

            logger.info("**** 公众号支付回调结果 ****：" + result);

            // 解析回调结果并通知业务biz
            ChargeRes chargeRes = weChatAO.doCallbackH5(result);

            // 业务特殊处理
            if (chargeRes.getIsSuccess()) {
                logger.info("orderCode:" + chargeRes.getOrderCode()
                        + ",payCode:" + chargeRes.getWechatOrderNo());
                doPayOrder(chargeRes.getBizType(), chargeRes.getOrderCode());
            }

            // 通知微信服务器(我已收到请求，不用再继续回调我了)
            String noticeStr = setXML("SUCCESS", "");
            out.print(new ByteArrayInputStream(
                noticeStr.getBytes(Charset.forName("UTF-8"))));
            logger.info("******************微信公众号回调结束******************");
        } catch (Exception e) {
            logger.info("******************公众号支付回调异常,原因：" + e.getMessage());
        }
    }

    private String getReqResult(PrintWriter out, InputStream inStream)
            throws IOException {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return new String(outSteam.toByteArray(), "utf-8");
    }

    public String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    public void doPayOrder(String bizType, String bizCode) {
        if (EJourBizTypeUser.ADOPT.getCode().equals(bizType)) {
            adoptOrderAO.paySuccess(bizCode);
        } else if (EJourBizTypeUser.ADOPT_COLLECT.getCode().equals(bizType)) {
            groupAdoptOrderAO.paySuccess(bizCode);
            groupAdoptOrderAO.toFullAdopt();
        } else if (EJourBizTypeUser.PRESELL.getCode().equals(bizType)) {
            presellOrderAO.paySuccess(bizCode);
            presellOrderAO.assignPresellInventory(bizCode);
        } else if (EJourBizTypeUser.CONSIGN_SELL.getCode().equals(bizType)) {
            groupOrderAO.paySuccess(bizCode);
        } else if (EJourBizTypeUser.COMMODITY.getCode().equals(bizType)) {
            commodityOrderAO.paySuccess(bizCode);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "业务类型订单不存在");
        }
    }
}
