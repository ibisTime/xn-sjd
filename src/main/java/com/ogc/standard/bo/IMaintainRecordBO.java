package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.MaintainRecord;

/**
 * 养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:00:19 
 * @history:
 */
public interface IMaintainRecordBO extends IPaginableBO<MaintainRecord> {

    public boolean isMaintainRecordExist(String code);

    // 添加养护记录
    public String saveMaintainRecord(MaintainRecord data);

    // 删除养护记录
    public void removeMaintainRecord(String code);

    // 修改养护记录
    public void refreshMaintainRecord(String code, String pic,
            String description, String updater, String remark);

    public List<MaintainRecord> queryMaintainRecordList(
            MaintainRecord condition);

    public MaintainRecord getLastMaintainRecord(String treeNumber);

    public MaintainRecord getMaintainRecord(String code);

}
