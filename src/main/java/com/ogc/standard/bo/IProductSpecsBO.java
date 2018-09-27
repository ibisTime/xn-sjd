package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.req.XN629010ReqSpecs;

/**
 * 产品规格
 * @author: silver 
 * @since: 2018年9月27日 下午3:09:32 
 * @history:
 */
public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {

    // 添加规格
    public String saveProductSpecs(String productCode, XN629010ReqSpecs specs);

    // 删除产品规格
    public void removeProductSpecsByProduct(String productCode);

    // 获取产品下的规格列表
    public List<ProductSpecs> queryProductSpecsListByProduct(
            String productCode);

    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    public ProductSpecs getProductSpecs(String code);

}
