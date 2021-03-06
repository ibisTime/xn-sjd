package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellSpecs;

public interface IPresellSpecsDAO extends IBaseDAO<PresellSpecs> {
    String NAMESPACE = IPresellSpecsDAO.class.getName().concat(".");

    // 更新库存
    public int updatePackCount(PresellSpecs data);

    // 更新价格
    public int updatePrice(PresellSpecs data);

    // 更新当前间隔
    public int updateNowInterval(PresellSpecs data);

}
