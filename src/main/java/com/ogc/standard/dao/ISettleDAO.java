package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Settle;

/**
 * 结算订单
 * @author: silver 
 * @since: Sep 29, 2018 4:58:47 PM 
 * @history:
 */
public interface ISettleDAO extends IBaseDAO<Settle> {
    String NAMESPACE = ISettleDAO.class.getName().concat(".");

    // 修改参考订单状态
    public int updateStatusByRefCode(Settle data);
}
