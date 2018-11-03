package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellInventory;

public interface IPresellInventoryDAO extends IBaseDAO<PresellInventory> {
    String NAMESPACE = IPresellInventoryDAO.class.getName().concat(".");

    // 更新组
    public int updateGroup(PresellInventory data);

}
