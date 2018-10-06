package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IShareRecordAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IShareRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.ShareRecord;
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

    @Override
    @Transactional
    public String addShareRecord(String userId, String channel) {
        String code = shareRecordBO.saveShareRecord(userId, channel);

        // 添加碳泡泡
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
        BigDecimal quantity = new BigDecimal(configMap.get(SysConstants.SHARE));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userTppAccount = accountBO.getAccountByUser(userId,
            ECurrency.TPP.getCode());
        Account sysTppAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_TPP.getCode());

        accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
            EJourBizTypeUser.SIGN.getCode(), EJourBizTypePlat.SIGN.getCode(),
            EJourBizTypeUser.SIGN.getValue(), EJourBizTypePlat.SIGN.getValue(),
            userId);

        return code;

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
