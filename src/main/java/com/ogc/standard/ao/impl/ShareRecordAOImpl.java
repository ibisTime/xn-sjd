package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IShareRecordAO;
import com.ogc.standard.bo.IShareRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ShareRecord;

@Service
public class ShareRecordAOImpl implements IShareRecordAO {

    @Autowired
    private IShareRecordBO shareRecordBO;

    @Override
    public String addShareRecord(String userId, String channel) {
        return shareRecordBO.saveShareRecord(userId, channel);
    }

    @Override
    public Paginable<ShareRecord> queryShareRecordPage(int start, int limit,
            ShareRecord condition) {
        return shareRecordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ShareRecord> queryShareRecordList(ShareRecord condition) {
        return shareRecordBO.queryShareRecordList(condition);
    }

    @Override
    public ShareRecord getShareRecord(String code) {
        return shareRecordBO.getShareRecord(code);
    }
}
