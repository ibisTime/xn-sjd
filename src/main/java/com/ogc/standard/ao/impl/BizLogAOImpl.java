
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.bo.IBarrageBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Barrage;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629900Res;
import com.ogc.standard.enums.EBizLogType;

@Service
public class BizLogAOImpl implements IBizLogAO {

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IBarrageBO barrageBO;

    @Override
    public XN629900Res weekTpp(String userId, String toUserId) {
        XN629900Res res = new XN629900Res();

        // 用户信息
        User userInfo = userBO.getUser(userId);
        res.setUserInfo(userInfo);

        // 好友信息
        User toUserInfo = userBO.getUser(toUserId);
        res.setToUserInfo(toUserInfo);

        // 用户本周取好友碳泡泡数量
        BigDecimal userWeekQuantity = bizLogBO.getWeekQuantitySum(toUserId,
            userId);
        res.setUserWeekQuantity(userWeekQuantity);

        // 好友本周取用户碳泡泡数量
        BigDecimal toUserWeekQuantity = bizLogBO.getWeekQuantitySum(userId,
            toUserId);
        res.setToUserWeekQuantity(toUserWeekQuantity);

        return res;
    }

    @Override
    public Paginable<BizLog> queryBizLogPage(int start, int limit,
            BizLog condition) {
        Paginable<BizLog> page = bizLogBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (BizLog bizLog : page.getList()) {
                initBizLog(bizLog);
            }
        }
        return page;
    }

    @Override
    public List<BizLog> queryBizLogList(BizLog condition) {
        List<BizLog> list = bizLogBO.queryBizLogList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (BizLog bizLog : list) {
                initBizLog(bizLog);
            }
        }

        return list;
    }

    @Override
    public BizLog getBizLog(int id) {
        BizLog bizLog = bizLogBO.getBizLog(id);

        initBizLog(bizLog);

        return bizLog;
    }

    private void initBizLog(BizLog bizLog) {
        // 操作人
        User user = userBO.getUserUnCheck(bizLog.getUserId());
        bizLog.setUserInfo(user);

        // 认养人
        User adoptUser = userBO.getUserUnCheck(bizLog.getAdoptUserId());
        bizLog.setAdoptUserInfo(adoptUser);

        // 弹幕内容
        if (EBizLogType.BARRAGE.getCode().equals(bizLog.getType())) {
            Barrage barrage = barrageBO.getBarrage(bizLog.getNote());

            bizLog.setBarrageContent(barrage.getContent());
            bizLog.setBarragePic(barrage.getPic());
        }
    }

}
