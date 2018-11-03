package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.OriginalGroup;

public interface IOriginalGroupDAO extends IBaseDAO<OriginalGroup> {
    String NAMESPACE = IOriginalGroupDAO.class.getName().concat(".");

    // 认养
    public int updateAdoptOrignalGroup(OriginalGroup data);

    // 更新数量
    public int updateQuantity(OriginalGroup data);

    // 发货
    public int updateDeliverOrignalGroup(OriginalGroup data);

    // 收货
    public int updateReceiveOrignalGroup(OriginalGroup data);
}
