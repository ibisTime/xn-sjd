package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ISignLogBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.domain.User;
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

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public void addSignLog(XN805140Req req) {
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

        signLogBO.saveSignLog(data);

    }

    @Override
    public XN805140Res doAssignSignTPP(String userId) {
        BigDecimal quantity = BigDecimal.ZERO;
        long continueSignDay = 1L;

        if (signLogBO.isFirstCheckIn(userId, ESignLogType.SIGN_IN.getCode())) {

            // 添加碳泡泡
            continueSignDay = keepCheckIn(userId,
                ESignLogType.SIGN_IN.getCode());// 连续签到天数

            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
            quantity = new BigDecimal(configMap.get(SysConstants.SIGN_TPP));
            BigDecimal continueSignRate = new BigDecimal(
                configMap.get(SysConstants.CONTINUE_SIGN_RATE));
            quantity = AmountUtil.mul(quantity, 1000L);
            quantity = AmountUtil.mul(quantity, continueSignDay);// 连续签到天数
            quantity = AmountUtil.mul(quantity, continueSignRate);// 连续签到比例

            Account userTppAccount = accountBO.getAccountByUser(userId,
                ECurrency.TPP.getCode());
            Account sysTppAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());

            if (quantity.compareTo(sysTppAccount.getAmount()) == 1) {
                quantity = sysTppAccount.getAmount();
            }

            String note = "获得" + AmountUtil.div(quantity, 1000L).intValue()
                    + "碳泡泡，已连续签到" + continueSignDay + "天";
            accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
                EJourBizTypeUser.SIGN.getCode(),
                EJourBizTypePlat.SIGN.getCode(), note, note, userId);

        }

        return new XN805140Res(quantity.intValue(), continueSignDay);
    }

    @Override
    public Paginable<SignLog> querySignLogPage(int start, int limit,
            SignLog condition) {
        Paginable<SignLog> page = signLogBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (SignLog signLog : page.getList()) {

                init(signLog);

            }
        }

        return page;
    }

    @Override
    public List<SignLog> querySignLogList(SignLog condition) {
        return signLogBO.querySignLogList(condition);
    }

    @Override
    public long keepCheckIn(String userId, String logType) {
        SignLog condition = new SignLog();
        condition.setUserId(userId);
        condition.setType(logType);
        List<SignLog> signLogList = signLogBO.querySignLogList(condition);
        // 排序
        signLogBO.ListSort(signLogList);
        // 没有签到数据返回0
        if (signLogList.size() == 0) {
            return 0;
        }
        // 今天没签到返回0
        if (!signLogBO.isCheckIn(userId, logType)) {
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

    private void init(SignLog signLog) {
        // 签到人
        String userName = null;
        User user = userBO.getUserUnCheck(signLog.getUserId());
        if (null != user) {
            userName = user.getMobile();
            if (StringUtils.isNotBlank(user.getRealName())) {
                userName = user.getRealName().concat("-").concat(userName);
            }
        }

        signLog.setUserName(userName);
    }
}
