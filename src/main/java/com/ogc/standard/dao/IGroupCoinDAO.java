package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupCoin;

public interface IGroupCoinDAO extends IBaseDAO<GroupCoin> {
    String NAMESPACE = IGroupCoinDAO.class.getName().concat(".");

    /**
     * 修改账户余额
     * @param data
     * @return 
     * @create: 2018年8月23日 下午10:44:40 lei
     * @history:
     */
    public int updateAmount(GroupCoin data);

    public int frozenAmount(GroupCoin data);

    public int unfrozenAmount(GroupCoin data);
}
