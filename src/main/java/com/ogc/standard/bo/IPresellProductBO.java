package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.req.XN629400Req;
import com.ogc.standard.dto.req.XN629401Req;
import com.ogc.standard.dto.res.XN630065PriceRes;

public interface IPresellProductBO extends IPaginableBO<PresellProduct> {

    // 保存
    public PresellProduct savePresellProduct(XN629400Req req);

    // 修改
    public void refreshEditPresellProduct(XN629401Req req);

    // 提交
    public void refreshSubmitPresellProduct(String code, String updater,
            String remark);

    // 审核
    public void refreshApprovePresellProduct(String code, String status,
            String updater, String remark);

    // 上架
    public void refreshPutOnPresellProduct(String code, String location,
            Integer orderNo, String updater);

    // 下架
    public void refreshPutOffPresellProduct(String code, String updater);

    // 更新已预售数量
    public void refreshNowCount(String code, Integer nowCount);

    public List<PresellProduct> queryPresellProductList(
            PresellProduct condition);

    public XN630065PriceRes getOwnerProductPrice(String ownerId);

    public PresellProduct getPresellProduct(String code);

}
