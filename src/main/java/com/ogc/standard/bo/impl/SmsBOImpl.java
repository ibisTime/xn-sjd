package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISmsBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.PhoneUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ISmsDAO;
import com.ogc.standard.domain.Sms;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAccountType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESmsStauts;
import com.ogc.standard.enums.ESmsType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午2:14:47 
 * @history:
 */
@Component
public class SmsBOImpl extends PaginableBOImpl<Sms> implements ISmsBO {

    @Autowired
    private ISmsDAO smsDAO;

    @Autowired
    private IUserBO userBO;

    @Override
    public boolean isSmsExit(String code) {
        Sms sms = new Sms();
        sms.setCode(code);
        if (smsDAO.selectTotalCount(sms) >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public String saveSms(Sms data) {
        if (data != null) {

            data.setCreateDatetime(new Date());
            smsDAO.insert(data);
        }
        return data.getCode();
    }

    @Override
    public int refreshSms(Sms data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = smsDAO.updateSms(data);
        }
        return count;
    }

    @Override
    public int revokeSms(String code, String updater, String remark) {
        Sms data = new Sms();
        data.setCode(code);
        data.setUpdater(updater);
        data.setRemark(remark);
        data.setUpdateDatetime(new Date());
        data.setStatus(ESmsStauts.WITHDRAW.getCode());
        return smsDAO.updateStatus(data);
    }

    @Override
    public void saveAdoptBulletin(String userId, String count, String sellType,
            String productName) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        String hours = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_8);
        User user = userBO.getUserUnCheck(userId);

        String userName = user.getNickname();
        if (StringUtils.isEmpty(userName)) {
            userName = PhoneUtil.hideMobile(user.getMobile());
        }

        String unit = "棵";
        if (ESellType.DONATE.getCode().equals(sellType)
                || ESellType.COLLECTIVE.getCode().equals(sellType)
                || ESellType.PRESELL.getCode().equals(sellType)) {
            unit = "份";
        }

        String content = userName + "在" + hours + "认养" + count + unit
                + productName;

        sms.setCode(code);
        sms.setType(ESmsType.BULLETIN.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setTitle(ESmsType.BULLETIN.getValue());
        sms.setContent(content);

        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());
        smsDAO.insert(sms);
    }

    @Override
    public void saveFirstAdoptBulletin(String userId, String productName) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        User user = userBO.getUserUnCheck(userId);

        String userName = user.getNickname();
        if (StringUtils.isEmpty(userName)) {
            userName = PhoneUtil.hideMobile(user.getMobile());
        }

        String content = userName + "已成功开启" + productName + "的集体认养.";

        sms.setCode(code);
        sms.setType(ESmsType.BULLETIN.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setTitle(ESmsType.BULLETIN.getValue());
        sms.setContent(content);

        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());
        smsDAO.insert(sms);
    }

    @Override
    public void saveExpireGroupAdoptBulletin(String userId,
            String productName) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        User user = userBO.getUserUnCheck(userId);

        String userName = user.getNickname();
        if (StringUtils.isEmpty(userName)) {
            userName = PhoneUtil.hideMobile(user.getMobile());
        }

        String content = userName + "开启的" + productName + "的集体认养已超时，可再度开启.";

        sms.setCode(code);
        sms.setType(ESmsType.BULLETIN.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setTitle(ESmsType.BULLETIN.getValue());
        sms.setContent(content);

        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());
        smsDAO.insert(sms);
    }

    @Override
    public void saveCommodityBulletin(String userId, String count) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        String hours = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_8);
        User user = userBO.getUserUnCheck(userId);

        String userName = user.getNickname();
        if (StringUtils.isEmpty(userName)) {
            userName = PhoneUtil.hideMobile(user.getMobile());
        }

        String unit = "件";
        String content = userName + "在" + hours + "购买" + count + unit + "商品";

        sms.setCode(code);
        sms.setType(ESmsType.BULLETIN.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setTitle(ESmsType.BULLETIN.getValue());
        sms.setContent(content);

        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());
        smsDAO.insert(sms);
    }

    @Override
    public void saveArticlePoint(String userId, String articleTitle,
            User user) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        sms.setCode(code);
        sms.setType(ESmsType.MESSAGE.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setUserId(userId);
        sms.setTitle("文章点赞");

        String pointUserNickName = user.getNickname();
        if (StringUtils.isBlank(pointUserNickName)) {
            pointUserNickName = PhoneUtil.hideMobile(user.getMobile());
        }

        String content = "您的" + articleTitle + "文章被" + pointUserNickName + "点赞";
        sms.setContent(content);
        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());

        smsDAO.insert(sms);
    }

    @Override
    public void saveAdoptOrderTreeExpire(String userId, String treeName,
            String treeNumber) {
        Sms sms = new Sms();
        String code = OrderNoGenerater.generate(EGeneratePrefix.XX.getCode());
        sms.setCode(code);
        sms.setType(ESmsType.MESSAGE.getCode());
        sms.setObject(EAccountType.CUSTOMER.getCode());
        sms.setUserId(userId);
        sms.setTitle("树木过期");

        String content = "您认养的树木【" + treeName + treeNumber + "】将于7天后过期";
        sms.setContent(content);
        sms.setStatus(ESmsStauts.SENDED.getCode());
        sms.setCreateDatetime(new Date());

        smsDAO.insert(sms);
    }

    @Override
    public void readBulletin(String code) {
        Sms sms = new Sms();
        sms.setCode(code);
        sms.setStatus(ESmsStauts.READED.getCode());
        sms.setUpdateDatetime(new Date());
        smsDAO.updateReadBulletin(sms);
    }

    @Override
    public List<Sms> querySmsList(Sms data) {
        return smsDAO.selectList(data);
    }

    @Override
    public Sms getSms(String code) {
        Sms data = null;
        if (StringUtils.isNotBlank(code)) {
            Sms condition = new Sms();
            condition.setCode(code);
            data = smsDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "该信息不存在");
            }
        }
        return data;
    }

}
