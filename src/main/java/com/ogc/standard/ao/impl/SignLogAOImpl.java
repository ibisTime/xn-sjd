package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805140Req;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Override
    public String addSignLog(XN805140Req req) {
        SignLog data = new SignLog();
        data.setClient(req.getClient());
        data.setIp(req.getIp());
        data.setLocation(req.getLocation());
        data.setUserId(req.getUserId());
        return signLogBO.saveSignLog(data);
    }

    @Override
    public Paginable<SignLog> querySignLogPage(int start, int limit,
            SignLog condition) {
        return signLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogBO.querySignLogList(condition);
    }

}
