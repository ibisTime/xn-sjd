package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.dto.req.XN629035Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询古树
 * @author: silver 
 * @since: 2018年9月27日 下午8:16:42 
 * @history:
 */
public class XN629035 extends AProcessor {

    private ITreeAO treeAO = SpringContextHolder.getBean(ITreeAO.class);

    private XN629035Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Tree condition = new Tree();
        condition.setTreeNumber(req.getTreeNumber());
        condition.setOwnerId(req.getOwnerId());
        condition.setMaintainId(req.getMaintainId());
        condition.setProductCode(req.getProductCode());

        condition.setScientificName(req.getScientificName());
        condition.setStatus(req.getStatus());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ITreeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return treeAO.queryTreePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629035Req.class);
        ObjValidater.validateReq(req);
    }
}
