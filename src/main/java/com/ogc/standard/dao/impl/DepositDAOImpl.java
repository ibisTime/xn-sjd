/**
 * @Title DepositDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午2:56:56 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDepositDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Deposit;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午2:56:56 
 * @history:
 */
@Repository("depositDAOImpl")
public class DepositDAOImpl extends AMybatisTemplate implements IDepositDAO {

    @Override
    public int insert(Deposit data) {
        return 0;
    }

    @Override
    public int delete(Deposit data) {
        return 0;
    }

    @Override
    public Deposit select(Deposit condition) {
        return super.select(NAMESPACE.concat("select_deposit"), condition,
            Deposit.class);
    }

    @Override
    public long selectTotalCount(Deposit condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_deposit_count"),
            condition);
    }

    @Override
    public List<Deposit> selectList(Deposit condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Deposit> selectList(Deposit condition, int start, int count) {
        // TODO Auto-generated method stub
        return null;
    }

}
