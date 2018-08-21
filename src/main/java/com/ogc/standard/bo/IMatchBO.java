package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Match;

/**
 * 赛事表
 * @author: silver 
 * @since: 2018年8月21日 上午10:44:34 
 * @history:
 */

public interface IMatchBO extends IPaginableBO<Match> {

    // code是否存在
    public boolean isMatchExist(String code);

    // 新增
    public void saveMatch(Match data);

    // 修改
    public void refreshMatch(Match data);

    // 发布
    public void releaseMatch(String code, String updater, String remark);

    // 开始赛事
    public void startMatch();

    // 结束赛事
    public void endMatch();

    public List<Match> queryMatchList(Match condition);

    public Match getMatch(String code);

}
