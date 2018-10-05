package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BizLog;

public interface IBizLogBO extends IPaginableBO<BizLog> {

    public int saveBizLog(BizLog data);

    // 留言
    public long leaveMessage(String adoptTreeCode, String note, String userId);

    // 收取碳泡泡
    public long gatherCarbonBubble(String adoptTreeCode, Integer quantity,
            String userId);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
