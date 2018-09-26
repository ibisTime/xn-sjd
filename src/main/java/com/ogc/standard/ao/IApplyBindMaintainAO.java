package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ApplyBindMaintain;

/**
 * 申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月26日 下午5:54:57 
 * @history:
 */
@Component
public interface IApplyBindMaintainAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addApplyBindMaintain(ApplyBindMaintain data);

    public int dropApplyBindMaintain(String code);

    public int editApplyBindMaintain(ApplyBindMaintain data);

    public Paginable<ApplyBindMaintain> queryApplyBindMaintainPage(int start,
            int limit, ApplyBindMaintain condition);

    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition);

    public ApplyBindMaintain getApplyBindMaintain(String code);

}
