package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BizLog;

public interface IBizLogBO extends IPaginableBO<BizLog> {

    public int saveBizLog(BizLog data);

    public List<BizLog> queryBizLogList(BizLog condition);

    public BizLog getBizLog(int id);

}
