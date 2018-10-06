package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGiftOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGiftOrderDAO;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EGiftOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class GiftOrderBOImpl extends PaginableBOImpl<GiftOrder>
        implements IGiftOrderBO {

    @Autowired
    private IGiftOrderDAO giftOrderDAO;

    @Override
    public boolean isGiftOrderExist(String code) {
        GiftOrder condition = new GiftOrder();
        condition.setCode(code);
        if (giftOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveGiftOrder(GiftOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.GIFT_ORDER.getCode());
            data.setCode(code);
            giftOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeGiftOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            GiftOrder data = new GiftOrder();
            data.setCode(code);
            count = giftOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshGiftOrder(GiftOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = giftOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public void refreshExpireGift(String code) {
        if (StringUtils.isNotBlank(code)) {
            GiftOrder giftOrder = getGiftOrder(code);
            giftOrder.setStatus(EGiftOrderStatus.EXPIRED.getCode());
            giftOrderDAO.updateExpireGift(giftOrder);
        }
    }

    @Override
    public List<GiftOrder> queryGiftOrderList(GiftOrder condition) {
        return giftOrderDAO.selectList(condition);
    }

    @Override
    public GiftOrder getGiftOrder(String code) {
        GiftOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            GiftOrder condition = new GiftOrder();
            condition.setCode(code);
            data = giftOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "礼物不存在");
            }
        }
        return data;
    }

}
