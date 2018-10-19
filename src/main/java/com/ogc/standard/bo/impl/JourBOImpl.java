/**
 * @Title AJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:07 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IJourBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IJourDAO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EChannelType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourDirection;
import com.ogc.standard.enums.EJourStatus;
import com.ogc.standard.enums.EJourType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class JourBOImpl extends PaginableBOImpl<Jour> implements IJourBO {
    @Autowired
    private IJourDAO jourDAO;

    @Override
    public String addJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String refNo, String bizType, String bizNote,
            BigDecimal transAmount) {

        if (StringUtils.isBlank(refNo)) {// 必须要有的判断。每一次流水新增，必有有对应流水分组
            throw new BizException("xn000000", "新增流水流水分组不能为空");
        }
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "新增流水变动金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());

        Jour data = new Jour();

        data.setCode(code);
        data.setType(EJourType.BALANCE.getCode());
        data.setUserId(dbAccount.getUserId());
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setAccountType(dbAccount.getType());

        data.setCurrency(dbAccount.getCurrency());
        data.setBizType(bizType);
        data.setBizNote(bizNote);
        data.setTransAmount(transAmount);
        data.setPreAmount(dbAccount.getAmount());

        data.setPostAmount(dbAccount.getAmount().add(transAmount));
        data.setStatus(EJourStatus.todoCheck.getCode());
        data.setChannelType(channelType.getCode());
        data.setChannelOrder(channelOrder);// 内部转账时为空，外部转账时必定有
        data.setRefNo(refNo);

        data.setRemark("记得对账哦");
        data.setCreateDatetime(new Date());
        data.setWorkDate(
            DateUtil.dateToStr(new Date(), DateUtil.DB_DATE_FORMAT_STRING));

        jourDAO.insert(data);
        return code;
    }

    @Override
    public String addFrozenJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String refNo, String bizType, String bizNote,
            BigDecimal transAmount) {

        if (StringUtils.isBlank(refNo)) {// 必须要有的判断。每一次流水新增，必有有对应流水分组
            throw new BizException("xn000000", "新增流水流水分组不能为空");
        }
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "新增流水变动金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());

        Jour data = new Jour();

        data.setCode(code);
        data.setType(EJourType.FROZEN.getCode());
        data.setUserId(dbAccount.getUserId());
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setAccountType(dbAccount.getType());

        data.setCurrency(dbAccount.getCurrency());
        data.setBizType(bizType);
        data.setBizNote(bizNote);
        data.setTransAmount(transAmount);
        data.setPreAmount(
            dbAccount.getAmount().subtract(dbAccount.getFrozenAmount()));

        data.setPostAmount(data.getPreAmount().add(transAmount));
        data.setStatus(EJourStatus.todoCheck.getCode());
        data.setChannelType(channelType.getCode());
        data.setChannelOrder(channelOrder);// 内部转账时为空，外部转账时必定有
        data.setRefNo(refNo);

        data.setRemark("记得对账哦");
        data.setCreateDatetime(new Date());
        data.setWorkDate(
            DateUtil.dateToStr(new Date(), DateUtil.DB_DATE_FORMAT_STRING));

        jourDAO.insert(data);
        return code;
    }

    @Override
    public String addJourForHL(Account dbAccount, String bizType) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.AJour.getCode());

        Jour data = new Jour();
        data.setCode(code);
        data.setAccountNumber(dbAccount.getAccountNumber());
        data.setUserId(dbAccount.getUserId());
        data.setType(dbAccount.getType());
        data.setCurrency(dbAccount.getCurrency());
        data.setChannelType(EChannelType.NBZ.getCode());

        data.setRefNo(null);
        data.setBizType(bizType);
        data.setBizNote("根据红蓝订单《" + "" + "》变动资金");
        data.setTransAmount(null);
        data.setPreAmount(dbAccount.getAmount());

        data.setPostAmount(dbAccount.getAmount().add(null));
        data.setStatus(EJourStatus.noAdjust.getCode());
        data.setCreateDatetime(new Date());
        data.setWorkDate(null);
        jourDAO.insert(data);
        return code;

    }

    @Override
    public void doCheckJour(Jour jour, EBoolean checkResult,
            BigDecimal checkAmount, String checkUser, String checkNote) {
        Jour data = new Jour();
        data.setCode(jour.getCode());
        EJourStatus eJourStatus = EJourStatus.Checked_YES;
        if (EBoolean.NO.equals(checkResult)) {
            eJourStatus = EJourStatus.Checked_NO;
        }
        data.setStatus(eJourStatus.getCode());
        data.setCheckUser(checkUser);
        data.setCheckNote(checkNote + ":调整金额" + checkAmount.toString());
        data.setCheckDatetime(new Date());
        jourDAO.checkJour(data);
    }

    @Override
    public void adjustJourNO(Jour jour, String adjustUser, String adjustNote) {
        Jour data = new Jour();
        data.setCode(jour.getCode());
        data.setStatus(EJourStatus.Checked_YES.getCode());
        data.setAdjustUser(adjustUser);
        data.setAdjustNote(adjustNote);
        data.setAdjustDatetime(new Date());
        jourDAO.adjustJour(data);
    }

    @Override
    public void adjustJourYES(Jour jour, String adjustUser, String adjustNote) {
        Jour data = new Jour();
        data.setCode(jour.getCode());
        data.setStatus(EJourStatus.Adjusted.getCode());
        data.setAdjustUser(adjustUser);
        data.setAdjustNote(adjustNote);
        data.setAdjustDatetime(new Date());
        jourDAO.adjustJour(data);
    }

    @Override
    public Jour getJour(String code) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            data = jourDAO.select(condition);
            if (data == null) {
                throw new BizException("xn000000", "单号不存在");
            }
        }
        return data;
    }

    @Override
    public Jour getJourNotException(String code) {
        Jour data = null;
        if (StringUtils.isNotBlank(code)) {
            Jour condition = new Jour();
            condition.setCode(code);
            data = jourDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        return jourDAO.selectList(condition);
    }

    @Override
    public BigDecimal getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd) {
        Jour jour = new Jour();
        jour.setType(EJourType.BALANCE.getCode());
        jour.setBizType(bizType);
        jour.setChannelType(channelType);
        jour.setAccountNumber(accountNumber);
        jour.setCreateDatetimeStart(DateUtil.getFrontDate(dateStart, false));
        jour.setCreateDatetimeEnd(DateUtil.getFrontDate(dateEnd, true));
        BigDecimal a = jourDAO.selectTotalAmount(jour);
        return a;
    }

    @Override
    public BigDecimal getTotalAmount(String bizType, String channelType,
            String accountNumber) {
        Jour jour = new Jour();
        jour.setType(EJourType.BALANCE.getCode());
        jour.setBizType(bizType);
        jour.setChannelType(channelType);
        jour.setAccountNumber(accountNumber);
        BigDecimal a = jourDAO.selectTotalAmount(jour);
        return a;
    }

    @Override
    public BigDecimal getTotalAmount(Jour condition) {
        return jourDAO.selectTotalAmount(condition);
    }

    @Override
    public BigDecimal getHistoryAmount(String accountNumber, String direction) {
        Jour condition = new Jour();
        condition.setAccountNumber(accountNumber);
        List<String> bizTypeList = new ArrayList<String>();

        // 积分池
        if (ESystemAccount.SYS_ACOUNT_JF_POOL.getCode().equals(accountNumber)) {

            // 发放
            if (EJourDirection.OUT.getCode().equals(direction)) {
                bizTypeList.add(EJourBizTypePlat.REGIST.getCode());
                bizTypeList.add(EJourBizTypePlat.BIND_MOBILE.getCode());
                bizTypeList.add(EJourBizTypePlat.BIND_email.getCode());
                bizTypeList.add(EJourBizTypePlat.UPLOAD_PHOTO.getCode());
                bizTypeList.add(EJourBizTypePlat.COMPLETE_INFO.getCode());
                bizTypeList.add(EJourBizTypePlat.LOGIN.getCode());
                bizTypeList.add(EJourBizTypePlat.INVITE_USER.getCode());
                bizTypeList.add(EJourBizTypePlat.ADOPT_PAY_BACK.getCode());
            }

            // 回收
            if (EJourDirection.IN.getCode().equals(direction)) {
                bizTypeList.add(EJourBizTypePlat.ADOPT_BUY_DEDUCT.getCode());
                bizTypeList.add(EJourBizTypePlat.TOOL_BUY.getCode());
            }
        }

        // 碳泡泡
        if (ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode()
            .equals(accountNumber)) {

            // 发放
            if (EJourDirection.OUT.getCode().equals(direction)) {
                bizTypeList.add(EJourBizTypePlat.SIGN.getCode());
                bizTypeList.add(EJourBizTypePlat.ADOPT_DAY_BACK.getCode());
                bizTypeList.add(EJourBizTypePlat.SHARE.getCode());
            }

            // 回收
            if (EJourDirection.IN.getCode().equals(direction)) {
                bizTypeList.add("");
            }

        }

        condition.setBizTypeList(bizTypeList);
        return jourDAO.selectTotalAmount(condition);
    }

}
