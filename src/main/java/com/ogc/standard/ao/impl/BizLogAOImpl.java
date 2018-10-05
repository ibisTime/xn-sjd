package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BizLog;

@Service
public class BizLogAOImpl implements IBizLogAO {

    @Autowired
    private IBizLogBO bizLogBO;

    @Override
    public long leaveMessage(String adoptTreeCode, String note, String userId) {
        return bizLogBO.leaveMessage(adoptTreeCode, note, userId);
    }

    @Override
    public Paginable<BizLog> queryBizLogPage(int start, int limit,
            BizLog condition) {
        return bizLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BizLog> queryBizLogList(BizLog condition) {
        return bizLogBO.queryBizLogList(condition);
    }

    @Override
    public BizLog getBizLog(int id) {
        return bizLogBO.getBizLog(id);
    }

}
