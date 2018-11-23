package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Barrage;

public interface IBarrageDAO extends IBaseDAO<Barrage> {
    String NAMESPACE = IBarrageDAO.class.getName().concat(".");

    public int updateBarrage(Barrage barrage);

    public int updateStatus(Barrage barrage);

}
