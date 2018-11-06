/**
 * @Title XN629700.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:05:50 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICommodityAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.dto.req.XN629706Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查商品
 * @author: taojian 
 * @since: 2018年11月6日 上午10:05:50 
 * @history:
 */
public class XN629706 extends AProcessor {

    private ICommodityAO commodityAO = SpringContextHolder
        .getBean(ICommodityAO.class);

    private XN629706Req req;

    @Override
    public Object doBusiness() throws BizException {
        Commodity condition = new Commodity();
        condition.setName(req.getName());
        condition.setParentCategoryCode(req.getParentCategoryCode());
        condition.setCategoryCode(req.getCategoryCode());
        condition.setDeliverPlace(req.getDeliverPlace());
        condition.setWeight(req.getWeight());
        condition.setLogistics(req.getLogistics());
        condition.setShopCode(req.getShopCode());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = "code";
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commodityAO.queryCommodityPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629706Req.class);
        ObjValidater.validateReq(req);
    }

}
