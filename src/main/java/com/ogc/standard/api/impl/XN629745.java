/**
 * @Title XN629740.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午2:37:12 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPostageTemplateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.PostageTemplate;
import com.ogc.standard.dto.req.XN629745Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查邮费模版
 * @author: taojian 
 * @since: 2018年11月7日 下午2:37:12 
 * @history:
 */
public class XN629745 extends AProcessor {

    private IPostageTemplateAO postageTemplateAO = SpringContextHolder
        .getBean(IPostageTemplateAO.class);

    private XN629745Req req;

    @Override
    public Object doBusiness() throws BizException {
        PostageTemplate condition = new PostageTemplate();
        condition.setShopCode(req.getShopCode());
        condition.setDeliverPlace(req.getReceivePlace());
        condition.setReceiverPlace(req.getReceivePlace());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return postageTemplateAO.queryTemplatePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629745Req.class);
        ObjValidater.validateReq(req);
    }

}
