package com.ogc.standard.bo.impl;

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
    public boolean isCarbonBubbleOrderExist(String code) {
        CarbonBubbleOrder condition = new CarbonBubbleOrder();
        condition.setCode(code);
        if (carbonBubbleOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCarbonBubbleOrder(CarbonBubbleOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.CARBON_BUBBLE_ORDER.getCode());
            data.setCode(code);
            carbonBubbleOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCarbonBubbleOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CarbonBubbleOrder data = new CarbonBubbleOrder();
            data.setCode(code);
            count = carbonBubbleOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCarbonBubbleOrder(CarbonBubbleOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = carbonBubbleOrderDAO.update(data);
        }
        return count;
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
