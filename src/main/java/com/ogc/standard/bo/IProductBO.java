package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Product;

/**
 * 产品
 * @author: silver 
 * @since: 2018年9月26日 下午8:35:42 
 * @history:
 */
public interface IProductBO extends IPaginableBO<Product> {

    public boolean isProductExist(String code);

    // 添加产品
    public void saveProduct(Product data);

    // 修改产品
    public void refreshProduct(Product data);

    // 提交产品
    public void refreshSubmitProduct(String code, String updater,
            String remark);

    // 审核产品
    public void refreshApproveProduct(String code, String status,
            String updater, String remark);

    // 上架产品
    public void refreshPutOnProduct(String code, String location,
            Integer orderNo, String updater, String remark);

    // 锁定产品
    public void refreshLoakProduct(String code);

    // 更新现募集数量
    public void refreshNowCount(String code, Integer nowCount);

    // 认养产品
    public void refreshAdoptProduct(String code);

    // 下架产品
    public void refreshPutOffProduct(String code, String updater);

    public List<Product> queryProductList(Product condition);

    public Product getProduct(String code);

}
