package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Product;
import com.ogc.standard.dto.req.XN629010Req;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.dto.res.XN630065PriceRes;

/**
 * 产品
 * @author: silver 
 * @since: 2018年9月26日 下午8:35:42 
 * @history:
 */
public interface IProductBO extends IPaginableBO<Product> {

    // 添加产品
    public Product saveProduct(XN629010Req req);

    // 修改产品
    public void refreshProduct(Product data, XN629011Req req);

    // 修改产品类型
    public void refreshProductCategory(String code, String parentCategoryCode,
            String categoryCode, String updater);

    // 提交产品
    public void refreshSubmitProduct(String code, String updater,
            String remark);

    // 审核产品
    public void refreshApproveProduct(String code, String status,
            String updater, String remark);

    // 上架产品
    public void refreshPutOnProduct(String code, String location,
            Integer orderNo, String updater, String remark);

    // 更新募集总量
    public void refreshRaiseCount(String code, Integer raiseCount);

    // 更新现募集数量
    public void refreshNowCount(String code, Integer nowCount);

    // 认养产品
    public void refreshAdoptProduct(String code);

    // 下架产品
    public void refreshPutOffProduct(String code, String updater);

    // 锁定产品
    public void refreshLockProduct(String code, String identifyCode,
            String specsCode);

    // 解锁产品
    public void refreshUnLockProduct(String code);

    // 更新集体第一下单人
    public void refreshCollectFirstUser(String code, String collectFirstUser);

    // 统计产权方的产品市值
    public XN630065PriceRes getOwnerProductPrice(String ownerId);

    // 查询是否满标的锁定产品
    public List<Product> queryLockedProductList(String isAdopting);

    public List<Product> queryProductList(Product condition);

    // 查询产品区域
    public List<Product> queryDistinctArea();

    public Product getProduct(String code);

}
