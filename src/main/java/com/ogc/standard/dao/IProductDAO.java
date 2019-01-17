package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Product;

/**
 * 产品
 * @author: silver 
 * @since: 2018年9月26日 下午8:27:38 
 * @history:
 */
public interface IProductDAO extends IBaseDAO<Product> {
    String NAMESPACE = IProductDAO.class.getName().concat(".");

    // 修改产品
    public int updateProduct(Product data);

    // 修改产品类型
    public int updateProductCategory(Product data);

    // 提交产品
    public int updateSubmitProduct(Product data);

    // 审核产品
    public int updateApproveProduct(Product data);

    // 上架产品
    public int updatePutOnProduct(Product data);

    // 下架产品
    public int updatePutOffProduct(Product data);

    // 认养产品
    public int updateAdoptProduct(Product data);

    // 锁定产品
    public int updateLockProduct(Product data);

    // 解锁产品
    public int updateUnLockProduct(Product data);

    // 更新募集数量
    public int updateRaiseCount(Product data);

    // 更新现募集数量
    public int updateNowCount(Product data);

    // 更新集体第一下单人
    public int updateCollectFirstUser(Product data);

    // 查询产品区域
    public List<Product> selectDistinctArea(Product data);

}
