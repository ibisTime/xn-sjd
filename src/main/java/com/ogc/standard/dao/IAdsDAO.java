package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Ads;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public interface IAdsDAO extends IBaseDAO<Ads> {

    String NAMESPACE = IAdsDAO.class.getName().concat(".");

    public long selectFrontTotalCount(Ads condition);

    public Ads selectFront(Ads condition);

    public List<Ads> selectFrontList(Ads condition);

    public List<Ads> selectFrontList(Ads condition, int start, int count);

    public int updateByPrimaryKeySelective(Ads condition);

    public int updateByPrimaryKey(Ads condition);

    public void updateAllMarketPrice(BigDecimal marketPrice);


}
