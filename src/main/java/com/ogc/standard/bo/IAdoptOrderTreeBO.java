package com.ogc.standard.bo;

import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrderTree;

public interface IAdoptOrderTreeBO extends IPaginableBO<AdoptOrderTree> {

    public boolean isAdoptOrderTreeExist(String code);

    public String saveAdoptOrderTree(String orderCode, String treeNumber,
            Date startDatetime, Date endDatetime, Long amount,
            String currentHolder);

    public int removeAdoptOrderTree(String code);

    public int refreshAdoptOrderTree(AdoptOrderTree data);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition);

    public AdoptOrderTree getAdoptOrderTree(String code);

    public void giveTree(AdoptOrderTree data);

}
