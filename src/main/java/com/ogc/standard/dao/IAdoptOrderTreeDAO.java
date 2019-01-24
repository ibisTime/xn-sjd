package com.ogc.standard.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdoptOrderTree;

//dao层 
public interface IAdoptOrderTreeDAO extends IBaseDAO<AdoptOrderTree> {
    String NAMESPACE = IAdoptOrderTreeDAO.class.getName().concat(".");

    int updateStatus(AdoptOrderTree data);

    // 更新集体订单的失效认养权
    public int updateInvalidAdoptByOrder(AdoptOrderTree data);

    // 更新数量
    public int updateQuantity(AdoptOrderTree data);

    // 查询认养总额
    public BigDecimal selectTotalAmount(AdoptOrderTree condition);

    public long selectDistinctTotalCount(AdoptOrderTree condition);

    // 树的认养列表
    public List<AdoptOrderTree> selectDistictByTreeNumber(
            AdoptOrderTree condition);

    // 品种
    public List<AdoptOrderTree> selectDistictVariety(AdoptOrderTree condition);

    // 产品已认养名单
    public List<AdoptOrderTree> selectProductAdoptedOrder(
            AdoptOrderTree condition);

}
