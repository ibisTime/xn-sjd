package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellInventory;

public interface IPresellInventoryBO extends IPaginableBO<PresellInventory> {

    public String savePresellInventory(String groupCode, String treeNumber);

    // 更新组
    public void refreshGroup(String code, String groupType, String groupCode);

    // 查询组下的预售权
    public List<PresellInventory> queryPresellInventoryListByGroup(
            String groupCode);

    // 查询组下的树木列表
    public List<PresellInventory> queryTreeNumberListByGroup(String groupCode);

    public List<PresellInventory> queryPresellInventoryList(
            PresellInventory condition);

    public PresellInventory getPresellInventory(String code);

}
