/**
 * @Title ICommodityOrderAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:33:13 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.dto.req.XN629714Req;
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.dto.res.XN629048Res;
import com.ogc.standard.dto.res.XN629801Res;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:33:13 
 * @history:
 */
public interface ICommodityOrderAO {

    // 提交订单
    public String commitCommodityOrder(String applyUser, String applyNote,
            String expressType, Long specsId, Long quantity,
            String addressCode);

    // 支付
    public Object toPayCommodityOrder(XN629721Req req);

    // 组合支付
    public Object toPayGroupCommodityOrder(XN629714Req req);

    // 取消
    public void cancelCommodityOrder(String code, String updater,
            String remark);

    // 三方回调
    public void paySuccess(String payGroup);

    // 编辑收货地址
    public void editAddress(String code, String addressCode);

    // 发货
    public void delive(String code, String logisticsCompany,
            String logisticsNumber, String deliver);

    // 收货
    public void receive(String code, String receiver);

    // 获取订单抵扣金额
    public XN629048Res getOrderDkAmount(String code);

    // 获取组合抵扣金额
    public XN629048Res getGroupOrderDkAmount(String code);

    // 邮费
    public XN629801Res getPostage(List<String> commodityCodeList,
            String addressCode);

    public Paginable<CommodityOrder> queryOrderPage(int start, int limit,
            CommodityOrder condition);

    public List<CommodityOrder> queryOrderList(CommodityOrder condition);

    public CommodityOrder getCommodityOrder(String code, String isSettle);

}
