package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.MaintainRecord;
import com.ogc.standard.dto.req.XN629630Req;

public interface IMaintainRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加养护记录
    public String addMaintainRecord(XN629630Req req);

    // 删除养护记录
    public void dropMaintainRecord(String code);

    // 修改养护记录
    public void editMaintainRecord(String code, String pic, String description,
            String updater, String remark);

    public Paginable<MaintainRecord> queryMaintainRecordPage(int start,
            int limit, MaintainRecord condition);

    public List<MaintainRecord> queryMaintainRecordList(MaintainRecord condition);

    public MaintainRecord getMaintainRecord(String code);

}
