package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629600Req;
import com.ogc.standard.dto.req.XN629601Req;
import com.ogc.standard.dto.req.XN629602Req;

/**
 * 申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月26日 下午5:54:57 
 * @history:
 */
@Component
public interface IApplyBindMaintainAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addApplyBindMaintain(XN629600Req req);

    public int dropApplyBindMaintain(String code);

    public boolean editApplyBindMaintain(XN629601Req req);

    public Paginable<ApplyBindMaintain> queryApplyBindMaintainPage(int start,
            int limit, ApplyBindMaintain condition);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

    // 审核
    public void approveApplyBindMaintain(XN629602Req req);

}
