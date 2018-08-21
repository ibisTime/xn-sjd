package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IMatchBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.dao.IMatchDAO;
import com.ogc.standard.domain.Match;
import com.ogc.standard.enums.EMatchStatus;
import com.ogc.standard.exception.BizException;

/**
 * 赛事表
 * @author: silver 
 * @since: 2018年8月21日 上午10:46:05 
 * @history:
 */
@Component
public class MatchBOImpl extends PaginableBOImpl<Match> implements IMatchBO {

    @Autowired
    private IMatchDAO matchDAO;

    @Override
    public boolean isMatchExist(String code) {
        Match condition = new Match();
        condition.setCode(code);
        if (matchDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveMatch(Match data) {
        matchDAO.insert(data);
    }

    @Override
    public void refreshMatch(Match data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            matchDAO.update(data);
        }
    }

    @Override
    public void releaseMatch(String code, String updater, String remark) {
        if (StringUtils.isNotBlank(code)) {
            Match data = new Match();
            data.setCode(code);
            data.setStatus(EMatchStatus.PUBLISHED.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            matchDAO.updateRelease(data);
        }
    }

    @Override
    public void startMatch() {
        Match condition = new Match();
        condition.setMatchStartDatetime(DateUtil.getTodayStart());
        condition.setStatus(EMatchStatus.STARTED.getCode());

        matchDAO.updateStartMatch(condition);
    }

    @Override
    public void endMatch() {
        Match condition = new Match();
        condition.setMatchEndDatetime(DateUtil.getTodayStart());
        condition.setStatus(EMatchStatus.EXPIRED.getCode());

        matchDAO.updateEndMatch(condition);
    }

    @Override
    public List<Match> queryMatchList(Match condition) {
        return matchDAO.selectList(condition);
    }

    @Override
    public Match getMatch(String code) {
        Match data = null;
        if (StringUtils.isNotBlank(code)) {
            Match condition = new Match();
            condition.setCode(code);
            data = matchDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }

}
