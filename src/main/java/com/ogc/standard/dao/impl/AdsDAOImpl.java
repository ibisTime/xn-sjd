package com.ogc.standard.dao.impl;

import com.ogc.standard.dao.IAdsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Ads;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tianlei on 2017/十一月/14.
 */
@Repository("adsDAOImpl")
public class AdsDAOImpl extends AMybatisTemplate implements IAdsDAO {

    @Override
    public int insert(Ads data) {

        return super.insert(NAMESPACE.concat("insert"), data);

    }

    //
    @Override
    public int delete(Ads data) {
        return 0;
    }

    //
    @Override
    public long selectTotalCount(Ads condition) {

        return super.selectTotalCount(NAMESPACE.concat("selectTotalCount"),condition);

    }

    //
    @Override
    public Ads select(Ads condition) {
        return super.select(NAMESPACE.concat("select"), condition, Ads.class);
    }

    //
    @Override
    public List<Ads> selectList(Ads condition) {

        return super.selectList(NAMESPACE.concat("select"), condition, Ads.class);

    }

    //
    @Override
    public List<Ads> selectList(Ads condition, int start, int limit) {

        return super.selectList(NAMESPACE.concat("select"), start, limit, condition, Ads.class);

    }

    @Override
    public int updateByPrimaryKeySelective(Ads condition) {

        return super.update(NAMESPACE.concat("updateByPrimaryKeySelective"),condition);

    }

    @Override
    public int updateByPrimaryKey(Ads condition) {
        return super.update(NAMESPACE.concat("updateByPrimaryKey"),condition);

    }

    @Override
    public void updateAllMarketPrice(BigDecimal marketPrice) {

        Ads condition = new Ads();
        condition.setMarketPrice(marketPrice);
        super.update(NAMESPACE.concat("updateMarketPrice"),condition);

    }

    //  前端
    @Override
    public Ads selectFront(Ads condition) {

        return super.select(NAMESPACE.concat("selectFront"), condition, Ads.class);

    }

    @Override
    public long selectFrontTotalCount(Ads condition) {

        return super.selectTotalCount(NAMESPACE.concat("selectFrontTotalCount"), condition);

    }

    @Override
    public List<Ads> selectFrontList(Ads condition) {

        return super.selectList(NAMESPACE.concat("selectFront"), condition, Ads.class);

    }

    @Override
    public List<Ads> selectFrontList(Ads condition, int start, int limit) {

        return super.selectList(NAMESPACE.concat("selectFront"), start, limit, condition, Ads.class);

    }


}
