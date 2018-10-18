package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN805041Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.enums.EUserRefereeType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 前端用户普通注册
 * @author: myb858 
 * @since: 2015年8月23日 上午11:42:00
 * @history:
 */
public class XN805041 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805041Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = null;

        // 注册用户
        synchronized (IUserAO.class) {
            userId = userAO.doRegisterByMobile(req);
            userAO.doAssignRegistJf(userId, req.getUserReferee(),
                req.getUserRefereeType());

            // 注册用户升级
            userAO.upgradeUserLevel(userId);

            // 推荐用户升级
            if (StringUtils.isNotBlank(req.getUserReferee())
                    && EUserRefereeType.USER.getCode()
                        .equals(req.getUserRefereeType())) {
                User refereeUser = userAO.getUserByMobile(req.getUserReferee());

                userAO.upgradeUserLevel(refereeUser.getUserId());
            }
        }

        return new PKCodeRes(userId);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805041Req.class);
        ObjValidater.validateReq(req);
    }
}
