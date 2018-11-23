package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBarrageAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBarrageBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Barrage;
import com.ogc.standard.enums.EBarrageStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class BarrageAOImpl implements IBarrageAO {

    @Autowired
    private IBarrageBO barrageBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Override
    public String saveBarrage(String content, String pic, String updater,
            String remark) {
        return barrageBO.saveBarrage(content, pic, updater, remark);
    }

    @Override
    public void editBarrage(String code, String content, String pic,
            String updater, String remark) {
        Barrage barrage = barrageBO.getBarrage(code);
        if (!EBarrageStatus.DOWN.getCode().equals(barrage.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前弹幕不是可修改状态");
        }

        barrageBO.editBarrage(code, content, pic, updater, remark);
    }

    @Override
    public void refreshPuton(String code, String orderNo, String updater) {
        Barrage barrage = barrageBO.getBarrage(code);
        if (!EBarrageStatus.DOWN.getCode().equals(barrage.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前弹幕不是可上架状态");
        }

        barrageBO.refreshPuton(code, EBarrageStatus.UP.getCode(), orderNo,
            updater);
    }

    @Override
    public void refreshPutoff(String code, String updater) {
        Barrage barrage = barrageBO.getBarrage(code);
        if (!EBarrageStatus.UP.getCode().equals(barrage.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前弹幕不是可上架状态");
        }

        barrageBO.refreshPutoff(code, EBarrageStatus.DOWN.getCode(), updater);
    }

    @Override
    public void sendBarrage(String adoptTreeCode, String userId, String code) {
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(adoptTreeCode);

        bizLogBO.sendBarrage(adoptTreeCode, adoptOrderTree.getOwnerId(), userId,
            code);
    }

    @Override
    public Paginable<Barrage> queryBarragePage(int start, int limit,
            Barrage condition) {
        return barrageBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Barrage> queryBarrageList(Barrage condition) {
        return barrageBO.queryBarrageList(condition);
    }

    @Override
    public Barrage getBarrage(String code) {
        return barrageBO.getBarrage(code);
    }

}
