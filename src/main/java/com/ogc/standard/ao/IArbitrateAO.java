package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Arbitrate;

public interface IArbitrateAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    // 仲裁工单处理
    public void handle(String code, String result, String updater, String remark);

    public Paginable<Arbitrate> queryArbitratePage(int start, int limit,
            Arbitrate condition);

    public List<Arbitrate> queryArbitrateList(Arbitrate condition);

    public Arbitrate getArbitrate(String code);

}
