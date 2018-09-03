package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuOrder;

public interface ISimuOrderBO extends IPaginableBO<SimuOrder> {

    public boolean isSimuOrderExist(String code);

    public void saveSimuOrder(SimuOrder data);

    public void tradeSuccess(SimuOrder data);

    public int cancel(SimuOrder data);

    public int refreshMarketSimuOrder(SimuOrder data);

    public int refreshLimitSimuOrder(SimuOrder data);

    public List<SimuOrder> querySimuOrderList(SimuOrder condition);

    /**
     * 查询买方盘
     * @return 
     * @create: 2018年8月29日 下午4:58:47 lei
     * @history:
     */
    public List<SimuOrder> queryBidsHandicapList(int handicapQuantity);

    /**
     * 查询卖方盘
     * @return 
     * @create: 2018年8月29日 下午4:58:47 lei
     * @history:
     */
    public List<SimuOrder> queryAsksHandicapList(int handicapQuantity);

    public SimuOrder getSimuOrder(String code);

}
