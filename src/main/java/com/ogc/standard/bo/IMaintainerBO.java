package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Maintainer;

/**
 * 养护人
 * @author: silver 
 * @since: 2018年9月28日 下午7:39:54 
 * @history:
 */
public interface IMaintainerBO extends IPaginableBO<Maintainer> {

    public boolean isMaintainerExist(String code);

    // 添加养护人
    public String saveMaintainer(String maintainId, String name, String mobile,
            String updater, String remark);

    // 删除养护人
    public void removeMaintainer(String code);

    // 修改养护人
    public void refreshMaintainer(String code, String name, String mobile,
            String updater, String remark);

    public List<Maintainer> queryMaintainerList(Maintainer condition);

    public Maintainer getMaintainer(String code);

}
