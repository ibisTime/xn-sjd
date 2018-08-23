package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISignLogDAO;
import com.ogc.standard.domain.SignLog;

@Component
public class SignLogBOImpl extends PaginableBOImpl<SignLog>
        implements ISignLogBO {

    @Autowired
    private ISignLogDAO signLogDAO;

    @Override
    public boolean isSignLogExist(Integer id) {
        SignLog condition = new SignLog();
        condition.setId(id);
        if (signLogDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveSignLog(SignLog data) {
        // 获取用户ip
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder
        // .getRequestAttributes()).getRequest();
        // data.setIp(request.getRemoteAddr());
        data.setLocation("location");
        data.setCreateDatetime(new Date());

        return signLogDAO.insert(data);
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogDAO.selectList(condition);
    }

    @Override
    public boolean isCheckIn(String userId) {
        SignLog condition = new SignLog();
        condition.setUserId(userId);
        List<SignLog> signLogList = querySignLogList(condition);
        sort(signLogList);
        if (signLogList.get(0) == null) {
            return false;
        }
        Date now = new Date();
        long day = signLogList.get(0).getCreateDatetime().getTime() / 86400000;
        long nowDay = now.getTime() / 86400000;
        if (day == nowDay) {
            return true;
        }
        return false;
    }

    // 将List按照日期排序
    @Override
    public void sort(List<SignLog> dataList) {
        int l = dataList.size();
        SignLog t1 = null;
        SignLog t2 = null;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < i; j++) {
                if (dataList.get(j).getCreateDatetime()
                    .before(dataList.get(j + 1).getCreateDatetime())) {
                    t1 = dataList.get(j);
                    t2 = dataList.get(j + 1);
                    dataList.set(j, t2);
                    dataList.set(j + 1, t1);
                }
            }
        }
    }

}
