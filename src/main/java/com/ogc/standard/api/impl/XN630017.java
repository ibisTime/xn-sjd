package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSMenuAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.dto.req.XN630017Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 菜单-列表查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:06 
 * @history:
 */
public class XN630017 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN630017Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        // condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        return sysMenuAO.querySYSMenuList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630017Req.class);
        ObjValidater.validateReq(req);
    }
}
