package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ShareRecord;

/**
 * 分享记录
 * @author: silver 
 * @since: Sep 29, 2018 9:50:47 PM 
 * @history:
 */
public interface IShareRecordBO extends IPaginableBO<ShareRecord> {

    // 添加分享记录
    public String saveShareRecord(String userId, String channel);

    public List<ShareRecord> queryShareRecordList(ShareRecord condition);

    public ShareRecord getShareRecord(String code);

}
