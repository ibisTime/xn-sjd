package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Product;
import com.ogc.standard.dto.req.XN629010Req;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.dto.res.XN629028Res;
import com.ogc.standard.dto.res.XN629029Res;

/**
 * 认养产品
 * @author: silver 
 * @since: 2018年9月26日 下午9:08:49 
 * @history:
 */
public interface IProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加产品
    public String addProduct(XN629010Req req);

    // 修改产品
    public void editProduct(XN629011Req req);

    // 修改产品类型
    public void editProductCategory(String code, String parentCategoryCode,
            String categoryCode, String updater);

    // 提交产品
    public void submitProduct(String code, String updater, String remark);

    // 审核产品
    public void approveProduct(String code, String approveResult,
            String updater, String remark);

    // 上架产品
    public void putOnProduct(String code, List<String> codeList,
            String location, Integer orderNo, String updater, String remark);

    // 下架产品
    public void putOffProduct(String code, List<String> codeList,
            String updater);

    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition);

    public List<Product> queryProductList(Product condition);

    // 查询产品区域
    public List<XN629028Res> queryProductArea();

    // 查询产品品种
    public List<XN629029Res> queryProductVariety();

    public Product getProduct(String code);

}
