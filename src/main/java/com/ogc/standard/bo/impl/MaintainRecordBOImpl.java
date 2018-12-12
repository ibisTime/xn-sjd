package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IMaintainRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IMaintainRecordDAO;
import com.ogc.standard.domain.MaintainRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class MaintainRecordBOImpl extends PaginableBOImpl<MaintainRecord>
        implements IMaintainRecordBO {

    @Autowired
    private IMaintainRecordDAO maintainRecordDAO;

    @Override
    public boolean isMaintainRecordExist(String code) {
        MaintainRecord condition = new MaintainRecord();
        condition.setCode(code);
        if (maintainRecordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveMaintainRecord(MaintainRecord data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.MaintainRecord.getCode());
            data.setCode(code);
            maintainRecordDAO.insert(data);
        }
        return code;
    }

    @Override
    public void removeMaintainRecord(String code) {
        if (StringUtils.isNotBlank(code)) {
            MaintainRecord data = new MaintainRecord();
            data.setCode(code);
            maintainRecordDAO.delete(data);
        }
    }

    @Override
    public void refreshMaintainRecord(String code, String pic,
            String description, String updater, String remark) {
        MaintainRecord data = new MaintainRecord();

        data.setCode(code);
        data.setPic(pic);
        data.setDescription(description);
        data.setUpdateDatetime(new Date());
        data.setUpdater(updater);

        data.setRemark(remark);
        maintainRecordDAO.updateMaintainRecord(data);
    }

    @Override
    public List<MaintainRecord> queryMaintainRecordList(
            MaintainRecord condition) {
        return maintainRecordDAO.selectList(condition);
    }

    @Override
    public MaintainRecord getLastMaintainRecord(String treeNumber) {
        MaintainRecord data = null;

        if (StringUtils.isNotBlank(treeNumber)) {
            MaintainRecord condition = new MaintainRecord();
            condition.setTreeNumber(treeNumber);
            condition.setOrder("update_datetime", "desc limit 0, 1 ");
            List<MaintainRecord> maintainRecordList = queryMaintainRecordList(
                condition);
            if (CollectionUtils.isNotEmpty(maintainRecordList)) {
                data = maintainRecordList.get(0);
            }
        }

        return data;
    }

    @Override
    public MaintainRecord getMaintainRecord(String code) {
        MaintainRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            MaintainRecord condition = new MaintainRecord();
            condition.setCode(code);
            data = maintainRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "养护记录不存在！");
            }
        }
        return data;
    }

}
