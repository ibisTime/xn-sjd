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

    // 名称是否存在
    public boolean isMatchNameExist(String name);

    // 新增
    public void saveMatch(Match data);

    // 修改
    public void refreshMatch(Match data);

    // 发布
    public void releaseMatch(String code, String updater, String remark);

    // 开始赛事(定时器)
    public void startMatch(String code);

    // 结束赛事(定时器)
    public void endMatch(String code);

    public List<Match> queryMatchList(Match condition);

    public Match getMatch(String code);

}
