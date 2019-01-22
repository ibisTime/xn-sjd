package com.ogc.standard.dto.res;

import java.util.Date;
import java.util.List;

/**
 * 列表查询连续签到
 * @author: silver 
 * @since: Jan 22, 2019 10:00:38 PM 
 * @history:
 */
public class XN805146Res {
    // 签到时间列表
    private List<Date> signResList;

    public List<Date> getSignResList() {
        return signResList;
    }

    public void setSignResList(List<Date> signResList) {
        this.signResList = signResList;
    }

    public XN805146Res(List<Date> signResList) {
        super();
        this.signResList = signResList;
    }

}
