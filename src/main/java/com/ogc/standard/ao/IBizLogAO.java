package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BizLog;

@Component
public interface IBizLogAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public int addBizLog(BizLog data);

    public Paginable<BizLog> queryBizLogPage(int start, int limit,
            BizLog condition);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
