package com.ogc.standard.bo.impl;

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
    public boolean isGiveTreeRecordExist(String code) {
        GiveTreeRecord condition = new GiveTreeRecord();
        condition.setCode(code);
        if (giveTreeRecordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveGiveTreeRecord(GiveTreeRecord data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.GIVE_TREE_RECORD
                .getCode());
            data.setCode(code);
            giveTreeRecordDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeGiveTreeRecord(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            GiveTreeRecord data = new GiveTreeRecord();
            data.setCode(code);
            count = giveTreeRecordDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshGiveTreeRecord(GiveTreeRecord data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = giveTreeRecordDAO.update(data);
        }
        return count;
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
