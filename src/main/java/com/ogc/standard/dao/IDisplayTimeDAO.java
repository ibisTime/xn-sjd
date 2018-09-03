package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdsDisplayTime;

import java.util.List;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public interface IDisplayTimeDAO extends IBaseDAO<AdsDisplayTime> {

    String NAMESPACE = IDisplayTimeDAO.class.getName().concat(".");

    void deleteByAdsCode(String adsCode);

}
