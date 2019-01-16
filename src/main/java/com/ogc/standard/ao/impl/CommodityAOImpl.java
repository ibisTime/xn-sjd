/**
 * @Title CommodityAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午1:26:31 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICommodityAO;
import com.ogc.standard.bo.ICategoryBO;
import com.ogc.standard.bo.ICommodityBO;
import com.ogc.standard.bo.ICommoditySpecsBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Category;
import com.ogc.standard.domain.Commodity;
import com.ogc.standard.domain.CommoditySpecs;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN629700Req;
import com.ogc.standard.dto.req.XN629701Req;
import com.ogc.standard.dto.res.XN629709Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECategoryStatus;
import com.ogc.standard.enums.ECommodityStatus;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午1:26:31 
 * @history:
 */
@Service
public class CommodityAOImpl implements ICommodityAO {

    @Autowired
    private ICommodityBO commodityBO;

    @Autowired
    private ICommoditySpecsBO commoditySpecsBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ICategoryBO categoryBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    @Transactional
    public String addCommodity(XN629700Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "商品分类已下架，请重新选择！");
        }

        // 添加商品
        String code = commodityBO.saveCommodity(req);

        // 落地规格数据
        for (CommoditySpecs data : req.getSpecsList()) {
            data.setCommodityCode(code);
            commoditySpecsBO.saveSpecs(data);
        }

        return code;
    }

    @Override
    @Transactional
    public void editCommodity(XN629701Req req) {
        Category category = categoryBO.getCategory(req.getCategoryCode());
        if (ECategoryStatus.PUT_OFF.getCode().equals(category.getStatus())) {
            throw new BizException("xn0000", "商品分类已下架，请重新选择！");
        }

        // 状态判断
        Commodity data = commodityBO.getCommodity(req.getCode());
        if (!ECommodityStatus.DRAFT.getCode().equals(data.getStatus())
                && !ECommodityStatus.FAILED.getCode().equals(data.getStatus())
                && !ECommodityStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法修改的状态");
        }

        // 判空，判断不可删除的规格
        List<Long> notInIdList = new ArrayList<Long>();
        for (CommoditySpecs specs : req.getSpecsList()) {
            if (null != specs.getId()) {
                notInIdList.add(specs.getId());
            }
        }

        List<CommoditySpecs> usedSpecs = commoditySpecsBO
            .queryUsedSpecsList(req.getCode(), notInIdList);
        if (CollectionUtils.isNotEmpty(usedSpecs)) {
            throw new BizException("xn000000",
                "规格【" + usedSpecs.get(0).getName() + "】已被下单，无法删除");
        }

        // 删除可删除的规格
        commoditySpecsBO.deleteUnUsedCommoditySpecs(req.getCode(), notInIdList);

        // 落地新规格数据
        for (CommoditySpecs specs : req.getSpecsList()) {
            if (null != specs.getId()) {

                commoditySpecsBO.refreshSpecs(specs);

            } else {

                specs.setCommodityCode(req.getCode());
                commoditySpecsBO.saveSpecs(specs);

            }
        }

        // 修改数据
        commodityBO.refreshCommodity(req);

    }

    @Override
    public void submitCommodity(String code, String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.DRAFT.getCode().equals(data.getStatus())
                && !ECommodityStatus.FAILED.getCode().equals(data.getStatus())
                && !ECommodityStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该商品处于无法提交的状态");
        }

        Company shop = companyBO.getCompany(data.getShopCode());
        SYSUser sysUser = sysUserBO.getSYSUser(shop.getUserId());

        if (!ESYSUserStatus.NORMAL.getCode().equals(sysUser.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "商家未通过审核，无法提交商品");
        }

        commodityBO.refreshStatus(code, ECommodityStatus.TOAPPROVE.getCode(),
            updater, remark);
    }

    @Override
    public void approveCommodity(String code, String approver,
            String approveResult, String approveNote) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.TOAPPROVE.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法审核的状态");
        }

        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ECommodityStatus.PASS.getCode();
        } else {
            status = ECommodityStatus.FAILED.getCode();
        }

        commodityBO.refreshStatus(code, status, approver, approveNote);
    }

    @Override
    public void putOn(String code, String location, Long orderNo,
            String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.PASS.getCode().equals(data.getStatus())
                && !ECommodityStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法上架的状态");
        }

        commodityBO.refreshOn(code, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Commodity data = commodityBO.getCommodity(code);
        if (!ECommodityStatus.ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该商品处于无法下架的状态");
        }

        commodityBO.refreshStatus(code, ECommodityStatus.OFF.getCode(), updater,
            remark);
    }

    @Override
    public void doCommodityMonth() {

        Commodity condition = new Commodity();
        condition.setStatus(ECommodityStatus.ON.getCode());
        List<Commodity> commodities = commodityBO.queryCommodityList(condition);// 所有上架商品

        for (Commodity commodity : commodities) {
            commodityBO.refreshMonthSellCount(commodity, Long.valueOf(0));
        }

    }

    @Override
    public Paginable<Commodity> queryCommodityPage(int start, int limit,
            Commodity condition) {
        Paginable<Commodity> page = commodityBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Commodity data : page.getList()) {

                init(data);

            }
        }

        return page;
    }

    @Override
    public List<Commodity> queryCommodityList(Commodity condition) {
        List<Commodity> dataList = commodityBO.queryCommodityList(condition);

        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Commodity commodity : dataList) {
                init(commodity);
            }
        }

        return dataList;
    }

    @Override
    public XN629709Res queryPlaceList(String placeType) {
        List<String> placeList = new ArrayList<String>();

        // 产地
        if ("originalPlace".equals(placeType)) {
            List<Commodity> commodities = commodityBO.queryOriginalPlaceList();
            if (CollectionUtils.isNotEmpty(commodities)) {
                for (Commodity commodity : commodities) {
                    placeList.add(commodity.getOriginalPlace());
                }
            }
        }

        // 发货地
        if ("deliverPlace".equals(placeType)) {
            // TODO
        }
        return null;
    }

    @Override
    public Commodity getCommodity(String code) {
        Commodity data = commodityBO.getCommodity(code);

        init(data);

        return data;
    }

    private void init(Commodity data) {
        // 规格
        List<CommoditySpecs> specsList = commoditySpecsBO
            .querySpecsList(data.getCode());
        data.setSpecsList(specsList);

        // 初始化最小价格和最大价格
        BigDecimal minPrice = BigDecimal.ZERO;
        BigDecimal maxPrice = minPrice;
        for (CommoditySpecs commoditySpecs : specsList) {
            if (minPrice.compareTo(BigDecimal.ZERO) == 0) {
                minPrice = commoditySpecs.getPrice();
            }
            if (commoditySpecs.getPrice().compareTo(minPrice) < 0) {
                minPrice = commoditySpecs.getPrice();
            }
            if (commoditySpecs.getPrice().compareTo(maxPrice) > 0) {
                maxPrice = commoditySpecs.getPrice();
            }
        }
        data.setMinPrice(minPrice);
        data.setMaxPrice(maxPrice);

        // 店铺名称
        Company company = companyBO.getCompany(data.getShopCode());
        data.setShopName(company.getName());
    }

}
