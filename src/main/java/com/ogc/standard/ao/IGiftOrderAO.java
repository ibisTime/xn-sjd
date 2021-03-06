package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.dto.req.XN629323Req;

public interface IGiftOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addGiftOrder(String adoptTreeCode, String name, String price,
            String listPic, String description, String invalidDatetime);

    public int dropGiftOrder(String code);

    public int editGiftOrder(GiftOrder data);

    // 发货礼物
    public void sendGift(String code);

    // 认领礼物
    public void claimGift(XN629323Req req);

    public Paginable<GiftOrder> queryGiftOrderPage(int start, int limit,
            GiftOrder condition);

    public List<GiftOrder> queryGiftOrderList(GiftOrder condition);

    public GiftOrder getGiftOrder(String code);

}
