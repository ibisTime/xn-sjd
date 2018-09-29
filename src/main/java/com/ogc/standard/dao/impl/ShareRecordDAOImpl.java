package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IShareRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ShareRecord;

@Repository("shareRecordDAOImpl")
public class ShareRecordDAOImpl extends AMybatisTemplate
        implements IShareRecordDAO {

    @Override
    public int insert(ShareRecord data) {
        return super.insert(NAMESPACE.concat("insert_shareRecord"), data);
    }

    @Override
    public int delete(ShareRecord data) {
        return 0;
    }

    @Override
    public ShareRecord select(ShareRecord condition) {
        return super.select(NAMESPACE.concat("select_shareRecord"), condition,
            ShareRecord.class);
    }

    @Override
    public long selectTotalCount(ShareRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_shareRecord_count"), condition);
    }

    @Override
    public List<ShareRecord> selectList(ShareRecord condition) {
        return super.selectList(NAMESPACE.concat("select_shareRecord"),
            condition, ShareRecord.class);
    }

    @Override
    public List<ShareRecord> selectList(ShareRecord condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_shareRecord"), start,
            count, condition, ShareRecord.class);
    }

}
