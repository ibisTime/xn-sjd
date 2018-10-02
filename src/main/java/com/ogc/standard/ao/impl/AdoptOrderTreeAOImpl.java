package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.GiveTreeRecord;

@Service
public class AdoptOrderTreeAOImpl implements IAdoptOrderTreeAO {

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Override
    public void giveTree(String code, String toUserId, String userId) {
        // 更改认养权持有人
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        data.setCurrentHolder(toUserId);
        adoptOrderTreeBO.giveTree(data);
        // 新增赠送树记录
        GiveTreeRecord record = new GiveTreeRecord();
        record.setAdoptTreeCode(code);
        record.setUserId(userId);
        record.setToUserId(toUserId);
        record.setCreateDatetime(new Date());
        giveTreeRecordBO.saveGiveTreeRecord(record);
    }

    @Override
    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition) {
        return adoptOrderTreeBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition) {
        return adoptOrderTreeBO.queryAdoptOrderTreeList(condition);
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        return adoptOrderTreeBO.getAdoptOrderTree(code);
    }

}
