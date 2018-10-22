package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Settle;

/**
 * 结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:33:22 PM 
 * @history:
 */
public interface ISettleBO extends IPaginableBO<Settle> {

    // 添加结算订单
    public String saveSettle(AgentUser user, BigDecimal settleAmount,
            BigDecimal settleRate, String refCode, String refType,
            String refNote);

    // 更新参考订单下的状态
    public void refreshStatusByRefCode(String refCode, String approveResult,
            String handler, String handleNote);

    public List<Settle> querySettleList(Settle condition);

    public List<Settle> querySettleList(String refCode);

    public Settle getSettle(String code);

    // 查询结算总额
    public BigDecimal getTotalAmount(String userId, String status,
            Date createStartDatetime, Date createEndDatetime);

}
