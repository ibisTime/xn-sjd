package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGiftOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GiftOrder;

@Repository("giftOrderDAOImpl")
public class GiftOrderDAOImpl extends AMybatisTemplate
        implements IGiftOrderDAO {

    @Override
    public int insert(GiftOrder data) {
        return super.insert(NAMESPACE.concat("insert_giftOrder"), data);
    }

    @Override
    public int delete(GiftOrder data) {
        return super.delete(NAMESPACE.concat("delete_giftOrder"), data);
    }

    @Override
    public int updateExpireGift(GiftOrder data) {
        return super.update(NAMESPACE.concat("update_expireGiftOrder"), data);
    }

    @Override
    public GiftOrder select(GiftOrder condition) {
        return super.select(NAMESPACE.concat("select_giftOrder"), condition,
            GiftOrder.class);
    }

    @Override
    public long selectTotalCount(GiftOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_giftOrder_count"), condition);
    }

    @Override
    public List<GiftOrder> selectList(GiftOrder condition) {
        return super.selectList(NAMESPACE.concat("select_giftOrder"), condition,
            GiftOrder.class);
    }

    @Override
    public List<GiftOrder> selectList(GiftOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_giftOrder"), start,
            count, condition, GiftOrder.class);
    }

    @Override
    public int update(GiftOrder data) {
        return super.update(NAMESPACE.concat("update_giftOrder"), data);
    }

    @Override
    public int updateSendGift(GiftOrder data) {
        return super.update(NAMESPACE.concat("update_sendGiftOrder"), data);
    }

}
