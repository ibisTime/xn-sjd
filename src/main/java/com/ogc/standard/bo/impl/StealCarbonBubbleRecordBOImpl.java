package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IStealCarbonBubbleRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IStealCarbonBubbleRecordDAO;
import com.ogc.standard.domain.StealCarbonBubbleRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class StealCarbonBubbleRecordBOImpl
        extends PaginableBOImpl<StealCarbonBubbleRecord>
        implements IStealCarbonBubbleRecordBO {

    @Autowired
    private IStealCarbonBubbleRecordDAO stealCarbonBubbleRecordDAO;

    @Override
    public String saveStealCarbonBubbleRecord(String stealUserId,
            String stealedUserId, String adoptTreeCode,
            String carbonBubbleOrderCode, BigDecimal quantity) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.STEAL_CARBON_BUBBLE_RECORD.getCode());
        StealCarbonBubbleRecord stealCarbonBubbleRecord = new StealCarbonBubbleRecord();

        stealCarbonBubbleRecord.setCode(code);
        stealCarbonBubbleRecord.setStealUserId(stealUserId);
        stealCarbonBubbleRecord.setStealedUserId(stealedUserId);
        stealCarbonBubbleRecord.setAdoptTreeCode(adoptTreeCode);
        stealCarbonBubbleRecord.setCarbonBubbleOrderCode(carbonBubbleOrderCode);
        stealCarbonBubbleRecord.setQuantity(quantity);
        stealCarbonBubbleRecord.setStealDatetime(new Date());

        stealCarbonBubbleRecordDAO.insert(stealCarbonBubbleRecord);
        return code;

    }

    @Override
    public Boolean isTodayStealed(String stealUserId, String stealedUserId,
            String adoptTreeCode) {
        StealCarbonBubbleRecord stealCarbonBubbleRecord = new StealCarbonBubbleRecord();

        stealCarbonBubbleRecord.setStealUserId(stealUserId);
        stealCarbonBubbleRecord.setStealedUserId(stealedUserId);
        stealCarbonBubbleRecord.setAdoptTreeCode(adoptTreeCode);
        stealCarbonBubbleRecord.setStealStartDatetime(DateUtil.getTodayStart());
        stealCarbonBubbleRecord.setStealEndDatetime(DateUtil.getTodayEnd());

        return stealCarbonBubbleRecordDAO.selectList(stealCarbonBubbleRecord)
            .isEmpty();
    }

    @Override
    public Long getTodayStealQuantity(String stealUserId,
            String stealedUserId) {
        StealCarbonBubbleRecord stealCarbonBubbleRecord = new StealCarbonBubbleRecord();

        stealCarbonBubbleRecord.setStealUserId(stealUserId);
        stealCarbonBubbleRecord.setStealedUserId(stealedUserId);
        stealCarbonBubbleRecord.setStealStartDatetime(DateUtil.getTodayStart());
        stealCarbonBubbleRecord.setStealEndDatetime(DateUtil.getTodayEnd());

        return stealCarbonBubbleRecordDAO
            .selectSumQuantity(stealCarbonBubbleRecord);
    }

    @Override
    public List<StealCarbonBubbleRecord> queryStealCarbonBubbleRecordList(
            StealCarbonBubbleRecord condition) {
        return stealCarbonBubbleRecordDAO.selectList(condition);
    }

    @Override
    public StealCarbonBubbleRecord getStealCarbonBubbleRecord(String code) {
        StealCarbonBubbleRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            StealCarbonBubbleRecord condition = new StealCarbonBubbleRecord();
            condition.setCode(code);
            data = stealCarbonBubbleRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "偷取碳泡泡记录不存在");
            }
        }
        return data;
    }

}
