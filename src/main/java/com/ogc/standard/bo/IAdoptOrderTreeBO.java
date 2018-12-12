package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;

public interface IAdoptOrderTreeBO extends IPaginableBO<AdoptOrderTree> {

    public String saveAdoptOrderTree(Product product, AdoptOrder adoptOrder,
            String treeNumber);

    public String saveAdoptOrderTree(Product product,
            GroupAdoptOrder groupAdoptOrder, String treeNumber);

    public String saveAdoptOrderTree(PresellProduct presellProduct,
            PresellOrder presellOrder, String treeNumber);

    public String saveAdoptOrderTree(PresellProduct presellProduct,
            GroupOrder groupOrder, String treeNumber);

    public void giveTree(AdoptOrderTree data, User user, User toUser);

    public void refreshAdoptOrderTree(AdoptOrderTree data,
            EAdoptOrderTreeStatus adoptOrderTreeStatus);

    // 更新集体订单的失效认养权
    public void refreshInvalidAdoptByOrder(String groupAdoptCode);

    // 更新数量
    public void refreshQuantity(String code, Integer quantity);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition);

    public List<AdoptOrderTree> queryAdoptOrderTreeList(String orderCode);

    // 用户已认养名单
    public List<AdoptOrderTree> queryUserAdoptedOrder(String userId,
            String treeNumber);

    // 产品已认养名单
    public List<AdoptOrderTree> queryProductAdoptedOrder(
            AdoptOrderTree condition);

    // 树的认养列表
    public List<AdoptOrderTree> queryDistictByTreeNumber(String treeNumber);

    public List<AdoptOrderTree> queryAdoptOrderTreeByNum(String treeNumber);

    public AdoptOrderTree getAdoptOrderTree(String code);

    // 用户的认养权数量
    public long getCountByCurrentHolder(String currentHolder);

    // 产权方的认养权数量
    public long getCountByOwner(String ownerId, Date createDatetimeStart,
            Date createDatetimeEnd);

    // 产权方的认养权数量
    public long getDistinctCountByOwner(String ownerId,
            Date createDatetimeStart, Date createDatetimeEnd,
            List<String> orderTypeList);

    // 产权方认养总额
    public BigDecimal getTotalAmount(String ownerId, List<String> statusList,
            List<String> orderTypeList);
}
