package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.req.XN629030Req;

/**
 * 个人/定向/捐赠认养订单
 * @author: jiafr 
 * @since: 2018年9月26日 下午6:31:15 
 * @history:
 */
@Component
public interface IAdoptOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addAdoptOrder(XN629030Req req);

    public int dropAdoptOrder(String code);

    public int editAdoptOrder(AdoptOrder data);

    public Paginable<AdoptOrder> queryAdoptOrderPage(int start, int limit,
            AdoptOrder condition);

    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition);

    public AdoptOrder getAdoptOrder(String code);

    // 取消订单
    public void cancelAdoptOrder(String code);

    // 支付订单
    public void payAdoptOrder(String code);

}
