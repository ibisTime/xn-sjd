package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.res.XN629048Res;

/**
 * 个人/定向/捐赠认养订单
 * @author: jiafr 
 * @since: 2018年9月26日 下午6:31:15 
 * @history:
 */
public interface IAdoptOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交订单
    public String commitAdoptOrder(String userId, String specsCode,
            Integer quantity);

    // 取消订单
    public void cancelAdoptOrder(String code, String userId, String remark);

    // 支付订单
    public Object toPayAdoptOrder(String code, String payType, String isJfDeduct);

    public Paginable<AdoptOrder> queryAdoptOrderPage(int start, int limit,
            AdoptOrder condition);

    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition);

    public AdoptOrder getAdoptOrder(String code, String isSettle);

    // 获取订单抵扣金额
    public XN629048Res getOrderDkAmount(String code);
}
