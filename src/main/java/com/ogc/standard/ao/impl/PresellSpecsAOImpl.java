package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IPresellSpecsAO;
import com.ogc.standard.bo.IPresellSpecsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellSpecs;

@Service
public class PresellSpecsAOImpl implements IPresellSpecsAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IPresellSpecsBO presellSpecsBO;

    @Override
    public void doRefreshPrice() {
        logger.info("***************开始扫描待涨价规格***************");
        PresellSpecs condition = new PresellSpecs();
        condition.setMinIncrease(0d);
        int start = 1;
        int limit = 10;

        while (true) {
            Paginable<PresellSpecs> page = queryPresellSpecsPage(start, limit,
                condition);

            if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
                for (PresellSpecs presellSpecs : page.getList()) {
                    if (null != presellSpecs.getIncrease()) {
                        BigDecimal price = presellSpecs.getPrice();
                        price = price.multiply(new BigDecimal(
                            presellSpecs.getIncrease() * 0.01 + 1));

                        presellSpecsBO.refreshPresellSpecsPrice(
                            presellSpecs.getCode(), price);
                    }

                }
            } else {
                break;
            }

            start += 1;
        }

        logger.info("***************结束扫描待涨价规格***************");
    }

    @Override
    public Paginable<PresellSpecs> queryPresellSpecsPage(int start, int limit,
            PresellSpecs condition) {
        return presellSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PresellSpecs> queryPresellSpecsList(PresellSpecs condition) {
        return presellSpecsBO.queryPresellSpecsList(condition);
    }

    @Override
    public PresellSpecs getPresellSpecs(String code) {
        return presellSpecsBO.getPresellSpecs(code);
    }

}
