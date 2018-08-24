package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMatchAO;
import com.ogc.standard.bo.IMatchBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Match;
import com.ogc.standard.dto.req.XN628290Req;
import com.ogc.standard.dto.req.XN628291Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EMatchStatus;
import com.ogc.standard.enums.ETeamStatus;
import com.ogc.standard.exception.BizException;

/**
 * @author: silver 
 * @since: 2018年8月21日 上午11:00:38 
 * @history:
 */
@Service
public class MatchAOImpl implements IMatchAO {

    @Autowired
    private IMatchBO matchBO;

    @Autowired
    private ITeamBO teamBO;

    @Override
    public String addMatch(XN628290Req req) {
        if (matchBO.isMatchNameExist(req.getName())) {
            throw new BizException("xn0000", "赛事名称已存在！");
        }

        Match data = new Match();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Match.getCode());

        data.setCode(code);
        data.setName(req.getName());
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setStatus(EMatchStatus.TO_PUBLISH.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        matchBO.saveMatch(data);

        return code;
    }

    @Override
    public void editMatch(XN628291Req req) {
        Match match = matchBO.getMatch(req.getCode());
        if (!EMatchStatus.TO_PUBLISH.getCode().equals(match.getStatus())) {
            throw new BizException("xn0000", "赛事未处于可修改状态！");
        }

        Match data = new Match();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        matchBO.refreshMatch(data);
    }

    @Override
    public void publishMatch(String code, String updater, String remark) {
        Match match = matchBO.getMatch(code);
        if (!EMatchStatus.TO_PUBLISH.getCode().equals(match.getStatus())) {
            throw new BizException("xn0000", "赛事未处于待发布状态");
        }

        matchBO.refreshReleaseMatch(code, updater, remark);
    }

    @Override
    public void doDailyStartMatch() {
        Match condition = new Match();
        condition.setMatchStartDatetime(DateUtil.getTodayStart());
        condition.setStatus(EMatchStatus.PUBLISHED.getCode());

        while (true) {
            Paginable<Match> page = matchBO.getPaginable(0, 100,
                condition);
            if (null != page
                    && CollectionUtils.isNotEmpty(page.getList())) {
                for (Match match : page.getList()) {
                    // 更新赛事状态
                    matchBO.startMatch(match.getCode());

                    // 更新赛事战队状态
                    teamBO.refreshMatchTeamStatus(match.getCode(),
                        ETeamStatus.STARTED.getCode());
                }
            } else {
                break;
            }
        }
    }

    @Override
    public void doDailyEndMatch() {
        Match condition = new Match();
        condition.setMatchEndDatetime(DateUtil.getTodayStart());
        condition.setStatus(EMatchStatus.STARTED.getCode());

        while (true) {
            Paginable<Match> page = matchBO.getPaginable(0, 100,
                condition);
            if (null != page
                    && CollectionUtils.isNotEmpty(page.getList())) {
                for (Match match : page.getList()) {
                    // 更新赛事状态
                    matchBO.endMatch(match.getCode());

                    // 更新赛事战队状态
                    teamBO.refreshMatchTeamStatus(match.getCode(),
                        ETeamStatus.END.getCode());
                }
            } else {
                break;
            }
        }
    }

    @Override
    public Paginable<Match> queryMatchPage(int start, int limit,
            Match condition) {
        return matchBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Match> queryMatchList(Match condition) {
        return matchBO.queryMatchList(condition);
    }

    @Override
    public Match getMatch(String code) {
        return matchBO.getMatch(code);
    }

}
