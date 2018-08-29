package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.MatchApply;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:30:00 
 * @history:
 */
public interface IMatchApplyBO extends IPaginableBO<MatchApply> {

    public boolean isMatchApplyExist(String code);

    // 战队名是否存在
    public boolean isTeamNameExist(String name);

    // 用户是否已申请
    public boolean isApplyUserExist(String applyUser);

    // 添加申请
    public void saveMatchApply(MatchApply data);

    // 审核申请
    public void approveMatchApply(String code, String status, String approver,
            String remark);

    public List<MatchApply> queryMatchApplyList(MatchApply condition);

    public MatchApply getMatchApply(String code);

}
