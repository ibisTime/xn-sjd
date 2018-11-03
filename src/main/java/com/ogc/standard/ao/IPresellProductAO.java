package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.req.XN629400Req;
import com.ogc.standard.dto.req.XN629401Req;

/**
 * 预售产品
 * @author: silver 
 * @since: Nov 2, 2018 6:53:26 PM 
 * @history:
 */
@Component
public interface IPresellProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加
    public String addPresellProduct(XN629400Req req);

    // 编辑
    public void editPresellProduct(XN629401Req req);

    // 提交
    public void submitPresellProduct(String code, String updater,
            String remark);

    // 审核
    public void approvePresellProduct(String code, String approveResult,
            String updater, String remark);

    // 上架
    public void putOnPresellProduct(String code, String location,
            Integer orderNo, String updater);

    // 下架
    public void putOffPresellProduct(String code, String updater);

    public Paginable<PresellProduct> queryPresellProductPage(int start,
            int limit, PresellProduct condition);

    public List<PresellProduct> queryPresellProductList(
            PresellProduct condition);

    public PresellProduct getPresellProduct(String code);

}
