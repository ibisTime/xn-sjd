/**
 * @Title ICommodityOrderDetailDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午1:36:45 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CommodityOrderDetail;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午1:36:45 
 * @history:
 */
public interface ICommodityOrderDetailDAO
        extends IBaseDAO<CommodityOrderDetail> {
    String NAMESPACE = ICommodityOrderDetailDAO.class.getName().concat(".");

    // 待评价
    public int updateToCommentByOrder(CommodityOrderDetail data);

    // 待评价
    public int updateToComment(CommodityOrderDetail data);

    // 评价
    public int updateComment(CommodityOrderDetail data);

    // 申请售后
    public int updateToAfterSell(CommodityOrderDetail data);

    // 处理售后
    public int updateHandleAfterSell(CommodityOrderDetail data);

    public int updateStatusByOrder(CommodityOrderDetail data);

    public int updateAfterSaleStatus(CommodityOrderDetail data);

}
