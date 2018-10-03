package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629600Req;

public interface IApplyBindMaintainBO extends IPaginableBO<ApplyBindMaintain> {

    public String saveApplyBindMaintain(XN629600Req req);

    public void approveApplyBindMaintain(ApplyBindMaintain data);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

    public String getMaintainId(String userId);

}
