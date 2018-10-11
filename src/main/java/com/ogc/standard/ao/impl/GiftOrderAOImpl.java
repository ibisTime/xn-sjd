package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.bo.IGiftOrderBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.dto.req.XN629323Req;
import com.ogc.standard.enums.EGiftOrderStatus;
import com.ogc.standard.exception.BizException;

@Service
public class GiftOrderAOImpl implements IGiftOrderAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IGiftOrderBO giftOrderBO;

    @Override
    public String addGiftOrder(String adoptTreeCode, String name, String price,
            String listPic, String description, String invalidDatetime) {
        GiftOrder data = new GiftOrder();
        data.setAdoptTreeCode(adoptTreeCode);
        data.setName(name);
        data.setPrice(StringValidater.toLong(price));
        data.setListPic(listPic);
        data.setDescription(description);
        data.setStatus(EGiftOrderStatus.TO_CLAIM.getCode());
        data.setCreateDatetime(new Date());
        data.setInvalidDatetime(
            DateUtil.strToDate(invalidDatetime, DateUtil.DATA_TIME_PATTERN_1));
        return giftOrderBO.saveGiftOrder(data);
    }

    @Override
    public void claimGift(XN629323Req req) {
        GiftOrder data = giftOrderBO.getGiftOrder(req.getCode());
        if (EGiftOrderStatus.TO_CLAIM.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "礼物不是待认领状态");
        }
        if (new Date().getTime() > data.getInvalidDatetime().getTime()) {
            throw new BizException("xn0000", "礼物已失效");
        }
        data.setReceiver(req.getReceiver());
        data.setReAddress(req.getReAddress());
        data.setReMobile(req.getReMobile());
        data.setClaimer(req.getClaimer());
        data.setStatus(EGiftOrderStatus.CLAIMED.getCode());
        data.setClaimDatetime(new Date());
        giftOrderBO.refreshGiftOrder(data);
    }

    @Override
    public int editGiftOrder(GiftOrder data) {
        if (!giftOrderBO.isGiftOrderExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return giftOrderBO.refreshGiftOrder(data);
    }

    @Override
    public int dropGiftOrder(String code) {
        if (!giftOrderBO.isGiftOrderExist(code)) {
            throw new BizException("xn0000", "礼物编号不存在");
        }
        return giftOrderBO.removeGiftOrder(code);
    }

    public void doDailyExpireGift() {
        logger.info("***************开始扫描已过期礼物***************");
        GiftOrder condition = new GiftOrder();
        condition.setStatus(EGiftOrderStatus.TO_CLAIM.getCode());
        condition.setInvalidEndDatetime(DateUtil.getTodayStart());

        Integer start = 0;
        Integer limit = 10;

        while (true) {
            Paginable<GiftOrder> page = giftOrderBO.getPaginable(start, limit,
                condition);

            if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
                for (GiftOrder giftOrder : page.getList()) {
                    giftOrderBO.refreshExpireGift(giftOrder.getCode());
                }
            } else {
                break;
            }

            start = start + limit;
        }
        logger.info("***************开始扫描已过期礼物***************");
    }

    @Override
    public Paginable<GiftOrder> queryGiftOrderPage(int start, int limit,
            GiftOrder condition) {
        return giftOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GiftOrder> queryGiftOrderList(GiftOrder condition) {
        return giftOrderBO.queryGiftOrderList(condition);
    }

    @Override
    public GiftOrder getGiftOrder(String code) {
        return giftOrderBO.getGiftOrder(code);
    }

}
