package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IBarrageBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IBarrageDAO;
import com.ogc.standard.domain.Barrage;
import com.ogc.standard.enums.EBarrageStatus;
import com.ogc.standard.exception.BizException;

@Component
public class BarrageBOImpl extends PaginableBOImpl<Barrage>
        implements IBarrageBO {

    @Autowired
    private IBarrageDAO barrageDAO;

    @Override
    public String saveBarrage(String content, String pic, String updater,
            String remark) {
        String code = OrderNoGenerater.generate("BA");
        Barrage barrage = new Barrage();

        barrage.setCode(code);
        barrage.setContent(content);
        barrage.setPic(pic);
        barrage.setStatus(EBarrageStatus.DOWN.getCode());

        barrage.setUpdater(updater);
        barrage.setRemark(remark);
        barrage.setUpdateDatetime(new Date());

        barrageDAO.insert(barrage);

        return code;
    }

    @Override
    public void editBarrage(String code, String content, String pic,
            String updater, String remark) {
        Barrage barrage = new Barrage();

        barrage.setCode(code);
        barrage.setContent(content);
        barrage.setPic(pic);

        barrage.setUpdater(updater);
        barrage.setRemark(remark);
        barrage.setUpdateDatetime(new Date());

        barrageDAO.updateBarrage(barrage);

    }

    @Override
    public void refreshPuton(String code, String status, String orderNo,
            String updater) {
        Barrage barrage = new Barrage();

        barrage.setCode(code);
        barrage.setStatus(status);
        barrage.setOrderNo(orderNo);
        barrage.setUpdater(updater);
        barrage.setUpdateDatetime(new Date());

        barrageDAO.updateStatus(barrage);
    }

    @Override
    public void refreshPutoff(String code, String status, String updater) {
        Barrage barrage = new Barrage();

        barrage.setCode(code);
        barrage.setStatus(status);
        barrage.setUpdater(updater);
        barrage.setUpdateDatetime(new Date());

        barrageDAO.updateStatus(barrage);
    }

    @Override
    public List<Barrage> queryBarrageList(Barrage condition) {
        return barrageDAO.selectList(condition);
    }

    @Override
    public Barrage getBarrage(String code) {
        Barrage data = null;
        if (StringUtils.isNotBlank(code)) {
            Barrage condition = new Barrage();
            condition.setCode(code);
            data = barrageDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "弹幕不存在");
            }
        }
        return data;
    }

}
