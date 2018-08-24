package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.dto.req.XN628300Req;

/**
 * @author: silver 
 * @since: 2018年8月21日 下午2:38:09 
 * @history:
 */
public interface IMatchApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 报名申请
    public String addMatchApply(XN628300Req req);

    // 申请审核
    public void approveMatchApply(String code, String approveResult,
            String approver, String remark);

    public Paginable<MatchApply> queryMatchApplyPage(int start, int limit,
            MatchApply condition);

    public List<MatchApply> queryMatchApplyList(MatchApply condition);

    public MatchApply getMatchApply(String code);

}
