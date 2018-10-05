package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;

public interface IAdoptOrderTreeBO extends IPaginableBO<AdoptOrderTree> {

    public String saveAdoptOrderTree(Product product, AdoptOrder adoptOrder,
            String treeNumber);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(String orderCode);

    public AdoptOrderTree getAdoptOrderTree(String code);

    public void giveTree(AdoptOrderTree data, User user, User toUser);

    public void refreshAdoptOrderTree(AdoptOrderTree data,
            EAdoptOrderTreeStatus adoptOrderTreeStatus);

    // 用户的认养权数量
    public long getCountByCurrentHolder(String currentHolder);

}
