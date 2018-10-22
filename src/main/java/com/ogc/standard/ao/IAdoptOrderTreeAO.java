package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.dto.res.XN629904Res;

public interface IAdoptOrderTreeAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 赠送树
    public void giveTree(String code, String userId, String toMobile);

    // 来访人落地
    public String visit(String code, String userId);

    // 留言
    public long leaveMessage(String code, String note, String userId);

    // 定时产生碳泡泡
    public void doDailyAdoptOrderTree();

    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition);

    public AdoptOrderTree getAdoptOrderTree(String code);

    // 查询产权方认养总额
    public XN629904Res getTotalAmount(String ownerId, List<String> statusList);
}
