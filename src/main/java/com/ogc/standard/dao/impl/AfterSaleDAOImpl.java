/**
 * @Title AfterSaleDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午7:54:00 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAfterSaleDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AfterSale;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午7:54:00 
 * @history:
 */
@Repository("afterSaleDAOImpl")
public class AfterSaleDAOImpl extends AMybatisTemplate implements IAfterSaleDAO {

    @Override
    public int insert(AfterSale data) {
        return super.insert(NAMESPACE.concat("insert_after_sale"), data);
    }

    @Override
    public int delete(AfterSale data) {
        return 0;
    }

    @Override
    public AfterSale select(AfterSale condition) {
        return super.select(NAMESPACE.concat("select_after_sale"), condition,
            AfterSale.class);
    }

    @Override
    public long selectTotalCount(AfterSale condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_after_sale_count"), condition);
    }

    @Override
    public List<AfterSale> selectList(AfterSale condition) {
        return super.selectList(NAMESPACE.concat("select_after_sale"),
            condition, AfterSale.class);
    }

    @Override
    public List<AfterSale> selectList(AfterSale condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_after_sale"), start,
            count, condition, AfterSale.class);
    }

    @Override
    public int updateHandle(AfterSale data) {
        return super.update(NAMESPACE.concat("update_handle"), data);
    }

    @Override
    public int updateReceive(AfterSale data) {
        return super.update(NAMESPACE.concat("update_receive"), data);
    }

}
