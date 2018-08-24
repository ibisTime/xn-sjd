package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.bo.IGroupBO;
import com.ogc.standard.bo.IMatchApplyBO;
import com.ogc.standard.bo.IMatchBO;
import com.ogc.standard.bo.ITeamBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.domain.Match;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN628300Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EMatchApplyStatus;
import com.ogc.standard.enums.EMatchStatus;
import com.ogc.standard.exception.BizException;

/**
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

    @Autowired
    private IGroupBO groupBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String addMatchApply(XN628300Req req) {
        Match match = matchBO.getMatch(req.getMatchCode());
        if (!EMatchStatus.PUBLISHED.getCode().equals(match.getStatus())) {
            throw new BizException("xn000", "当前赛事未处于可报名状态！");
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
        MatchApply matchApply = matchApplyBO.getMatchApply(code);
        if (!EMatchApplyStatus.TO_APPROVE.getCode()
            .equals(matchApply.getStatus())) {
            throw new BizException("xn0000", "报名申请未处于待审核状态！");
        }

        Match match = matchBO.getMatch(matchApply.getMatchCode());
        if (!EMatchStatus.PUBLISHED.getCode().equals(match.getStatus())) {
            throw new BizException("xn000", "赛事未处于待审核状态！");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EMatchApplyStatus.APPROVED_YES.getCode();

            // 审核通过后添加战队
            String teamCode = teamBO.saveTeam(code, matchApply.getTeamName(),
                matchApply.getLogo(), matchApply.getDescription(),
                matchApply.getApplyUser());

            // 添加组合
            // groupBO.saveGroup(match.getCode(), teamCode,
            // matchApply.getApplyUser(), new BigDecimal(0));
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
        // 赛事信息
        Match match = matchBO.getMatch(matchApply.getMatchCode());
        matchApply.setMatchName(match.getName());

        // 申请人
        User applyUserInfo = userBO.getUser(matchApply.getApplyUser());
        matchApply.setApplyUserInfo(applyUserInfo);

    }
}
