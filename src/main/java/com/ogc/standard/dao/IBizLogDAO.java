package com.ogc.standard.dao;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BizLog;

//dao层 
public interface IBizLogDAO extends IBaseDAO<BizLog> {
    String NAMESPACE = IBizLogDAO.class.getName().concat(".");

    // 查询最大的id
    public long selectMaxId();

    // 查询操作数量总量
    public BigDecimal selectQuantitySum(BizLog condition);
}
