package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.bo.IAddressBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IGiftOrderBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN629323Req;
import com.ogc.standard.enums.EGiftOrderStatus;
import com.ogc.standard.exception.BizException;

@Service
public class GiftOrderAOImpl implements IGiftOrderAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IGiftOrderBO giftOrderBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAddressBO addressBO;

    @Override
    public String addGiftOrder(String adoptTreeCode, String name, String price,
            String listPic, String description, String invalidDatetime) {
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(adoptTreeCode);

        GiftOrder data = new GiftOrder();
        data.setToUser(adoptOrderTree.getCurrentHolder());
        data.setAdoptTreeCode(adoptTreeCode);
        data.setName(name);
        data.setPrice(StringValidater.toLong(price));
        data.setListPic(listPic);
        data.setDescription(description);
        data.setStatus(EGiftOrderStatus.TO_CLAIM.getCode());
        data.setCreateDatetime(new Date());
        data.setInvalidDatetime(DateUtil.strToDate(invalidDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
        return giftOrderBO.saveGiftOrder(data);
    }

    @Override
    public void claimGift(XN629323Req req) {
        GiftOrder data = giftOrderBO.getGiftOrder(req.getCode());
        if (!EGiftOrderStatus.TO_CLAIM.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "礼物不是待认领状态");
        }
        Address address = addressBO.getAddress(req.getAddressCode());

        data.setReceiver(address.getAddressee());
        data.setProvince(address.getProvince());
        data.setCity(address.getCity());
        data.setArea(address.getDistrict());
        data.setReAddress(address.getDetailAddress());

        data.setReMobile(address.getMobile());
        data.setClaimer(req.getClaimer());
        data.setStatus(EGiftOrderStatus.TO_SEND.getCode());
        data.setClaimDatetime(new Date());
        giftOrderBO.refreshGiftOrder(data);
    }

    @Override
    public void sendGift(String code) {
        GiftOrder data = giftOrderBO.getGiftOrder(code);
        if (!EGiftOrderStatus.TO_SEND.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "礼物不是待发货状态");
        }

        giftOrderBO.refreshSendGift(code);
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

        Integer start = 1;
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

            start = start + 1;
        }
        logger.info("***************开始扫描已过期礼物***************");
    }

    @Override
    public Paginable<GiftOrder> queryGiftOrderPage(int start, int limit,
            GiftOrder condition) {
        Paginable<GiftOrder> page = giftOrderBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (GiftOrder giftOrder : page.getList()) {

                init(giftOrder);

            }

        }

        return page;
    }

    @Override
    public List<GiftOrder> queryGiftOrderList(GiftOrder condition) {
        return giftOrderBO.queryGiftOrderList(condition);
    }

    @Override
    public GiftOrder getGiftOrder(String code) {
        GiftOrder giftOrder = giftOrderBO.getGiftOrder(code);

        init(giftOrder);

        return giftOrder;
    }

    private void init(GiftOrder giftOrder) {
        // 认领人
        String claimerName = null;
        User user = userBO.getUserUnCheck(giftOrder.getClaimer());
        if (null != user) {
            claimerName = user.getMobile();
            if (null != user.getRealName()) {
                claimerName = user.getRealName().concat("-")
                    .concat(claimerName);
            }
        }
        giftOrder.setClaimerName(claimerName);
    }

}
