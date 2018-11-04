package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DeriveGroup;

@Component
public interface IDeriveGroupAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 撤销转让
    public void revock(String code, String claimant, String remark);

    // 认领定向转让
    public void claimDirect(String code, String claimant);

    // 拒绝定向转让
    public void rejectDirect(String code, String claimant, String remark);

    // 认领二维码转让
    public void claimQr(String code, String claimant);

    // 认领挂单寄售
    public void claimPublic(String code, String claimant, Integer quantity);

    public Paginable<DeriveGroup> queryDeriveGroupPage(int start, int limit,
            DeriveGroup condition);

    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition);

    public DeriveGroup getDeriveGroup(String code);

}
