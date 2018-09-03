package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.ao.IUserRelationAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.UserStatistics;
import com.ogc.standard.dto.req.XN625256Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EUserReleationType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/23.
 */
public class XN625256 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    //
    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    //
    private XN625256Req req;

    @Override
    public Object doBusiness() throws BizException {

        // throw new BizException("xn000","已经弃用");
        UserStatistics userStatistics = this.tradeOrderAO
            .userStatisticsInfoContainTradeCount(req.getMaster(),
                req.getCurrency());
        //
        if (StringUtils.isNotBlank(req.getVisitor())) {
            //
            boolean isTruest = this.userRelationAO.isExistUserRelation(
                req.getVisitor(), req.getMaster(),
                EUserReleationType.TRUST.getCode());

            boolean isAddBlackList = this.userRelationAO.isExistUserRelation(
                req.getVisitor(), req.getMaster(),
                EUserReleationType.BLACKLIST.getCode());

            userStatistics.setIsTrust(
                isTruest ? EBoolean.YES.getCode() : EBoolean.NO.getCode());
            userStatistics
                .setIsAddBlackList(isAddBlackList ? EBoolean.YES.getCode()
                        : EBoolean.NO.getCode());

            // 之间的交易次数
            long tradeTimesBetweenUser = this.tradeOrderAO
                .getTradeTimesBetweenUser(req.getMaster(), req.getVisitor());
            userStatistics.setBetweenTradeTimes(
                Long.valueOf(tradeTimesBetweenUser).toString());

        } else {

            userStatistics.setIsTrust(EBoolean.NO.getCode());
            userStatistics.setIsAddBlackList(EBoolean.NO.getCode());

        }

        return userStatistics;

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625256Req.class);
        ObjValidater.validateReq(req);

    }
}
