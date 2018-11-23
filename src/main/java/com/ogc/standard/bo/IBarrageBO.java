package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Barrage;

public interface IBarrageBO extends IPaginableBO<Barrage> {

    // 添加弹幕
    public String saveBarrage(String content, String pic, String updater,
            String remark);

    // 编辑弹幕
    public void editBarrage(String code, String content, String pic,
            String updater, String remark);

    // 上架
    public void refreshPuton(String code, String status, String orderNo,
            String updater);

    // 下架
    public void refreshPutoff(String code, String status, String updater);

    public List<Barrage> queryBarrageList(Barrage condition);

    public Barrage getBarrage(String code);

}
