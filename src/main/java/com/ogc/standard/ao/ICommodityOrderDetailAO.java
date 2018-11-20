/**
 * @Title ICommodityOrderDetailAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:47:41 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CommodityOrderDetail;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:47:41 
 * @history:
 */
public interface ICommodityOrderDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 评价
    public String comment(String code, String userId, String content);

    public Paginable<CommodityOrderDetail> queryDetailPage(int start, int limit,
            CommodityOrderDetail condition);

    public List<CommodityOrderDetail> queryDetailList(
            CommodityOrderDetail condition);

    public CommodityOrderDetail getCommodityOrderDetail(String code);
}
