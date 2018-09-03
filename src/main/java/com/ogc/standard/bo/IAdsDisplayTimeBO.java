package com.ogc.standard.bo;

import com.ogc.standard.domain.AdsDisplayTime;

import java.util.List;

/**
 * Created by tianlei on 2017/十一月/15.
 */
public interface IAdsDisplayTimeBO {

    //插入展示时间
    void insertDisplayTime(AdsDisplayTime adsDisplayTime);

    //删除广告的展示时间
    void deleteAdsDisplayTimeByAdsCode(String adsCode);

    List<AdsDisplayTime> queryList(String adsCode);
}
