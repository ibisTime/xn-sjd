package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.DeriveGroup;

public interface IDeriveGroupDAO extends IBaseDAO<DeriveGroup> {
    String NAMESPACE = IDeriveGroupDAO.class.getName().concat(".");

    // 撤销寄售

    // 认领寄售

    // 拒绝寄售

}
