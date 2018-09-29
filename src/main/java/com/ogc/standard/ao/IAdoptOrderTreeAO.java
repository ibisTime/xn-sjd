package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;

@Component
public interface IAdoptOrderTreeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public int dropAdoptOrderTree(String code);

    public int editAdoptOrderTree(AdoptOrderTree data);

    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(AdoptOrderTree condition);

    public AdoptOrderTree getAdoptOrderTree(String code);

    // 赠送树
    public void giveTree(String code, String toUserId, String userId);

}
