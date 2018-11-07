/**
 * @Title XN629760.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午5:29:25 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Keyword;
import com.ogc.standard.dto.req.XN629765Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查关键字
 * @author: taojian 
 * @since: 2018年11月7日 下午5:29:25 
 * @history:
 */
public class XN629765 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN629765Req req;

    @Override
    public Object doBusiness() throws BizException {
        Keyword condition = new Keyword();
        condition.setWord(req.getWord());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = "id";
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return keywordAO.queryKeywordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629765Req.class);
        ObjValidater.validateReq(req);
    }

}
