package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICarbonBubbleOrderDAO;
import com.ogc.standard.domain.CarbonBubbleOrder;
import com.ogc.standard.enums.ECarbonBubbleOrderStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class CarbonBubbleOrderBOImpl extends PaginableBOImpl<CarbonBubbleOrder>
        implements ICarbonBubbleOrderBO {

    @Autowired
    private ICarbonBubbleOrderDAO carbonBubbleOrderDAO;

    @Override
    public void takeCarbonBubble(String code, String collector) {
        CarbonBubbleOrder data = getCarbonBubbleOrder(code);
        data.setStatus(ECarbonBubbleOrderStatus.TAKED.getCode());
        data.setTaker(collector);
        data.setTakeDatetime(new Date());
        carbonBubbleOrderDAO.updateTakeCarbonBubble(data);
    }

    @Override
    public String saveCarbonBubbleOrder(String adoptTreeCode,
            Date createDatetime, Date invalidDatetime, String adoptUserId,
            BigDecimal quantity) {
        CarbonBubbleOrder data = new CarbonBubbleOrder();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CARBON_BUBBLE_ORDER.getCode());
        data.setCode(code);
        data.setAdoptTreeCode(adoptTreeCode);
        data.setAdoptUserId(adoptUserId);
        data.setCreateDatetime(createDatetime);
        data.setQuantity(quantity);

        data.setStatus(ECarbonBubbleOrderStatus.TO_TAKE.getCode());
        data.setInvalidDatetime(invalidDatetime);
        carbonBubbleOrderDAO.insert(data);
        return code;
    }

    @Override
    public void expireCarbonBubbleOrder(String code) {
        CarbonBubbleOrder condition = new CarbonBubbleOrder();
        condition.setCode(code);
        condition.setStatus(ECarbonBubbleOrderStatus.INVALID.getCode());
        carbonBubbleOrderDAO.updateExpireCarbonBubble(condition);
    }

    @Override
    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition) {
        return carbonBubbleOrderDAO.selectList(condition);
    }

    @Override
    public CarbonBubbleOrder getCarbonBubbleOrder(String code) {
        CarbonBubbleOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            CarbonBubbleOrder condition = new CarbonBubbleOrder();
            condition.setCode(code);
            data = carbonBubbleOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "碳泡泡产生订单不存在");
            }
        }
        return data;
    }

}
