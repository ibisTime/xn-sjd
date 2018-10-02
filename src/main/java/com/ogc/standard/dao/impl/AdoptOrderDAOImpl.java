package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAdoptOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AdoptOrder;

@Repository("adoptOrderDAOImpl")
public class AdoptOrderDAOImpl extends AMybatisTemplate implements
        IAdoptOrderDAO {

    @Override
    public int insert(AdoptOrder data) {
        return super.insert(NAMESPACE.concat("insert_adoptOrder"), data);
    }

    @Override
    public int delete(AdoptOrder data) {
        return 0;
    }

    @Override
    public AdoptOrder select(AdoptOrder condition) {
        return super.select(NAMESPACE.concat("select_adoptOrder"), condition,
            AdoptOrder.class);
    }

    @Override
    public long selectTotalCount(AdoptOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_adoptOrder_count"), condition);
    }

    @Override
    public List<AdoptOrder> selectList(AdoptOrder condition) {
        return super.selectList(NAMESPACE.concat("select_adoptOrder"),
            condition, AdoptOrder.class);
    }

    @Override
    public List<AdoptOrder> selectList(AdoptOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_adoptOrder"), start,
            count, condition, AdoptOrder.class);
    }

    @Override
    public void updateCancelAdoptOrder(AdoptOrder data) {
        super.update(NAMESPACE.concat("update_cancelAdoptOrder"), data);
    }

    // 余额支付
    @Override
    public void updatePayYueSuccess(AdoptOrder data) {
        super.update(NAMESPACE.concat("update_payYueSuccess"), data);
    }

    // 预支付更新
    @Override
    public void updatePayGroup(AdoptOrder data) {
        super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    // 支付回调成功
    @Override
    public void updatePaySuccess(AdoptOrder data) {
        super.update(NAMESPACE.concat("update_paySuccess"), data);

    }

}
