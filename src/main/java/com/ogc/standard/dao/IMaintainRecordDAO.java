package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.MaintainRecord;

public interface IMaintainRecordDAO extends IBaseDAO<MaintainRecord> {
    String NAMESPACE = IMaintainRecordDAO.class.getName().concat(".");

    // 修改养护记录
    public int updateMaintainRecord(MaintainRecord data);

}
