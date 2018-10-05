package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BizLog;

public interface IBizLogBO extends IPaginableBO<BizLog> {

    public int saveBizLog(BizLog data);

    // 留言
    public long leaveMessage(String adoptTreeCode, String note, String userId);

    // 收取碳泡泡
    public long gatherCarbonBubble(String adoptTreeCode, BigDecimal quantity,
            String userId, String type);

    // 本周用户碳泡泡总量
    public long getWeekQuantitySum(String adoptUserId, String userId);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
