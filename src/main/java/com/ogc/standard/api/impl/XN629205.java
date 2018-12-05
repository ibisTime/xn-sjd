package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.dto.req.XN629205Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询认养权
 * @author: xieyj 
 * @since: 2018年10月3日 下午9:16:23 
 * @history:
 */
public class XN629205 extends AProcessor {

    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629205Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOrderCode(req.getOrderCode());
        condition.setParentCategoryCode(req.getParentCategoryCode());
        condition.setCategoryCode(req.getCategoryCode());
        condition.setOwnerId(req.getOwnerId());
        condition.setProductCode(req.getProductCode());

        condition.setTreeNumber(req.getTreeNumber());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());
        condition.setCurrentHolder(req.getCurrentHolder());
        condition.setOrderCodeForQuery(req.getOrderCodeForQuery());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAdoptOrderTreeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return adoptOrderTreeAO.queryAdoptOrderTreePage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629205Req.class);
        ObjValidater.validateReq(req);
    }
}
