package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Settle;

/**
 * 结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:33:22 PM 
 * @history:
 */
public interface ISettleBO extends IPaginableBO<Settle> {

    // 添加结算订单
    public String saveSettle(String userId, String userKind, String refType,
            String refCode, String refNote);

    // 更新参考订单下的状态
    public void refreshStatusByRef(String refCode, String status,
            String handleNote);

    public List<Settle> querySettleList(Settle condition);

    public Settle getSettle(String code);

}
