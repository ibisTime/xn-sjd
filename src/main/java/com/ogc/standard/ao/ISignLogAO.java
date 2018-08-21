package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805140Req;

public interface ISignLogAO {

    static final String DEFAULT_ORDER_COLUMN = "id";

    public String addSignLog(XN805140Req req);

    public Paginable<SignLog> querySignLogPage(int start, int limit,
            SignLog condition);

    public List<SignLog> querySignLogList(SignLog condition);

}
