package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellInventory;

public interface IPresellInventoryBO extends IPaginableBO<PresellInventory> {

    public String savePresellInventory(String groupCode, String treeNumber);

    // 更新组
    public void refreshGroup(String code, String groupType, String groupCode);

    public List<PresellInventory> queryPresellInventoryList(
            PresellInventory condition);

    public PresellInventory getPresellInventory(String code);

}
