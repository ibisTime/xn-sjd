package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IGiveCarbonBubbleRecordAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.IGiveCarbonBubbleRecordBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EBizLogType;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.exception.BizException;

@Service
public class GiveCarbonBubbleRecordAOImpl implements IGiveCarbonBubbleRecordAO {

    @Autowired
    private IGiveCarbonBubbleRecordBO giveCarbonBubbleRecordBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String addGiveCarbonBubbleRecord(String userId, String toUserId) {
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.PRESENT_TPP));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userTppAccount = accountBO.getAccountByUser(userId,
            ECurrency.TPP.getCode());
        if (quantity.compareTo(userTppAccount.getAmount()) == 1) {
            throw new BizException("xn000", "碳泡泡余额不足，无法赠送!");
        }

        Account toUserTppAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.TPP.getCode());

        String recordCode = giveCarbonBubbleRecordBO
            .saveGiveCarbonBubbleRecord(userId, toUserId, quantity);

        // 余额划转
        accountBO.transAmount(userTppAccount, toUserTppAccount, quantity,
            EJourBizTypeUser.PRESENT.getCode(),
            EJourBizTypePlat.PRESENT.getCode(),
            EJourBizTypeUser.PRESENT.getValue(),
            EJourBizTypePlat.PRESENT.getValue(), recordCode);

        // 添加日志
        bizLogBO.gatherCarbonBubble(null, toUserId, quantity, userId,
            EBizLogType.GIVE.getCode());

        return recordCode;
    }

    @Override
    public Paginable<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordPage(
            int start, int limit, GiveCarbonBubbleRecord condition) {
        Paginable<GiveCarbonBubbleRecord> page = giveCarbonBubbleRecordBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (GiveCarbonBubbleRecord giveCarbonBubbleRecord : page
                .getList()) {
                init(giveCarbonBubbleRecord);
            }
        }

        return page;
    }

    @Override
    public List<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordList(
            GiveCarbonBubbleRecord condition) {
        return giveCarbonBubbleRecordBO
            .queryGiveCarbonBubbleRecordList(condition);
    }

    @Override
    public GiveCarbonBubbleRecord getGiveCarbonBubbleRecord(String code) {
        GiveCarbonBubbleRecord giveCarbonBubbleRecord = giveCarbonBubbleRecordBO
            .getGiveCarbonBubbleRecord(code);

        init(giveCarbonBubbleRecord);

        return giveCarbonBubbleRecord;
    }

    private void init(GiveCarbonBubbleRecord giveCarbonBubbleRecord) {
        // 赠送人
        String userName = null;
        User user = userBO.getUserUnCheck(giveCarbonBubbleRecord.getUserId());
        if (null != user) {
            userName = user.getMobile();
            if (StringUtils.isNotBlank(user.getRealName())) {
                userName = user.getRealName().concat("-").concat(userName);
            }
        }

        // 被赠送人
        String toUserName = null;
        User toUser = userBO
            .getUserUnCheck(giveCarbonBubbleRecord.getToUserId());
        if (null != toUser) {
            toUserName = user.getMobile();
            if (StringUtils.isNotBlank(toUser.getRealName())) {
                toUserName = toUser.getRealName().concat("-")
                    .concat(toUserName);
            }
        }

        giveCarbonBubbleRecord.setUserName(userName);
        giveCarbonBubbleRecord.setToUserName(toUserName);
    }
}
