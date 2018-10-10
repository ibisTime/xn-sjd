package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805140Req;
import com.ogc.standard.dto.res.XN805140Res;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESignLogClient;
import com.ogc.standard.enums.ESignLogType;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;

@Service
public class SignLogAOImpl implements ISignLogAO {

    @Autowired
    private ISignLogBO signLogBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public XN805140Res addSignLog(XN805140Req req) {
        if (signLogBO.isCheckIn(req.getUserId(),
            ESignLogType.SIGN_IN.getCode())) {
            throw new BizException("3", "今日已签到");
        }
        SignLog data = new SignLog();
        data.setClient(req.getClient());
        data.setIp(req.getIp());
        data.setLocation(req.getLocation());
        data.setUserId(req.getUserId());
        data.setType(ESignLogType.SIGN_IN.getCode());
        data.setClient(ESignLogClient.ANDROID.getCode());

        // 添加碳泡泡
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
        BigDecimal quantity = new BigDecimal(
            configMap.get(SysConstants.SIGN_TPP));
        quantity = AmountUtil.mul(quantity, 1000L);

        Account userTppAccount = accountBO.getAccountByUser(req.getUserId(),
            ECurrency.TPP.getCode());
        Account sysTppAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());

        if (quantity.compareTo(sysTppAccount.getAmount()) == 1) {
            quantity = sysTppAccount.getAmount();
        }

        accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
            EJourBizTypeUser.SIGN.getCode(), EJourBizTypePlat.SIGN.getCode(),
            EJourBizTypeUser.SIGN.getValue(), EJourBizTypePlat.SIGN.getValue(),
            req.getUserId());

        signLogBO.saveSignLog(data);
        return new XN805140Res(quantity.intValue(), null);
    }

    @Override
    public Paginable<SignLog> querySignLogPage(int start, int limit,
            SignLog condition) {
        return signLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogBO.querySignLogList(condition);
    }

    @Override
    public long keepCheckIn(String userId) {
        SignLog condition = new SignLog();
        condition.setUserId(userId);
        List<SignLog> signLogList = signLogBO.querySignLogList(condition);
        // 排序
        signLogBO.ListSort(signLogList);
        // 没有签到数据返回0
        if (signLogList.size() == 0) {
            return 0;
        }
        // 今天没签到返回0
        if (!signLogBO.isCheckIn(userId, ESignLogType.SIGN_IN.getCode())) {
            return 0;
        } else {
            long count = 1;
            long dayNum = 0;
            for (int i = 0; i < signLogList.size() - 1; i++) {
                // 获取连续数据的天数差（24*60*60*1000=86400000ms）
                dayNum = signLogList.get(i).getCreateDatetime().getTime()
                        / 86400000
                        - signLogList.get(i + 1).getCreateDatetime().getTime()
                                / 86400000;
                if (dayNum == 1) {
                    count++;
                } else {
                    break;
                }
            }

            return count;
        }
    }

}
