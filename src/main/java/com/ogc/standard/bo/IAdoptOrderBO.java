package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrder;

public interface IAdoptOrderBO extends IPaginableBO<AdoptOrder> {

    public boolean isAdoptOrderExist(String code);

    public String saveAdoptOrder(AdoptOrder data);

    public int removeAdoptOrder(String code);

    public int refreshAdoptOrder(AdoptOrder data);

    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition);

    public AdoptOrder getAdoptOrder(String code);

    // 取消订单
    public void cancelAdoptOrder(AdoptOrder data);

    // 支付订单
    public void payAdoptOrder(AdoptOrder data);

}
