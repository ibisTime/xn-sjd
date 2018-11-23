package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Barrage;

@Component
public interface IBarrageAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加弹幕
    public String saveBarrage(String content, String pic, String updater,
            String remark);

    // 编辑弹幕
    public void editBarrage(String code, String content, String pic,
            String updater, String remark);

    // 上架
    public void refreshPuton(String code, String orderNo, String updater);

    // 下架
    public void refreshPutoff(String code, String updater);

    // 发送弹幕
    public void sendBarrage(String adoptTreeCode, String userId, String code);

    public Paginable<Barrage> queryBarragePage(int start, int limit,
            Barrage condition);

    public List<Barrage> queryBarrageList(Barrage condition);

    public Barrage getBarrage(String code);

}
