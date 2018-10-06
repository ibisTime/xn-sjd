package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.enums.EChannelType;

public interface IChargeBO extends IPaginableBO<Charge> {

    /*
     * String applyOrderOnline(Account account, String payGroup, String refNo,
     * String bizType, String bizNote, BigDecimal transAmount, EChannelType
     * channelType, String applyUser, String fromAddress);
     */

    String applyOrderOnline(Account account, String payCardInfo,
            String fromAddress, BigDecimal transAmount,
            EChannelType channelType, String channalOrder, String payNote,
            String hash);

    void callBackChange(Charge dbCharge, boolean booleanFlag);

    String applyOrderOffline(Account account, String bizType, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote);

    void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote);

    List<Charge> queryChargeList(Charge condition);

    Charge getCharge(String code);

    public boolean isExistOfChannelOrder(String orderNo);

    public boolean isExistOfRefNo(String refNo);

}
