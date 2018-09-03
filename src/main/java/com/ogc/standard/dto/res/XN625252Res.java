/**
 * @Title XN625252Res.java 
 * @Package com.ogc.standard.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午8:10:40 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.TradeOrder;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月23日 下午8:10:40 
 * @history:
 */
public class XN625252Res {

    // 交易订单详情
    private TradeOrder tradeOrder;

    // 交易订单相关流水
    private List<Jour> jourList;

    public TradeOrder getTradeOrder() {
        return tradeOrder;
    }

    public void setTradeOrder(TradeOrder tradeOrder) {
        this.tradeOrder = tradeOrder;
    }

    public List<Jour> getJourList() {
        return jourList;
    }

    public void setJourList(List<Jour> jourList) {
        this.jourList = jourList;
    }

}
