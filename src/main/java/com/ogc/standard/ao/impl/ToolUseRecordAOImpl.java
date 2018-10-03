package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IToolUseRecordAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EToolUseRecordStatus;

@Service
public class ToolUseRecordAOImpl implements IToolUseRecordAO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IToolOrderBO toolOrderBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IToolUseRecordBO toolUseRecordBO;

    @Override
    public Paginable<ToolUseRecord> queryToolUseRecordPage(int start, int limit,
            ToolUseRecord condition) {

        Paginable<ToolUseRecord> page = toolUseRecordBO.getPaginable(start,
            limit, condition);

        if (null != page) {
            for (ToolUseRecord toolUseRecord : page.getList()) {
                init(toolUseRecord);
            }
        }

        return page;
    }

    @Override
    public List<ToolUseRecord> queryToolUseRecordList(ToolUseRecord condition) {
        List<ToolUseRecord> list = toolUseRecordBO
            .queryToolUseRecordList(condition);

        for (ToolUseRecord toolUseRecord : list) {
            init(toolUseRecord);
        }

        return list;
    }

    @Override
    public ToolUseRecord getToolUseRecord(String code) {
        ToolUseRecord toolUseRecord = toolUseRecordBO.getToolUseRecord(code);
        init(toolUseRecord);
        return toolUseRecord;
    }

    private void init(ToolUseRecord toolUseRecord) {

        ToolOrder toolOrder = toolOrderBO
            .getToolOrder(toolUseRecord.getToolOrderCode());
        toolUseRecord.setToolOrderInfo(toolOrder);

        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(toolUseRecord.getAdoptTreeCode());
        toolUseRecord.setAdoptTreeInfo(adoptOrderTree);

        User user = userBO.getUser(toolUseRecord.getUserId());
        toolUseRecord.setUserInfo(user);
    }

    /**
     * 定时验证道具使用记录是否失效
     *  
     * @create: 2018年10月3日 下午8:49:46 lei
     * @history:
     */
    public void recordStatusValidater() {

        ToolUseRecord condition = new ToolUseRecord();
        condition.setStatus(EToolUseRecordStatus.ACTIVE.getCode());
        List<ToolUseRecord> list = queryToolUseRecordList(condition);

        for (ToolUseRecord toolUseRecord : list) {
            if (new Date().after(toolUseRecord.getInvalidDatetime())) {
                toolUseRecordBO.doInvalid(toolUseRecord);
            }
        }
    }
}
