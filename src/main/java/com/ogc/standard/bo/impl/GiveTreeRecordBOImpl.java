package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IGiveTreeRecordDAO;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class GiveTreeRecordBOImpl extends PaginableBOImpl<GiveTreeRecord>
        implements IGiveTreeRecordBO {

    @Autowired
    private IGiveTreeRecordDAO giveTreeRecordDAO;

    @Override
    public String saveGiveTreeRecord(String userId, String toUserId,
            String adoptTreeCode) {
        String code = null;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(toUserId)) {
            GiveTreeRecord data = new GiveTreeRecord();
            code = OrderNoGenerater.generate(EGeneratePrefix.GIVE_TREE_RECORD
                .getCode());
            data.setCode(code);
            data.setAdoptTreeCode(adoptTreeCode);
            data.setUserId(userId);
            data.setToUserId(toUserId);
            data.setCreateDatetime(new Date());
            giveTreeRecordDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition) {
        return giveTreeRecordDAO.selectList(condition);
    }

    @Override
    public GiveTreeRecord getGiveTreeRecord(String code) {
        GiveTreeRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            GiveTreeRecord condition = new GiveTreeRecord();
            condition.setCode(code);
            data = giveTreeRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "赠送记录不存在");
            }
        }
        return data;
    }
}
