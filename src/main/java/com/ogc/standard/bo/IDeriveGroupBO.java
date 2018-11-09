package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DeriveGroup;

public interface IDeriveGroupBO extends IPaginableBO<DeriveGroup> {

    // 定向转让
    public String saveDirectSales(String originalCode, BigDecimal price,
            Integer quantity, String claimant);

    // 二维码转让
    public String saveQrSales(String originalCode, BigDecimal price,
            Integer quantity);

    // 挂单寄售
    public String savePublicSales(String originalCode, BigDecimal price,
            Integer quantity);

    // 撤销转让
    public void refreshRevock(String code, String claimant, String remark);

    // 认领定向转让
    public void refreshClaimDirect(String code);

    // 拒绝定向转让
    public void refreshRejectDirect(String code, String remark);

    // 认领二维码转让
    public void refreshClaimQr(String code, String claimant);

    // 认领挂单寄售
    public void refreshClaimPublic(String code, String claimant,
            Integer quantity, String status);

    // 更新数量
    public void refreshQuantity(String code, Integer quantity);

    // 我发布的寄售
    public List<DeriveGroup> queryDeriveGroupListByCreater(String creater,
            String status);

    // 别人转给我的定向
    public List<DeriveGroup> queryDeriveGroupListDirect(String claimant,
            String status);

    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition);

    // 查询品种
    public List<DeriveGroup> queryVarietyList(DeriveGroup data);

    public DeriveGroup getDeriveGroup(String code);

}
