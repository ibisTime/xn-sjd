package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BizLog;

public interface IBizLogBO extends IPaginableBO<BizLog> {

    // 留言
    public long leaveMessage(String adoptTreeCode, String note, String userId);

    // 收取碳泡泡
    public long gatherCarbonBubble(String adoptTreeCode, String adoptUserId,
            BigDecimal quantity, String userId, String type);

    // 收取碳泡泡被阻挡
    public long gatherNoCarbonBubble(String adoptTreeCode, String adoptUserId,
            String userId);

    // 使用保护罩
    public long useShelter(String adoptTreeCode, String adoptUserId,
            String userId);

    // 使用一键收取
    public long useGetall(String adoptTreeCode, String adoptUserId,
            String userId, BigDecimal quantity);

    // 本周用户碳泡泡总量
    public BigDecimal getWeekQuantitySum(String adoptUserId, String userId);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
