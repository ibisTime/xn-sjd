package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629600Req;
import com.ogc.standard.dto.req.XN629602Req;

/**
 * 申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月26日 下午5:54:57 
 * @history:
 */
public interface IApplyBindMaintainAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String applyBindMaintain(XN629600Req req);

    // 审核
    public void approveApplyBindMaintain(XN629602Req req);

    // 重新绑定
    public void reBingMaintain(String code, String maintainId, String updater,
            String remark);

    public Paginable<ApplyBindMaintain> queryApplyBindMaintainPage(int start,
            int limit, ApplyBindMaintain condition);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

}
