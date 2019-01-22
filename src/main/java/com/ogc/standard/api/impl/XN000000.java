package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.ao.IPresellSpecsAO;
import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.bo.IAlipayBO;
import com.ogc.standard.bo.IWechatBO;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.dto.req.XN000000Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.pay.CallbackConroller;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 测试接口
 * @author: silver 
 * @since: Oct 10, 2018 4:47:04 PM 
 * @history:
 */
public class XN000000 extends AProcessor {
    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private IOriginalGroupAO originalGroupAO = SpringContextHolder
        .getBean(IOriginalGroupAO.class);

    private IPresellSpecsAO presellSpecsAO = SpringContextHolder
        .getBean(IPresellSpecsAO.class);

    private IPresellOrderAO presellOrderAO = SpringContextHolder
        .getBean(IPresellOrderAO.class);

    private CallbackConroller callbackConroller = SpringContextHolder
        .getBean(CallbackConroller.class);

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private IAlipayBO alipayBO = SpringContextHolder.getBean(IAlipayBO.class);

    private IWechatBO wechatBO = SpringContextHolder.getBean(IWechatBO.class);

    private XN000000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        adoptOrderTreeAO.doDailyAdoptOrderTree();
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN000000Req.class);
    }
}
