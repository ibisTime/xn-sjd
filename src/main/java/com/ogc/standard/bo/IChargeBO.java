package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.EthWAddress;
import com.ogc.standard.enums.EChannelType;

public interface IChargeBO extends IPaginableBO<Charge> {

    String applyOrderOnline(Account account, String payGroup, String refNo,
            String bizType, String bizNote, BigDecimal transAmount,
            EChannelType channelType, String applyUser, String fromAddress);

    void callBackChange(Charge dbCharge, boolean booleanFlag);

    String applyOrderOffline(Account account, String bizType, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote);

    List<Charge> queryChargeList(Charge condition);

    Charge getCharge(String code);

    public EthWAddress getAddressUseInfo(String fromAddress, String currency);

    public boolean isExistOfChannelOrder(String orderNo);

}