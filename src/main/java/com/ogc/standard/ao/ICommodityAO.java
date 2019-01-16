/**
 * @Title ICommodityAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午1:01:53 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.dto.req.XN629700Req;
import com.ogc.standard.dto.req.XN629701Req;
import com.ogc.standard.dto.res.XN629709Res;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午1:01:53 
 * @history:
 */
public interface ICommodityAO {

    // 新增商品
    public String addCommodity(XN629700Req req);

    // 修改商品
    public void editCommodity(XN629701Req req);

    // 发布
    public void submitCommodity(String code, String updater, String remark);

    // 审核
    public void approveCommodity(String code, String approver,
            String approveResult, String approveNote);

    // 上架
    public void putOn(String code, String location, Long orderNo,
            String updater, String remark);

    // 下架
    public void putOff(String code, String updater, String remark);

    // 月初将销售数量更新为0
    public void doCommodityMonth();

    public Paginable<Commodity> queryCommodityPage(int start, int limit,
            Commodity condition);

    public Commodity getCommodity(String code);

    public List<Commodity> queryCommodityList(Commodity condition);

    public XN629709Res queryPlaceList(String placeType);
}
