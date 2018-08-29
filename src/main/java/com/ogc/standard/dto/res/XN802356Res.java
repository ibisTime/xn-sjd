/**
 * @Title XN802758Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午7:44:55 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.EthTransaction;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.domain.Withdraw;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月23日 下午7:44:55 
 * @history:
 */
public class XN802356Res {

    // 取现订单详情
    private Withdraw withdraw;

    // 取现订单相关流水
    private List<Jour> jourList;

    // ETH相关广播记录
    private List<EthTransaction> ethTransList;

    public Withdraw getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Withdraw withdraw) {
        this.withdraw = withdraw;
    }

    public List<Jour> getJourList() {
        return jourList;
    }

    public void setJourList(List<Jour> jourList) {
        this.jourList = jourList;
    }

    public List<EthTransaction> getEthTransList() {
        return ethTransList;
    }

    public void setEthTransList(List<EthTransaction> ethTransList) {
        this.ethTransList = ethTransList;
    }

}
