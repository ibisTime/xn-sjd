package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Match;
import com.ogc.standard.dto.req.XN628290Req;
import com.ogc.standard.dto.req.XN628291Req;

/**
 * 赛事表
 * @author: silver 
 * @since: 2018年8月21日 上午10:59:32 
 * @history:
 */
public interface IMatchAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加赛事
    public String addMatch(XN628290Req req);

    // 修改赛事
    public void editMatch(XN628291Req req);

    // 发布赛事
    public void releaseMatch(String code, String updater, String remark);

    // 定时器每天凌晨更新赛事及其他数据状态
    public void updateMatchDaily();

    public Paginable<Match> queryMatchPage(int start, int limit,
            Match condition);

    public List<Match> queryMatchList(Match condition);

    public Match getMatch(String code);

}
