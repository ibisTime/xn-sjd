package com.ogc.standard.dao;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Collect;
import com.ogc.standard.domain.EthWAddress;

public interface ICollectDAO extends IBaseDAO<Collect> {

    String NAMESPACE = ICollectDAO.class.getName().concat(".");

    public int updateNoticeETH(Collect data);

    public EthWAddress selectAddressUseInfo(Collect data);

    public BigDecimal selectTotalCollect(Collect data);

    public int updateNoticeSC(Collect data);

    public int updateNoticeBTC(Collect data);

    public int updateNoticeWAN(Collect data);

}
