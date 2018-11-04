package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellLogistics;

public interface IPresellLogisticsDAO extends IBaseDAO<PresellLogistics> {
    String NAMESPACE = IPresellLogisticsDAO.class.getName().concat(".");

    // 发货
    public int updateSendLogistisc(PresellLogistics data);

    // 收货
    public int updateRceiveLogistisc(PresellLogistics data);
}
