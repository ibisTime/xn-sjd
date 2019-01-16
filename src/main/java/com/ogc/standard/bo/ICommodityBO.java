/**
 * @Title ICommodityBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午4:35:51 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.dto.req.XN629700Req;
import com.ogc.standard.dto.req.XN629701Req;

/** 
 * @author: taojian 
 * @since: 2018年11月2日 下午4:35:51 
 * @history:
 */
public interface ICommodityBO extends IPaginableBO<Commodity> {
    public String saveCommodity(XN629700Req req);

    public void refreshCommodity(XN629701Req req);

    public void refreshStatus(String code, String status, String updater,
            String remark);

    public void refreshOn(String code, String location, Long orderNo,
            String updater, String remark);

    public List<Commodity> queryDeliverPlaceList(List<String> codeList);

    public List<Commodity> queryOriginalPlaceList();

    public List<Commodity> queryCommodityList(Commodity condition);

    public Commodity getCommodity(String code);

    public boolean isOnShelf(String code);

    public void refreshMonthSellCount(Commodity data, Long quantity);

}
