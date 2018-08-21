package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.bo.IMatchApplyBO;
import com.ogc.standard.bo.IMatchBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.Match;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.dto.req.XN628300Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EMatchApplyStatus;
import com.ogc.standard.exception.BizException;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:39:54 
 * @history:
 */
@Service
public class MatchApplyAOImpl implements IMatchApplyAO {

    @Autowired
    private IMatchApplyBO matchApplyBO;

    @Autowired
    private IMatchBO matchBO;

    @Autowired
    private ITeamBO teamBO;

    @Override
    @Transactional
    public String addMatchApply(XN628300Req req) {

        if (!matchBO.isMatchExist(req.getMatchCode())) {
            throw new BizException("xn000", "参赛赛事不存在！");
        }

        if (matchApplyBO.isTeamNameExist(req.getTeamName())) {
            throw new BizException("xn000", "战队名称已存在，请重新输入！");
        }

        MatchApply data = new MatchApply();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.MatchApply.getCode());
        data.setCode(code);
        data.setStatus(EMatchApplyStatus.TO_APPROVE.getCode());
        data.setMatchCode(req.getMatchCode());
        data.setTeamName(req.getTeamName());

        data.setLogo(req.getTeamLogo());
        data.setDescription(req.getTeamDesc());
        data.setApplyUser(req.getUserId());
        data.setApplyDatetime(new Date());
        matchApplyBO.saveMatchApply(data);

        return code;
    }

    @Override
    @Transactional
    public void approveMatchApply(String code, String approveResult,
            String approver, String remark) {
        if (!matchApplyBO.isMatchApplyExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }

        MatchApply matchApply = matchApplyBO.getMatchApply(code);
        if (!EMatchApplyStatus.TO_APPROVE.getCode()
            .equals(matchApply.getStatus())) {
            throw new BizException("xn0000", "记录不处于待审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EMatchApplyStatus.APPROVED_YES.getCode();

            // 审核通过后添加战队
            teamBO.saveTeam(code, matchApply.getTeamName(),
                matchApply.getLogo(), matchApply.getDescription(),
                matchApply.getApplyUser());
        } else {
            status = EMatchApplyStatus.APPROVED_NO.getCode();
        }

        matchApplyBO.approveMatchApply(code, status, approver, remark);
    }

    @Override
    public Paginable<MatchApply> queryMatchApplyPage(int start, int limit,
            MatchApply condition) {
        Paginable<MatchApply> page = matchApplyBO.getPaginable(start, limit,
            condition);
        List<MatchApply> list = page.getList();

        if (CollectionUtils.isNotEmpty(list)) {
            for (MatchApply matchApply : list) {
                initMatchApply(matchApply);
            }
        }

        return page;
    }

    @Override
    public List<MatchApply> queryMatchApplyList(MatchApply condition) {
        List<MatchApply> list = matchApplyBO.queryMatchApplyList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (MatchApply matchApply : list) {
                initMatchApply(matchApply);
            }
        }

        return list;
    }

    @Override
    public MatchApply getMatchApply(String code) {
        MatchApply matchApply = matchApplyBO.getMatchApply(code);

        initMatchApply(matchApply);

        return matchApply;
    }

    private void initMatchApply(MatchApply matchApply) {

        // 填充赛事信息
        Match match = matchBO.getMatch(matchApply.getMatchCode());
        if (null != match) {
            matchApply.setMatchName(match.getName());
        }
    }
}
