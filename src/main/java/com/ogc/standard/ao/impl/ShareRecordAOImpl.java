package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IShareRecordAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IShareRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.ShareRecord;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESystemAccount;

@Service
public class ShareRecordAOImpl implements IShareRecordAO {

    @Autowired
    private IShareRecordBO shareRecordBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String addShareRecord(String userId, String channel,
            String content) {
        String code = shareRecordBO.saveShareRecord(userId, channel, content);

        // 添加碳泡泡
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
        BigDecimal quantity = new BigDecimal(configMap.get(SysConstants.SHARE));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userTppAccount = accountBO.getAccountByUser(userId,
            ECurrency.TPP.getCode());
        Account sysTppAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());

        // if (quantity.compareTo(sysTppAccount.getAmount()) == 1) {
        // quantity = sysTppAccount.getAmount();
        // }

        accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
            EJourBizTypeUser.SHARE.getCode(), EJourBizTypePlat.SHARE.getCode(),
            EJourBizTypeUser.SHARE.getValue(),
            EJourBizTypePlat.SHARE.getValue(), userId);

        return code;

    }

    @Override
    public Paginable<ShareRecord> queryShareRecordPage(int start, int limit,
            ShareRecord condition) {
        Paginable<ShareRecord> page = shareRecordBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ShareRecord shareRecord : page.getList()) {
                init(shareRecord);
            }
        }
        return page;
    }

    @Override
    public List<ShareRecord> queryShareRecordList(ShareRecord condition) {
        List<ShareRecord> list = shareRecordBO.queryShareRecordList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (ShareRecord shareRecord : list) {
                init(shareRecord);
            }
        }

        return list;
    }

    @Override
    public ShareRecord getShareRecord(String code) {
        ShareRecord shareRecord = shareRecordBO.getShareRecord(code);

        init(shareRecord);

        return shareRecord;
    }

    private void init(ShareRecord shareRecord) {
        String userName = null;
        User user = userBO.getUserUnCheck(shareRecord.getUserId());
        if (null != user) {
            userName = user.getMobile();
            if (null != user.getRealName()) {
                userName = user.getRealName() + userName;
            }
            shareRecord.setUserName(userName);
        }

    }

}
