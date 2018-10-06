package com.ogc.standard.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ogc.standard.core.CalculationUtil;

public class AmountUtil {
    public static Long mul(Long amount, double rate) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b).longValue();
    }

    public static BigDecimal mul(BigDecimal amount, double rate) {
        BigDecimal a = amount;
        BigDecimal b = new BigDecimal(Double.toString(rate));
        return a.multiply(b);
    }

    public static BigDecimal mul(BigDecimal amount, Long radix) {
        BigDecimal a = amount;
        BigDecimal b = new BigDecimal(radix);
        return a.multiply(b);
    }

    public static BigDecimal mul(BigDecimal amount, BigDecimal rate) {
        BigDecimal a = amount;
        BigDecimal b = rate;
        return a.multiply(b);
    }

    public static double div(Double amount, Long number) {
        BigDecimal a = new BigDecimal(Double.toString(amount));
        BigDecimal b = new BigDecimal(Double.toString(number));
        return a.divide(b).doubleValue();
    }

    public static BigDecimal div(BigDecimal amount, Long number) {
        BigDecimal b = new BigDecimal(Double.toString(number));
        return amount.divide(b, 2, BigDecimal.ROUND_HALF_UP);
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiUp(Long amount) {
        String amountString = CalculationUtil.diviUp(amount);
        return Long.valueOf(CalculationUtil.multUp(amountString));
    }

    // 保留两位小数，末尾数不管是几，前一位都加1
    public static Long eraseLiDown(Long amount) {
        String amountString = CalculationUtil.diviDown(amount);
        return Long.valueOf(CalculationUtil.multDown(amountString));
    }

    public static String toDisplayAmount(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.DOWN).toPlainString();
    }
}
