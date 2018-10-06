/**
 * @Title AlipayAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:56:56 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.WebUtils;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IChargeBO;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.PropertiesUtil;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EChargeStatus;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2017年1月11日 下午8:56:56 
 * @history:
 */
@Service
public class AlipayAOImpl implements IAlipayAO {
    static Logger logger = Logger.getLogger(AlipayAOImpl.class);

    public static final String CHARSET = "utf-8";

    @Autowired
    IChargeBO chargeBO;

    @Autowired
    IAccountBO accountBO;

    @Override
    public boolean doCallback(String result) {
        boolean isSuccess = false;
        // 解析回调结果
        logger.info("**** 支付宝支付回调结果： ****：" + result);
        // 将异步通知中收到的待验证所有参数都存放到map中
        Map<String, String> paramsMap = split(result);
        try {
            // 拿到签名
            // 调用SDK验证签名
            boolean verify_result = false;
            verify_result = AlipaySignature.rsaCheckV1(paramsMap,
                PropertiesUtil.Config.ALIPAY_PUBLICKEY, CHARSET,
                PropertiesUtil.Config.ALIPAY_SIGNTYPE);
            logger.info("验签结果：" + verify_result);
            if (verify_result) {
                // TODO 验签成功后
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                String outTradeNo = paramsMap.get("out_trade_no");
                String totalAmount = paramsMap.get("total_amount");
                String sellerId = paramsMap.get("seller_id");
                String appId = paramsMap.get("app_id");
                String alipayOrderNo = paramsMap.get("trade_no");
                String tradeStatus = paramsMap.get("trade_status");
                // 取到订单信息
                Charge order = chargeBO.getCharge(outTradeNo);
                if (!EChargeStatus.toPay.getCode().equals(order.getStatus())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "充值订单不处于待支付状态，重复回调");
                }
                // 数据正确性校验
                BigDecimal orderAmount = order.getAmount().divide(
                    BigDecimal.TEN);
                if (orderAmount.compareTo(AmountUtil.mul(totalAmount, 100L)) == 0
                        && sellerId
                            .equals(PropertiesUtil.Config.ALIPAY_PROVIDERID)
                        && appId.equals(PropertiesUtil.Config.ALIPAY_APPID)) {
                    if ("TRADE_SUCCESS".equals(tradeStatus)
                            || "TRADE_FINISHED".equals(tradeStatus)) {// 支付成功
                        isSuccess = true;
                        // 更新充值订单状态
                        chargeBO.callBackChange(order, true);
                        // 收款方账户加钱
                        Account account = accountBO.getAccount(order
                            .getAccountNumber());
                        accountBO
                            .changeAmount(account, order.getAmount(),
                                EChannelType.getEChannelType(order
                                    .getChannelType()), alipayOrderNo, order
                                    .getPayGroup(), EJourBizTypeUser
                                    .getBizType(order.getBizType()).getCode(),
                                order.getBizNote());
                        // 托管账户加钱
                        Account sysAccount = accountBO
                            .getAccount(ESystemAccount.SYS_ACOUNT_ALIPAY
                                .getCode());
                        accountBO
                            .changeAmount(sysAccount, order.getAmount(),
                                EChannelType.getEChannelType(order
                                    .getChannelType()), alipayOrderNo, order
                                    .getPayGroup(), EJourBizTypeUser
                                    .getBizType(order.getBizType()).getCode(),
                                order.getBizNote());

                    } else {// 支付失败
                        // 更新充值订单状态
                        chargeBO.callBackChange(order, false);
                    }
                } else {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "数据正确性校验失败，非法回调");
                }
            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "验签失败，默认为非法回调");
            }
        } catch (AlipayApiException e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "支付结果通知验签异常");
        }
        return isSuccess;
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
