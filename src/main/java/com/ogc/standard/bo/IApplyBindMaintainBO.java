package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ApplyBindMaintain;

public interface IApplyBindMaintainBO extends IPaginableBO<ApplyBindMaintain> {

    public String saveApplyBindMaintain(String ownerId, String maintainId,
            String updater, String remark);

    public void approveApplyBindMaintain(ApplyBindMaintain data);

    public void doCheckBindMaintain(String ownerId);

    // 解除绑定
    public void unBindMaintain(String code);

    // 重新绑定
    public void reBindMaintain(String code, String maintainId, String updater,
            String remark);

    public List<String> queryBindOwnerList(String maintainId);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

    public String getMaintainId(String userId);

}
