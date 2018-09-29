package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Maintainer;

/**
 * 养护人
 * @author: silver 
 * @since: 2018年9月28日 下午7:56:19 
 * @history:
 */
@Component
public interface IMaintainerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加养护人
    public String addMaintainer(String maintainId, String name, String mobile,
            String updater, String remark);

    // 删除养护人
    public void dropMaintainer(String code);

    // 修改养护人
    public void editMaintainer(String code, String name, String mobile,
            String updater, String remark);

    public Paginable<Maintainer> queryMaintainerPage(int start, int limit,
            Maintainer condition);

    public List<Maintainer> queryMaintainerList(Maintainer condition);

    public Maintainer getMaintainer(String code);

}
