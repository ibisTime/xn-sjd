package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellProduct;

public interface IPresellProductDAO extends IBaseDAO<PresellProduct> {
    String NAMESPACE = IPresellProductDAO.class.getName().concat(".");

    // 编辑产品
    public int updateEditPresellProduct(PresellProduct data);

    // 提交
    public int updateSubmitPresellProduct(PresellProduct data);

    // 审核
    public int updateApprovePresellProduct(PresellProduct data);

    // 上架
    public int updatePutOnPresellProduct(PresellProduct data);

    // 下架
    public int updatePutOffPresellProduct(PresellProduct data);

    // 更新已预售数量
    public int updateNowCount(PresellProduct data);

}
