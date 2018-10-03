package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ShareRecord;

/**
 * 分享记录
 * @author: silver 
 * @since: Sep 29, 2018 9:59:43 PM 
 * @history:
 */
public interface IShareRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加分享记录
    public String addShareRecord(String userId, String channel);

    public Paginable<ShareRecord> queryShareRecordPage(int start, int limit,
            ShareRecord condition);

    public List<ShareRecord> queryShareRecordList(ShareRecord condition);

    public ShareRecord getShareRecord(String code);

}
