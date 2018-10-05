package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629900Res;

@Service
public class BizLogAOImpl implements IBizLogAO {

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public XN629900Res weekTpp(String userId, String toUserId) {
        XN629900Res res = new XN629900Res();

        // 用户信息
        User userInfo = userBO.getUser(userId);
        res.setUserInfo(userInfo);

        // 好友信息
        User toUserInfo = userBO.getUser(toUserId);
        res.setToUserInfo(toUserInfo);

        // 用户本周碳泡泡数量
        long userWeekQuantity = bizLogBO.getWeekQuantitySum(toUserId, userId);
        res.setUserWeekQuantity(userWeekQuantity);

        // 好友本周碳泡泡数量
        long toUserWeekQuantity = bizLogBO.getWeekQuantitySum(userId, toUserId);
        res.setToUserWeekQuantity(toUserWeekQuantity);

        return res;
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
