package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IShareRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IShareRecordDAO;
import com.ogc.standard.domain.ShareRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class ShareRecordBOImpl extends PaginableBOImpl<ShareRecord>
        implements IShareRecordBO {

    @Autowired
    private IShareRecordDAO shareRecordDAO;

    @Override
    public String saveShareRecord(String userId, String channel,
            String content) {
        ShareRecord data = new ShareRecord();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ShareRecord.getCode());
        data.setCode(code);
        data.setUserId(userId);
        data.setChannel(channel);
        data.setContent(content);
        data.setCreateDatetime(new Date());
        shareRecordDAO.insert(data);

        return code;
    }

    @Override
    public List<ShareRecord> queryShareRecordList(ShareRecord condition) {
        return shareRecordDAO.selectList(condition);
    }

    @Override
    public ShareRecord getShareRecord(String code) {
        ShareRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            ShareRecord condition = new ShareRecord();
            condition.setCode(code);
            data = shareRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "分享记录不存在！");
            }
        }
        return data;
    }
}
