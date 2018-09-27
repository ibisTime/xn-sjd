package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ApplyBindMaintain;

public interface IApplyBindMaintainBO extends IPaginableBO<ApplyBindMaintain> {

    public boolean isApplyBindMaintainExist(String code);

    public String saveApplyBindMaintain(ApplyBindMaintain data);

    public int removeApplyBindMaintain(String code);

    public int refreshApplyBindMaintain(ApplyBindMaintain data);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

    public void approveApplyBindMaintain(ApplyBindMaintain data);

}
