package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.domain.User;

@Service
public class GiveTreeRecordAOImpl implements IGiveTreeRecordAO {

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<GiveTreeRecord> queryGiveTreeRecordPage(int start,
            int limit, GiveTreeRecord condition) {
        Paginable<GiveTreeRecord> page = giveTreeRecordBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (GiveTreeRecord giveTreeRecord : page.getList()) {
                init(giveTreeRecord);
            }
        }

        return page;
    }

    @Override
    public List<GiveTreeRecord> queryGiveTreeRecordList(
            GiveTreeRecord condition) {
        List<GiveTreeRecord> list = giveTreeRecordBO
            .queryGiveTreeRecordList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (GiveTreeRecord giveTreeRecord : list) {
                init(giveTreeRecord);
            }
        }

        return list;
    }

    @Override
    public GiveTreeRecord getGiveTreeRecord(String code) {
        GiveTreeRecord giveTreeRecord = giveTreeRecordBO
            .getGiveTreeRecord(code);

        init(giveTreeRecord);

        return giveTreeRecord;
    }

    private void init(GiveTreeRecord giveTreeRecord) {
        // 赠送人
        User user = userBO.getUserUnCheck(giveTreeRecord.getUserId());
        giveTreeRecord.setUserName(getRealName(user));

        // 被赠送人
        User toUser = userBO.getUserUnCheck(giveTreeRecord.getToUserId());
        giveTreeRecord.setToUserName(getRealName(toUser));
    }

    private String getRealName(User user) {
        String realName = user.getMobile();

        if (StringUtils.isNotBlank(user.getRealName())) {
            realName = user.getRealName().concat("-").concat(realName);
        }

        return realName;
    }

}
