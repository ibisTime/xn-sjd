package com.ogc.standard.bo.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
        ListSort(signLogList);

        if (CollectionUtils.isEmpty(signLogList)
                || signLogList.get(0) == null) {
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
    public void ListSort(List<SignLog> list) {
        Collections.sort(list, new Comparator<SignLog>() {
            @Override
            public int compare(SignLog o1, SignLog o2) {

                if (o1.getCreateDatetime().getTime() > o2.getCreateDatetime()
                    .getTime()) {
                    return -1;
                } else if (o1.getCreateDatetime().getTime() < o2
                    .getCreateDatetime().getTime()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

}
