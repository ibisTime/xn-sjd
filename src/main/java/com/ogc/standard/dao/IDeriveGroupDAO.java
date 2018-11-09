package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.DeriveGroup;

public interface IDeriveGroupDAO extends IBaseDAO<DeriveGroup> {
    String NAMESPACE = IDeriveGroupDAO.class.getName().concat(".");

    // 撤销转让
    public int updateRevock(DeriveGroup data);

    // 认领定向转让
    public int updateClaimDirect(DeriveGroup data);

    // 拒绝定向转让
    public int updateRejectDirect(DeriveGroup data);

    // 认领二维码转让
    public int updateClaimQr(DeriveGroup data);

    // 认领挂单寄售
    public int updateClaimPublic(DeriveGroup data);

    // 更新数量
    public int updateQuantity(DeriveGroup data);

    // 查询品种
    public List<DeriveGroup> selectVarietyList(DeriveGroup data);

}
