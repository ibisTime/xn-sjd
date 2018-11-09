/**
 * @Title CommodityBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午5:34:33 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICommodityDAO;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.dto.req.XN629700Req;
import com.ogc.standard.dto.req.XN629701Req;
import com.ogc.standard.enums.ECommodityStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月2日 下午5:34:33 
 * @history:
 */
@Component
public class CommodityBOImpl extends PaginableBOImpl<Commodity> implements
        ICommodityBO {
    @Autowired
    private ICommodityDAO commodityDAO;

    @Override
    public String saveCommodity(XN629700Req req) {
        Commodity data = new Commodity();
        String code = OrderNoGenerater.generate(EGeneratePrefix.Commodity
            .getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setParentCategoryCode(req.getParentCategoryCode());
        data.setCategoryCode(req.getCategoryCode());
        data.setDeliverPlace(req.getDeliverPlace());
        data.setWeight(req.getWeight());
        data.setLogistics(req.getLogistics());
        data.setListPic(req.getListPic());
        data.setBannerPic(req.getBannerPic());
        data.setDescription(req.getDescription());
        data.setShopCode(req.getShopCode());
        data.setStatus(ECommodityStatus.DRAFT.getCode());
        commodityDAO.insert(data);
        return code;
    }

    @Override
    public void refreshCommodity(XN629701Req req) {
        Commodity data = getCommodity(req.getCode());
        data.setName(req.getName());
        data.setParentCategoryCode(req.getParentCategoryCode());
        data.setCategoryCode(req.getCategoryCode());
        data.setDeliverPlace(req.getDeliverPlace());
        data.setWeight(req.getWeight());
        data.setLogistics(req.getLogistics());
        data.setListPic(req.getListPic());
        data.setBannerPic(req.getBannerPic());
        data.setDescription(req.getDescription());
        data.setShopCode(req.getShopCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        commodityDAO.updateCommodity(data);
    }

    @Override
    public void refreshStatus(String code, String status, String updater,
            String remark) {
        Commodity data = getCommodity(code);
        data.setStatus(status);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        commodityDAO.updateStatus(data);
    }

    @Override
    public void refreshOn(String code, String location, Long orderNo,
            String updater, String remark) {
        Commodity data = getCommodity(code);
        data.setStatus(ECommodityStatus.ON.getCode());
        data.setLocation(location);
        data.setOrderNo(orderNo);
        data.setMonthSellCount(Long.valueOf(0));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        commodityDAO.updateOn(data);
    }

    @Override
    public List<Commodity> queryCommodityList(Commodity condition) {

        return commodityDAO.selectList(condition);
    }

    @Override
    public Commodity getCommodity(String code) {
        Commodity condition = new Commodity();
        condition.setCode(code);
        Commodity data = commodityDAO.select(condition);
        if (data == null) {
            throw new BizException("xn000000", "不存在该商品");
        }
        return data;
    }

    @Override
    public boolean isOnShelf(String code) {
        Commodity data = getCommodity(code);
        if (ECommodityStatus.ON.getCode().equals(data.getStatus())) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void refreshMonthSellCount(Commodity data, Long quantity) {
        data.setMonthSellCount(quantity);
        commodityDAO.updateMonthSellCount(data);
    }

}
