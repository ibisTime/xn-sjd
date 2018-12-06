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
    public String claimDirect(String code, String claimant, Integer quantity);

    // 拒绝定向转让
    public void rejectDirect(String code, String claimant, String remark);

    // 认领二维码转让
    public String claimQr(String code, String claimant, Integer quantity);

    // 认领挂单寄售
    public String claimPublic(String code, String claimant, Integer quantity);

    public Paginable<DeriveGroup> queryDeriveGroupPage(int start, int limit,
            DeriveGroup condition);

    // 分页查询寄售中
    public Paginable<DeriveGroup> queryToclaimDeriveGroupPage(
            DeriveGroup condition);

    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition);

    // 查询品种
    public List<DeriveGroup> queryVarietyList(DeriveGroup data);

    public DeriveGroup getDeriveGroup(String code);

}
