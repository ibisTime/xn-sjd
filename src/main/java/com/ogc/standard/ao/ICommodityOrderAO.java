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
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.dto.res.XN629048Res;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:33:13 
 * @history:
 */
public interface ICommodityOrderAO {

    public String commitCommodityOrder(String applyUser, String applyNote,
            String expressType, Long specsId, Long quantity,
            String addressCode);

    public Object toPayCommodityOrder(XN629721Req req);

    public void cancelCommodityOrder(String code, String updater,
            String remark);

    public void paySuccess(String payGroup);

    public void delive(String code, String logisticsCompany,
            String logisticsNumber, String deliver);

    public void receive(String code, String receiver);

    public XN629048Res getOrderDkAmount(String code);

    public Paginable<CommodityOrder> queryOrderPage(int start, int limit,
            CommodityOrder condition);

    public List<CommodityOrder> queryOrderList(CommodityOrder condition);

    public CommodityOrder getCommodityOrder(String code);

}
