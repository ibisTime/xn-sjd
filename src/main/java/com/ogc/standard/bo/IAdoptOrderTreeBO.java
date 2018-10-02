package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;

public interface IAdoptOrderTreeBO extends IPaginableBO<AdoptOrderTree> {

    public String saveAdoptOrderTree(AdoptOrder adoptOrder, String treeNumber);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition);

    public AdoptOrderTree getAdoptOrderTree(String code);

    public void giveTree(AdoptOrderTree data);

}
