package com.ogc.standard.dao.impl;

import com.ogc.standard.dao.IDisplayTimeDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AdsDisplayTime;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tianlei on 2017/十一月/14.
 */
@Repository("displayTimeDAOImpl")
public class DisplayTimeDAOImpl extends AMybatisTemplate implements IDisplayTimeDAO {

    @Override
    public int delete(AdsDisplayTime data) {

        return 0;
    }

    @Override
    public void deleteByAdsCode(String adsCode) {

        AdsDisplayTime condition = new AdsDisplayTime();
        condition.setAdsCode(adsCode);
        super.delete(NAMESPACE.concat("deleteByAdsCode"), condition);

    }

    @Override
    public int insert(AdsDisplayTime data) {

        return super.insert(NAMESPACE.concat("insert"), data);

    }

    @Override
    public AdsDisplayTime select(AdsDisplayTime condition) {
        return null;
    }

    @Override
    public long selectTotalCount(AdsDisplayTime condition) {
        return 0;
    }

    @Override
    public List<AdsDisplayTime> selectList(AdsDisplayTime condition) {

        return super.selectList(NAMESPACE.concat("select"),condition,AdsDisplayTime.class);
    }

    @Override
    public List<AdsDisplayTime> selectList(AdsDisplayTime condition, int start, int count) {
        return null;
    }
}
