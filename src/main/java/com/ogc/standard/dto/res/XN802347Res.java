/**
 * @Title XN802707Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午2:47:23 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.Collect;
import com.ogc.standard.domain.EthTransaction;
import com.ogc.standard.domain.Jour;

public class XN802347Res {

    // 充值订单详情
    private Charge charge;

    // 充值关联归集订单详情
    private Collect collect;

    // 充值订单相关流水
    private List<Jour> jourList;

    // ETH充值相关广播记录
    private List<EthTransaction> ethTransList;

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Collect getCollect() {
        return collect;
    }

    public void setCollect(Collect collect) {
        this.collect = collect;
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
