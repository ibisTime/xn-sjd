package com.ogc.standard.ao;

import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805140Req;
import com.ogc.standard.dto.res.XN629906Res;
import com.ogc.standard.dto.res.XN805140Res;

public interface ISignLogAO {

    static final String DEFAULT_ORDER_COLUMN = "id";

    public void addSignLog(XN805140Req req);

    public XN805140Res doAssignSignTPP(String userId);

    public void doAssignSignJf(String userId);

    public Paginable<SignLog> querySignLogPage(int start, int limit,
            SignLog condition);

    public List<SignLog> querySignLogList(SignLog condition);

    // 连续签到天数
    public long keepCheckIn(String userId, String logType);

    // 本月签到统计
    public XN629906Res monthSignStatistics(String userId,
            Date createStartDatetime, Date createEndDatetime);
}
