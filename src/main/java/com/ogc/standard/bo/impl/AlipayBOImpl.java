/**
 * @Title AlipayAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:56:56 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.internal.util.WebUtils;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.PropertiesUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import com.ogc.standard.pay.alipay.config.AlipayConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2017年1月11日 下午8:56:56 
 * @history:
 */
@Service
public class AlipayBOImpl implements IAlipayBO {
    static Logger logger = Logger.getLogger(AlipayBOImpl.class);

    public static final String CHARSET = "utf-8";

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public String getSignedOrder(String applyUser, String toUser,
            String payGroup, String bizType, String bizNote,
            BigDecimal transAmount) {
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "发生金额为零，不能使用支付宝支付");
        }
        // 获取收款方账户信息
        Account toAccount = accountBO.getAccountByUser(toUser,
            ECurrency.CNY.getCode());

        // 落地此次付款的订单信息
        String orderCode = chargeBO.applyOrderOnline(toAccount, payGroup,
            bizType, bizNote, transAmount, EChannelType.Alipay, applyUser);

        // 商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = orderCode;
        // 订单名称，必填
        String subject = bizNote;
        // 付款金额，必填
        String total_amount = String
            .valueOf(AmountUtil.div(transAmount, 1000L));
        // 商品描述，可空
        String body = bizNote;

        // 超时时间 可空
        String timeout_express = "1m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_PAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        // 调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL,
            AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
            AlipayConfig.FORMAT, AlipayConfig.CHARSET,
            AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        ExtendParams eParams = new ExtendParams();
        eParams.setSysServiceProviderId(AlipayConfig.PROVIDER_ID);
        model.setExtendParams(eParams);
        alipay_request.setBizModel(model);

        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.RETURN_URL);

        String form = null;
        try {
            form = client.sdkExecute(alipay_request).getBody();
            form = PropertiesUtil.Config.ALIPAY_GATEWAY.concat("?")
                .concat(form);
            // form = client.pageExecute(alipay_request).getBody();
            // logger.info("*********h5支付表单唤醒参数：" + form + "*********");
        } catch (AlipayApiException e) {
            logger.error("支付宝支付唤起异常:" + e.getMessage());
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "支付宝支付异常，请联系客服");
        }
        return form;
    }

    @Override
    public void doCallbackH5(String result) {
        // 解析回调结果
        logger.info("**** 支付回调结果： ****：" + result);
        // 将异步通知中收到的待验证所有参数都存放到map中
        Map<String, String> paramsMap = split(result);
        // 获取支付宝配置参数
        String passback = paramsMap.get("passback_params");
        String[] codes = passback.split("\\|\\|");
        String systemCode = codes[0];
        String companyCode = codes[1];
    }

    private Map<String, String> split(String urlparam) {
        Map<String, String> map = new HashMap<String, String>();
        String[] param = urlparam.split("&");
        for (String keyvalue : param) {
            String[] pair = keyvalue.split("=");
            if (pair.length == 2) {
                map.put(pair[0], WebUtils.decode(pair[1]));
            }
        }
        return map;
    }

}
