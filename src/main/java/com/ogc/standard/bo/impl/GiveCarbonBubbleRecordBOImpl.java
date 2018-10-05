package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGiveCarbonBubbleRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGiveCarbonBubbleRecordDAO;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class GiveCarbonBubbleRecordBOImpl
        extends PaginableBOImpl<GiveCarbonBubbleRecord>
        implements IGiveCarbonBubbleRecordBO {

    @Autowired
    private IGiveCarbonBubbleRecordDAO giveCarbonBubbleRecordDAO;

    @Override
    public String saveGiveCarbonBubbleRecord(String userId, String toUserId,
            BigDecimal quantity) {
        GiveCarbonBubbleRecord data = new GiveCarbonBubbleRecord();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.GiveCarbonBubbleRecord.getCode());
        data.setCode(code);
        data.setUserId(toUserId);
        data.setToUserId(toUserId);
        data.setQuantity(quantity);

        data.setCreateDatetime(new Date());
        giveCarbonBubbleRecordDAO.insert(data);
        return code;
    }

    @Override
    public List<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordList(
            GiveCarbonBubbleRecord condition) {
        return giveCarbonBubbleRecordDAO.selectList(condition);
    }

    @Override
    public GiveCarbonBubbleRecord getGiveCarbonBubbleRecord(String code) {
        GiveCarbonBubbleRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            GiveCarbonBubbleRecord condition = new GiveCarbonBubbleRecord();
            condition.setCode(code);
            data = giveCarbonBubbleRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "赠送碳泡泡记录不存在！");
            }
        }
        return data;
    }
}
