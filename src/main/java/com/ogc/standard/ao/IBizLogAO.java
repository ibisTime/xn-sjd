package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.dto.res.XN629900Res;

public interface IBizLogAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    // 留言
    public long leaveMessage(String adoptTreeCode, String note, String userId);

    // 本周能量比拼
    public XN629900Res weekTpp(String userId, String toUserId);

    public Paginable<BizLog> queryBizLogPage(int start, int limit,
            BizLog condition);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
